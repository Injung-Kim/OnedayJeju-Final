<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- 스마트 에디터2 라이브러리 로드 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<link rel="stylesheet" href="/resources/css/notice.css" type="text/css">

<c:set var="now" value="<%=new java.util.Date()%>" />

<script type="text/javascript">
$(document).ready(function(){
	
	$("#ntTitle").focus();
	
	//파일네임 추출
	$("#file").change(function(e){
		
		$(".upload_name").remove();

		var files = e.target.files;
		
		//파일 갯수 제한
		if( 3 < files.length  ) {
			alert( "최대 업로드 갯수는 3개입니다");
			
			return false;
		}
		
		
		//생성된 파일 name 삭제
		$(".upload-name").remove();
		
		//파일객체확인
// 		console.log( e )
// 		console.log( e.target.files )

		
		//file객체 변수 선언
		
		//파일 이름 확인
// 		console.log( files[0].name )
		
		
		//파일이름 삽입
		for(var i=0; i<files.length; i++) {
			
			$("<input>").attr({
				class : "upload_name"
				, type : "text"
				, readonly : "readonly"
			}).appendTo($(".file_box")).val( i+1 + ". " + files[i].name );
			
		}
		
	});
	
	//스마트에디터
	$("#btnWrite").click(function() {

		//스마트에디터의 내용을 <textare>에 적용하기
		submitContents( $("#btnWrite") );

		var ntContent = $("#ntContent").val();
		
		if( ""== $("#ntTitle").val() ) {
			alert("제목을 작성해주세요");	
			$("#ntTitle").focus();
			
			return false;
		}
		
		if( ntContent == ""  || ntContent == null || ntContent == '&nbsp;' || ntContent == '<p>&nbsp;</p>' || ntContent == '<p><br></p>' ) {
			alert("내용을 작성해주세요");	
			oEditors.getById["ntContent"].exec("FOCUS");
			
			return false;
		}
		
		
		
		//form submit 수행하기
		$("form").submit();
	})
	

}); 

</script>

<div class="container">

	<form action="/notice/write" method="post" enctype="multipart/form-data">
	
		<div class="view_box bor_rad_five">
		
			<div class="top">
				<h3 class="sub">
					<input class="box_shadow" type="text" id="ntTitle" name="ntTitle" placeholder="제목을 작성해주세요"/>
				</h3>
			</div>
		
			
	
			
			<div class="write_info">
					<ul>
						<li><label for="ntDiv">구분</label></li>
						<li>|</li>
						<li>
							<select class="box_shadow" id="ntDiv" name="ntDiv">
								<option value="관리자">관리자</option>
								<option value="여행일정">여행일정</option>
								<option value="여행경비">여행경비</option>
								<option value="여행경비">커뮤니티</option>
							</select>
						</li>
						<li>작성일</li>
						<li>|</li>
						<li><fmt:formatDate value="${now }" pattern="yy-MM-dd"/></li>
					</ul>
			</div>
			
			<div class="con"><textarea rows="10" style="width: 100%" id="ntContent" name="ntContent"></textarea></div>
			<hr style="margin-top: 5px; margin-bottom:0;">
			<p class="count ntContent_cnt"><span>0</span> / 3800</p>

		</div>
		
		<div class="rel mid">
		
			<div class="file_box"> 
	 			 <label class="box_shadow bor_rad_ten" for="file">업로드</label> 
	 			 <input multiple="multiple" type="file" id="file" name="file"/> 
			</div>
			<input type="submit" class="abs update_btn box_shadow bor_rad_ten" id="btnWrite" value="등록" />
			<input type="button" class="abs delete_btn box_shadow bor_rad_ten" onclick="history.go(-1)" value="취소" />
	
		<!-- .view_box bor_rad_five -->
		</div>
		
	</form>
	
	

	
<!-- container -->
</div>

<!-- 스마트 에디터 적용하는 코드 -->
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "ntContent", //에디터가 적용될 <textarea>의 id
		sSkinURI: "/resources/se2/SmartEditor2Skin.html", //에디터 스킨
		fCreator: "createSEditor2"

	})
	
	setTimeout(function() { 
		var ctntarea = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector(".se2_inputarea");
		
		ctntarea.addEventListener("keyup", function(e) { 
			
			var text = this.innerHTML; 
			text = text.replace(/<br>/ig, ""); // br 제거
			text = text.replace(/&nbsp;/ig, "");// 공백 제거
			text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, ""); // html 제거
			console.log( text.charAt(0) )
			
			var maxByte = 3800;
			var rbyte = 0;
			var rlen = 0;
			var one_char = "";
			var text2
			var len = text.length;
			
			for (var i=0; i<len; i++) {
				one_char = text.charAt(i);
				
				if (escape(one_char).length > 4) {
					rbyte +=3;//한글
				} else {
					rbyte++//영어
				}
				
				if(rbyte <= maxByte) {
					rlen = i +1; //return할 문자열 갯수				
				}
			}
			
			
			
			console.log(text);
			if(rbyte > maxByte) {
				
				text2 = text.substr(0, rlen);
				this.innerHTML = text2;
				$('.count span').html("3800");
				
			} else {
				document.querySelector(".count span").innerHTML = rbyte;
				
			}
		});
	}, 1000)


	// <form>태그가 submit되면 스마트에디터에 작성된 내용이 <textarea>에
	//적용되도록 하는 코드
	function submitContents(elClickedObj) {
	
		oEditors.getById["ntContent"].exec("UPDATE_CONTENTS_FIELD", []);
	
		try {
			elClickedObj.form.submit();
		} catch(e) { }
	
	}
	
</script>

	

<c:import url="/WEB-INF/views/layout/footer.jsp" />
