package cn.xiaoyu.service.file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface UploadOrDownloadService {
	
    String uploadFile(HttpServletRequest request) throws ServletException, IOException;
    
}
