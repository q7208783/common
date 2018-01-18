package group.zhangcc.login.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Data
public class CreateUserRequest {
	@NotNull
	private String userName;
	@NotNull
	private String password;
	private String phoneNum;
	private String email;
}
