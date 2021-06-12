<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- css -->
<link rel="stylesheet" href="/resources/css/elist.css" type="text/css">


<!-- 구글 charts api -->
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


<script type="text/javascript">
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChartDonut);
 
function drawChartDonut() {
	var data = new google.visualization.DataTable();
	
		data.addColumn('string', 'Category');
		data.addColumn('number', 'Cost');
		
		data.addRows([
			<c:forEach items="${map}" var="stat" varStatus="st" >
				['${stat.CATEGORY}', ${stat.COST}]
				<c:if test="${not st.last}">,</c:if>
			</c:forEach>
		]);
  
	var options = { 'title': '${category}',
					'width': 450,
					'height': 600,
					legend: {position: 'top', textStyle: {fontSize: 14}, maxLines:2},
					pieHole: 0.3,
					colors: ['#92CFBF', '#C7E2CF', '#92B8B1', '#97C1A9', '#8FCACA', '#A2E1DB']
	
	};
		
	var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
	chart.draw(data, options);
 }



$(document).ready(function() {
	
var action = '';
var url = '';
var type = '';
var expno = '';
	
	//'경비 추가' 버튼 클릭 시
	$("#addExpBtn").click(function() {
		url = 'addexp';
		type = "get"
	
		var data = {
			"pNo" : '${param.selectPlan }'
		};
		
		$.ajax ({
			url: url
			, type: type
			, data: data
			, dataType: "html"
			, success: function(res){
				$("#addModal .modal-body").html(res)
			}
		})
		
		$("#addModal .modal-title").text("경비 추가하기");
	});
	
	
	//모달의 '추가' 버튼 클릭 시
	$("#modalSubmit").click(function() {
		console.log("#modalSubmit clicked")
		
		//상호명(상세정보) 정규식
		$r1 = /^[a-zA-Z0-9가-힣 ]*$/;
		
		//사용금액 정규식
		$r2 = /^[0-9]/g;
		
		
		//카테고리 정규식
		if ($("#category").val() == '') {
			alert("카테고리를 선택해주세요");
			return false;
		}
		
		//상호명(상세정보) 정규식
		if ($("#expName").val() == '' || $("#expName").val().length > 45 || !$r1.test($("#expName").val()) ) {
			alert("상호명(상세정보)를 영어 대소문자, 한글, 숫자를 사용해 45자 이내로 작성해주세요")
			$("#expName").focus();
			return false;
		}
		
		//비용 정규식
		if ($("#cost").val() == '' || $("#cost").val() < 100 || !$r2.test($("#cost").val()) ) {
			alert("사용 금액을 공백없이 숫자로만 100원 이상 작성해주세요")
			$("#cost").focus();
			return false;
		}
		
		
		url = 'addexp';
		type = "post"
		
		var data = {
			"pNo" : '${param.selectPlan }',
			"expDate": $("#expDate").val(),
			"category": $("#category").val(),
			"expName": $("#expName").val(),
			"cost": $("#cost").val(),
			"memo": $("#memo").val()
		};
		
		$.ajax ({
			url: url
			, type: type
			, data: data
			, dataType: "json"
			, success: function(res) {
				console.log(res);
			}
		})
		location.reload(true);
		
	});
	
	
	//'상세보기' 버튼 클릭 시
	$(".viewExpBtn").click(function() {
		url = "view";
		type = "get";
		
		var data = {
			"expNo" : $(this).attr("data-expno")
		}
		
		$.ajax({
			url: url
			, type: type
			, data: data
			, dataType: "html"
			, success: function(res){
				$("#viewModal .modal-body").html(res)
			}
		})
		
		$("#viewModal .modal-title").text("경비 상세보기")
	});
	
})

</script>



<div class="container" align="center">

<div class="sub_title">${nick} 님의 <strong>${eList[0].P_TITLE}</strong> 경비 목록
	<p style="font-size:0.4em; color:#d6d6d6">(지출일 기준으로 정렬됩니다)</p>
</div>

<div class="list-container">
	
<%-- 	<jsp:include page="expmodal.jsp" /> --%>
	<button id="addExpBtn" type="button" data-toggle="modal" data-target="#addModal"><i class="far fa-plus-square fa-3x"></i></button>
	
	<table class="table list-table">
	<tr>
		<th>지출일</th>
		<th>상호명 (상세정보)</th>
		<th>카테고리</th>
		<th>사용 금액</th>
		<th></th>
		<th></th>
	</tr>
	
	<c:forEach var="e" items="${eList}">
	<tr>
		<td><fmt:formatDate value="${e.EXP_DATE}" pattern="yyyy-MM-dd" /></td>
		<td>${e.EXP_NAME}</td>
		<td>${e.CATEGORY}</td>
		<td><fmt:formatNumber value="${e.COST}" pattern="#,###원" /></td>
		<td>
			<button class="viewExpBtn" type="button" data-toggle="modal" data-target="#viewModal" data-expno="${e.EXP_NO}"><i class="fas fa-search-plus"></i></button>
		</td>
		<td>
			<a href="/expenses/delete?expno=${e.EXP_NO}" role="button" id="delExpBtn" onclick="return confirm('해당 경비 내역을 삭제하시겠습니까?');">
				<i class="far fa-trash-alt"></i>
			</a>
		</td>
	</tr>
	</c:forEach>
	</table>
</div>	

<div id="donutchart"></div>

	
	
	
	
	
<!-- 경비 추가 모달 -->
<div class="modal fade" id="addModal" role="dialog">
	<div class="modal-dialog">
		
		<!-- Modal Content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 id="modal-title" class="modal-title"></h3>
			</div>
			
			<div class="modal-body"></div>
			
			<div class="modal-footer">
				<button id="modalSubmit" type="submit">추가</button>
				<button type="button" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 경비 상세보기 모달 -->
<div class="modal fade" id="viewModal" role="dialog">
	<div class="modal-dialog">
		
		<!-- Modal Content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 id="modal-title" class="modal-title"></h3>
			</div>
			
			<div class="modal-body"></div>
			
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="cancel">닫기</button>
			</div>
		</div>
	</div>
</div>



</div><!-- .container -->


<div class="button-container">
	<button type="button" id="pListBtn" onclick="document.location.href='/expenses/plist';"><span>여행일정 목록</span></button>
</div>


<c:import url="/WEB-INF/views/layout/footer.jsp" />