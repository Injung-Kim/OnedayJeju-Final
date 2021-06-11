package jeju.dao.face;

import java.util.List;
import java.util.Map;

import jeju.dto.DetailPlan;
import jeju.dto.Dppm;
import jeju.dto.Plan;

public interface DetailPlanDao {
	
	/**
	 * 일정번호로 상세일정 리스트 조회
	 * @param inData - 일정번호
	 * @return 상세일정 리스트
	 */
	public List<DetailPlan> selectDpListByPno(Plan inData);
	
	/**
	 * 상세일정 번호로 상세일정 관련 장소정보 리스트 조회
	 * @param dpno - 상세일정 번호
	 * @return 상세일정 관련 장소정보 리스트
	 */
	public List<Map<String, Object>> selectDpInfoListByDpno(DetailPlan dpno);
	
	/**
	 * 상세일정번호에 해당하는 모든 상세일정_장소 Map 삭제 
	 * @param dppm - 상세일정 번호만 있음
	 */
	public void deleteAllByDpno(Dppm dppm);
	
	/**
	 * 날짜별 상세일정의 새로운 상세일정_장소 Map 삽입
	 * @param newData - 삽입될 데이터
	 */
	public void insertDppm(Dppm newData);

}
