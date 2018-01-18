package group.zhangcc.login.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
public class AuthUser implements UserDetails{
	private String userName;
	@JsonIgnore
	private String password;
	private String lastResetPwYmdt;
	private Collection<? extends GrantedAuthority> authorities;

	public AuthUser(String userName, String password, String lastResetPwYmdt,
		Collection<? extends GrantedAuthority> authorities) {
		this.userName = userName;
		this.password = password;
		this.lastResetPwYmdt = lastResetPwYmdt;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	public String getLastResetPwYmdt() {
		return lastResetPwYmdt;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
