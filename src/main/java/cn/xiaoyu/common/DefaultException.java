package cn.xiaoyu.common;

 
public class DefaultException extends RuntimeException{

	private static final long serialVersionUID = -2678203134198782909L;
    /**
     * 描述 :共通异常定义
     *     使用MessageCode默认定义的信息附加自定义信息
     */
	public DefaultException(MessageCode messageCode, String message) {
	    super("{\"code\":"+ String.valueOf(messageCode.getCode()) + ""
	                    + ", \"msg\":\"" + messageCode.getValue() + "-" + message +"\"}");
	}

	public DefaultException(String message, Throwable cause) {
		super(message, cause);
	}

	public DefaultException(Throwable cause) {
		super(cause);
	}
}
