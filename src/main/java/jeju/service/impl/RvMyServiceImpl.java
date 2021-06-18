package jeju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.RvMyDao;
import jeju.dto.Review;
import jeju.service.face.RvMyService;

@Service
public class RvMyServiceImpl implements RvMyService{

	@Autowired private RvMyDao rvmyDao;
	
	@Override
	public List<Review> getList(Review myreview) {
		return rvmyDao.selectAll(myreview);
	}
	
}
