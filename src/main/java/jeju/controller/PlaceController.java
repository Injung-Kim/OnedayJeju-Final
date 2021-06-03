package jeju.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jeju.dto.Place;
import jeju.service.face.PlaceService;

@Controller
public class PlaceController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
	
	@Autowired private PlaceService placeService;
	
	@RequestMapping(value="/placebykeword", method=RequestMethod.GET)
	public ModelAndView getListByKeyword(ModelAndView mav, String data) {
		logger.info("getListByKeyword() GET 호출");
		
		mav.setViewName("jsonView");
		List<Place> placeList = placeService.getListByKeyword(data);
		mav.addObject("placeList", placeList);
		
		return mav;
	}
	
	@RequestMapping(value="/placebycategory", method=RequestMethod.GET)
	public ModelAndView getListByCategory(ModelAndView mav, String data) {
		logger.info("getListByCategory() GET 호출");
		
		mav.setViewName("jsonView");
		List<Place> placeList = placeService.getListByCategory(data);
		mav.addObject("placeList", placeList);	
		
		return mav;
	}
	
	@RequestMapping(value="/detailinfo", method=RequestMethod.GET)
	public ModelAndView getDetailInfo(ModelAndView mav, String contentid) {
		logger.info("getDetailInfo() GET 호출");
		
		mav.setViewName("jsonView");
		Map<String, String> result = placeService.getDetailInfo(contentid);
		mav.addObject("detailInfo", result);
		
		return mav;
	}
}
