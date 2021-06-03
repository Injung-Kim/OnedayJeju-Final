<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!--FontAwsome 아이콘  -->
<script src="https://kit.fontawesome.com/5e1e2577f7.js" crossorigin="anonymous"></script>
<!--웹폰트(Spoqa Han Sans Neo )  -->
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>

<!-- 드래그 앤 드랍 -->
<link rel="stylesheet" href="/resources/css/dragstyles.css">
<script src="/resources/js/dragscript.js" defer></script>
<!-- css -->
<link rel="stylesheet" href="/resources/css/planupdate.css">

<script type="text/javascript">
$(document).ready(function(){
	//검색부분 접기 펼치기
	//접기
	$(".on_close_btn").click(function(){
		$(".search").css("left","0");
		setTimeout(function(){
			$(".on_open_btn").removeClass("displaynone");
		}, 900);
	})
	//펼치기
	$(".on_open_btn").click(function(){
		$(".search").css("left", "400px");
		$(".on_open_btn").addClass("displaynone");
	})
	
	//일정 삭제 버튼
	$("#deleteBtn").click(function(){
		var url = '/plan/delete?pNo=' + ${plan.pNo};
		if(confirm('정말로 삭제하시겠습니까?')){
			$(location).attr("href", url);
		}
	})
	//저장&닫기 버튼
	$("#saveBtn").click(function(){
		$(location).attr("href", "/mypage/plan");
	})
	
	//여행 날짜 클릭
	$(".day").click(function(){
		$(".day").each(function(index, item){
			$(item).removeClass("day_active");
		})
		var ordinal = $(this).find(".ordinal").html();
		var planDate = $(this).find(".plan_date").html();
		var dpNo = $(this).find(".dpNo").html();
		$("#ordinal").html(ordinal);
		$("#planDate").html(planDate);
		$("#dpNo").html(dpNo);
		$(this).addClass("day_active");
		
		//ajax - 해당 날짜의 상세일정 가져오기
		$.ajax({
			type: "get"
			, url: "/detailplan"
			, data: {	
				dpNo: dpNo
			}
			, dataType: "json"
			, success: function( res ) {
				console.log("AJAX 성공")
				console.log(res)
				console.log(res.infoList) //[{}, {}, {}, {}]
				console.log(res.infoList.length) //4
				
				//컨테이너 비우기
				$(".drag_container").empty();
				
				//요소 만들어서 추가하기
				for(var i in res.infoList){
					var element = $("<div>").addClass("plan_place draggable").attr({
						"draggable": true
						, "data-cid" : res.infoList[i].CONTENTID
						, "data-ctid" : res.infoList[i].CONTENTTYPEID
						, "data-lat" : res.infoList[i].MAPX
						, "data-lng" : res.infoList[i].MAPY
						, "data-order" : res.infoList[i].ORDERTRAVEL
					});
					if( res.infoList[i].FIRSTIMAGE2 != null){
						var child1 = $("<div>").addClass("fl detail_img_box").append($("<img>").attr("src", res.infoList[i].FIRSTIMAGE2));
					} else {
						var child1 = $("<div>").addClass("fl detail_img_box").append($("<img>").attr("src", "/resources/image/jejunoimage.png"));
					}
					var child2 = $("<div>").addClass("fr place_title").text(res.infoList[i].TITLE)
					var child3 = $("<div>").addClass("detail_delete_btn").append(
							$("<button>").attr({type: "button", style: "font-weight:bold"}).text("X"));
					var child4 = $("<div>").addClass("clearfix");
					
					element.append(child1,child2,child3,child4);
					$(".drag_container").append(element);
				}
				//추가한 요소들 드래그 가능하게 설정
				setDraggable();
			}
			, error: function () {
				console.log("AJAX 실패");
			}
		})
	})
	
	//장소 검색 버튼 클릭
	$("#searchBtn").click(function(){
		var keyword = $("#inputSearch").val();
		searchAjax("/placebykeword", keyword);
	})
	$("#inputSearch").keydown(function(){
		if(event.keyCode == 13){
			$("#searchBtn").trigger("click");		
		}
	})
	
	//카테고리 버튼 클릭
	$(".category_btn").click(function(){
		if( $(this).hasClass("touristspot") ){
			var category = '12';
		} else if( $(this).hasClass("eatery") ){
			var category = '39';
		} else if( $(this).hasClass("lodging") ){
			var category = '32';
		} else {
			var category = '38';
		}
		searchAjax("/placebycategory", category);
	})
	
	//처음 접속했을때 트리거
	var first = $(".day")[0];
	$(first).trigger("click");
	$(".touristspot").trigger("click");
	
	//상세보기 모달창
	const modal = document.querySelector(".modal");
	const overlay = modal.querySelector(".modal_overlay");
	const closeBtn =  modal.querySelector(".modal_close");
	
	const closeModal = () => {
		modal.classList.add("hidden")
	}
	overlay.addEventListener("click", closeModal);
	closeBtn.addEventListener("click", closeModal);
	
})
//동적 태그 재설정
//드래그
$(document).on("dragend", ".draggable", updateOrder);
//장소 삭제
$(document).on("click", ".detail_delete_btn", function(){
	$(this).parent().remove();
	updateOrder();

})
//장소에 대한 상세정보 보기
$(document).on("click", ".list_box .place_title", function(){
	var cid = $(this).parent().attr("data-cid");
	$.ajax({
		type: "get"
		, url: "/detailinfo"
		, data: {	
			contentid: cid 
		}
		, dataType: "json"
		, success: function( res ) {
			console.log("AJAX 성공");
			console.log(res);
			if(res.detailInfo.img != null){
				$("#modalImg").attr("src", res.detailInfo.img)
			} else {
				$("#modalImg").attr("src", "/resources/image/jejunoimage.png")
			}
			$(".modal_title").html("<strong>장소명</strong> : " + res.detailInfo.title);
			if(res.detailInfo.tel == null){
				res.detailInfo.tel = '-';
			}
			$(".modal_tel").html("<strong>전화번호</strong> : " + res.detailInfo.tel);
			$(".modal_addr").html("<strong>주소</strong> : " + res.detailInfo.addr);
			$(".modal_homepage").html("<strong>홈페이지</strong> : " + res.detailInfo.homepage);
			$(".modal_overview").html("<strong>개요</strong> : " + res.detailInfo.overview);
		}
		, error: function () {
			console.log("AJAX 실패");
		}
	})
	$(".modal").removeClass("hidden");
})
//장소 추가
$(document).on("click", ".place_add_button", function(){
	var dataset = $(this).parent().data();
	//상세일정에 div 추가
	var element = $("<div>").addClass("plan_place draggable").attr({
						"draggable": true
						, "data-cid" : dataset.cid
						, "data-ctid" : dataset.ctid
						, "data-lat" : dataset.lat
						, "data-lng" : dataset.lng
					});
	if(dataset.img != null){
		var child1 = $("<div>").addClass("fl detail_img_box").append($("<img>").attr("src", dataset.img));
	} else {
		var child1 = $("<div>").addClass("fl detail_img_box").append($("<img>").attr("src", "/resources/image/jejunoimage.png"));
	}
	var child2 = $("<div>").addClass("fr place_title").text(dataset.title)
	var child3 = $("<div>").addClass("detail_delete_btn").append(
			$("<button>").attr({type: "button", style: "font-weight:bold"}).text("X"));
	var child4 = $("<div>").addClass("clearfix");
	
	element.append(child1,child2,child3,child4);
	//요소 추가
	$(".drag_container").append(element);
	//추가한 요소들 드래그 가능하게 설정
	setDraggable();
	//여행 순서 update
	updateOrder();

})
//검색 ajax
function searchAjax(url, data){
	$.ajax({
		type: "get"
		, url: url
		, data: {	
			data: data
		}
		, dataType: "json"
		, success: function( res ) {
			console.log("AJAX 성공");
			console.log(res.placeList);
			
			//조회결과 박스 비우기
			$(".list_box").empty();
			//요소 만들어서 추가하기
			for(var i in res.placeList){
				var element = $("<div>").addClass("search_place").attr({
					"data-cid" : res.placeList[i].contentid
					, "data-ctid" : res.placeList[i].contenttypeid
					, "data-lat" : res.placeList[i].mapx
					, "data-lng" : res.placeList[i].mapy
					, "data-title" : res.placeList[i].title
					, "data-img" : res.placeList[i].firstimage2
				});
				if( res.placeList[i].firstimage2 != null ) {
					var child1 = $("<div>").addClass("fl place_img_box").append($("<img>").attr("src", res.placeList[i].firstimage2));
				} else {
					var child1 = $("<div>").addClass("fl place_img_box").append($("<img>").attr("src", "/resources/image/jejunoimage.png"));
				}
				var child2 = $("<div>").addClass("fr place_title").text(res.placeList[i].title)
				var child3 = $("<div>").addClass("place_add_button").append(
						$("<button>").attr({type: "button", style: "font-weight:bold"}).text("+"));
				var child4 = $("<div>").addClass("clearfix");
				
				element.append(child1,child2,child3,child4);
				$(".list_box").append(element);
			}
		}
		, error: function () {
			console.log("AJAX 실패");
		}
	})
}
//상세일정 업데이트 Ajax
function planUpdateAjax() {
	var arr = [];
	arr = $(".plan_place");
	console.log(arr)
	var jsonArr = [];
	
	var dpno = $("#dpNo").text(); 
	
	for(var i=0; i<arr.length; i++) {
		var obj = {
			dpNo: dpno
			, contentid: $(arr[i]).attr("data-cid")
			, order: $(arr[i]).attr("data-order")
		}
		jsonArr.push( obj );
	}
	

	var jsonStr = JSON.stringify(jsonArr);
	//일정을 모두 삭제한 경우
	if( jsonArr.length === 0) {
		jsonArr.push( {dpNo: dpno} );
		jsonStr = JSON.stringify(jsonArr);
	}
	console.log(jsonStr)
	$.ajax({
		type: "post"
		, url: "/detailplan/update"
		, data: jsonStr
		, contentType: "application/json; charset=UTF-8"  // json 보낼 때 필수
		, success: function( res ) {
			console.log("AJAX 성공");
			console.log(res);
		}
		, error: function () {
			console.log("AJAX 실패");
		}
	})
}
//순서 바꾸는 함수
function updateOrder(){
	var detailPlaces = $(".drag_container").children();
	
	detailPlaces.each(function( index, element ){
		$(element).attr("data-order", index+1);
	})
	//상세일정 update
	planUpdateAjax();
}
</script>
</head>
<body>

<div class="header">
	<a href="/" class="logo"><img src="/resources/image/logo.png" ></a>

	<div class="subject">상세 일정 만들기</div>
	<button id="deleteBtn" class="header_btn" type="button">삭제</button>
	<button id="saveBtn" class="header_btn" type="button">저장&닫기</button>
	<div class="clearfix"></div>
</div>

<div class="content">
	<!-- 날짜, 상세일정 -->
	<div class="fl left_box">
		<div class="fl days">
			<c:forEach var="detailPlan" items="${dpList }" varStatus="iter">
				<div class="day">
					<div class="dpNo" style="display: none;">${detailPlan.dpNo }</div>
					<div class="fl date_left_box">
						<div class="date_order"><span class="ordinal">DAY ${iter.count }</span></div>
						<div class="date">
							<span class="plan_date"><fmt:formatDate value="${detailPlan.dpDate }" pattern="MM.dd"/></span>
						</div>
					</div>
					<div class="fl date_right_box">
						<div class="date_weekday">
							<fmt:formatDate value="${detailPlan.dpDate }" pattern="(E)"/>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 상세일정 -->
		<div class="fl detail_plan">
			<div class="detail_sub_title text-center">
				<span id="ordinal">DAY 1</span> | <span id="planDate">00.00</span><span id="dpNo" style="display: none;"></span>
			</div>
			<div class="drag_container">
				
			</div>
		</div>
	</div>
	<!-- 장소 검색 -->
	<div class="on_open_btn displaynone"></div>
	<div class="search">
		<div class="place_list">
		<div class="on_close_btn"></div>
			<div class="place_search_box text-center">
				<input type="text" id="inputSearch" placeholder="장소 검색">
				<button type="button" id="searchBtn">검색</button>
				<div class="category_box text-center">
					<button class="category_btn touristspot">관광</button>
					<button class="category_btn eatery">식당</button>
					<button class="category_btn lodging">숙박</button>
					<button class="category_btn shopping">쇼핑</button>
				</div>
			</div>
			<div class="list_box">
				
			</div>
		
		</div>
	</div>
	<!-- 지도 -->
	<div class="fr map_area" id="map">4</div>
</div>
<!-- 장소 상세정보 모달창 -->
<div class="modal hidden">
	<div class="modal_overlay"></div>
	<div class="modal_content">
		<div class="modal_close">X</div>
		<div class="modal_img_box">
			<img id="modalImg" src="/resources/image/jejunoimage.png" />
		</div>
		<div class="modal_detail_info">
			<div class="modal_title">타이틀</div>
			<div class="modal_tel">전화번호</div>
			<div class="modal_addr">주소</div>
			<div class="modal_homepage">홈페이지</div>
			<div class="modal_overview">개요</div>
		</div>
	</div>
</div>

<!-- 카카오 맵 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=086009600bc20afd4142f6181ae47e1f"></script>
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=086009600bc20afd4142f6181ae47e1f&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.39237600, 126.49594100), //지도의 중심좌표.
	level: 9 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
</script>
</body>
</html>