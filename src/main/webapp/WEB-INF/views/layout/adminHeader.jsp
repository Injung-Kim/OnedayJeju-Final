<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<style type="text/css">
*{
	font-family: 'Spoqa Han Sans Neo', 'sans-serif'; 
	margin: 0 auto;
	max-width: 1200px;
}
.asideicon{
	font-size: 2em;
	cursor : pointer;
}
#aside{
	font-size : 2em;
	border : none;
	background : transparent;
}
a { text-decoration:none !important } 
a:hover { text-decoration:none !important }
header{
	height : 70px;
}
.menu{
	position: absolute;
	background: rgba(139, 215, 210, 0.5);
	width : 180px;
	height : 100%;
	margin : 0px;
	text-align: center;
	padding : 20px;
	float : left;
	left : -160px;
}
.adminpage{
	border-bottom : 1px solid black;
	padding : 15px;
}

.section ul{
	list-style: none;
	padding : 0px;
}
.section ul > li{
	font-size: 20px;
	line-height: 75px;
}
.section ul > li > a{
	color : black;
}
.section ul > li:hover > a{
	color : white;
}
article{
	border : 1px solid #ccc;
	max-width : 1002px;
	padding : 15px;
	float : left;	
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#aside').click(function(){
		sidemenu();
	})
	$('.menu').mouseleave(function(){
		sidemenu();
	})
})
// 사이드바 메뉴 (on : 활성화 , off : 비활성화)
function sidemenu(){
	if($('.asideicon').hasClass("off")){
		$('.asideicon').toggleClass("on")
		$('.asideicon').removeClass("off")		
		$('.menu').css('position', 'relative');
		$('.menu').animate({
			left : 0
		}, 1000)
	}else if($('.asideicon').hasClass("on")){
		$('.asideicon').removeClass("on")		
		$('.asideicon').toggleClass("off")
	 	$('.menu').css('position', 'absolute'); 
		$('.menu').animate({
		left : -160
	}, 1000)	
	}
}
</script>
</head>

<body>
<header>
<a href="/"><img src="/resources/image/logo.png" alt="어느 날, 제주 로고" ></a>
</header>
<label for="aside" class="asideicon off"><i class="fas fa-bars"></i></label>
<input type="button" value="Menu" id="aside">
<aside>
	<div class="menu">
	<div class="adminpage">
		<img alt="프로필" src="/resources/image/admin.png" style="width : 100px; height : 100px;" id="profile">
		<p style="line-height : 50px;"><strong>${nick}</strong> 님</p>
		<button type="button" class="btn" onclick="location.href='/member/logout'">로그아웃</button>
	</div>
	<div class="section">
	<h3>관리자 페이지</h3>
		<ul>
			<li><a href="/admin/user/list">회원관리</a></li>
			<li><a href="#">일정공유</a></li>
			<li><a href="#">여행후기</a></li>
			<li><a href="#">공지사항</a></li>
			<li><a href="#">Q & A</a></li>
		</ul>
	</div>
	</div>
</aside>
<article>
