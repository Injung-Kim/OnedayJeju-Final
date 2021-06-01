<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" href="/resources/css/notice.css" type="text/css">

<script type="text/javascript">
$(document).ready(function() {
	
	//검색 버튼 클릭
	$("#btnSearch").click(function() {
		location.href="/notice/list?condition="+$("#condition").val()+ "&search="+ $("#search").val();
	});
	
})
</script>

<div class="container">
	
	<div class="rel write_search_box">
	
		<div class="rel write_box">
			<button class="write_btn box_shadow bor_rad_ten" onclick="location.href='/notice/write'">글 작성</button>
		</div>
	
		<div class="rel search_box">
			<select class="abs bor_rad_ten box_shadow" id="condition" name="condition">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_and_content">제목+내용</option>
			</select>
	
			<input class="abs bor_rad_ten box_shadow" type="text" id="search" name="search" />
			<button class="abs search_btn box_shadow bor_rad_ten" id="btnSearch">검색</button>
		</div>	
	
	
	
	</div><!-- .rel_write_search_box -->
	
	<c:forEach items="${ntList }" var="notice">
	
		<div class="list_box bor_rad_five">
		
			<div class="title_box">
				<a href="/notice/view?ntNo=${notice.ntNo }">${notice.ntTitle }</a>
				<span>#</span>
			</div>
		
			<div class="info">
				<ul>
					<li>${notice.ntNo }</li>
					<li>|</li>
					<li>${notice.ntDiv }</li>
					<li>|</li>
					<li><fmt:formatDate value="${notice.ntUtime }" pattern="yy-MM-dd HH:mm:ss"/></li>
					<li>|</li>
					<li>${notice.hit }</li>
				</ul>
			</div>
		
		</div><!-- .listbox -->
	</c:forEach>

<%-- 페이징 JSP --%>
<jsp:include page="/WEB-INF/views/util/noticePaging.jsp" />

</div><!-- .container -->



<c:import url="/WEB-INF/views/layout/footer.jsp" />
