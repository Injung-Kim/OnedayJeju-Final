package jeju.dao.face;

import java.util.List;

import jeju.dto.Review;

public interface RvMyDao {

	
	/**
	 * 일정 모두 조회
	 * 
	 * @param myreview - 유저번호
	 * @return 로그인 유저의 모든 일정 리스트
	 */
	public List<Review> selectAll(Review myreview);

}
