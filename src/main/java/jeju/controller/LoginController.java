package jeju.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginProc(JejuUser login, HttpSession session) {
//		logger.info("/member/login [POST]");
//		
//		boolean isLogin = loginService.login(login);
//		
//		if (isLogin) { //로그인 성공
//			session.setAttribute("login", true);
//			session.setAttribute("id", login.getUserId());
//			session.setAttribute("nick", loginService.getNickData(login));
//			session.setAttribute("grade", loginService.getGradeData(login));
//		
//		} 
//		
//		return "redirect:/main";
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginProc(JejuUser login, HttpSession session) {
		logger.info("/member/login [POST]");
		
		boolean isLogin = loginService.login(login);
		ModelAndView mav = new ModelAndView();
		
		if (isLogin == true) { //로그인 성공
			
			mav.setViewName("main");
			mav.addObject("msg", "success");
			
			session.setAttribute("login", true);
			session.setAttribute("id", login.getUserId());
			session.setAttribute("nick", loginService.getNickData(login));
			session.setAttribute("grade", loginService.getGradeData(login));
			
			
		} else {
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		
		return mav;
	}
	
	
	//로그아웃 처리
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	//아이디 찾기
	@RequestMapping(value = "/findid", method = RequestMethod.GET)
	public String findIdView(Model model) {
		logger.info("/member/findid [GET]");
		
		return "member/findid";
	}
	
	
	//아이디 찾기 처리
	@RequestMapping(value = "/findid", method = RequestMethod.POST)
	public String findId(
			@RequestParam("inputName_1") String name
			, @RequestParam("inputPhone_1") String phone
			, Model model
			) {
		
		logger.info("/member/findid [POST]");
		
		//아이디 찾기
		String resultid = loginService.getSearchId(name, phone);
		logger.info(resultid);
		
		model.addAttribute("findId", resultid);
//		model.addAttribute("findId", "TEST");
		
		
		return "member/resultid";
	}
	
	
	//비밀번호 찾기
	@RequestMapping(value = "/findpw", method = RequestMethod.GET)
	public String findPwView(Model model) {
		logger.info("/member/findpw [GET]");
		
		return "member/findpw";
	}
	
	
	//비밀번호 찾기 처리
	@RequestMapping(value = "/findpw", method = RequestMethod.POST)
	public String findPw(
			@RequestParam("inputId_2") String id
			, @RequestParam("inputEmail_2") String email
			, Model model
			) {
		
		logger.info("/member/findpw [POST]");
		
		//비밀번호 찾기
		String resultpw = loginService.getSearchPw(id, email);
		logger.info("findPw controller - 비밀번호 찾기: {}", resultpw);
		
		model.addAttribute("findPw", resultpw);
		
		
		return "member/resultpw";
	}
	

	


}
