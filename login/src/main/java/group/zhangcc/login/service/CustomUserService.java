package group.zhangcc.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Service
public class CustomUserService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return userService.getAuthUser(userName);
	}
}
