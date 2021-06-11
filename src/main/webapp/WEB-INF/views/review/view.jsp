<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:import url="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
  $(document).ready(function() { 
<%-- <%--  	if(${isRecommend}) { --%>
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
<%-- <%--  			, data: { "boardNo": '${view.boardNo }' } --%> 
// <!--  			, dataType: "json" -->
// <!--  			, success: function( data ) { -->
// <!--  					console.log("성공"); -->

// <!--  				if( data.result ) { 추천 성공 -->
// <!--  					$("#btnRecommend") -->
// <!--  					.removeClass("btn-primary") -->
// <!--  					.addClass("btn-warning") -->
// <!--  					.html('추천 취소'); -->

// <!--  				} else { 추천 취소 성공 -->
// <!--  					$("#btnRecommend") -->
// <!--  					.removeClass("btn-warning") -->
// <!--  					.addClass("btn-primary") -->
// <!--  					.html('추천'); -->

// <!--  				} -->

// <!--  				추천수 적용 -->
// <!--  				$("#recommend").html(data.cnt); -->
// <!--  			} -->
// <!--  			, error: function() { -->
// <!--  				console.log("실패"); -->
// <!--  			} -->
// <!--  		}); ajax end -->

// <!--  	}); $("#btnRecommend").click() end -->




//  댓글 입력 
$("#btnCommInsert").click(function() {  
	
	console.log("입력됨");
	
	$form = $("<form>").attr({  action: "/comment/insert",  method: "post"  }).append(
	 $("<input>").attr({  type:"hidden",  name:"rvNo", value:"${view.rvNo }"  })  ).append(  $("<textarea>")
				.attr("name", "rvContent")
 				.css("display", "none")
 				.text($("#commentContent").val())
 		);
 		$(document.body).append($form);
 		$form.submit();
		
	 		
 		
 	}); //<!-- $("#btnCommInsert").click()  end -->
	
 }); // <!-- $(document).ready end -->

 function deleteComment(rvCmtNo) {
 	$.ajax({
 		type: "post"
 		, url: "/comment/delete"
 		, dataType: "json"
 		, data: {
 			rvCmtNo: rvCmtNo
 		}
 		, success: function(data){
 			if(data.success) {
				
 				$("[data-commentno='"+rvCmtNo+"']").remove();
		
 			} else {
 				alert("댓글 삭제 실패");
 			}
 		}
 		, error: function() {
 			console.log("error");
 		}
 	});
 };
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
</style>

<div class="container">

<h1 class="">게시글 상세보기</h1>
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
</div>

<!-- 버튼 영역 -->
<div class="text-center">
	<a href="/review/list"><button class="btnL">목록</button></a>
	<c:if test="${id eq view.userId }">
		<a href="/review/update?rvNo=${view.rvNo }"><button
						class="btn btn-primary">수정</button></a>
		<a href="/review/delete?No=${view.rvNo }"><button
						class="btn btn-danger">삭제</button></a>
	</c:if>
</div><!-- 버튼 영역 end -->

<!-- 댓글 처리 -->
<hr>
<div>

	<!-- 비로그인상태  -->
	<c:if test="${not login }">
	<strong>로그인이 필요합니다</strong>
				<br>
	<button onclick='location.href="/member/login";'>로그인</button>
	<button onclick='location.href="/member/join";'>회원가입</button>
	</c:if>
	
<!-- 	<!-- 로그인상태 --> 
	<c:if test="${login }">
<!-- 	<!-- 댓글 입력 -->
	<div class="form-inline text-center">
		<input type="text" size="10" class="form-control" id="commentWriter"
						value="${id } " readonly="readonly" />
		<textarea rows="2" cols="60" class="form-control" id="commentContent"></textarea>
		<button id="btnCommInsert" class="btn">입력</button>
	</div>	<!-- 댓글 입력 end -->
	</c:if>
	
<!-- 	<!-- 댓글 리스트 -->
	<table class="table table-striped table-hover table-condensed">
	<thead>
	<tr>
		<th style="width: 10%;">작성자</th>
		<th style="width: 50%;">댓글</th>
		<th style="width: 20%;">작성일</th>
		<th style="width: 5%;"></th>
	</tr>
	</thead>
	<tbody id="commentBody">
	<c:forEach items="${commentList }" var="rvComment">
	<tr data-commentno="${rvComment.rvCmtNo }">
		<td style="width: 10%;">${rvComment.userId }</td>
							<!-- 닉네임으로 해도 좋음 -->
		<td style="width: 50%;">${rvComment.rvContent }</td>
		<td style="width: 20%;"><fmt:formatDate	value="${rvComment.rvCreateDate }" pattern="yy-MM-dd" /></td>
		<td style="width: 5%;">
			<div>
				
				
				<c:if test="${sessionScope.id eq rvComment.userId }">
					<button class="btn" onclick="updateComment(${rv.Comment.rvCmtNo});">수정</button>
					<button class="btn btn-default btn-xs" onclick="deleteComment(${rvComment.rvCmtNo });">삭제</button>
				</c:if>
			</div>
		</td>
		
	</tr>
	</c:forEach>
	
			</table>	<!-- 댓글 리스트 end -->

</div> <!-- 댓글 처리 end -->

</div><!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
