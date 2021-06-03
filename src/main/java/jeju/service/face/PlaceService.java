package jeju.service.face;

import java.util.List;
import java.util.Map;

import jeju.dto.Place;

public interface PlaceService {
	
	/**
	 * 장소데이터에서 제목에 검색어가 들어간 데이터 조회
	 * @param keyword - 검색어
	 * @return 장소 리스트
	 */
	public List<Place> getListByKeyword(String keyword);
	
	/**
	 * 카테고리에 해당하는 장소 데이터 조회
	 * @param category - 카테고리
	 * @return 장소 리스트
	 */
	public List<Place> getListByCategory(String category);
	
	/**
	 * contentid에 해당하는 장소의 상세정보 조회
	 * @param contentid
	 * @return 상세정보 hashmap
	 */
	public Map<String, String> getDetailInfo(String contentid);

}
