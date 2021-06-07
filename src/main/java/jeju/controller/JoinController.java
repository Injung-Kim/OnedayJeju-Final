package jeju.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jeju.dto.JejuUser;
import jeju.service.face.JoinService;

@Controller
@RequestMapping(value = "/user")
public class JoinController {

	//로깅객체
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired JoinService joinService;
	
	@RequestMapping(value = "/joinAgree")
	public void joinAgree() {
		logger.info("/user/joinAgree");
	}
	
	@RequestMapping(value = "/joinInfo", method = RequestMethod.GET)	
	public void getJoinInfo() {
			logger.info("/user/joinInfo [GET]");
	}
	
	@RequestMapping(value = "/joinInfo", method = RequestMethod.POST)
	public String joinInfoProcess(JejuUser user) {
		logger.info("/user/joinInfo [POST]");
		logger.info("Info : {}", user);
		
		//회원정보 등록
		joinService.registerUserInfo(user);
		
		//인증메일 발송
		
		//인증요청페이지 리다이렉션
		return "redirect:/user/joinReqAuth";
	}
	
	@RequestMapping(value = "/joinReqAuth")
	public void getRequestMailAuth() {
		logger.info("/user/joinReqAuth [GET]");
	}
}
