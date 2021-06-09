<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


		<table class="table modal-table">
			<tr>
				<td style="width: 35%"><label for="expDate">지출 날짜 선택 <strong style="color:red">*</strong></label></td>
				<td>
					<select id="expDate" name="expDate">
						<c:forEach var="p" begin="0" end="${pData[0].P_NUM_DAYS -1 }">							
						<option>
							<c:set var="time" value="${pData[0].P_DEPARTURE_DATE.getTime() + p*24*60*60*1000 }" />
							<c:set var="date" value='<%=new Date((long) pageContext.getAttribute("time")) %>' />
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
						</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="category">카테고리 <strong style="color:red">*</strong></label></td>
				<td>
					<select id="category" class="category">
						<option value="">--- 카테고리를 선택해주세요 ---</option>
						<option value="식비">식비</option>
						<option value="교통비">교통비</option>
						<option value="숙박비">숙박비</option>
						<option value="쇼핑">쇼핑</option>
						<option value="관광지 입장료">관광지 입장료</option>
						<option value="기타">기타</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="expName">상호명(상세정보) <strong style="color:red">*</strong></label></td>
				<td><input type="text" id="expName" name="expName" /></td>
			</tr>
			
			<tr>
				<td><label for="cost">사용 금액 <strong style="color:red">*</strong></label></td>
				<td><input type="number" id="cost" name="cost" min="100" step="100" /></td>
			</tr>
			
			<tr>
				<td><label for="memo">메모</label></td>
				<td><textarea id="memo" name="memo"></textarea></td>
			</tr>
		
		</table>
		<div>
			<p>필수 입력사항(*)을 입력하지 않으면 반영되지 않습니다!</p>
			<p>사용 금액은 숫자만 입력해주세요</p>
		</div>
				