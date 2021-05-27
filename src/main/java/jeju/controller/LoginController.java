package jeju.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jeju.dto.JejuUser;
import jeju.service.face.LoginService;

@Controller
@RequestMapping(value = "/member")
public class LoginController {
	
	@Autowired private LoginService loginService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	//로그인 폼
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		logger.info("/member/login [GET]");
	}
	
	
	//로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProc(JejuUser login, HttpSession session) {
		logger.info("/member/login [POST]");
		
		boolean isLogin = loginService.login(login);
		
		if (isLogin) {
			session.setAttribute("login", true);
			session.setAttribute("id", login.getUserId());
			session.setAttribute("nick", loginService.getNickData(login));
			session.setAttribute("grade", loginService.getGradeData(login));
		}
		
		return "redirect:/";
	}
	
	//로그아웃 처리
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}

}
