package group.zhangcc.lib.common.response;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/28.
 */
@Data
public class CommonResponse<T> {
	private final String NORMAL_CODE = "0";

	private String returnCode;
	private String returnMsg;
	private T data;
	private String procTime;

	public CommonResponse() {
	}

	public CommonResponse(T data) {
		this.data = data;
		this.returnCode = NORMAL_CODE;
	}

	public CommonResponse(String returnCode, T data) {
		this.returnCode = returnCode;
		this.data = data;
	}

	public CommonResponse(String returnCode, String returnMsg, T data) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.data = data;
	}
}
