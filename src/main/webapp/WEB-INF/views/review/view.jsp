<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:import url="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
  $(document).ready(function() { 
//   	if(${isRecommend}) {
// <!--  		$("#btnRecommend") -->
// <!--  			.addClass("btn-warning") -->
// // <!--  			.html('추천 취소'); -->
// <!--  	} else { -->
// <!--  		$("#btnRecommend") -->
// <!--  			.addClass("btn-primary") -->
// <!--  			.html('추천'); -->
// <!--  	} -->

// <!--  	$("#btnRecommend").click(function() { -->

// <!--  		$.ajax({ -->
// <!--  			type: "get" -->
// <!--  			, url: "/board/recommend" -->
<%-- <%--  			, data: { "boardNo": '${view.boardNo }' }  --%>
<%-- <!--  			, dataType: "json" --> --%>
<%-- <!--  			, success: function( data ) { --> --%>
<%-- <!--  					console.log("성공"); --> --%>

<%-- <!--  				if( data.result ) { 추천 성공 --> --%>
<%-- <!--  					$("#btnRecommend") --> --%>
<%-- <!--  					.removeClass("btn-primary") --> --%>
<%-- <!--  					.addClass("btn-warning") --> --%>
<%-- <!--  					.html('추천 취소'); --> --%>

<%-- <!--  				} else { 추천 취소 성공 --> --%>
<%-- <!--  					$("#btnRecommend") --> --%>
<%-- <!--  					.removeClass("btn-warning") --> --%>
<%-- <!--  					.addClass("btn-primary") --> --%>
<%-- <!--  					.html('추천'); --> --%>

<%-- <!--  				} --> --%>

<%-- <!--  				추천수 적용 --> --%>
<%-- <!--  				$("#recommend").html(data.cnt); --> --%>
<%-- <!--  			} --> --%>
<%-- <!--  			, error: function() { --> --%>
<%-- <!--  				console.log("실패"); --> --%>
<%-- <!--  			} --> --%>
<%-- <!--  		}); ajax end --> --%>

<%-- <!--  	}); $("#btnRecommend").click() end --> --%>




<%-- //  댓글 입력  --%>
<%-- // $("#btnCommInsert").click(function() {   --%>
	
<%-- // 	console.log("입력됨"); --%>
	
<%-- // 	$form = $("<form>").attr({  action: "/comment/insert",  method: "post"  }).append( --%>
<%-- // 	 $("<input>").attr({  type:"hidden",  name:"rvNo", value:"${view.rvNo }"  })  ).append(  $("<textarea>") --%>
<%-- // 				.attr("name", "rvContent") --%>
<%-- //  				.css("display", "none") --%>
<%-- //  				.text($("#commentContent").val()) --%>
<%-- //  		); --%>
<%-- //  		$(document.body).append($form); --%>
<%-- //  		$form.submit(); --%>
		
	 		
 		
<%-- //  	}); //<!-- $("#btnCommInsert").click()  end --> --%>
	
<%-- // 	/review/delete?No=${view.rvNo } --%>
 	
<%-- //  	$(".upbtn").click(function(){ --%>
<%-- //  		if(confirm("수정하시겠습니까?")){ --%>
<%-- //  			//예 클릭시 이벤트 --%>
<%-- //  			location.href="/review/update?rvNo=${view.rvNo }"; --%>
 			
<%-- //  		}else{ --%>
<%-- //  			//취소 클릭시 이벤트 --%>
<%-- //  			return false; --%>
<%-- //  		}  --%>
<%-- //  	}); --%>

	
 }); // <!-- $(document).ready end -->
 
	
	
//  function deleteComment(rvCmtNo) {
//  	$.ajax({
//  		type: "post"
//  		, url: "/review/comment/delete"
//  		, dataType: "json"
//  		, data: {
//  			rvCmtNo: rvCmtNo
//  		}
//  		, success: function(data){
//  			if(data.success) {
				
//  				$("[data-commentno='"+rvCmtNo+"']").remove();
		
//  			} else {
//  				alert("댓글 삭제 실패");
//  			}
//  		}
//  		, error: function() {
//  			console.log("error");
//  		}
//  	});
//  };

	
</script>

<style type="text/css">
article {
	border: none;
}

.txBd {
	width: 1150px;
	border: 1px solid aqua;
	height: 1000px;
	padding: 20px;
}

.textBox {
	width: 100%;
}

.textBox td {
	width: 150px;
	text-indent: 20px;
}

.textBox .title {
	font-size: 25px;
	padding: 20px 0;
}

.textBox .infoBox {
	width: 800px;
	height: 50px;
	margin: 0 auto;
	line-height: 50px;
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	background-color: #f9f9f9;
}

.textBox .infoBox .tdT {
	width: 60px;
	text-align: right;
	color: #000;
}

.textBox .infoBox .rightText {
	width: 150px;
	color: #666;
}

.conBox {
	width: 900px;
	overflow: hidden;
}

.text-center .btnL {
	width: 100px;
	height: 40px;
	background-color: #8BD7D2;
	color: #fff;
	border: none;
	margin-top: 30px;
}

.conV img{width:100%; overflow:hidden;}
</style>

<div class="container">

<h1 class="tit1">게시글 상세보기</h1>
<!-- <button id="btnRecommend" class="btn pull-right">추천</button> -->

<hr>
<!-- <div class="txBd">게시글 박스 border -->
	<table class="textBox">
		<tr>
			<td class="title" colspan="3">${view.rvTitle }</td>
		</tr>
		<tr class="infoBox">
			<td class="tdT infoId">작성자</td>
					<td class="rightText">${view.userId }</td>
		
		<td class="info">추천수</td><td id="recommend">${cntRecommend }</td>
			<td class="tdT infoWrite">작성일</td>
					<td class="rightText"><fmt:formatDate value="${view.rvCreateDate }" pattern="yy-MM-dd" /></td>
		
			<td class="tdT infoHit">조회수</td>
				<td class="rightText">${view.rvHit }</td>
		</tr>
	</table>
	<hr>


	<div class="conBox">
				<!-- contentBox -->
		<div class="conV">${view.rvContent }</div>
				<!-- ContentView -->
	</div>
	<p class="gdBox">
		<input type="button" value="추천" class="good" onclick="gd_func"/>
	</p>
</div>

<!-- 버튼 영역 -->
<div class="text-center">
	<c:if test="${id eq view.userId }">
		<input type="button" class="btn btn-primary upbtn" value="수정" onclick="func_conf()">
		<input type="button" class="btn btn-danger debtn" value="삭제" onclick="func_del()">
	</c:if><br><br>
	<a href="/review/list"><button class="btnL">목록</button></a>
</div><!-- 버튼 영역 end -->
<script>
	function func_conf(){
		 console.log("클릭됨")
		 
		 if(confirm('수정하시겠습니까?')){
			 //예 클릭시 이벤트
			 location.href="/review/update?rvNo=${view.rvNo}";
			 
		 } else{
			 //취소 클릭시 이벤트
			 return false;
		 }
	}
	
	function func_del(){
		console.log("클릭되었습니다.")
		
		 if(confirm('삭제하시겠습니까?')){
			 //예 클릭시 이벤트
			 location.href="/review/delete?rvNo=${view.rvNo}";
			 
		 } else{
			 //취소 클릭시 이벤트
			 return false;
		 }
	}
</script>
<!-- 댓글 처리 -->
<hr>
<!-- <div> -->

<!-- 	<!-- 비로그인상태  --> 
<%-- 	<c:if test="${not login }"> --%>
<!-- 	<strong>로그인이 필요합니다</strong> -->
<!-- 				<br> -->
<!-- 	<button onclick='location.href="/member/login";'>로그인</button> -->
<!-- 	<button onclick='location.href="/member/join";'>회원가입</button> -->
<%-- 	</c:if> --%>
	
<!-- 	로그인상태 -->
	<c:if test="${login }">
<!-- 	댓글 입력 -->
	<div class="form-inline text-center">
		<input type="text" size="10" class="form-control" id="commentWriter"
						value="${id } " readonly="readonly" />
		<textarea rows="2" cols="60" class="form-control" id="commentContent"></textarea>
		<button id="btnCommInsert" class="btn" onclick="commentWrite()">입력</button>
	</div>	<!-- 댓글 입력 end  -->
	</c:if>
	
	<br><br>
	
<div id="commentBox">
	
</div>
<!-- <!-- 	댓글 리스트 -->
<!-- 	<table class="table table-striped table-hover table-condensed"> -->
<!-- 	<thead> -->
<!-- 	<tr> -->
<!-- 		<th style="width: 10%;">작성자</th> -->
<!-- 		<th style="width: 50%;">댓글</th> -->
<!-- 		<th style="width: 20%;">작성일</th> -->
<!-- 		<th style="width: 5%;"></th> -->
<!-- 	</tr> -->
<!-- 	</thead> -->
<!-- 	<tbody id="commentBody"> -->
<%-- 	<c:forEach items="${commentList }" var="rvComment"> --%>
<%-- 	<tr data-commentno="${rvComment.rvCmtNo }"> --%>
<%-- 		<td style="width: 10%;">${rvComment.userId }</td> --%>
<!-- <!-- 							닉네임으로 해도 좋음 --> 
<%-- 		<td style="width: 50%;">${rvComment.rvContent }</td> --%>
<%-- 		<td style="width: 20%;"><fmt:formatDate	value="${rvComment.rvCreateDate }" pattern="yy-MM-dd" /></td> --%>
<!-- 		<td style="width: 5%;"> -->
<!-- 			<div> -->
<%-- 				<c:if test="${sessionScope.id eq rvComment.userId }"> --%>
<%-- 					<button id="modbtn" class="modbtnc" onclick="updateComment(${rv.Comment.rvCmtNo});">수정</button> --%>
<%-- 					<button class="btn btn-default btn-xs" onclick="deleteComment(${rvComment.rvCmtNo });">삭제</button> --%>
<%-- 				</c:if> --%>
<!-- 			</div> -->
<!-- 		</td> -->
		
<!-- 	</tr> -->
<%-- 	</c:forEach> --%>
	
<!-- 			</table>	댓글 리스트 end -->

<!-- </div> 댓글 처리 end -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>

<script>

$(document).ready(function(){

	commentList();
	
	
});


function commentWrite() {
   
   $.ajax({
      type : 'get'
      , url : '/review/comment/insert'
      , data : {'rvNo' : '${view.rvNo}'
            , 'rvContent' : $("#commentContent").val()}
      , success : function() {
         
         $("#commentBox").html("");
         $("#commentContent").val("");
         commentList();
      }
   })
}

function commentList() {
   $.ajax({
      type : 'get'
      , url : '/review/comment/list'
      , data : {'rvNo' : '${view.rvNo }'}
      , dataType : 'json'
      , success : function(data) {
         
         for(var i=0; i<data.length; i++) {

            var time = new Date(data[i].writeDate);
            
            var content = "";
            
            content += '<ul class="list-group list-group-flush">'
            content += '<li class="list-group-item">'
            content += '<div class="form-inline mb-2">'
            content += '<label><i class="fa fa-user-circle-o fa-2x"></i></label>'
            content += ' ' + data[i].userId
            content += ' ' + moment(data[i].rvCreateDate).format('YYYY-MM-DD');
            content += "<div id='commentTextWrap" + data[i].rvCmtNo + "'>"
            if( data[i].id == $("#userid").val() ) {
               content += "<input type='button' onclick='commentUpdate(" + data[i].rvCmtNo + ")' value='수정' class='commentBtn' style='color:blue;'/>"  
               content += "<input type='button' onclick='commentCancel(" + data[i].rvCmtNo + ")' value='삭제' class='commentBtn' style='color:red;'/> <br>" 
            } else {
               content += "<br>"
            }
            content += "<span id='commentText" + data[i].rvCmtNo + "'>" + data[i].rvContent + "</span>"
            content += "</div>"
            content += "</li>"
            content += "</ul>"
            
            $("#commentBox").append(content);
         }
      }
   })
}


function commentCancel(rvCmtNo) {
   
   var conCancel = confirm('정말 삭제하시겠습니까?');
   
   if(conCancel) {
   
      $.ajax({
         type : 'get'
         , url : '/review/comment/cancel'
         , data : {'rvNo' : '${view.rvNo}'
               , 'rvCmtNo' : rvCmtNo}
         , success : function() {
            
            $("#commentBox").html("");
            commentList();
         }
      })
   }
}

function commentUpdate(rvCmtNo) {
   
   var commentText = $("#commentText" + rvCmtNo).text();
   
   var updateWrap = "";
   
   updateWrap += "<input type='text' id='commentUpdateBox' value='"+ commentText +"'/>"
   updateWrap += "<input type='button' class='comupbtn' id='updateConfirm' onclick='commentUpdateCommit(" + rvCmtNo + ")' value='수정'/>"
   updateWrap += "<input type='button' class='comupbtn' id='canclebtn' onclick='commentUpdateCancel()' value='취소'/>"
   
   $("#commentTextWrap"+rvCmtNo).html(updateWrap);
   
}

function commentUpdateCommit(rvCmtNo) {
   
   var commentText = $("#commentUpdateBox").val();
   
   $.ajax({
      type : 'get' //HTTP 요청 방식 
      , url : '/review/comment/update' // 클라이언트가 요청을 보낼 서버의 URL 주소
      , data : {'rvCmtNo' : rvCmtNo // HTTP 요청과 함께 서버로 보낼 데이터
            , 'rvContent' : commentText}
      , success : function() {
         
         $("#commentBox").html("");
         commentList();
      }
   })
}

function commentUpdateCancel() {
   
   $("#commentBox").html("");
   commentList();
   
}
</script>

</div><!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
