package group.zhangcc.login.service;

import java.util.List;

import group.zhangcc.lib.common.exception.ServiceException;
import group.zhangcc.login.constant.AuthorityEnum;
import group.zhangcc.login.model.Authority;
import group.zhangcc.login.model.UserAuthority;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityService {
	boolean deleteAllAuthorities(int userId)throws ServiceException;

	List<Authority> getUserAuthorities(String userName);

	boolean addAuthority(String userName, AuthorityEnum authority)throws ServiceException;

	boolean deleteAuthority(String userName, AuthorityEnum authority)throws ServiceException;

	boolean isAuthExist(UserAuthority userAuthority);
}
