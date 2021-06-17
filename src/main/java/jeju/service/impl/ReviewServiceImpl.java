package jeju.service.impl;

import java.util.List;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import jeju.dao.face.ReviewDao;
import jeju.dao.face.RvCommentDao;
import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.dto.RvLike;
import jeju.service.face.ReviewService;
import jeju.util.Paging;
import jeju.util.RvPaging;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	
	@Autowired private ReviewDao reviewDao;
	@Autowired private RvCommentDao rvcommentDao;

	@Override
	public RvPaging getPaging(RvPaging inData) {
		
		//총 게시글 수 조회
		int totalCount = reviewDao.selectCntAll(inData);
		
		//페이징 계산
		RvPaging paging = new RvPaging(totalCount, inData.getCurPage());
		
		return paging;
	}

	@Override
	public List<Review> list(RvPaging paging) {
		
		
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
	public void insert(RvComment rvcomment) {
		rvcommentDao.insert(rvcomment);
	}

	@Override
	public void commentUpdateCommit(RvComment rvcomment) {
		rvcommentDao.commentUpdateCommit(rvcomment);
	}

	@Override
	public void commentCancel(RvComment rvcomment) {
		rvcommentDao.commentCancel(rvcomment);
	}

	@Override
	public void update(Review review, MultipartFile file) {

		if( "".equals(review.getRvTitle()) ) {
			review.setRvTitle("(제목없음)");
		}
		reviewDao.update(review);

		
	}

	@Override
	public void delete(Review review) {
		reviewDao.delete(review);
		
	}
	
	@Override
	public boolean isRvLike(RvLike rvlike) {
		int cnt = reviewDao.selectCntRecommend(rvlike);
		
		if(cnt > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean recommend(RvLike rvlike) {
		if( isRvLike(rvlike) ) {
			reviewDao.deleteRecommend(rvlike);
			
			return false;
		} else {
			reviewDao.insertRecommend(rvlike);
		return true;
		
		}
	}


	@Override
	public int getTotalCntRecommend(RvLike rvlike) {
		return reviewDao.selectTotalCntRecommend(rvlike);
	}





	
	
	
	

}
