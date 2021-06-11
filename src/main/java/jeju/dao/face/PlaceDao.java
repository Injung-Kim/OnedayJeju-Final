package jeju.dao.face;

import java.util.List;
import java.util.Map;

import jeju.dto.Place;

public interface PlaceDao {
	
	/**
	 * 장소데이터에서 제목에 검색어가 들어간 데이터 조회
	 * @param keyword - 검색어
	 * @return 장소 리스트
	 */
	public List<Place> selectByKeyword(String keyword);
	
	/**
	 * 카테고리로 장소 조회 - 관광
	 * @return 장소 리스트
	 */
	public List<Place> selectByCategories();
	
	/**
	 * 카테고리로 장소 조회 - 식당, 숙박, 쇼핑
	 * @param category
	 * @return 장소리스트
	 */
	public List<Place> selectByCategory(String category);
	
	/**
	 * contentid 에 해당하는 장소의 상세정보 조회
	 * @param contentid
	 * @return 장소의 상세정보
	 */
	public Map<String, String> selectDetailInfo(String contentid);

}
