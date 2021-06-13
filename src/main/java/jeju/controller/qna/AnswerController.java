package jeju.controller.qna;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jeju.dto.qna.Answer;
import jeju.dto.qna.FileTB;
import jeju.service.face.QnAService;

@Controller
@RequestMapping(value="/qna")
public class AnswerController {
	@Autowired QnAService qnaService;
	private Logger logger = LoggerFactory.getLogger(AnswerController.class);
	@RequestMapping(value="/write/answer", method = RequestMethod.POST)
	@Transactional
	public String writeAnswer(
			Answer answer
			, HttpSession session
			, @RequestParam(value = "ansFiles", required = false) List<MultipartFile> files
			) {
		logger.info("post요청성공");
		//파일 처리하기
		List<FileTB> list = qnaService.createFile(files);
		//답변글 생성하기
		qnaService.createAnswer(list, session, answer);
		
		return "redirect:/qna/view?qstNo=" + answer.getQstNo();
	}
	@RequestMapping(value="/delete/answer", method = RequestMethod.GET)
	@Transactional
	public String deleteAnswer(FileTB ansNo, int qstNo) {
		//첨부파일 삭제하기
		qnaService.removeFiles(ansNo);
		
		//답변글 삭제하기
		qnaService.deleteAnswer(ansNo);
		
		return "redirect:/qna/view?qstNo="+ qstNo;
	}
}
