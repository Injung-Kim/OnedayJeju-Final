<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">

.listWrapper{
	width : 970px;
/* 	border : 1px solid #ccc; */
}
.qstTitle{
	width : 400px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
.searchTag{
	width : 20%;
	height : 51px;
	margin-bottom : 12px;
	float : right;
}
.searchTag #inputTag{
	border : 1px solid #ccc;
	width : 80%;
	height : 49px; 
	padding : 15px 10px;
}
.searchTag button{
	float:right;
	border : none;
	width : 20%;
	height : 49px;
	background: #14768f;
}
input[id="inputTag"]:focus{
	outline: none;
}

</style>
<c:import url="/WEB-INF/views/layout/adminHeader.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('.deleteQst').click(function(){
		
		var check = confirm('이 게시글을 삭제하시겠습니까?')
		if(check){
			$(this).parent().parent().find('input[name="qstNo"]').attr("checked", "checked")
			console.log("이 게시글 삭제함" + $('input[name="qstNo"]').val())
			$('.deleteForm').submit()
		}
	})
})
</script>
<div class="listWrapper">
	<form>
	<div class="searchTag">
	<input type="text" id="inputTag" name="word" placeholder="태그를 검색하세요">
	<button><i class="fas fa-search" style="color : #fff;"></i></button>
	</div>
	</form>
	<h2>QnA목록</h2>
	<hr>
	<table class="table table-striped " style="table-layout: fixed;">
		<thead>
		<tr>
			<th style="width : 10%;">번호</th>
			<th style="width : 45%;">제목</th>
			<th style="width : 15%;">작성일</th>
			<th style="width : 15%;">태그명</th>
			<th style="width : 8%;">조회수</th>
			<th style="width : 7%;">삭제</th>
		</tr>
		</thead>
		<tbody>
	<form action="/admin/qna/delete" method="post" class="deleteForm">
		<c:forEach items="${questionList }" var="qst">
		<tr>
			<td style="text-align: center;">
			<input type="checkbox" value="${qst.qstNo}" name="qstNo" style="display : none;"/>
			${qst.qstNo}
			</td>
			<td>
			<div class="qstTitle"><a href="/admin/qna/view?qstNo=${qst.qstNo}">${qst.title }</a></div>
			<div class="qstContent">${list.qstContent}</div>
			</td>
			<td><fmt:formatDate value="${qst.qstTime}" pattern="yyyy-MM-dd"/></td>
			<td>
			<c:forEach items="${qst.qtags}" var="tagName">
			#${tagName.TAG_NAME}
			<input type="checkbox" name="tagNo[]" value="${tagName.TAG_NO}" style="display : none;"/>
			</c:forEach>
			</td>
			<td style="text-align : center;">${qst.qstCnt}</td>
			<td><button type="button" class="btn btn-danger btn-sm deleteQst">삭제</button></td>
		</tr>
		</c:forEach>
	</form>
		</tbody>
	
	</table>
</div><!--listWrapper  -->
<c:import url="/WEB-INF/views/admin/qna/adminQstPaging.jsp"/>
<c:import url="/WEB-INF/views/layout/adminFooter.jsp"/>
</body>
</html>