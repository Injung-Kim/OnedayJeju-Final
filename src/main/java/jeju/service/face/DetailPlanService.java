package jeju.service.face;

import java.util.List;
import java.util.Map;

import jeju.dto.DetailPlan;
import jeju.dto.Dppm;
import jeju.dto.Plan;

public interface DetailPlanService {
	
	/**
	 * 일정번호로 상세일정 리스트 조회
	 * @param inData - 일정번호 
	 * @return 상세일정 리스트
	 */
	public List<DetailPlan> getDpListByPno(Plan inData);
	
	/**
	 * 상세일정 번호로 상세일정 관련 장소정보 리스트 조회
	 * @param dpno - 상세일정 번호가 담긴 DetailPlan
	 * @return 상세일정 관련 장소정보 리스트
	 */
	public List<Map<String, Object>> getDpInfoListByDpno(DetailPlan dpno);
	
	/**
	 * 날짜별 상세일정을 수정(기존 데이터 삭제 후 새로운 데이터 삽입)
	 * @param inData - 새로운 데이터 리스트
	 */
	public void modify(List<Dppm> inData);
}
