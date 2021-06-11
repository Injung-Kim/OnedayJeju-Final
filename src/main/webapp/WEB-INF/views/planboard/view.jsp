<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!-- pbview css -->
<link rel="stylesheet" href="/resources/css/pbview.css">

<style type="text/css">
/* 대표이미지(커버) 부분 */
.cover_area {
	border: 1px solid #ccc;
	min-width: 1140px;
	height: 400px; 
	padding: 20px 24px;
	background: url("/getImg?filename=${info.PBFILENAME}") no-repeat;
	background-size: cover;
	background-position: center center;
	color: #FFFBFA;
}
</style>
</head>

<script type="text/javascript">
//header 부분 script
$(document).ready(function(){
	 $('#mypage').click(function(){
		if($('.balloon').hasClass("hidden")){
			$('.balloon').toggleClass("hidden show")
		}
	})
	$('.balloon').mouseleave(function(){
		if($('.balloon').hasClass("show")){
			$('.balloon').toggleClass("show hidden")
		}
	})
	 
})
</script>
<script>
$(document).ready(function(){
	//페이지 로드되었을 때 .page.show 의 div 높이를 자식요소 높이로 맞추기 -> 지도부분 sticky 기능을 위해
	$('.page.show').css('height', $('.page_left').height());
	
	//페이지 요청했을 때 유저의 좋아요 and 북마크 상태 반영
	if(${initLikeStatus == 1}) {
		$('.like_btn').addClass('active_btn');
	} else if(${initLikeStatus == 0}) {
		$('.like_btn').removeClass('active_btn');
	}
	
	if(${initBmStatus == 1}) {
		$('.bookmark_btn').addClass('active_btn');
	} else if(${initLikeStatus == 0}) {
		$('.bookmark_btn').removeClass('active_btn');
	}
	
	//좋아요 클릭 이벤트
	$('.like_btn').click(function(){
		var userNo = 0;
		if(${empty sessionScope.uno}){ return false;}
		else {var userNo = <%=session.getAttribute("uno")%>;}
		//좋아요 상태 변경
		$.ajax({
			type: "get"
			, url: "/planboard/like"
			, data:{
				pbNo: ${info.PBNO}
				, userNo: userNo
			}
			, dataType: "json"
			, success: function(res){
				console.log('좋아요 ajax 통신 성공')
				//좋아요 갯수 갱신
				$('.like_num').text('좋아요: '+ res.likeCnt);
				if(res.likeStatus == 1) {
					$('.like_btn').addClass('active_btn');
				} else if(res.likeStatus == 0) {
					$('.like_btn').removeClass('active_btn');
				}
			}
			, error: function(){
				console.log('좋아요 ajax 통신 실패')
			}
		})
	})
	//북마크 클릭 이벤트
	$('.bookmark_btn').click(function(){
		var userNo = 0;
		if(${empty sessionScope.uno}){ return false;}
		else {var userNo = <%=session.getAttribute("uno")%>;}
		//북마크 상태 변경
		$.ajax({
			type: "get"
			, url: "/planboard/bookmark"
			, data:{
				pbNo: ${info.PBNO}
				, userNo: userNo
			}
			, dataType: "json"
			, success: function(res){
				console.log('북마크 ajax 통신 성공')
				//북마크 상태 반영
				if(res.bookmarkStatus == 1) {
					$('.bookmark_btn').addClass('active_btn');
				} else if(res.bookmarkStatus == 0) {
					$('.bookmark_btn').removeClass('active_btn');
				}
			}
			, error: function(){
				console.log('북마크 ajax 통신 실패')
			}
		})
	})
	
	//메뉴 선택 -> 페이지변환
	$('.plan_menu').click(function(){
		//선택한 메뉴 번호 가져오기
		var pagenum = $(this).attr('data-id');
		//선택한 메뉴의 페이지만 보이게
		$('.page').removeClass('show');
		$('.page[data-id='+pagenum+']').addClass('show');
		//선택한 메뉴 색 변경
		$('.plan_menu').removeClass('on');
		$(this).addClass('on');
	})
	
	//댓글 전체 조회 ajax
	$('.plan_menu[data-id=2]').click( function() {
		//해당 게시글의 댓글 전체 조회
		$.ajax({
			type: "get"
			, url: "/planboard/comment"
			, data: {
				pbNo: ${info.PBNO}
			}
			, dataType: "json"
			, success: function(res) {
				console.log("Ajax 통신 성공")
				console.log(res)
				console.log(res.cmtList)
				
				//댓글창 비우기
				$('.comment_list').empty();
				
				var numText = '댓글 ' + res.cmtList.length;
				//댓글 총 갯수 추가
				$('.comment_num').text(numText);
				
				//댓글 추가
				for(var i in res.cmtList) {
					var element = $('<div>').addClass('comment').attr({"data-pbcno": res.cmtList[i].pbcNo, "data-uno": res.cmtList[i].userNo});
					var html = '<div class="cmt_user_info">'
							+	'<div class="cmt_user_nick">'+res.cmtList[i].userNick+'</div>'
							+	'<div class="cmt_date">'+ getFormatDate(res.cmtList[i].pbcCDate) +'</div>'
							+	'<div class="cmt_delete_btn"><a>삭제</a></div>'
							+	'<div class="clearfix"></div>'
							+	'<div class="cmt_value">'+ res.cmtList[i].pbcContent +'</div>' //xss 방지??
							+	'</div>';
							
					element.html(html);
					$('.comment_list').append(element);
					
				}
				
			}
			, error: function() {
				console.log("Ajax 통신 실패")
			}
		})
	})
	
	//댓글 작성 이벤트
	$('#cmtWriteBtn').click(function(){
		var userNo = 0;
		if(${empty sessionScope.uno}){ return false;}
		else {var userNo = <%=session.getAttribute("uno")%>;}
		//댓글 작성 ajax
		$.ajax({
			type: "post"
			, url: "/planboard/comment/write"
			, data: {
				pbcContent: $('#cmtContent').val()
				, pbNo: ${info.PBNO}
				, userNo: userNo
			}
			, dataType: "json"
			, success: function(res){
				console.log("Ajax 통신 성공");
				console.log(res)
				//댓글요소 만들기
				var element = $('<div>').addClass('comment').attr({"data-pbcno": res.comment.pbcNo, "data-uno": res.comment.userNo});
				var html = '<div class="cmt_user_info">'
						+	'<div class="cmt_user_nick">'+res.comment.userNick+'</div>'
						+	'<div class="cmt_date">'+ getFormatDate(res.comment.pbcCDate) +'</div>'
						+	'<div class="cmt_delete_btn"><a>삭제</a></div>'
						+	'<div class="clearfix"></div>'
						+	'<div class="cmt_value">'+ res.comment.pbcContent +'</div>' //xss 방지??
						+	'</div>';
						
				element.html(html);
				//댓글 맨위에 추가
				$('.comment_list').prepend(element);
				//댓글 갯수 업데이트
				var cmtCount = $('.comment_list').children().length;
				$('.comment_num').text('댓글 '+ cmtCount);
			}
			, error: function(){
				console.log("Ajax 통신 실패");
			}
		})
		//댓글 작성 부분 초기화
		$('#cmtContent').val('');
	})
	
	//사진변경 버튼 클릭
	$('#updateBtn').click(function(){
		$('input[name=imgfile]').trigger('click');
	})
	
	//사진변경 파일 첨부 -> 업로드된 파일 체크 -> form 데이터 전송
	$('input[name=imgfile]').change(function(e){
		if( !fileCheck(e) ){
			return false;
		}
		$('#imageUpdate').submit();
	})
	
	//삭제버튼 클릭
	$('#deleteBtn').click(function(){
		if( confirm('정말로 삭제하시겠습니까?') ) {
			var url = '/planboard/delete?pbNo=' + ${info.PBNO}
			$(location).attr('href', url);
		}
	})
	
	//목록버튼 클릭
	$('#listBtn').click(function(){
		var url = '/planboard/list?curPage=${paging.curPage}&condition=${paging.condition}&search=${paging.search}';
		$(location).attr('href', url);
	})
	
	//댓글입력창 글자수 세기
	$('#cmtContent').keyup(function(){
		var content = $(this).val();
		$('#counter').text( content.length + '/300');
	})
	
	//상세보기 모달창 닫기 설정
	const modal = document.querySelector(".modal");
	const overlay = modal.querySelector(".modal_overlay");
	const closeBtn =  modal.querySelector(".modal_close");
	
	const closeModal = () => {
		modal.classList.add("hidden")
	}
	overlay.addEventListener("click", closeModal);
	closeBtn.addEventListener("click", closeModal);
	
	//장소 상세보기 클릭 -> 장소에 대한 상세정보 보기 
	$('.place_detail').click( function(){
		var cid = $(this).parent().parent().attr("data-cid");
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
				//상세정보 채워넣기
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
				if(res.detailInfo.homepage == null){
					res.detailInfo.homepage = '-';
				}
				$(".modal_homepage").html("<strong>홈페이지</strong> : " + res.detailInfo.homepage);
				$(".modal_overview").html("<strong>개요</strong> : " + res.detailInfo.overview);
			}
			, error: function () {
				console.log("AJAX 실패");
			}
		})
		//모달 보이게 하기
		$(".modal").removeClass("hidden");
	})
	
	//======================== 카카오 맵 ============================
	var markers = []; // 마커를 담을 배열입니다
	var customOverlays = []; // customOverlay를 담을 배열입니다
	var polylines = []; // 지도에 표시한 선을 담는 배열입니다
	
	//지도에서 보기 버튼 클릭
	$('.map_btn').click(function(){
		//지도에 표시된 마커, 커스텀오버레이, 선을 제거
		//마커제거
		for(var i=0; i<markers.length; i++){
			markers[i].setMap(null);
		}
		markers = [];
		//커스텀오버레이 제거
		for(var i=0; i<customOverlays.length; i++){
			customOverlays[i].setMap(null);
		}
		customOverlays = [];
		//선 제거
		for(var i=0; i<polylines.length; i++){
			polylines[i].setMap(null);
		}
		polylines = [];
		
		var day = $(this).parent().parent().attr('data-id'); //클릭한 버튼이 여행 몇번째 날인지 확인
		
		var schList = $('.day_sch_box[data-id^='+ day +']'); //클릭한 날짜의 상세일정 장소들을 저장하는 변수
		var positionInfo = []; //상세일정 장소들의 정보를 담는 객체
		$.each(schList, function(index, item){
			var plainObj = {
					title: $(item).attr('data-title') //장소명
					, lat: $(item).attr('data-lat') //위도
					, lng: $(item).attr('data-lng') //경도
			}
			positionInfo.push(plainObj);
		})
		
		// 마커를 표시할 위치와 title 객체 배열입니다 
		var positions = [];
		for(var i in positionInfo) {
			var position = {
					title: positionInfo[i].title
					, latlng: new kakao.maps.LatLng( positionInfo[i].lat, positionInfo[i].lng )
			}
			positions.push(position);
		}
		//마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

		//선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
		var linePath = [];

		for (var i = 0; i < positions.length; i ++) {
		    
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도 -> 이게 있으면 marker.setMap(map) 안해도 된다.
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage, // 마커 이미지 
		    });
		    console.log(marker);
		    //표시한 마커는 배열에 저장해둔다.
		    markers.push(marker);
		    
		    // 커스텀 오버레이에 표시할 내용입니다
		    // HTML 문자열 또는 Dom Element 입니다 
		    var content = '<div class ="label"><span class="left"></span><span class="center">'+ positions[i].title +'</span><span class="right"></span></div>';

		    // 커스텀 오버레이가 표시될 위치입니다 
		    var position = new kakao.maps.LatLng(positions[i].latlng.Ma, positions[i].latlng.La);  

		    // 커스텀 오버레이를 생성합니다
		    var customOverlay = new kakao.maps.CustomOverlay({
		        position: position,
		        content: content   
		    });

		    // 커스텀 오버레이를 지도에 표시합니다
		    customOverlay.setMap(map);
		    
		    //표시한 커스텀 오버레이는 배열에 저장해둔다.
		    customOverlays.push(customOverlay);
		    
		 	//선으로 표시할 좌표를 하나씩 추가합니다.
		 	linePath.push(positions[i].latlng);

		    
		}
		//지도에 표시할 선을 생성합니다
		var polyline = new kakao.maps.Polyline({
		    path: linePath, // 선을 구성하는 좌표배열 입니다
		    strokeWeight: 2, // 선의 두께 입니다
		    strokeColor: 'red', // 선의 색깔입니다
		    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		    strokeStyle: 'solid' // 선의 스타일입니다
		});
		// 지도에 선을 표시합니다 
		polyline.setMap(map);
		//표시한 선은 배열에 저장해둔다.
		polylines.push(polyline);
	})
	//============================ 카카오 맵 끝 =============================
})
$(document).on("click", ".cmt_delete_btn", function(){
	var uno = $(this).parent().parent().attr("data-uno");
	var pbcno = $(this).parent().parent().attr("data-pbcno");
	if(<%=session.getAttribute("uno")%> == uno ){
		if(confirm('정말로 삭제하시겠습니까?')){
			$.ajax({
				type: "get"
				, url: "/planboard/comment/delete"
				, data: {
					pbcNo: pbcno
				}
				, success: function(){
					console.log('Ajax 통신 성공')
					//해당 댓글 삭제
					$('.comment[data-pbcno='+pbcno+']').remove();
					//댓글 갯수 업데이트
					var cmtCount = $('.comment_list').children().length;
					$('.comment_num').text('댓글 '+ cmtCount);
				}
				, error: function(){
					console.log('Ajax 통신 실패')
				}
			})
		}
	} else {
		alert('본인 글만 삭제가능합니다.');
	}
})

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
    var hour = date.getHours();
    hour = hour >= 10 ? hour : '0' + hour;
    var min = date.getMinutes();
    min = min >= 10 ? min : '0' + min;
    var sec = date.getSeconds();
    sec = sec >= 10? sec : '0' + sec;
    
    return  year + '.' + month + '.' + day + " " + hour + ":" + min + ":" + sec;       //yyyy.MM.dd HH:mm:ss
}
//업로드 파일 체크
function fileCheck(e) {
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
	return true;
}


</script>

<body>


<header>
	<a href="/" class="logo"><img src="/resources/image/logo.png" ></a>
	<!-- <a href="/" class="logo">어느 날, 제주<i class="fas fa-paper-plane"></i></a> -->
	
	<c:if test="${empty login }">
		<div class="nologin" style="right: 40px; position : absolute">
			<a href="/member/login">로그인</a>
			| 
			<a href="#">회원가입</a>
		</div>
	</c:if>
	<c:if test="${login}">
		<span class="welcome"><strong>${nick} </strong>님 환영합니다!</span>
		<div class="login" style="margin-right: 20px;">
			<img src="/resources/image/user.png" style="width : 70px; height : 70px;" id="mypage">
			
			<c:if test="${grade eq '2' }">		
				<div class="balloon hidden">	
				<p><a href="#">마이페이지</a></p>
				<p><a href="/member/logout">로그아웃</a></p>
				</div>
			</c:if>
			<c:if test="${grade ne '2' }">
				<div class="balloon hidden">	
				<p><a href="/admin">관리자 페이지</a></p>
				<p><a href="/member/logout">로그아웃</a></p>
				</div>
			</c:if>
			
		</div>
	</c:if>
	<nav>
		<ul class="nav_first">
		<li >
		<a href="/plan/create">여행일정</a>
		</li>
		<li >
		<a href="#">여행경비</a>
		</li>
		<li>
		<a href="#">커뮤니티</a>
			<ul class="nav_second">
				<li><a href="/planboard/list">일정 공유</a></li>
				<li class="devide">|</li>
				<li><a href="#">여행 후기</a></li>
				<li class="devide">|</li>
				<li><a href="/notice/list">공지사항</a></li>
				<li class="devide">|</li>
				<li><a href="#">Q & A</a></li>
			</ul>
		</li>
		</ul>
	</nav>
</header>



<article>

<div class="container">

	<div class="sub_title">
		<span style="font-size:16px;">일정공유 | </span><span style="font-size:16px;">일정 상세보기</span>
	</div>
	<!-- 커버 -->
	<div class="cover_area">
		<div class="plan_title_btn_area">
			<div class="pull-left"><span id="planTitle">${info.PBTITLE }</span></div>
			<div class="pull-right">
				<div class="pull-right like_btn circle"></div>
				<div class="pull-right bookmark_btn circle"></div>
			</div>
			<div class="clearfix"></div>
			<div class="period"><fmt:formatDate value="${info.STARTDATE }" pattern="yyyy.MM.dd"/> ~ <fmt:formatDate value="${info.ENDDATE }" pattern="yyyy.MM.dd"/> (${info.DAYS }일)</div>
			<div class="num like_num">좋아요: ${info.LIKENUM }</div>
			<div class="num views">조회수: ${info.PBVIEWS }</div>
		</div>
	</div>
	
	<!-- 메뉴  -->
	<div class="plan_menu_box">
		<div class="plan_menu on" data-id="1">개요</div>
		<div class="plan_menu" data-id="2">댓글</div>
		<c:if test="${info.USERNO eq uno }">
		<button id="deleteBtn" class="pull-right" type="button">삭제</button>
		<button id="updateBtn" class="pull-right" type="button">사진변경</button>
		<form id="imageUpdate" action="/planboard/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pbNo" value="${info.PBNO }">
			<input type="file" name="imgfile" style="display: none;">
		</form>
		</c:if>
		<button id="listBtn" class="pull-right" type="button">목록</button>
	</div>
	
	<!-- 내용  -->
	<!-- 개요 페이지 -->
	<div class="page show" data-id="1">
		<div class="page_left">
			<div class="day_box">
				<c:forEach var="day" items="${detailsList}" varStatus="i">
					<div class="day_info" data-id="${i.count }">
						<div class="day_txt">DAY ${i.count}</div>
						<div class="day_date"><fmt:formatDate value="${dpList[i.index].dpDate }" pattern="yyyy.MM.dd (E)"/> </div>
						<div class="day_btn"><button class="map_btn">지도에서 보기</button></div>
					</div>
					
					<div class="day_sch">
						<c:forEach var="details" items="${day}" varStatus="j">
							<div class="day_sch_box" data-id="${i.count }-${j.count}" data-lat="${details.MAPY }" data-lng="${details.MAPX }" data-title="${details.TITLE }" data-cid="${details.CONTENTID }" data-ctid="${details.CONTENTTYPEID }">
								<div class="day_sch_img_box">
									<c:choose>
										<c:when test="${not empty details.FIRSTIMAGE2 }">
											<img src="${details.FIRSTIMAGE2 }">
										</c:when>
										<c:otherwise>
											<img src="/resources/image/jejunoimage.png">
										</c:otherwise>
									</c:choose>
								</div>
								<div class="day_sch_content_box">
									<div class="place_name"><strong>${details.TITLE }</strong></div>
									<div class="place_addr">주소: ${details.ADDR1 }</div>
									<div class="place_detail"><a>상세보기</a></div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="page_right">
			<div class="right_map_box" id="map"></div>
		</div>
	</div>
	<!-- 댓글 페이지 -->
	<div class="page" data-id="2">
		<div class="page_left">
			<div class="comment_box">
				<div class="comment_num">댓글 000</div>
				<div class="comment_list">
<!-- 					<div class="commnet"> -->
<!-- 						<div class="cmt_user_info"> -->
<!-- 							<div class="cmt_user_nick">닉네임1</div> -->
<!-- 							<div class="cmt_date">2021.05.05 14:24</div> -->
<!-- 							<div class="cmt_delete_btn"><a>삭제</a></div> -->
<!-- 						</div> -->
<!-- 						<div class="clearfix"></div> -->
<!-- 						<div class="cmt_value">좋아요</div> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
		
		<div class="page_right">
			<div class="comment_write_box">
				<textarea id="cmtContent" maxlength="300"></textarea>
				<div id="counter">0/300</div>
				<button id="cmtWriteBtn">작성</button>
			</div>
		</div>
	</div>
	
</div><!-- .container -->

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
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=086009600bc20afd4142f6181ae47e1f&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.39237600, 126.49594100), //지도의 중심좌표.
	level: 10 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp" />
