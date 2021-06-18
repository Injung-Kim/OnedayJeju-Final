<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어느 날, 제주</title>
<link rel="shortcut icon" href="/resources/image/MAuJqhWr.ico">
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
	height : 100px;
	margin-bottom : 20px;
}
.pageName{
	float : right;
}
section{
	width : 1200px;
	height : 100%;
}
aside{
	float : left;
}
.menu{
	position : absolute;
	top : 140px;
	background: rgba(139, 215, 210, 0.5);
	width : 180px;
	/* height :100%; */
	margin : 0px;
	text-align: center;
	padding : 20px;
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
	position : relative;
	max-width : 1002px;
	margin : 0px;
	padding : 15px;
	float : right;
	margin-left : 200px;	
}
</style>

</head>
<body>
<header>
<a href="/"><img src="/resources/image/logo2.png" alt="어느 날, 제주 로고" style="height: 100px;"></a>
<div style="float:right;"><h2>Admin Page</h2></div>
</header>
<section>
<aside>
	<div class="menu">
	<div class="adminpage">
		<!-- <img alt="프로필" src="/resources/image/admin.png" style="width : 100px; height : 100px;" id="profile"> -->
		<p style="line-height : 50px;"><strong>${nick}</strong> 님</p>
		<button type="button" class="btn" onclick="location.href='/member/logout'">로그아웃</button>
	</div>
	<div class="section">
		<ul>
			<li><a href="/admin/user/list">회원관리</a></li>
			<li><a href="/admin/planboard">일정공유</a></li>
			<li><a href="/admin/reviewboard">여행후기</a></li>
			<li><a href="/notice/admin">공지사항</a></li>
			<li><a href="/admin/qna/list">Q & A</a></li>
		</ul>
	</div>
	</div>
</aside>
<article>
