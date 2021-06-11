<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<c:if test="${not empty param.condition }">
	<c:set var="condition" value="${param.condition }" />
</c:if>
<c:if test="${not empty param.search }">
	<c:set var="search" value="${param.search }" />
</c:if>
<style type="text/css">
.sub_title {
	padding: 10px;
}
#createBtn, #writeBtn {
	padding: 5px 10px;
	background-color: #00BD9D;
	border-radius: 10px;
	border: 1px solid #ccc;
	color: #FFFBFA;
}
#createBtn:hover, #writeBtn:hover {
	font-weight: bold;
}
/* 정렬, 검색 부분 */
select, input {
	padding: 5px;
	margin: 0 5px;
}
#searchBtn {
	padding: 0 5px;
	margin-top: 10px; 
}
.search_box {
	margin: 10px 0;
}
/* 게시글 부분 */
.plan {
	width: 360px;
	height: 320px;
	border: 1px solid #ccc;
	display: inline-block;
	float: left;
	margin: 30px 10px;
	cursor: pointer;
}
.plan_img_box img {
	width: 358px;
	height: 220px;
	
}
.like_views, .nick{
	margin: 7px;
}
.plan_info {
	overflow: hidden;
}
.plan_info .like_views span {
	margin: 10px 10px 10px 3px;
}
.inline {
	display: inline-block;
}
#days {
	color: #49C6E5;
	font-weight: 500;
}
#planTitle {
	font-weight: 600;
}
/* 모달창 부분 */
.modal {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	z-index: 9999;
}
.modal_overlay {
	background-color: rgba(0,0,0, 0.6);
	width: 100%;
	height: 100%;
	position: absolute;
}
.modal_content, .modal2_content {
	background-color: white;
	position: relative;
	border-radius: 10px;
 	box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
 	width: 600px;
 	height: 600px;
}
.modal_close, .modal2_close {
	position: absolute;
	right: 15px;
	top: 8px;
	font-size: 20px;
	font-weight: 700;
	cursor: pointer;
}
.modal_title {
	height: 40px;
}
.modal_plan_list {
	width: 560px;
	height: 470px;
	overflow: auto;
}
.hidden {
	display: none;
}
table, th, td {
	width: 540px;
	text-align: center;
}
th {
	border-bottom: 1px solid #ccc;
}
.modal_btn {
	margin-right: 26px;
	margin-top: 8px;
}
.modal_img_box {
	width: 540px;
	height: 350px;
	border: 1px solid #ccc;
	background-color: #ccc;
	display: flex;
    justify-content: center;
    align-items: center;
	cursor: pointer;
}
.modal_file_plan_title {
	margin: 0 30px;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
	//검색어 검색한 것으로 유지하기
	var sValue = '${search}';
	if(sValue != ''){
		$('#searchInput').val(sValue);
	}
	
	//정렬기준 선택한 것으로 유지하기
	var cValue ='${condition}';
	if(cValue != ''){
		$("#sort option[value="+ cValue +"]").attr("selected", 'selected');
	}
	
	//정렬기준 선택 이벤트
	$('#sort').change(function(){
		$('#searchBtn').trigger('click');
	})
	
	//일정 등록하기 버튼 클릭 이벤트
	$('#createBtn').click(function(){
		//유저 일정 불러오기
		$.ajax({
			type: "get"
			, url: "/planboard/planlist"
			, dataType: "json"
			, success: function(res){
				console.log("AJAX 성공");
				console.log(res)
				console.log(res.planList)
				
				//리스트 비우기
				$("#planDataList").empty();
				
				//일정 요소 만들어서 테이블에 추가하기
				for(var i in res.planList) {
					var element = $('<tr>');
					var html = "<td><div>"+res.planList[i].pTitle+"</div><div>작성일: "+getFormatDate( res.planList[i].pCDate )+"</div></td>"
							+ "<td>"+getFormatDate(res.planList[i].pDepartureDate)+"</td>"
							+ "<td>"+res.planList[i].pNumDays+"일</td>"
							+ "<td><input type='radio' name='pNo' value='"+res.planList[i].pNo+"'</td>";
					element.html(html);
					
					$("#planDataList").append(element);
				}
				
			}
			, error: function(){
				console.log("AJAX 실패");
			}
		})
		//modal hidden 설정해제
		$('.modal').removeClass('hidden');
	})
	//============MODAL================
		
	//modal 닫기
	$('.modal_close').click(modalClose);
	
	//이미지 미리보기
	$("input[name='imgfile']").change(function(e){ preview(e, ".modal_img_box") })
	
	//모달창 이미지 구역 클릭시 파일첨부 버튼 클릭
	$('.modal_img_box').click(function(){
		$('input[name=imgfile]').trigger('click');
	})
	
	//일정 선택버튼(다음페이지) 클릭
	$('#selectBtn').click(function(){
		if( $("input:radio[name='pNo']").is(":checked") ){
			$('.modal2_content').removeClass('hidden');
			$('.modal_content').addClass('hidden');
		}else{
			alert('일정을 선택해주세요');
			return;
		}
	})
	//=================================
	
	//게시글 클릭 이벤트
	$('.plan').click(function(){
		var pbno = $(this).attr('data-pbno');
		//목록으로 가기 구현을 위해 현재페이지, 정렬조건, 검색어도 같이 보낸다.
		var url = '/planboard/view?pbNo='+ pbno +'&curPage='+${paging.curPage};
		if(${not empty condition}){
			var url = '/planboard/view?pbNo='+ pbno +'&curPage='+${paging.curPage}+'&condition=${condition}'+'&search=${search}';
		}
		$(location).attr("href", url);
	})
	
})
function modalClose() {
	$('.modal').addClass('hidden');
	$('.modal2_content').addClass('hidden');
	$('.modal_content').removeClass('hidden');
	//파일업로드 리셋
	fileReset("input[name='imgfile']");
	$('.modal_img_box').html("<img src='/resources/image/plus.png'>")
	//글 제목 리셋
	$("input[name='pbTitle']").val("");
	//radio 리셋
	$('input[name="pNo"]').removeAttr('checked');
	
}
function fileReset(input) {
	var agent = navigator.userAgent.toLowerCase();
	if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
	    $(input).replaceWith($(input).clone(true));
	}else{
	    $(input).val("");
	}
}
//미리보기 
function preview(e, p){
	var files = e.target.files;
	var filesize = files[0].size;
	//이미지만 처리할 수 있도록 적용
	if( !files[0].type.includes("image")){
		alert("이미지가 아닙니다.")
		e.target.value = null;
		return false;// 이벤트 처리 중단
	} else if( filesize >= 10*1024*1024 ){ //파일 용량 제한
		alert("10MB 미만의 파일만 업로드할 수 있습니다.")
		e.target.value = null;
		return false;
	} 
	
	var reader = new FileReader();

	reader.onload = function(ev) {
		//미리보기 구현
		$(p).html(
				$("<img>").attr({
					"src": ev.target.result
					, "width": "540"
					, "height": "350"
				})
			)
		}
	
	reader.readAsDataURL(files[0])
}
/**
 *  yyyyMMdd 포맷으로 반환
 */
function getFormatDate(value){
	var date = new Date(value);
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '.' + month + '.' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}
//입력 여부 확인
function checkForm() {
	if( !$("input[name=pbTitle]").val() ) {
		alert('글 제목을 입력하세요.');
		$("input[name=pbTitle]").focus();
		return false;
	} else if ( !$("input[name=imgfile]").val() ) {
		alert('이미지 파일을 첨부하세요.');
		return false;
	}  
}
</script>

<div class="container">

	<div class="sub_title">
		<span style="font-size:24px;">일정공유 | </span><span style="font-size:20px;">목록</span>
	</div>
	<div class="clearfix"><button class="pull-right" id="createBtn">일정 등록하기</button></div>
	<div class="search_box">
		<form action="/planboard/list" method="get">
			<select id="sort" name="condition">
				<option value="latest">최신순</option>
				<option value="like">좋아요순</option>
				<option value="views">조회순</option>
			</select>
			
			<button class="pull-right" id="searchBtn">검색</button>
			<input class="pull-right" type="text" placeholder="제목 또는 닉네임" id="searchInput" name="search">
		</form>
	</div>
	
	<div class="list_box">
		<c:forEach var="planBoard" items="${list }">
		<div class="plan" data-pbno="${planBoard.pbno }">
			<div class="plan_img_box"><img src="/getImg?filename=${planBoard.filename }"></div><!-- 서버에 저장된 파일명으로 바꾸기 -->
			<div class="plan_info text-center">
				<div class="like_views">
					<div class="inline pull-left">좋아요:<span id="likeNum">${planBoard.likenum }</span></div>
					<div class="inline pull-left">조회수:<span id="views">${planBoard.views }</span></div>
					<div class="inline pull-left">작성일:<span id="createDate"><fmt:formatDate value="${planBoard.cdate }" pattern="yyyy.MM.dd"/></span></div>
				</div>
				<div class="clearfix"></div>
				<div class="nick inline pull-left">
					<span id="nick">닉네임: ${planBoard.nick }</span>
				</div>
				<div class="clearfix"></div>
				<div class="planboard_title">
					<span id="days">[${planBoard.days }일]</span>
					<span id="planTitle">${planBoard.title }</span>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	<div class="clearfix"></div>
	<div class="page_box">
		<c:import url="/WEB-INF/views/planboard/paging.jsp" />
	</div>
	
</div><!-- .container -->

<!-- 일정 등록 모달창 -->
<!-- 모달1 -->
<form action="/planboard/write" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
<div class="modal hidden">
	<div class="modal_overlay"></div>
	<div class="modal_content">
		<div class="modal_close">X</div>
		<div class="modal_title text-center"><h3>일정 선택</h3></div>
		<div class="modal_plan_list">
			<table>
			<thead>
				<tr>
					<th>일정명</th>
					<th>출발일</th>
					<th>여행일수</th>
					<th>선택</th>
				</tr>
			</thead>
			<tbody id="planDataList">
				
			</tbody>
			</table>
		</div>
		<div class="modal_btn">
			<button class="btn btn-default pull-right" type="button" id="selectBtn">선 택</button>
		</div>
	</div>
	<div class="modal2_content hidden">
		<div class="modal_close">X</div>
		<div class="modal_title text-center"><h3>대표이미지 선택</h3></div>
		<div class="modal_img_box"><img src="/resources/image/plus.png"></div>
		<div class="modal_file_plan_title">
			<input type="file" name="imgfile" accept="image/gif,image/jpeg,image/png" style="margin: 10px 0;">
			<label for="pbTitle" style="margin: 0 5px;">글 제목</label>
			<input type="text" name="pbTitle" id="pbTitle" style="margin: 10px 5px; width: 100%;" maxlength="50">
		</div>
		<div class="modal_btn">
			<button class="pull-right" id="writeBtn">등 록</button>
		</div>
	</div>
</div>
</form>


<c:import url="/WEB-INF/views/layout/footer.jsp" />
