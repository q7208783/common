package group.zhangcc.login.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Data
public class User {
	private int userId;
	private String userName;
	@JsonIgnore
	private String password;
	private String phoneNum;
	private List<Authority> authorities;
	private String email;
	private String createTimeYmdt;
	private String lastrResetPwYmdt;
	@JsonIgnore
	private String token;

	public boolean addAuthority(Authority authority){
		if(authorities==null)
			authorities=new ArrayList<>();
		return authorities.add(authority);
	}
}
