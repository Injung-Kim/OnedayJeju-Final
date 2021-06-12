package jeju.dao.face;

import java.util.HashMap;
import java.util.List;

import jeju.dto.Plan;

public interface PlanDao {
	/**
	 * 일정 정보 삽입
	 * @param inData - 일정 정보
	 */
	public void insert(Plan inData);
	/**
	 * 일정의 여행일수 만큼 상세일정 생성
	 * @param map - 일정번호, 여행날짜 정보
	 */
	public void insertDetail(HashMap<String, Object> map);
	/**
	 * 일정 번호로 일정 정보 조회
	 * @param inData - 일정번호
	 * @return 조회된 일정 정보
	 */
	public Plan selectPlanByPno(Plan inData);
	/**
	 * 일정번호에 해당하는 일정을 삭제
	 * @param inData - 일정번호
	 */
	public void deletePlanByPno(Plan inData);
	/**
	 * 로그인한 유저가 생성한 일정들을 모두 조회한다.
	 * @param myplan - 유저번호
	 * @return 로그인한 유저의 모든 일정 리스트
	 */
	public List<Plan> selectAll(Plan myplan);
	
}
