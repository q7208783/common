package group.zhangcc.login.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import group.zhangcc.lib.common.exception.ServiceException;
import group.zhangcc.lib.common.response.CommonResponse;
import group.zhangcc.login.model.ExpireTime;
import group.zhangcc.login.model.Token;
import group.zhangcc.login.model.User;
import group.zhangcc.login.service.LoginService;
import group.zhangcc.login.util.JwtTokenUtil;
import group.zhangcc.login.vo.LoginRequest;
import lombok.extern.apachecommons.CommonsLog;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Controller
@CommonsLog
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	@ResponseBody
	public CommonResponse<Token> login(@RequestBody @Valid LoginRequest request, Device device,
		HttpServletResponse response) throws ServiceException {
		return new CommonResponse<>(loginService.login(request, device, response));
	}

	@PostMapping("/logout")
	@ResponseBody
	public CommonResponse<String> logout(@ApiIgnore @RequestAttribute("user") User user, HttpServletResponse response) {
		loginService.logout(response);
		return new CommonResponse(user.getUserName() + " is logout");
	}

	@GetMapping("/userInfo")
	@ResponseBody
	public CommonResponse<User> user(@ApiIgnore @RequestAttribute("user") User user) {
		return new CommonResponse(user);
	}

	@GetMapping("/expire")
	@ResponseBody
	public CommonResponse<ExpireTime> expire(@ApiIgnore @RequestAttribute("user") User user) {
		String token = user.getToken();
		ExpireTime expireTime = new ExpireTime();
		expireTime.setIsExpire(JwtTokenUtil.isTokenExpired(token));
		expireTime.setRemainTimeStamp(JwtTokenUtil.getRemainExpireTime(token));
		return new CommonResponse<>(expireTime);
	}
}
