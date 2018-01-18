package group.zhangcc.login.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/5.
 */
@Data
public class NotificationRequest {
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private int userId;
	private String email;
	private String phoneNum;
}
