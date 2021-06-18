package jeju.service.impl;

import java.util.List;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.AdminReviewBoardDao;
import jeju.service.face.AdminReviewBoardService;
import jeju.util.Paging;

@Service
public class AdminReviewBoardServiceImpl implements AdminReviewBoardService{
	//로깅객체
	private static final Logger logger = LoggerFactory.getLogger(AdminReviewBoardServiceImpl.class);
	
	//DAO
	@Autowired AdminReviewBoardDao adminreviewBoardDao;
	
	@Override
	public Paging getPaging(Paging param) {
		logger.info("getPaging() 호출");
		//총 게시글 수 조회 
		int totalCount = adminreviewBoardDao.selectCntAll(param);
		
		//페이징객체 생성
		Paging paging = new Paging(totalCount, param.getCurPage());
		paging.setSearch(param.getSearch());
		
		return paging;
	}
	
	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		logger.info("getList() 호출");
		//게시글 목록 조회
		List<Map<String, Object>> list = adminreviewBoardDao.selectList(paging);
		return list;
	}
	
	@Override
	public void remove(List<Integer> rvNo) {
		logger.info("remove() 호출");
		//리스트에 있는 모든 게시글 삭제
		adminreviewBoardDao.delete(rvNo);
	}
}
