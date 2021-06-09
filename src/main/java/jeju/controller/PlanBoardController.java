package jeju.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jeju.dto.Bookmark;
import jeju.dto.DetailPlan;
import jeju.dto.PBComment;
import jeju.dto.Plan;
import jeju.dto.PlanBoard;
import jeju.dto.PlanLike;
import jeju.service.face.PlanBoardService;
import jeju.util.Paging;

@Controller
public class PlanBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);
	
	@Autowired PlanBoardService planBoardService;
	
	@RequestMapping(value="/planboard/list", method=RequestMethod.GET)
	public String getList(Model model, Paging inData) {
		logger.info("getList() GET 요청");
		logger.debug("inData.search : {}", inData.getSearch());
		logger.debug("inData.condition : {}", inData.getCondition());
		
		//페이징 객체 생성
		Paging paging = planBoardService.getPaging(inData);
		
		//정렬, 검색어, 페이징처리하여 조회
		List<Map<String, Object>> list = planBoardService.getList(paging);
		
		logger.debug("map : {}", list.get(0));
		
		model.addAttribute("list", list);
		return "/planboard/list";
	}
	
	@RequestMapping(value="/planboard/planlist", method=RequestMethod.GET)
	public ModelAndView getPlanList(ModelAndView mav, HttpSession session) {
		logger.info("getPlanList() GET 요청");
		//viewname 지정
		mav.setViewName("jsonView");
		
		logger.debug("uno : {}", session.getAttribute("uno") );
		//로그인한 유저번호로 모든 일정 조회
		List<Plan> planList = planBoardService.getPlanList( (int)session.getAttribute("uno") );
		
		//모델값 전달
		mav.addObject("planList", planList);
		
		return mav;
	}
	
	@RequestMapping(value="/planboard/write", method=RequestMethod.POST)
	public String write(PlanBoard inData, MultipartFile imgfile, HttpSession session) {
		logger.info("write() POST 요청");
		logger.debug("inData : {}", inData);
		logger.debug("imgfile : {} ",imgfile);
		
		//세션에서 유저번호 얻기
		inData.setUserNo( (int)session.getAttribute("uno") );
		
		PlanBoard planBoard = planBoardService.write(inData, imgfile);
		
		//등록한 게시글 상세보기 페이지로 리다이렉트
		return "redirect:/planboard/view?pbNo="+planBoard.getPbNo();
	}
	
	@RequestMapping(value="/planboard/view", method=RequestMethod.GET)
	public void view(Model model, String pbNo, HttpSession session) {
		logger.info("view() GET 요청");
		
		//게시글번호로 공유일정게시글 관련 기본정보 조회(공유일정 게시글 TB + 좋아요수)
		Map<String, Object> info = planBoardService.getPlanBoardInfo(pbNo);
		
		//게시글번호로 조회한 일정번호로 상세일정번호 리스트 조회
		int pNo =  Integer.parseInt(String.valueOf(info.get("PNO")));
		List<DetailPlan> dpList = planBoardService.getDpList(pNo); 
		
		//상세일정번호로 상세일정의 장소정보 조회
		List<List<Map<String, Object>>> detailsList = new ArrayList<>();
		
		for(DetailPlan dp : dpList) {
			detailsList.add(planBoardService.getDetails( dp.getDpNo() ));
		}
		
		//접속한 유저의 해당 게시글 좋아요 상태 확인
		PlanLike pl = new PlanLike();
		pl.setUserNo( (int)session.getAttribute("uno") );
		pl.setPbNo( Integer.parseInt(pbNo) );
		int initLikeStatus = planBoardService.getLikeStatus(pl);
		
		//접속한 유저의 해당 게시글 북마크 상태 확인
		Bookmark bm = new Bookmark();
		bm.setUserNo( (int)session.getAttribute("uno") );
		bm.setPbNo( Integer.parseInt(pbNo) );
		int initBmStatus = planBoardService.getBmStatus(bm);
				
		logger.debug("info : {}", info);
		//모델값 전달
		model.addAttribute("info", info);
		model.addAttribute("dpList", dpList);
		model.addAttribute("detailsList", detailsList);
		model.addAttribute("initLikeStatus", initLikeStatus);
		model.addAttribute("initBmStatus", initBmStatus);
	}
	
	@RequestMapping(value="/getImg", method=RequestMethod.GET)
	public String getImg(String filename, Model model) {
		logger.info("getImg() 요청");
		//파라미터로 들어온 이미지파일명 모델값으로 뷰에 전달
		model.addAttribute("filename", filename);
		
		return "getImg";
	}
	
	@RequestMapping(value="/planboard/comment", method=RequestMethod.GET)
	public ModelAndView getCmtList(ModelAndView mav, PlanBoard inData) {
		logger.info("getCmtList() 요청");
		//viewName 설정
		mav.setViewName("jsonView");
		
		//댓글 전체 조회
		List<PBComment> cmtList = planBoardService.getCmtList(inData);
		
		//모델값 전달
		mav.addObject("cmtList", cmtList);
		
		return mav;
	}
	
	@RequestMapping(value="/planboard/comment/write", method=RequestMethod.POST)
	public ModelAndView writeComment(ModelAndView mav, PBComment inData) {
		logger.info("writeComment() 요청");
		//viewName 설정
		mav.setViewName("jsonView");
		
		//댓글 작성 및 작성한 댓글 조회 (데이터 삽입)
		PBComment cmt = planBoardService.writeComment(inData);
		
		//모델값 전달
		mav.addObject("comment", cmt);
		
		return mav;
	}
	
	@RequestMapping(value="/planboard/comment/delete", method=RequestMethod.GET)
	public ResponseEntity<Void> deleteComment(PBComment inData) {
		logger.info("deleteComment() 호출");
		
		//댓글번호를 이용하여 댓글 삭제
		planBoardService.removeComment(inData);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/planboard/like", method=RequestMethod.GET)
	public ModelAndView modifyLike(ModelAndView mav, PlanLike inData) {
		logger.info("modifyLike() 호출");
		//viewName 설정
		mav.setViewName("jsonView");
		
		//좋아요 상태 변경 후 상태 반환( 1 = 좋아요 상태, 0 = 좋아요 취소상태 )
		int likeStatus = planBoardService.modifyLike(inData);
		
		//좋아요 총 개수 조회
		int likeCnt = planBoardService.selectLikeCnt(inData);
		
		//모델값 전달
		mav.addObject("likeStatus", likeStatus);
		mav.addObject("likeCnt", likeCnt);
		return mav;
	}
	
	@RequestMapping(value="/planboard/bookmark", method=RequestMethod.GET)
	public ModelAndView modifyBookmark(ModelAndView mav, Bookmark inData) {
		logger.info("modifyBookmark() 요청");
		//viewName 설정
		mav.setViewName("jsonView");
		
		//북마크 상태 변경 후 상태 반환( 1 = 북마크 상태, 0 = 북마크 취소상태 )
		int bookmarkStatus = planBoardService.modifyBookmark(inData);
		
		//모델값 전달
		mav.addObject("bookmarkStatus", bookmarkStatus);
		return mav;
	}
	
	@RequestMapping(value="/planboard/update", method=RequestMethod.POST)
	public String modifyImage(PlanBoard inData, MultipartFile imgfile) {
		logger.info("modifyImage() 요청");
		
		//이미지 수정 ( 기존파일명 조회 -> 새로운 이미지파일 저장 -> 이미지파일명 컬럼 수정 -> 기존 이미지파일 삭제 )
		planBoardService.modifyImage(inData, imgfile);
		
		return "redirect:/planboard/view?pbNo="+inData.getPbNo();
	}
	
	@RequestMapping(value="/planboard/delete", method=RequestMethod.GET)
	public String remove(PlanBoard inData, HttpSession session) {
		logger.info("remove() 요청");
		
		//공유일정 게시글 조회
		PlanBoard planBoard = planBoardService.getPlanBoard(inData);
		
		//session.getAttribute("uno") == null 이부분은 인터셉터 작성되면 삭제
		//본인이 생성한 일정게시글인지 체크
		if( session.getAttribute("uno") == null || ( planBoard.getUserNo() != (int)session.getAttribute("uno") )) {
			return "redirect:/";
		}
		//공유일정 게시글 삭제
		planBoardService.remove(inData);
		
		//마이페이지 일정공유로 이동
		return "redirect:/mypage/planboard";
	}
	
}
