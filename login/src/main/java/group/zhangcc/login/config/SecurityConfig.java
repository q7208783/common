package group.zhangcc.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import group.zhangcc.login.handler.AuthenticationTokenFilter;
import group.zhangcc.login.handler.JwtAuthenticationEntryPoint;
import group.zhangcc.login.handler.LoginSuccessHandler;
import group.zhangcc.login.service.CustomUserService;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService userService;
	@Autowired
	private LoginSuccessHandler successHandler;
	@Autowired
	private AuthenticationTokenFilter filter;
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	// TODO: 2017/10/30  http://blog.csdn.net/eunyeon/article/details/52892028
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			// don't create session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers(
				HttpMethod.GET,
				"/",
				"/*.html",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.png",
				"/**/*.ttf",
				"/**/*.css",
				"/**/*.js",
				"/**/*.gif"
			).permitAll()
			.antMatchers(
				"/swagger*/**",
				"/v2/api-docs",
				"/demo",
				"/",
				"/api/test",
				"/api/auth/login",
				"/api/auth/login/**",
				"/api/user/**"
			).permitAll()

			.antMatchers("/admin/**").hasRole("ADMIN")
			
			.anyRequest().authenticated();
		// Custom JWT based security filter
		// disable page caching

		http.headers().cacheControl();
		//setAuthentication这个的Filter一定要放在UsernamePasswordAuthenticationFilter之前才可以
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		//自己实现logout需要将这个去掉，不然会变为GET/login?logout
		http.logout().disable();
		http.rememberMe();
		//验证错误的handler
		http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		//允许跨域
		//http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
//			.passwordEncoder(passwordEncoder());
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
