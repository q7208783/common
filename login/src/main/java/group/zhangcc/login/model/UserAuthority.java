package group.zhangcc.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/10.
 */
@Data
@AllArgsConstructor
public class UserAuthority {
	private int userId;
	private int authId;
}
