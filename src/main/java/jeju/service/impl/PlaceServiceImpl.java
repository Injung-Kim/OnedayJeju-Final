package jeju.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.PlaceDao;
import jeju.dto.Place;
import jeju.service.face.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

	private static final Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);
	
	@Autowired PlaceDao placeDao;
	
	@Override
	public List<Place> getListByKeyword(String keyword) {
		return placeDao.selectByKeyword(keyword);
	}
	
	@Override
	public List<Place> getListByCategory(String category) {
		
		if("12".equals(category)) {
			return placeDao.selectByCategories();
		}
		
		return placeDao.selectByCategory(category);
	}
	
	@Override
	public Map<String, String> getDetailInfo(String contentid) {
		return placeDao.selectDetailInfo(contentid);
	}
}
