package jeju.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jeju.dto.Expenses;
import jeju.dto.Plan;
import jeju.service.face.ExpensesService;

@Controller
@RequestMapping(value = "/expenses")
public class ExpensesController {

	@Autowired private ExpensesService expensesService;
	
	private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);
	
	
	//일정 리스트
	@RequestMapping(value = "/plist", method = RequestMethod.GET)
	public void planList( Model model) {
		logger.info("/expenses/plist [GET]");
		
		//일정 리스트 받아오기
		List<Plan> pList = expensesService.getPlanList();
		
		//모델값 전달
		model.addAttribute("pList", pList);
	}

	
	//경비 리스트 띄워주기 (ajax로 전달)
	@RequestMapping(value = "/plist", method = RequestMethod.POST)
	public String planListProc( 
			Model model
			, @RequestParam("selectPlan") int pNo ) {
		
		logger.info("/expenses/plist [POST]");
//		logger.info("얻어온 일정 번호 pNo: {}", pNo);
		
		//선택한 일정 번호에 해당하는 경비 리스트 가져오기
		List<HashMap<String, Object>> eList = expensesService.getExpList(pNo);
		logger.info("얻어온 경비 리스트 eList: {}", eList);
		
		//모델값 전달
		model.addAttribute("eList", eList);
		
		
		
		logger.info("지출 목록 차트 실행");
		
		//카테고리, 지출금액 hashmap으로 보내주기
		List<HashMap<String, Object>> map = expensesService.getExpStat(pNo);
		logger.info("차트 - map {}", map);
		
		
		//모델값 전달
		model.addAttribute("map", map);
		
		return "expenses/elist";
	}
	
	
	
	//경비 추가
	@RequestMapping(value="/addexp", method = RequestMethod.GET)
	public String create( Model model, Plan pNo ) {
		logger.info("/expenses/addexp [GET]");
		logger.info("얻어온 pNo: {}", pNo);
		
		List<HashMap<String, Object>> pData = expensesService.getPlanData(pNo);
		logger.info("pData {}", pData);
		
		//모델값 전달
		model.addAttribute("pData", pData);
		
		return "expenses/addexp_modal";
	}
	
	
	//경비 추가 처리
	@RequestMapping(value = "/addexp", method = RequestMethod.POST)
	public String createProc(
			
			Expenses add
			, HttpSession session
			) {
		
		logger.info("/expenses/addexp [POST]");
		logger.info("얻어온 Expenses 정보 : {}", add);

		int userno = (int) session.getAttribute("uno");
		add.setUserNo(userno);
		

		//경비 추가하기
		expensesService.addExp(add);
		
		return "expenses/elist";
	}
	
	
	//경비 내역 상세보기
	@RequestMapping(value = "/view")
	public String view(@RequestParam("expNo") int expno, Model model, HttpServletRequest req) {
		logger.info("/expenses/view");
		logger.info("얻어온 경비 번호: {}", expno);
		
		int eno = Integer.parseInt(req.getParameter("expNo"));
		
		Expenses view = expensesService.viewExp(eno);
		
		logger.info("view: {}", view);
		
		//모델값 전달
		model.addAttribute("view", view);
		
		return "expenses/viewexp_modal";
	}
	
	
	//경비 내역 삭제
	@RequestMapping(value = "/delete")
	public String delete( int expno) {
		logger.info("/expenses/delete");
		
		expensesService.delExp(expno);
		
		return "redirect:/expenses/plist";
	}
	
}
