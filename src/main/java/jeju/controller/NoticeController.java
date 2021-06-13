package jeju.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Notice;
import jeju.dto.NoticeComment;
import jeju.dto.NoticeFile;
import jeju.dto.NtcDisLike;
import jeju.dto.NtcLike;
import jeju.dto.NtcReport;
import jeju.service.face.NoticeService;
import jeju.util.Paging;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {

	//logger 객체
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired private NoticeService noticeService;
	
	@RequestMapping(value="/list")
	public void noticeList( Paging inData, Model model) {
//		logger.info("/notice/list");

		//페이징 계산
		Paging paging = noticeService.getPaging(inData);
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
	public String noticeView( Paging inData, Notice notice, HttpSession session, Model model ) {
//		logger.info( "notice : {}", nt.toString() );
		
		//공지사항 번호가 1보다 작으면 목록으로 보내기
		if (notice.getNtNo() < 1 ) {
			return "redirect:/notice/list";
		}
		
		//공지사항 상세 정보 전달
		notice = noticeService.viewNotice(notice);
//		logger.info("notice : {}", notice);
		model.addAttribute("notice", notice);
		
		//첨부파일 상세 정보 전달
		List<NoticeFile> noticeFileList = noticeService.viewAttachFile(notice);
//		for( NoticeFile f : noticeFile ) {
//			logger.info( f.toString() );
//		}
		model.addAttribute("noticeFileList", noticeFileList);
		
		//댓글 상세 정보 전달
		Paging paging = noticeService.getCmtPaging(inData);
		paging.setNtNo( notice.getNtNo() );
		logger.info("paging : {}", paging);
		
		List<NoticeComment> noticeCommentList = noticeService.viewNoticeComment(paging);
		for( NoticeComment c : noticeCommentList ) {
			logger.info( c.toString() );
		}
		model.addAttribute("noticeCommentList", noticeCommentList);
		model.addAttribute("paging", paging);
		
		//좋아요, 싫어요, 신고 상태 조회
		List<NtcLike> ntcLikeList = new ArrayList<NtcLike>();
		List<NtcDisLike> ntcDisLikeList = new ArrayList<NtcDisLike>();
		List<NtcReport> ntr = new ArrayList<NtcReport>();

		for( NoticeComment c : noticeCommentList ) {
			NtcLike nl = new NtcLike();
			NtcDisLike nd = new NtcDisLike();
			NtcReport nr = new NtcReport();

			nl.setNtcNo( c.getNtcNo() );
			nl.setUserId( (String) session.getAttribute("id") );
			ntcLikeList.add(nl);

			nd.setNtcNo( c.getNtcNo() );
			nd.setUserId( (String) session.getAttribute("id") );
			ntcDisLikeList.add(nd);
		
			nr.setNtcNo( c.getNtcNo() );
			nr.setUserId( (String) session.getAttribute("id") ); 
			ntr.add(nr);

		}
		
		//좋아요, 싫어요 상태 전달
		List<Boolean> isLike = noticeService.isLike(ntcLikeList);
		List<Boolean> isDisLike = noticeService.isDisLike(ntcDisLikeList);
		for( Boolean b : isLike ) {
			logger.info("b:{}", b);
		}
		
		for( Boolean b : isDisLike ) {
			logger.info("Like:{}", b);
		}
		
		
		model.addAttribute("isLike", isLike);
		model.addAttribute("isDisLike", isDisLike);
		
		
		for( NtcReport nr : ntr ) {
			logger.info("ntr:{}", nr);
		}

		List<Boolean> isNr = noticeService.isReport( ntr );
		model.addAttribute("isNr", isNr);
		
		for( Boolean b : isNr ) {
			logger.info("isNr:{}", b);
		}
		return "notice/view";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public void write() { }
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String writeProc( Notice nt,HttpSession session, MultipartFile[] file ) {
		logger.info("/write [POST]" );
		
		//전달파라미터 Notice객체 userNo 저장 후 확인
		nt.setUserNo( (int) session.getAttribute("uno"));
		logger.info("nt : {}", nt.toString());
		
		
		//전달파라미터 file 확인
//		for( int i=0 ; i<file.length ;i++ ) {
//			logger.info("file : {}", file[i] );
//		}
		
		//글쓰기
		noticeService.writeNotice(nt, file);
		
		return "redirect:/notice/list";
	}
	
	@RequestMapping(value="/download")
	public String noticeDown( NoticeFile ntf, Model model ) {
		
		ntf = noticeService.AttachNoticeFile(ntf);
		
		model.addAttribute("NtfdownFile", ntf);
		
		return "noticeDown";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void update( Notice notice , Model model ) {
		
		//공지사항 상세 정보 전달
		notice = noticeService.viewNotice(notice);
//		logger.info("notice : {}", notice);
		model.addAttribute("notice", notice);
		
		//파일정보 상세
		List<NoticeFile> noticeFileList = noticeService.viewAttachFile(notice);
		model.addAttribute("noticeFileList", noticeFileList);
		
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateProc( Notice nt, MultipartFile[] file ) {
		
		logger.info("/write [POST]" );
		
		//전달파라미터 Notice객체 userNo 저장 후 확인
		logger.info("nt : {}", nt.toString());
		
		//전달파라미터 file 확인
		for( int i=0 ; i<file.length ;i++ ) {
			logger.info("file : {}", file[i] );
		}
		
		//글쓰기
		noticeService.modifyNotice(nt, file);
		
		return "redirect:/notice/list";
	}
	
	@RequestMapping(value="/fileDelete")
	public @ResponseBody HashMap<String, Boolean> fileDelete( NoticeFile ntf, Model model) {
		
		logger.info("ntf : {}", ntf);
		
		boolean isntf = noticeService.deleteNoticeFile(ntf);
		
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("res", isntf);
		
		return map;
		
		
	}
	
	@RequestMapping(value="/delete")
	public String deleteProc(Notice notice) {
		
		noticeService.deleteNotice(notice);
		
		
		return "redirect:/notice/list";
	}
	
	@RequestMapping(value="/comment/insert")
	public String writeParentComment( NoticeComment ntc ) {
		//요청파라미터 확인
		logger.info("ntc : {}", ntc);
		
		noticeService.writeParentComment(ntc);
		
		
		return "redirect:/notice/view?ntNo=" + ntc.getNtNo();
	}
	
	@RequestMapping(value="/comment/delyn")
	public @ResponseBody HashMap<String, String> updateNtcDelete( NoticeComment ntc ) {
		
		ntc = noticeService.modifyNtcDelete(ntc);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("ntcDelete", ntc.getNtcDelete() );
		
		return map;
		
	}
	
	@RequestMapping(value="/comment/update")
	public @ResponseBody HashMap<String, NoticeComment> updateNtcContent( NoticeComment ntc ) {
		
		logger.info("ntc: {}", ntc);
		
		ntc = noticeService.modifyNtc(ntc);
		
		HashMap<String, NoticeComment> map = new HashMap<>();
		
		map.put("ntc", ntc);
		
		return map;
	}
	
	@RequestMapping(value="/like")
	public @ResponseBody HashMap<String, Object> ntcLike( NtcLike nl ) {
		logger.info("nl : {}" , nl);

		//싫어요 정보 토글
		boolean result = noticeService.Like(nl);
		
		//싫어요 수 조회
		int cnt = noticeService.getTotalCntLike(nl);
		HashMap<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("cnt", cnt);

		return map;
	}

	@RequestMapping(value="/dislike")
	public @ResponseBody HashMap<String, Object> ntcDisLike( NtcDisLike nd ) {
		logger.info("nl : {}" , nd);
		
		//싫어요 정보 토글
		boolean result = noticeService.disLike(nd);
		
		//싫어요 수 조회
		int cnt = noticeService.getTotalCntDisLike(nd);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("cnt", cnt);
		
		return map;
	}
	
	@RequestMapping(value="/comment/report")
	public @ResponseBody HashMap<String, Boolean> ntcReport( NtcReport nr, HttpSession session ) {
		
		logger.info(nr.toString());
		nr.setUserId( (String) session.getAttribute("id"));
		
		boolean isReport = noticeService.ntcReport( nr );
		
		
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("isReport", isReport);
		
		return map;
	}
	
	@RequestMapping(value="/admin")
	public void noticeAdminView( Paging inData, Model model ) {
		logger.info("/admin");
		
		//페이징 계산
		Paging paging = noticeService.getPaging(inData);
		paging.setCondition( inData.getCondition() );
		paging.setSearch( inData.getSearch() );
		
		
		//공지사항 목록 조회
		List<Notice> ntList = noticeService.getNoticeList(paging);
		
		//모델값 전달
		model.addAttribute("ntList", ntList);
		model.addAttribute("paging", paging);
		
		//댓글 상세 정보 전달
		List<NoticeComment> noticeCommentList = noticeService.viewAllNoticeComment();
		int cmtTotalCnt = noticeService.cmtTotalCnt();
		
		//모델값 전달
		model.addAttribute("noticeCommentList", noticeCommentList);
		model.addAttribute("cmtTotalCnt", cmtTotalCnt);

		//신고 현황
		List<NtcReport> ntcReport = noticeService.viewAllNtcReport();
		int cmtrTotalCnt = noticeService.cmtrTotalCnt();
		
		//모델값 전달
		model.addAttribute("ntcReport", ntcReport);
		model.addAttribute("cmtrTotalCnt", cmtrTotalCnt);
		
		
	}
	
	@RequestMapping(value="/comment/delete")
	public @ResponseBody HashMap<String, Object> NtcDelete( NoticeComment ntc ) {
		
		boolean isNtc = noticeService.NtcDelete(ntc);
		int cmtCnt = noticeService.cmtTotalCnt();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("isNtc",isNtc );
		map.put("cmtCnt", cmtCnt);
		
		return map;
		
	}
	
	@RequestMapping(value="/admin/delete")
	public String adminDeleteProc(Notice notice) {
		
		noticeService.deleteNotice(notice);
		
		
		return "redirect:/notice/admin";
	}
	
}
