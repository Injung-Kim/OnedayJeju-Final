<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.listWrapper{
	width : 1000px;
}
.qstList{
	box-sizing : border-box;
	border : 1px solid #49c6e5;
	border-radius : 15px;
	padding : 10px 20px;
	background: #fffbfa;
	/* height: 155px; */
	margin-top: 18px;
	transition: border 0.2s;
}
.blockQst{
	float : right;
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
	width : 15%;
	height : 51px;
	margin-bottom : 12px;
}
.searchTag #inputTag{
	border : 1px solid #ccc;
	width : 80%;
	height : 49px; 
	padding : 15px 10px;
	border-left-radius : 15px;
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
	$('.blockQst').click(function(){
		
	})
})

</script>
<div class="listWrapper">
	<form>
	<div class="searchTag" align = "right">
	<input type="text" id="inputTag" name="word" placeholder="태그를 검색하세요">
	<button><i class="fas fa-search" style="color : #fff;"></i></button>
	</div>
	</form>
	<c:forEach items="${questionList }" var="qst">
		<div class="qstList" onclick="location.href='/admin/qna/view?qstNo=${qst.qstNo}'">
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
		<!-- 질문글 차단 -->
		<!-- <div style="height : 15px;"><button type="button" class="blockQst btn" style="background : transparent;">차단하기</button></div> --> 
	</c:forEach>
</div><!--listWrapper  -->
<c:import url="/WEB-INF/views/admin/qna/adminQstPaging.jsp"/>
<c:import url="/WEB-INF/views/layout/adminFooter.jsp"/>
</body>
</html>