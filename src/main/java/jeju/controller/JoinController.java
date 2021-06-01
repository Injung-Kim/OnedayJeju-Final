package jeju.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class JoinController {

	//로깅객체
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@RequestMapping(value = "/joinAgree")
	public void getJoinAgreement() {
		logger.info("/user/joinAgree [GET]");
	}
	
	@RequestMapping(value = "/joinInfo")
	public void getJoinInfoForm() {
		logger.info("/user/joinInfoForm [GET]");
	}
			
			
	
}
