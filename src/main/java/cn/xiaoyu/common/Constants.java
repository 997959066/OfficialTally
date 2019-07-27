package cn.xiaoyu.common;

public class Constants {

	public static final String DATETIME_FORMAT_PATTEN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_PATTEN = "yyyy-MM-dd";
	public static final String FILE_UPLOAD_FOLDER= "/WEB-INF/upload";
	public static final long FILE_SIZE_MAX= 5*1048576;
	public static final int SIZE_THRESHOLD= 1024*100;
	public static final String MD5= "MD5";
	//记账模块
	public static final String TALLY = "execution(public * cn.xiaoyu.controller.tally.*.*(..))";
	//系统模块
	public static final String SYSTEM = "execution(public * cn.xiaoyu.controller.system.*.*(..))";
	
}
