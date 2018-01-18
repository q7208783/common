package group.zhangcc.login.model;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@Data
public class ExpireTime {
	private long remainTimeStamp;
	private Boolean isExpire;
}
