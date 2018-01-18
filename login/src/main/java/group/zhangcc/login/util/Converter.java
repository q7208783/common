package group.zhangcc.login.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import group.zhangcc.login.model.AuthUser;
import group.zhangcc.login.model.User;
import group.zhangcc.login.vo.CreateUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public class Converter {

	private final String rolePrefix = "ROLE_";

	private ModelMapper modelMapper;

	public void setModelMapper(ModelMapper modelMapper){
		this.modelMapper = modelMapper;
	}

	public User convertCreateRequestToUser(CreateUserRequest request) {
		return modelMapper.map(request, User.class);
	}

	public AuthUser covertUser2AuthUser(User user) {
		if(user == null)
			return null;
		String userName = user.getUserName();
		String password = user.getPassword();
		List<SimpleGrantedAuthority> authorityList = user.getAuthorities().stream()
			.map(authority -> new SimpleGrantedAuthority(rolePrefix+authority.getAuthName())).collect(Collectors.toList());
		return new AuthUser(userName, password, user.getLastrResetPwYmdt(), authorityList);
	}
}
