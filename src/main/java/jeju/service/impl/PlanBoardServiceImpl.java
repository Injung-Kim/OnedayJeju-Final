package jeju.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeju.controller.PlanBoardController;
import jeju.dao.face.PlanBoardDao;
import jeju.dto.Bookmark;
import jeju.dto.DetailPlan;
import jeju.dto.PBComment;
import jeju.dto.Plan;
import jeju.dto.PlanBoard;
import jeju.dto.PlanLike;
import jeju.service.face.PlanBoardService;
import jeju.util.Paging;

@Service
public class PlanBoardServiceImpl implements PlanBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);
	
	@Autowired private PlanBoardDao planBoardDao;
	@Autowired private ServletContext context;
	
	@Override
	public Paging getPaging(Paging inData) {
		logger.info("getPaging() 호출 ");
		//총 게시글 수 조회 (검색어 처리)
		int totalCount = planBoardDao.selectCntAll(inData);
		logger.debug("totalcount : {}", totalCount );
		//페이징객체 생성
		Paging paging = new Paging(totalCount, inData.getCurPage(), 12);
		paging.setCondition(inData.getCondition());
		paging.setSearch(inData.getSearch());
		return paging;
	}
	
	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		logger.info("getList() 호출 ");
		List<Map<String, Object>> list = planBoardDao.selectList(paging);
		logger.debug("list : {}", list);
		
		return list;
	}
	
	@Override
	public List<Plan> getPlanList(int uno) {
		logger.info("getPlanList() 호출 ");
		
		return planBoardDao.selectPlanByUserno(uno);
	}
	
	@Override
	public PlanBoard write(PlanBoard inData, MultipartFile imgfile) {
		logger.info("write() 호출 ");
		
		if( imgfile.getSize() == 0 ) {
			logger.info("파일의 크기가 0, 처리중단");
			return null;
		}
		
		
		//파일이 저장될 경로(stored path)
		String storedPath = context.getRealPath("upload");
		logger.info("storedpath : {}", storedPath);
		
		//폴더가 존재하지 않으면 생성하기
		File stored = new File(storedPath);
		if( !stored.exists() ) {
			stored.mkdir();
		}
		
		//저장될 파일의 이름 생성하기
		String filename = imgfile.getOriginalFilename(); //원본파일명
		
		//UUID값 생성
		String uid = UUID.randomUUID().toString().split("-")[4];
		logger.info(uid);
		
		//원본파일이름에 uuid 추가하기 (파일명이 중복되지 않도록 설정)
		filename = uid+"_"+filename;
		logger.info("filename : {}", filename);
		
		//저장될 파일 정보 객체
		File dest = new File(stored, filename);
		
		try {
			//업로드된 파일 서버에 저장하기
			imgfile.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//저장된 파일명 DTO에 설정
		inData.setPbFilename(filename);
		
		planBoardDao.insertPlanBoard( inData );
		
		//저장된 게시글번호도 포함한 DTO
		PlanBoard planBoard = inData;
		
		return planBoard;
	}
	
	@Override
	public Map<String, Object> getPlanBoardInfo(String pbNo) {
		logger.info("getPlanBoardInfo() 호출");
		//조회수 1 증가
		int pbno = Integer.parseInt(pbNo);
		planBoardDao.updateViews(pbno);
		return planBoardDao.selectBoardByPbno(pbno);
	}
	
	@Override
	public List<DetailPlan> getDpList(int pNo) {
		logger.info("getDpList() 호출");
		return planBoardDao.selectDpListByPno(pNo);
	}
	
	@Override
	public List<Map<String, Object>> getDetails(int dpNo) {
		logger.info("getDetails() 호출");
		return planBoardDao.selectDetailsByDpno(dpNo);
	}
	
	@Override
	public List<PBComment> getCmtList(PlanBoard inData) {
		logger.info("getCmtList() 호출");
		return planBoardDao.selectCmtByPbno(inData);
	}
	
	@Override
	public PBComment writeComment(PBComment inData) {
		logger.info("writeComment() 호출");
		//데이터 삽입
		planBoardDao.insertComment(inData);
		//삽입된 댓글 데이터 조회
		PBComment cmt = planBoardDao.selectCmtByPbcno(inData);
		return cmt;
	}
	
	@Override
	public void removeComment(PBComment inData) {
		logger.info("removeComment() 호출");
		
		//댓글 삭제
		planBoardDao.deleteComment(inData);
	}
	
	@Override
	public int modifyLike(PlanLike inData) {
		logger.info("modifyLike() 호출");
		
		//게시글에 대해 좋아요 누른 유저의 좋아요상태 조회 (좋아요 누른적이 없으면 null, 좋아요한 상태면 1, 좋아요취소 상태면 0)
		Integer presence = planBoardDao.selectLikePresence(inData);
		logger.debug("presence : {}", presence);
		
		Map<String, Object> param = new HashMap<>();
		param.put("presence", presence);
		param.put("pbNo", inData.getPbNo());
		param.put("userNo", inData.getUserNo());
		
		if( presence == null ) {
			//좋아요 테이블에 데이터 삽입
			planBoardDao.insertLike(inData);
		} else if( presence == 1 ) {
			//좋아요 상태 0으로 변경
			planBoardDao.updateLike(param);
			return 0;
		} else if( presence == 0 ) {
			//좋아요 상태 1로 변경
			planBoardDao.updateLike(param);
			
		}
		return 1;
	}
	
	@Override
	public int selectLikeCnt(PlanLike inData) {
		logger.info("selectLikeCnt() 호출");
		return planBoardDao.selectLikeCnt(inData);
	}
	
	@Override
	public int getLikeStatus(PlanLike pl) {
		logger.info("getLikeStatus() 호출");
		return planBoardDao.selectLikeStatus(pl);
	}
	
	@Override
	public int modifyBookmark(Bookmark inData) {
		logger.info("modifyBookmark() 호출");
		
		//게시글에 대해 (북마크 누른적이 없으면 null, 북마크 상태면 1, 북마크취소 상태면 0)
		Integer presence = planBoardDao.selectBmPresence(inData);
		logger.debug("presence : {}", presence);
		
		Map<String, Object> param = new HashMap<>();
		param.put("presence", presence);
		param.put("pbNo", inData.getPbNo());
		param.put("userNo", inData.getUserNo());
		
		if( presence == null ) {
			//북마크 테이블에 데이터 삽입
			planBoardDao.insertBookmark(inData);
		} else if( presence == 1 ) {
			//북마크 상태 0으로 변경
			planBoardDao.updateBookmark(param);
			return 0;
		} else if( presence == 0 ) {
			//북마크 상태 1로 변경
			planBoardDao.updateBookmark(param);
			
		}
		return 1;
	}
	
	@Override
	public int getBmStatus(Bookmark bm) {
		logger.info("getBmStatus() 호출");
		return planBoardDao.selectBmStatus(bm);
	}
	
	@Override
	public void modifyImage(PlanBoard inData, MultipartFile imgfile) {
		logger.info("modifyImage() 호출");
		
		//기존 파일명 조회
		String previousFilename = planBoardDao.selectFilenameByPbno(inData);
		
		if( imgfile.getSize() == 0 ) {
			logger.info("파일의 크기가 0, 처리중단");
			return;
		}
		
		//파일이 저장될 경로(stored path)
		String storedPath = context.getRealPath("upload");
		logger.info("storedpath : {}", storedPath);
		
		//폴더가 존재하지 않으면 생성하기
		File stored = new File(storedPath);
		if( !stored.exists() ) {
			stored.mkdir();
		}
		
		//저장될 파일의 이름 생성하기
		String filename = imgfile.getOriginalFilename(); //원본파일명
		
		//UUID값 생성
		String uid = UUID.randomUUID().toString().split("-")[4];
		logger.info(uid);
		
		//원본파일이름에 uuid 추가하기 (파일명이 중복되지 않도록 설정)
		filename = uid+"_"+filename;
		logger.info("filename : {}", filename);
		
		//저장될 파일 정보 객체
		File dest = new File(stored, filename);
		
		try {
			//업로드된 파일 서버에 저장하기
			imgfile.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//저장된 파일명 DTO에 설정
		inData.setPbFilename(filename);
		
		//파일명 수정하기
		planBoardDao.updatePlanBoard( inData );
		
		//기존 파일 삭제하기
		String path = context.getRealPath("upload") + "/" + previousFilename; //삭제할 파일의 경로
		
		File file = new File(path); //삭제할 파일 객체
		
		if( file.exists() ) { //파일이 존재하면 파일 삭제하기
			file.delete(); 
		}
	}
	
	@Override
	public PlanBoard getPlanBoard(PlanBoard inData) {
		logger.info("getPlanBoard() 호출");
		return planBoardDao.selectPlanBoardByPbno(inData);
	}
	
	@Override
	public void remove(PlanBoard inData) {
		logger.info("remove() 호출");
		//기존 파일 삭제하기
		String path = context.getRealPath("upload") + "/" + inData.getPbFilename(); //삭제할 파일의 경로
		
		File file = new File(path); //삭제할 파일 객체
		
		if( file.exists() ) { //파일이 존재하면 파일 삭제하기
			file.delete(); 
		}
		//게시글 삭제
		planBoardDao.deletePlanBoardByPbno(inData);
	}
	
	
	@Override
	public List<Map<String, Object>> getBookmarkList(int userNo) {
		logger.info("getBookmarkList() 호출");
		return planBoardDao.selectBookmarkList(userNo);
	}
}
