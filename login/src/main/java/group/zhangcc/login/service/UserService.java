package group.zhangcc.login.service;

import group.zhangcc.lib.common.exception.ServiceException;
import group.zhangcc.login.model.AuthUser;
import group.zhangcc.login.model.User;
import group.zhangcc.login.vo.ChangePasswordRequest;
import group.zhangcc.login.vo.CreateUserRequest;
import group.zhangcc.login.vo.NotificationRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface UserService {
	User getUser(String userName);

	User getUser(int userId);

	AuthUser getAuthUser(String userName);

	User createUser(CreateUserRequest request) throws ServiceException;

	boolean isUserNameExist(String userName);

	boolean deleteUser(String userName)throws ServiceException;

	int getUserId(String userName)throws ServiceException;

	boolean changePassword(ChangePasswordRequest request)throws ServiceException;

	Boolean changeNotification(NotificationRequest request)throws ServiceException;

	NotificationRequest getNotificationInfo(Integer userId)throws ServiceException;
}
