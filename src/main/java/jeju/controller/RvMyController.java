package jeju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jeju.dto.Review;
import jeju.service.face.RvMyService;

@Controller
public class RvMyController {
	private static final Logger logger = LoggerFactory.getLogger(RvMyController.class);
	
	@Autowired  private RvMyService rvmyService;
	
	@RequestMapping(value="/review/mypageList")
	public @ResponseBody List<Review> getList(HttpSession session) {
		
		logger.info("/review/list list Get 요청");
		
		Review myreview = new Review();
		myreview.setUserNo((int) session.getAttribute("uno"));
		List<Review> rvList = rvmyService.getList(myreview);
		logger.info("{}", rvList);
		return rvList;
	}
}
