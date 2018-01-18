package group.zhangcc.login.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.zhangcc.lib.common.constant.DataSourceEnum;
import group.zhangcc.lib.common.constant.ErrorCodeEnum;
import group.zhangcc.lib.common.exception.ServiceException;
import group.zhangcc.lib.common.utils.DateUtil;
import group.zhangcc.login.constant.AuthorityEnum;
import group.zhangcc.login.mapper.AuthorityMapper;
import group.zhangcc.login.mapper.UserInfoMapper;
import group.zhangcc.login.model.AuthUser;
import group.zhangcc.login.model.Authority;
import group.zhangcc.login.model.User;
import group.zhangcc.login.model.UserAuthority;
import group.zhangcc.login.service.AuthorityService;
import group.zhangcc.login.service.UserService;
import group.zhangcc.login.util.Converter;
import group.zhangcc.login.vo.ChangePasswordRequest;
import group.zhangcc.login.vo.CreateUserRequest;
import group.zhangcc.login.vo.NotificationRequest;
import group.zhangcc.login.vo.SearchUserRequest;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private Converter converter;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AuthenticationManager authenticationManager;

	private User getUser(SearchUserRequest request) {
		return userInfoMapper.getUser(request);
	}

	public AuthUser getAuthUser(String userName){
		return converter.covertUser2AuthUser(this.getUser(userName));
	}

	public User getUser(String userName){
		SearchUserRequest request = new SearchUserRequest();
		request.setUserName(userName);
		return this.getUser(request);
	}

	public User getUser(int userId){
		SearchUserRequest request = new SearchUserRequest();
		request.setUserId(userId);
		return this.getUser(request);
	}

	@Transactional
	public User createUser(CreateUserRequest request) throws ServiceException {
		if (isUserNameExist(request.getUserName()))
			throw new ServiceException(ErrorCodeEnum.USER_NAME_ALREADY_EXIST);
		User user = converter.convertCreateRequestToUser(request);
		user.setCreateTimeYmdt(DateUtil.timeStamp());
		user.setLastrResetPwYmdt(DateUtil.timeStamp());
		userInfoMapper.createUser(user);
		Authority authority = AuthorityEnum.getAuthority(AuthorityEnum.USER);
		UserAuthority userAuthority = new UserAuthority(user.getUserId(),authority.getAuthId());
		user.addAuthority(authority);
		authorityMapper.addAuthority(userAuthority);
		return user;
	}

	public boolean isUserNameExist(String userName) {
		return userInfoMapper.userNameExist(userName);
	}

	public int getUserId(String userName) throws ServiceException{
		Integer userId = userInfoMapper.getUserId(userName);
		if(userId == null)
			throw new ServiceException(ErrorCodeEnum.USER_NAME_NOT_EXIST);
		else
			return userId.intValue();
	}


	public boolean deleteUser(String userName) throws ServiceException{
		int userId = getUserId(userName);
		if (userInfoMapper.deleteUser(userName)) {
			return authorityService.deleteAllAuthorities(userId);
		}
		return false;
	}

	@Transactional
	public boolean changePassword(ChangePasswordRequest request)throws ServiceException{
		try{
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.getUserName(),
					request.getOldPwd()
				)
			);
		}catch (Exception e){
			throw new ServiceException(ErrorCodeEnum.AUTH_FAILURE);
		}
		request.setLastrResetPwYmdt(DateUtil.timeStamp());
		return userInfoMapper.changePassword(request);
	}

	@Override
	public Boolean changeNotification(NotificationRequest request) throws ServiceException {
		return userInfoMapper.changeNotification(request);
	}

	@Override
	public NotificationRequest getNotificationInfo(Integer userId) throws ServiceException {
		return userInfoMapper.getNotificationInfo(userId);
	}
}
