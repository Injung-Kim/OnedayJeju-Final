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

import jeju.service.face.AdminPlanBoardService;
import jeju.util.Paging;

@Controller
public class AdminPlanBoardController {
	//로깅객체
	private static final Logger logger = LoggerFactory.getLogger(AdminPlanBoardController.class);
	
	//서비스객체
	@Autowired private AdminPlanBoardService adminPlanBoardService;
	
	@RequestMapping(value="/admin/planboard", method=RequestMethod.GET)
	public void getPlanBoardList(Model model, Paging param) {
		logger.info("/admin/planboard GET");
		
		//페이징객체 생성
		Paging paging = adminPlanBoardService.getPaging(param);
		
		//일정공유게시글 최신순으로 조회
		List<Map<String, Object>> pbList = adminPlanBoardService.getList(paging);
		logger.debug("pbList : {}", pbList);
		
		//모델값 전달
		model.addAttribute("pbList", pbList);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping(value="/admin/planboard/delete", method=RequestMethod.POST)
	public String remove(@RequestParam(value="chk") List<Integer> pbNo) {
		logger.info("/admin/planboard POST");
		logger.debug("pbNo : {}", pbNo);
		
		//게시글 삭제
		adminPlanBoardService.remove(pbNo);
		
		return "redirect:/admin/planboard";
	}
}
