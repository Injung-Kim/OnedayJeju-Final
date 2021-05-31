<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" href="/resources/css/notice.css" type="text/css">



<div class="container">
	
	<div class="view_box bor_rad_five">
	
		<div class="top">
			<h3 class="sub">${notice.ntTitle }</h3>
		</div>
	
		<div class="view_info">
				<ul>
					<li>${notice.ntNo }</li>
					<li>|</li>
					<li>${notice.ntDiv }</li>
					<li>|</li>
					<li><fmt:formatDate value="${notice.ntUtime }" pattern="yy-MM-dd HH:mm:ss"/></li>
					<li>조회수</li>
					<li>|</li>
					<li>${notice.hit }</li>
				</ul>
		</div>
		
		<div class="con">${notice.ntContent }</div>
	
	</div>
	
	<div class="rel mid">
		<button>첨부 파일</button>
		<button class="abs list_btn box_shadow bor_rad_ten" onclick="location.href='/notice/list'">목록</button>
		<button class="abs update_btn box_shadow bor_rad_ten" onclick="location.href='#'">수정</button>
		<button class="abs delete_btn box_shadow bor_rad_ten"onclick="location.href='#'">삭제</button>
	</div>
	
	<div class="rel cmt_box bor_rad_five">
		<div class="cnt_box">
			<img src="/resources/image/user.png">
			<span>${id }</span><br>
			<textarea rows="3" cols="30" id="content" name="content"></textarea>
		</div>
			<hr style="margin: 0">
			<span id="content_cnt"> 0 / 300 </span>
			<button class="abs write_btn box_shadow bor_rad_ten" id="cmtbtn">등록</button>
	</div>

</div><!-- .container -->






<c:import url="/WEB-INF/views/layout/footer.jsp" />
