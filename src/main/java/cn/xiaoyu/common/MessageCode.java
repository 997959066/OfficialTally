package cn.xiaoyu.common;

public enum MessageCode {
	/** 未知错误 */
	UNKNOWN_ERROR("未知错误", -1),
	/** 成功 */
	SUCCESS("成功", 1), ERROR("失败", 0), DB_COLLECTION_IS_NULL("集合中无数据", -1203),
	/** 传入参数不能为空 */
	PARAMETER_INPUT_ERROR("传入参数不能为空", -1301),
	/** 传入参数数量不一致 */
	PARAMETER_INPUT_COUNT_ERROR("传入参数数量不一致", -1302),
	/** 数据操作失败,影响行数为0 */
	DB_OPERATION_ROWS_ZERO("数据操作失败,影响行数为0", -1201), APPLICATION_ERROR("应用级错误", -1000), 
	
	USER_NEED_RELOGIN("登录验证过期，请重新登录", -2001);

	private Integer code;
	private String value;

	MessageCode() {
	}

	MessageCode(String value, Integer code) {
		this.value = value;
		this.code = code;
	}

	public static MessageCode valueOf(Integer code) {
		switch (code) {
		}
		return UNKNOWN_ERROR;
	}

	@Override
	public String toString() {
		return value;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}