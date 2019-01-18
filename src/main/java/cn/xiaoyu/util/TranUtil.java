package cn.xiaoyu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.xiaoyu.common.DefaultException;
import cn.xiaoyu.common.MessageCode;

// xiaoyu.zhang
public class TranUtil {

	/**
	 * 生成excel后，导出文件
	 * 
	 * @param response
	 * @param wb
	 * @param fileName
	 */
	public static void outputFile(HttpServletResponse response, HSSFWorkbook wb, String fileName) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls" + "\"");
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
			throw new DefaultException(MessageCode.UNKNOWN_ERROR, "导出错误");
		}
	}
	 
	
	/**
	 * excel中 数据字符串转换
	 */
	public static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					DecimalFormat df = new DecimalFormat("0");
					cellvalue = df.format(cell.getNumericCellValue());
				} else {
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case HSSFCell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	/**
	 *校验格式 
	 * */
	public static Workbook checkFile(String filePath) {
		if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
			throw new DefaultException(MessageCode.APPLICATION_ERROR, "文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook workboook = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			workboook = new HSSFWorkbook(fis);
		} catch (Exception ex) {
			try {
				fis = new FileInputStream(filePath);
				workboook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				e.printStackTrace();
				throw new DefaultException(MessageCode.APPLICATION_ERROR, "读取excel文件失败");
			}
		} finally {
			// Venture: delete file after read
			try {
				 delete(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return workboook;
	}
	
	public static void delete(final File dir) {

		delete(dir, true);
	}
	public static void delete(final File dir, final boolean self) {
		if (!dir.exists()) {
			return;
		}
		if (!dir.isDirectory()) {
			dir.delete();
			return;
		}

		final String[] list = dir.list();
		if (list != null) {
			for (final String element : list) {
				final File child = new File(dir, element);
				delete(child);
			}
		}
		if (self) {
			dir.delete();
		}
	}

}
