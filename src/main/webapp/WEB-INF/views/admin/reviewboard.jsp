<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/adminHeader.jsp"/>
<style type="text/css">
.main{
	width : 970px;
	min-height : 700px;
}
table {
	width: 100%;
}
table, th, td {
	border: 1px solid #ccc;
	
}
.table_list {
	margin: 10px 0;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
	//체크박스가 체크가 되었을 때 삭제버튼 활성화
	$("input[type=checkbox]").click(function(){
		var tmp = null;
		$("input[name=chk]").each(function (index, item){
			if( $(item).prop('checked') ){
				tmp = true;
				return false;
			}
		})
		
		var cnt = $("input[name=chk]:checked").length;
		if(tmp==true || cnt>0){
		    $("#deleteBtn").prop("disabled",false);
		} else {
		    $("#deleteBtn").prop("disabled",true);
		}
	})
})
</script>
<div class="main">
	
	<h1>여행후기</h1>
	<hr>
<!-- 	<div class="search_box"> -->
<!-- 		<form class="form-inline" action="/admin/reviewboard" method="get"> -->
<!-- 			<button class="btn btn-default pull-right" id="searchBtn">검색</button> -->
<!-- 			<input class="form-control pull-right" type="text" placeholder="통합검색" id="searchInput" name="search"> -->
<!-- 		</form> -->
<!-- 	</div> -->
	<div class="clearfix"></div>
	
	<form action="/admin/reviewboard/delete" method="post">
	<div class="table_list">
		<table class="table table-striped">
			<thead>
				<tr>
					<th style="width: 4%">선택</th>
					<th style="width: 8%">글 번호</th>
					<th style="width: 25%">글 제목</th>
					<th style="width: 12%">아이디</th>
					<th style="width: 12%">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reviewboard" items="${rvList }">
				<tr>
					<td><input type="checkbox" name="chk" value="${reviewboard.rvNo }"></td>
					<td>${reviewboard.rvNo }</td>
					<td><a href="/review/view?rvNo=${reviewboard.rvNo }" target="_blank">${reviewboard.rvTitle }</a></td>
					<td>${reviewboard.userId }</td>
					<td><fmt:formatDate value="${reviewboard.rvCreateDate }" pattern="yyyy.MM.dd HH:mm"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<button class="btn btn-danger" id="deleteBtn" disabled="disabled" onclick="javascript:confirm('정말로 삭제하시겠습니까?')">선택글 삭제</button>
	</div>
	</form>
	
	<div class="page_box"> 
		<c:import url="/WEB-INF/views/admin/rvPaging.jsp" />
	</div>
	
</div><!-- .main 끝 -->
<c:import url="/WEB-INF/views/layout/adminFooter.jsp"/>