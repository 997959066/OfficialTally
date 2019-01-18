package cn.xiaoyu.service.file.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.google.common.base.Charsets;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.Constants;
import cn.xiaoyu.common.DefaultException;
import cn.xiaoyu.common.MessageCode;
import cn.xiaoyu.service.file.UploadOrDownloadService;

@Service
public class UplodOrDownloadServiceImpl extends Base implements UploadOrDownloadService {

	@Override
	public String uploadFile(HttpServletRequest request)
			throws ServletException, IOException {
		final String savePath = request.getSession().getServletContext().getRealPath(Constants.FILE_UPLOAD_FOLDER);
		String saveFilename = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(Constants.SIZE_THRESHOLD);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding(Charsets.UTF_8.name());
			if (!ServletFileUpload.isMultipartContent(request)) {
				if (logger.isInfoEnabled()) {
					logger.info(String.format("上传文件异常，实际没有选择上传文件"));
				}
				throw new DefaultException(MessageCode.APPLICATION_ERROR, "上传文件异常，实际没有选择上传文件");
			}
			upload.setFileSizeMax(Constants.FILE_SIZE_MAX);
			upload.setSizeMax(Constants.FILE_SIZE_MAX * 10);

			StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
			Iterator<String> iterator = req.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile file = req.getFile(iterator.next());
				String filename = file.getOriginalFilename();
				if (filename == null || filename.trim().equals("")) {
					if (logger.isErrorEnabled()) {
						logger.error(String.format("上传文件异常，实际上传文件名为【%s】", filename));

					}
					continue;
				}
				String name = filename.substring(filename.lastIndexOf("\\") + 1, filename.lastIndexOf("."));
				filename = name + filename.substring(filename.lastIndexOf("."));
				in = file.getInputStream();
				saveFilename = makeFileName(filename);
				File directory = new File(savePath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				try {
					out = new FileOutputStream(savePath + File.separator + saveFilename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
				} catch (java.io.IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error(String.format("文件上传失败,异常消息为【%s】", e.getMessage()));
					}
					throw new DefaultException("文件上传失败！", e);
				} finally {
					closeStream(in, out);
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("文件上传失败,异常消息为【%s】", e.getMessage()));
			}
			throw new DefaultException("文件上传失败！", e);
		} finally {
			closeStream(in, out);
		}
		return savePath + File.separator + saveFilename;
	}

	/**
	 * 
	 * Description: 添加方法功能描述
	 * 
	 * @param filename
	 * @return String
	 */
	public static String makeFileName(String filename) {
		return UUID.randomUUID().toString() + "_" + filename;
	}

	private void closeStream(InputStream in, OutputStream out) {
		if (null != in) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null != out) {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
