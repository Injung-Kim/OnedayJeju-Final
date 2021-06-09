<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


		<table class="table modal-table">
			<tr>
				<td style="width: 35%"><label>지출 날짜</label></td>
				<td><fmt:formatDate value="${view.expDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td><label>카테고리</label></td>
				<td>${view.category}</td>
			</tr>
			<tr>
				<td><label>상호명(상세정보)</label></td>
				<td>${view.expName}</td>
			</tr>
			<tr>
				<td><label>사용 금액</label></td>
				<td><fmt:formatNumber value="${view.cost}" pattern="#,###원" /></td>
			</tr>
			<tr>
				<td><label>메모</label></td>
				<td>${view.memo}</td>
			</tr>
		</table>



