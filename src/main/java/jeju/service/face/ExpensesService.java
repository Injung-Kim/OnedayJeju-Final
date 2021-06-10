package jeju.service.face;

import java.util.HashMap;
import java.util.List;

import jeju.dto.Expenses;
import jeju.dto.Plan;

public interface ExpensesService {

	
	/**
	 * 일정 리스트 조회
	 * 
	 * @return 일정 리스트
	 */
	public List<Plan> getPlanList();


	/**
	 * 얻어온 일정번호에 해당하는 경비 리스트 조회
	 * 
	 * @param pNo - 조회할 기준이 되는 일정번호
	 * @return 일정번호와 일치하는 경비 리스트
	 */
	public List<HashMap<String, Object>> getExpList(int pNo);
	
	
	/**
	 * 선택된 일정에 대한 여행날짜(p_departure_date)와 여행일(p_num_days) 얻어오기
	 * 
	 * @param pNo - 선택된 일정의 일정번호
	 * @return 일정번호와 일치하는 여행날짜, 여행일 리스트
	 */
	public List<HashMap<String, Object>> getPlanData(Plan pNo);
	
	
	/**
	 * 경비 작성하기
	 * 
	 * @param add - 작성된 경비 객체
	 */
	public void addExp(Expenses add);
	
	
	/**
	 * 경비 내역 상세보기
	 * 
	 * @param expno - 선택된 경비 번호
	 * @return 경비 번호(expno)에 해당하는 경비 정보 객체
	 */
	public Expenses viewExp(int eno);
	
	
	/**
	 * 경비 삭제하기
	 * 
	 * @param expno - 삭제하려는 경비 번호
	 */
	public void delExp(int expno);
	
	
	/**
	 * 경비 차트 현황 조회하기
	 * 
	 * @return 경비 차트 현황 리스트 객체
	 */
	public List<HashMap<String, Object>> getExpStat(int pNo);
	

}
