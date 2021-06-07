<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.listWrapper{
	width: 80%;
}
.qstList{
	box-sizing : border-box;
	border : 1px solid #49c6e5;
	border-radius : 15px;
	padding : 15px 20px;
	background: #fffbfa;
	height: 155px;
	margin-top: 18px;
	transition: border 0.2s;
}
.qstList:hover{
	border : 2px solid #49c6e5;
	cursor : pointer;
}
/*시간, 번호정보*/
.qstInfo{
	display : flex;
	flex-flow: row nowrap;
	justify-content: space-between;
}

.qstInfo p{ 
	font-size: 12px;
	margin : 0px;
}
/* 내용 간략히 */
#content{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	font-size: 12px;
    width: 80%;
    margin: 0px;
}

</style>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#writeQst').click(function(){
		console.log("클릭함")
		//로그인 상태 구분하여 작성 폼 모달 로드하기
		
		if(${sessionScope.login eq null}){
			alert("로그인이 필요합니다")
			location.href='/member/login'
			}
		
		
	})
})
</script>
<div class="listWrapper">
	<!-- 버튼 클릭시 질문글 작성 모달창 열기  -->
	<button type="button" class="btn btn-lg btn-block" data-toggle="modal" data-target="#writeQstModal" id="writeQst">질문하기</button>
	<c:forEach items="${questionList }" var="qst">
		<div class="qstList" onclick="location.href='/qna/view?qstNo=${qst.qstNo}'">
			<div  align = "right" class="qstInfo">
				<p id="time"><fmt:formatDate value="${qst.qstTime}" pattern="yyyy-MM-dd hh:mm:ss"/></p>
				<p>${qst.qstNo}</p>
			</div>
			<h4>${qst.title}</h4> 
			<div id="content">${qst.qstContent }</div>
		</div><!--qstList  -->
	</c:forEach>
</div><!--listWrapper  -->
<c:import url="/WEB-INF/views/qna/questionModal.jsp"/>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>