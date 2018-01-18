package group.zcc.lib.common.constant;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public enum ErrorCodeEnum {

	DEFAULT_ERROR("10000","Default error"),

	SQL_ERROR("10001","SQL got error!"),

	AUTH_FAILURE("10002","AUTHORIZED FAILED!"),
	USER_NAME_ALREADY_EXIST("10010","User Name Is Already Exist"),
	USER_NAME_NOT_EXIST("10011","User Name Not Exist"),

	USER_AUTH_NOT_EXIST("10020","User not contain this authority"),
	USER_AUTH_ALREADY_EXIST("10021","User auth is already contain authority");

	private String errorCode;
	private String errorMsg;

	ErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
