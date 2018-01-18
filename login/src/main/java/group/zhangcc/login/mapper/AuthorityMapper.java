package group.zhangcc.login.mapper;

import java.util.List;

import group.zhangcc.login.model.Authority;
import group.zhangcc.login.model.UserAuthority;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityMapper {
	boolean deleteAllAuthorities(int userId);

	boolean deleteAuthority(UserAuthority userAuthority);

	List<Authority> getUserAuthorities(String userName);

	boolean addAuthority(UserAuthority userAuthority);

	boolean isAuthExist(UserAuthority userAuthority);
}
