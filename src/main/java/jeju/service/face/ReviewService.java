package jeju.service.face;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.util.Paging;

public interface ReviewService {

	public Paging getPaging(Paging inData);

	public List<Review> list(Paging paging);

	public Review view(Review viewReview);

	public void write(Review review, MultipartFile file);

	public List getCommentList(Review viewReview);
	
	public void insertComment(RvComment rvcomment);


}
