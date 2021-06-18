package jeju.service.face;

import java.util.List;

import org.springframework.stereotype.Service;

import jeju.dto.Review;

public interface RvMyService {

	/**
	 * 작성한 일정 모두 조회
	 * 
	 * @param myreview - 유저번호
	 * @return 작성 일정 리스트
	 */
	List<Review> getList(Review myreview);

}
