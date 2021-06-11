package jeju.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jeju.dto.DetailPlan;
import jeju.dto.Plan;
import jeju.service.face.DetailPlanService;
import jeju.service.face.PlanService;

@Controller
public class PlanController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired private PlanService planService;
	@Autowired private DetailPlanService detailPlanService;
	
	@RequestMapping(value="/plan/create", method=RequestMethod.GET)
	public void create() {
		logger.info("create() GET 요청");
		logger.debug("create() GET 요청");
	}
	
	@RequestMapping(value="/plan/create", method=RequestMethod.POST)
	public String create(Plan inData, Model model, HttpSession session) {
		logger.info("create() POST 요청");
		
		//====[TEST] 유저번호=====
//		session.setAttribute("userNo", 1);
//		inData.setUserNo( (int)session.getAttribute("userNo") );
		//======================
		
		//세션에서 유저번호 가져오기
		inData.setUserNo((int)session.getAttribute("uno") );
		
		Plan plan = planService.create(inData);
		
		return "redirect:/plan/update?pNo=" + plan.getpNo();
	}
	
	@RequestMapping(value="/plan/update", method=RequestMethod.GET)
	public String update(Plan inData, Model model, HttpSession session) {
		logger.info("update() GET 요청");
		logger.debug("파라미터 inData : {}", inData);
		
		//잘못된 경로
		if( inData.getpNo() == 0) {
			return "redirect:/";
		}
		
		//파라미터로 일정 조회
		Plan plan = planService.getPlan(inData);
		logger.debug("plan : {}", plan);
		
		//세션 처리 (조회한 유저의 일정인지 체크)
		//session.getAttribute("uno") == null 이부분은 인터셉터 작성되면 삭제
		if( ( plan.getUserNo() != (int)session.getAttribute("uno") ) ) {
			return "redirect:/";
		}
		
		//일정번호로 상세일정 리스트 조회
		List<DetailPlan> dpList = detailPlanService.getDpListByPno(inData);
		
		//모델값 전달
		model.addAttribute("plan", plan);
		model.addAttribute("dpList", dpList);
		return "/plan/update";
	}
	
	@RequestMapping(value="/plan/delete", method=RequestMethod.GET)
	public String remove(Plan inData, HttpSession session) {
		logger.info("remove() GET 요청");
		
		//일정 조회
		Plan plan = planService.getPlan(inData);
		
		//session.getAttribute("uno") == null 이부분은 인터셉터 작성되면 삭제
		//본인이 생성한 일정인지 체크
		if( ( plan.getUserNo() != (int)session.getAttribute("uno") )) {
			return "redirect:/";
		}
		//일정 삭제
		planService.remove(inData);
		
		//마이페이지 여행일정으로 이동
		return "redirect:/mypage/plan";
	}
}
