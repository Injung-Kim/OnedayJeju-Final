package jeju.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jeju.dto.Notice;
import jeju.service.face.NoticeService;
import jeju.util.NoticePaging;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {

	//logger 객체
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired private NoticeService noticeService;
	
	@RequestMapping(value="/list")
	public void noticeList( NoticePaging inData, Model model) {
//		logger.info("/notice/list");

		//페이징 계산
		NoticePaging paging = noticeService.getPaging(inData);
		paging.setCondition( inData.getCondition() );
		paging.setSearch( inData.getSearch() );
		logger.info("paging : {}", paging);
		
		
		//공지사항 목록 조회
		List<Notice> ntList = noticeService.getNoticeList(paging);
		for(Notice n : ntList ) {
			logger.info(n.toString());
		}
		
		//모델값 전달
		model.addAttribute("ntList", ntList);
		model.addAttribute("paging", paging);
		
	}
	
	@RequestMapping(value="/view")
	public String noticeView( Notice nt, Model model ) {
//		logger.info( "notice : {}", nt.toString() );
		
		//공지사항 번호가 1보다 작으면 목록으로 보내기
		if (nt.getNtNo() < 1 ) {
			return "redirect:/notice/list";
		}
		
		//공지사항 상세 정보 전달
		Notice notice = noticeService.viewNotice(nt);
//		logger.info("notice : {}", notice);
		
		//모델값 전달
		model.addAttribute("notice", notice);
		
		return "notice/view";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public void write() { }
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String writeProc( Notice nt,HttpSession session, MultipartFile[] file ) {
		logger.info("/write [POST]" );
		
		//전달파라미터 Notice객체 userNo 저장 후 확인
//		nt.setUserNo( session.getAttribute("userNo"));
		logger.info("nt : {}", nt.toString());
		
		
		//전달파라미터 file 확인
		for( int i=0 ; i<file.length ;i++ ) {
			logger.info("file : {}", file[i] );
		}
		
		
//		noticeService.writeNotice()
		
		return "redirect:/notice/write";
	}
}
