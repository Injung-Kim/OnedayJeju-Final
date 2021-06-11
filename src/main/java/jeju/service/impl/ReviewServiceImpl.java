package jeju.service.impl;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import jeju.dao.face.ReviewDao;
import jeju.dao.face.RvCommentDao;
import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.service.face.ReviewService;
import jeju.util.Paging;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	
	@Autowired private ReviewDao reviewDao;
	@Autowired private RvCommentDao rvcommentDao;

	@Override
	public Paging getPaging(Paging inData) {
		
		//총 게시글 수 조회
		int totalCount = reviewDao.selectCntAll(inData);
		
		//페이징 계산
		Paging paging = new Paging(totalCount, inData.getCurPage());
		
		return paging;
	}

	@Override
	public List<Review> list(Paging paging) {
		
		
		return reviewDao.selectPageList(paging);
	}

	@Override
	public Review view(Review viewReview) {
		
		reviewDao.updateHit(viewReview);
		
		return reviewDao.selectReviewByrvNo(viewReview);
	}


	@Override
	public void write(Review review, MultipartFile file) {
		if("".equals(review.getRvTitle())){
			review.setRvTitle("(제목없음)");
		}
		
		
		
		reviewDao.insert(review);
		
		
	}

	@Override
	public List getCommentList(Review review) {
		return rvcommentDao.selectComment(review);
	}

	@Override
	public void insertComment(RvComment rvcomment) {
		rvcommentDao.insertComment(rvcomment);
	}

	
	
	
	

}
