package jeju.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jeju.dto.DetailPlan;
import jeju.dto.Dppm;
import jeju.service.face.DetailPlanService;

@Controller
public class DetailPlanController {
	
	private static final Logger logger = LoggerFactory.getLogger(DetailPlanController.class);
	
	@Autowired private DetailPlanService detailPlanService;
	
	@RequestMapping(value="/detailplan", method=RequestMethod.GET)
	public ModelAndView getDetailList(ModelAndView mav, DetailPlan inData) {
		logger.info("getDetailList() GET 요청");
		logger.debug("inData dpNo : {}", inData.getDpNo());
		
		//jsonView 지정
		mav.setViewName("jsonView");
		
		//상세일정번호로 상세일정 정보 조회
		List<Map<String, Object>> infoList = detailPlanService.getDpInfoListByDpno(inData);
		
		//모델값 전달
		mav.addObject("infoList", infoList);
		
		return mav;
	}
	
	@RequestMapping(value="/detailplan/update", method=RequestMethod.POST)
	public ResponseEntity<Void> modify(@RequestBody List<Dppm> inData) {
		logger.info("modify() POST 요청");
		logger.debug("inData : {}", inData.toString());
		
		//상세일정 상태 업데이트
		detailPlanService.modify(inData);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
