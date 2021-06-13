<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/adminHeader.jsp"/>
<link rel="stylesheet" href="/resources/css/notice.css" type="text/css">
<style type="text/css">
	.main{
		width : 100%;
		height : 100%;
	}
</style>

<script type="text/javascript">
$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

})

function ntcDelete( ntcNo ) {
	
	if(confirm("삭제 하시겠습니까?")) {
		$.ajax({
			type: "post"
			, url: "/notice/comment/delete"
			, dateType: "json"
			, data: { ntcNo: ntcNo }
			, success: function(data) {
				
				if( data.isNtc ) {
					$("[data-tab='tab-2']").html("공지사항 댓글 (" + data.cmtCnt + ")")
					$("[data-ntcNo='"+ntcNo+"']").remove();
				}
				
				
			}
			, error: function() {
				console.log("error")
				
	
			}
		
		})
	}	
}

function ntDelete( ntNo ) {
	
	if(confirm("삭제 하시겠습니까?")) {
		location.href='/notice/admin/delete?ntNo='+ntNo
	}
}

</script>

<div class="main">
	
	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">공지사항 (${paging.totalCount })</li>
		<li class="tab-link" data-tab="tab-2">공지사항 댓글(${cmtTotalCnt })</li>
		<li class="tab-link" data-tab="tab-3">신고 현황(${cmtrTotalCnt })</li>
	</ul>

	<div id="tab-1" class="tab-content current">
		<table class="table table-striped table-hover table-condensed" >
			<thead>
				<tr>
					<th style="width: 10%">글번호</th>
					<th style="width: 10%">구분</th>
					<th style="width: 20%">제목</th>
					<th style="width: 27%">본문</th>
					<th style="width: 10%">작성일</th>
					<th style="width: 10%">수정일</th>
					<th style="width: 7%">조회수</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${ntList }" var="notice">
					<tr>
						<td>${notice.ntNo }</td>
						<td>${notice.ntDiv }</td>
						<td><a href="/notice/view?ntNo=${notice.ntNo }">${notice.ntTitle }</a></td>
						<td>${notice.ntContent }</td>
						<td><fmt:formatDate value="${notice.ntCtime }" pattern="yy-MM-dd" /></td>
						<td><fmt:formatDate value="${notice.ntUtime }" pattern="yy-MM-dd"/></td>
						<td>${notice.hit }</td>
						<td><button onclick="ntDelete(${notice.ntNo})" >삭제</button></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
		<jsp:include page="/WEB-INF/views/util/noticeAdminPaging.jsp" />
		
	</div>
	
	<div id="tab-2" class="tab-content">
		<table class="table table-striped table-hover table-condensed" >
			<thead>
				<tr>
					<th style="width: 10%">댓글번호</th>
					<th style="width: 10%">공지글번호</th>
					<th style="width: 22%">본문</th>
					<th style="width: 11%">작성일</th>
					<th style="width: 11%">수정일</th>
					<th style="width: 10%">좋아요</th>
					<th style="width: 10%">싫어요</th>
					<th style="width: 10%">신고수</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${noticeCommentList }" var="cmtList">
					<tr data-ntcno="${cmtList.ntcNo }" >
						<td>${cmtList.ntcNo }</td>
						<td>${cmtList.ntNo }</td>
						<td>${cmtList.ntcContent }</td>
						<td><fmt:formatDate value="${cmtList.ntcCtime }" pattern="yy-MM-dd" /></td>
						<td><fmt:formatDate value="${cmtList.ntcUtime }" pattern="yy-MM-dd"/></td>
						<td>${cmtList.likeCnt }</td>
						<td>${cmtList.disLikeCnt }</td>
						<td>${cmtList.reportCnt }</td>
						<td><button onclick="ntcDelete( ${cmtList.ntcNo } )" >삭제</button></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	
	</div>
	
	<div id="tab-3" class="tab-content">
	
		<table class="table table-striped table-hover table-condensed" >
			<thead>
				<tr>
					<th style="width: 10%">댓글번호</th>
					<th style="width: 26%">신고한 아이디</th>
					<th style="width: 26%">신고 내용</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${ntcReport }" var="reportList">
					<tr data-ntcno="${cmtList.ntcNo }" >
						<td>${reportList.ntcNo }</td>
						<td>${reportList.userId }</td>
						<td>${reportList.ntcrContent }</td>
					</tr>
				</c:forEach>
				
			</tbody>
			
		</table>
	</div>

</div>
<c:import url="/WEB-INF/views/layout/adminFooter.jsp"/>