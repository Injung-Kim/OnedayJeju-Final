package jeju.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jeju.dto.JejuUser;
import jeju.service.face.JoinService;

@Controller
@RequestMapping(value = "/user")
public class JoinController {

	//로깅객체
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired private JoinService joinService;
	
	@RequestMapping(value = "/joinAgree")
	public void agree() {
//		logger.info("/user/joinAgree");
	}
	
	@RequestMapping(value = "/joinInfo", method = RequestMethod.GET)	
	public void join() {
//		logger.info("/user/joinInfo [GET]");
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinInfo/checkId", method = RequestMethod.GET)
	public int checkId(@RequestParam("id") String userId) {
		
		//아이디 중복조회 결과값 전달
		return joinService.checkId(userId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinInfo/checkNick", method = RequestMethod.GET)
	public int checkNick(@RequestParam("nick") String userNick) {
		
		//닉네임 중복조회 결과값 전달
		return joinService.checkNick(userNick);
	}
	
	@RequestMapping(value = "/joinInfo", method = RequestMethod.POST)
	public String joinProcess(JejuUser user, Model model) {
//		logger.info("/user/joinInfo [POST]");
//		logger.info("Info : {}", user);
		
//		int resId = joinService.checkId(userId);
//		int resNick = joinService.checkNick(userNick);
		
//		if(resId >= 1 || resNick >= 1 ) {
//			return "/user/joinInfo";
//		} else if(resId == 0 && resNick == 0 ) {
			//회원가입 처리
			joinService.join(user);
//		}

		model.addAttribute("userId", user.getUserId());
		model.addAttribute("userEmail", user.getUserEmail());
		
		//인증요청페이지로 이동
		return "/user/sendMail";
	}
	
	@RequestMapping(value = "/sendMail")
	public void send() {
//		logger.info("/user/sendMail");
	}
	
	@RequestMapping(value = "/confirmMail")
	public void confirm(@RequestParam String authkey) {
		logger.info("/user/confirmMail [authkey] : " + authkey);
		
		//authkey가 일치할 경우 userMailAuth값 업데이트
		joinService.checkMailAuth(authkey);
	}
	
}
