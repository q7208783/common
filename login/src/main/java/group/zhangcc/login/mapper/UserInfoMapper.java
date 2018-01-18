package group.zhangcc.login.mapper;

import group.zhangcc.login.model.User;
import group.zhangcc.login.vo.ChangePasswordRequest;
import group.zhangcc.login.vo.NotificationRequest;
import group.zhangcc.login.vo.SearchUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
public interface UserInfoMapper {
	User getUser(SearchUserRequest request);

	Integer getUserId(String userName);

	boolean createUser(User user);

	boolean userNameExist(String userName);

	boolean deleteUser(String userName);

	boolean changePassword(ChangePasswordRequest request);

	Boolean changeNotification(NotificationRequest request);

	NotificationRequest getNotificationInfo(Integer userId);
}
