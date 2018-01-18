package group.zhangcc.login.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/17.
 */
@Data
public class ChangePasswordRequest {
	@ApiModelProperty(hidden = true)
	private int userId;
	@NotNull
	private String userName;
	@NotNull
	private String oldPwd;
	@NotNull
	private String newPwd;
	@ApiModelProperty(hidden = true)
	private String lastrResetPwYmdt;
}
