<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style type="text/css">
/*질문글 css*/
.qstWrapper{
	width : 100%;
	border-top : 3px solid #49c6e5;
	border-bottom : 3px solid #49c6e5;
	border-radius : 25px;
	background: #fffbfa;
}
.question{
	width : 80%;
	text-align: right;
	padding : 15px;
}
.question .qstInfo{
	float : right;
	margin : 0 0 0 40px;
}
.question #content{
	text-align : left;
	line-height :  27px;
}
.question .qstInfo #qstCnt{
	margin-right : 18px;
	color : #00a388;
}
.question .tag span{
	line-height : 85px;
}
.makeAnswer{
	background: #00bd9d;
	color : #fffbfa;
}
/*답변글 css  */
.ansWrapper{
	width : 100%;
	margin-top : 20px;
}
.answers{
	width : 80%;
	border : 1px solid #49c6e5;
	height : 350px;
	border-radius: 15px;
	margin-bottom : 25px;
	padding: 15px;
	text-align: right;
}
.answers #ansInfo{
	text-align: left;
}
.answers #userid{
	line-height: 56px;
   	vertical-align: text-top;
   	font-size: 23px;
}

.answers #divider{
	margin-top : 5px;
	margin-bottom : 20px;
}
.ansContent{
	height : 53%;
	text-align: left;
}
/* 질문글 작성 폼 */
.writeAnswer{
	width : 80%;
	border-radius: 15px;
	/* background: #00a388; */
	border : 1px solid #ccc;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.deleteQst').click(function(){
		var confirmDelete = confirm('삭제하시겠습니까?')
		if(confirmDelete){location.href="/qna/delete/question?qstNo=${QST_NO}"}
	})
	$('.makeAnswer').click(function(){
		var login = ${login}
		if(login){
			$('writeAnswer').attr('style','display : block;')
		}else{
			alert('로그인이 필요합니다')
			location.href='/member/login'
		}
	})
	$('.ansImg').click(function(){
		$('#ansFiles').click()
	})
	$('#submitAnswer').click(function(){
		$('#ansForm').submit()
	})
	
})
</script>
<!-- 질문글 -->
<div class="qstWrapper">
<div class="question">
	<p>${QST_NO}</p>
	<!-- 기본정보(조회수, 작성자 아이디) -->
	<div id="content">
		<div class="qstInfo">
			<span id="qstCnt">조회수 ${QST_CNT}</span>
			<img src="/resources/image/user.png" alt="profile" style="width: 45px; height : 45px;"/> 
			<span style="line-height : 45px;">${USER_ID}</span>
		</div>
		<h4 style="font-weight : bold; font-size : 22px;">${TITLE}</h4>
		<p style="margin-top: 30px;">${QST_CONTENT}</p>
		<c:forEach items="${files}" var="file">
		<img src="/qna/${file.get('QST_STORED')}" style="width : 200px; height : 200px;"/>
		</c:forEach>
	</div>
<!-- 해시태그  -->
	<div class="tag">
		<c:forEach items = "${tagList}" var="tag">
			<span>#${tag.get("TAG_NAME")}</span>
		</c:forEach>
	</div>
	<!-- 작성자와 로그인한 아이디 비교하여 버튼 구분하기  -->
	<c:choose>
		<c:when test="${id eq USER_ID}">
		<button type="button" class="btn updateQst" data-toggle="modal" data-target="#updateQstModal" id="updateQst">수정</button>
		<button type="button" class="btn deleteQst">삭제</button>
		</c:when>
		<c:otherwise>
		<button type="button" class="btn btn-lg makeAnswer">답변하기</button>
		</c:otherwise>
	</c:choose>
</div>
</div>
<div class="ansWrapper">
 <h4>${ansTotal}개의 답변</h4>
 <hr>
<!-- 답변글 -->
	<c:forEach items="${answers}" var="answer" >
	<div class="answers">
		<div class="float" style="float : right;">
		<span style="margin-right : 15px;">좋아요 ${answer.get("ANS_LIKE") }</span>
		<p style="float:right;"><fmt:formatDate value="${answer.get('ANS_TIME')}" pattern="yyyy-MM-dd hh:mm:ss"/> </p>
		</div>
		<div id="ansInfo">
		<img src="/resources/image/user.png" alt="profile" style="width: 62px; height : 62px;"/> 
		<span id="userid">${answer.get("USER_ID")}님의 답변</span>
		</div>
		<hr id="divider">
		<!-- 답변글 본문  -->
		<div class="ansContent">${answer.get("ANS_CONTENT")}</div>
		<c:if test="${id eq answer.get('USER_ID')}">
		<button type="button" class="btn">수정</button>
		<button type="button" class="btn">삭제</button>
		</c:if>
	</div>
	</c:forEach>
	<!--답변글 작성 Form  -->
	<div class="writeAnswer">
	<h3>답변하기</h3>
	<form action="/qna/write/answer" method="post" id="ansForm">
	<textarea rows="6"  placeholder="내용을 입력하세요" class="form-control"></textarea>
	<input type="file" name="files" multiple="multiple" style="display:none;" id="ansFiles">
	<input type="hidden" name="qstNo" value="${QST_NO}"/>
	<img src="/resources/image/image.png" style="width : 80px; height : 80px;" class="ansImg">
	<button id="submitAnswer" type="button" class="btn btn-lg btn-block">답변하기</button>
	</form>
	</div>
	
</div>
<c:import url="/WEB-INF/views/qna/qstUpdateModal.jsp"/>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>