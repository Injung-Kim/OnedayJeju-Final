package jeju.controller;

import java.util.List;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jeju.service.face.AdminReviewBoardService;
import jeju.util.Paging;

@Controller
public class AdminReviewBoardController {
	//로깅객체
	private static final Logger logger = LoggerFactory.getLogger(AdminReviewBoardController.class);
	
	//서비스객체
	@Autowired private AdminReviewBoardService adminreviewBoardService;
	
	@RequestMapping(value="/admin/reviewboard", method=RequestMethod.GET)
	public void getReviewBoardList(Model model, Paging param) {
		logger.info("/admin/reviewboard GET");
		
		//페이징객체 생성
		Paging paging = adminreviewBoardService.getPaging(param);
		
		System.out.println("==============================" + paging);
		
		//여행후기게시글 최신순으로 조회
		List<Map<String, Object>> rvList = adminreviewBoardService.getList(paging);
		logger.debug("rvList : {}", rvList);
		
		//모델값 전달
		model.addAttribute("rvList", rvList);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping(value="/admin/reviewboard/delete", method=RequestMethod.POST)
	public String remove(@RequestParam(value="chk") List<Integer> rvNo) {
		logger.info("/admin/reviewboard POST");
		logger.debug("rvNo : {}", rvNo);
		
		//게시글 삭제
		adminreviewBoardService.remove(rvNo);
		
		return "redirect:/admin/reviewboard";
	}
}
