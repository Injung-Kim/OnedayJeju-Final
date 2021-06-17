package jeju.controller.qna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jeju.dto.qna.Hashtag;
import jeju.service.face.QnAService;

@Controller
@RequestMapping(value="/qna")
public class TagController {
	@Autowired QnAService qnaService;
	Logger logger = LoggerFactory.getLogger(TagController.class);
	@RequestMapping(value="/insert/tag")
	public @ResponseBody ModelAndView addTag(
		@RequestParam(value="tagName") String tagName
		, ModelAndView mav
			) {
		
		logger.info("tagName : {}", tagName);
		//입력한 해시태그 DB 등록여부 검사 - 등록되어있지 않은 경우 DB에 새로 등록
		boolean checkTag = qnaService.isExist(tagName);
		if(!checkTag) {
			qnaService.addTagName(tagName);
		}
		//입력한 태그명 조회하기
		Hashtag hashtag = qnaService.getTagInfo(tagName);
		logger.info("hashtag : {}", hashtag);
		mav.addObject("hashtag", hashtag);
		mav.setViewName("jsonView");
		return mav;
	}
}
