<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.listWrapper{
	width: 60%;
}
.qstList{
	box-sizing : border-box;
	border : 1px solid #8bd7d2;
	border-radius : 15px;
	padding : 10px 20px;
	background: #fffbfa;
	/* height: 155px; */
	margin-top: 18px;
	transition: border 0.2s;
}

.qstList:hover{
	border : 2px solid #49c6e5;
	cursor : pointer;
}
/*시간, 번호정보*/
.qstInfo{
	display : inline-block;
	width : 30%;
}
.qstInfo span{
	margin-right : 20px;
}

.qstInfo p{ 
	font-size: 12px;
	margin : 0px;
}
/* 내용 간략히 */
.content{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	font-size: 12px;
	min-height : 42px;
    width: 80%;
    margin: 11px 0px;
}
.tagList{
	text-align: right;
	color : #00bd9d;
	display : inline-block;
	width : 79%;
}
.tagList span{
	padding : 0px 0px 0px 10px;
}
.searchTag{
	width : 50%;
	height : 51px;
	margin-bottom : 12px;
}
.searchTag #inputTag{
	border : 1px solid #ccc;
	width : 90%;
	height : 49px; 
	padding : 15px 10px;
	border-left-radius : 15px;
}
.searchTag button{
	float:right;
	border : none;
	width : 10%;
	height : 49px;
	background: #14768f;
}
input[id="inputTag"]:focus{
	outline: none;
}
</style>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#writeQst').click(function(){
		console.log("클릭함")
		//로그인 상태 구분하여 작성 폼 모달 로드하기
		var login = ${sessionScope.login}
		console.log(login == null)
		if(login == null){
			alert("로그인이 필요합니다")
			location.href='/member/login'
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
	<!-- 버튼 클릭시 질문글 작성 모달창 열기  -->
	<button type="button" class="btn btn-lg btn-block" data-toggle="modal" data-target="#writeQstModal" id="writeQst">질문하기</button>
	<c:forEach items="${questionList }" var="qst">
		<div class="qstList" onclick="location.href='/qna/view?qstNo=${qst.qstNo}'">
			<h4 style="display : inline-block; width : 69%">${qst.title}</h4> 
			<div  align = "right" class="qstInfo">
				<span><i class="fas fa-pencil-alt"></i> ${qst.userId}</span>
				<span><i class="far fa-eye"></i> ${qst.qstCnt}</span>
				<span style="margin : 0px; font-size: small;">${qst.qstNo}</span>
			</div>
			<div class="content">${qst.qstContent }</div>
			<span id="time"><fmt:formatDate value="${qst.qstTime}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
			<div class="tagList">
			<c:if test="${qst.qtags.size() ne 0 }">
			<c:forEach items="${qst.qtags }" var="qtag">
				<span>#${qtag.TAG_NAME }</span>
			</c:forEach>
			</c:if>
			</div>
		</div><!--qstList  -->
	</c:forEach>
</div><!--listWrapper  -->
<c:import url="/WEB-INF/views/qna/questionModal.jsp"/>
<c:import url="/WEB-INF/views/qna/QuestionPaging.jsp"/>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>