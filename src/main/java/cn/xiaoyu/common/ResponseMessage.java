package cn.xiaoyu.common;


import java.io.Serializable;

public class ResponseMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer code = null;
    private String msg;
    private TokenMessage tokenMessage;
    private Object data;
    public ResponseMessage() {
    	
    }
    public ResponseMessage(MessageCode code) {
        this(code, null);
    }

    public ResponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseMessage(MessageCode code, Object data) {
        this.code = code.getCode();
        this.msg = code.getValue();
        this.data = data;
    }

    public int getCode() {
        if (code == null) {
            this.code = -1;
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
	public void setTokenMessage(TokenMessage tokenMessage) {
		this.tokenMessage = tokenMessage;
	}
	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", msg=" + msg + ", tokenMessage=" + tokenMessage + ", data=" + data
				+ "]";
	}


}