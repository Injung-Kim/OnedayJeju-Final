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
		

		$(".update_upload_name").remove();

		var upLoadFileCnt = $(".update_download_name").length;
		
		var files = e.target.files;
		
		//파일 갯수 제한
		if( $(".update_download_name").length == 3) {
			
			alert( "최대 업로드 갯수는 3개입니다");
			return false;
			
		}
		if( $(".update_download_name").length == 2) {

			if( 1 < files.length  ) {
			alert( "최대 업로드 갯수는 3개입니다");
			return false;
				
			}
			
			
		}
		if( $(".update_download_name").length == 1) {

			if( 2 < files.length  ) {
			alert( "최대 업로드 갯수는 3개입니다");
			return false;
				
			}
			
		}
		
		
		
		//생성된 파일 name 삭제
		$(".update_upload_name").remove();
		
		//파일객체확인
// 		console.log( e )
// 		console.log( e.target.files )

		
		//file객체 변수 선언
		
		//파일 이름 확인
// 		console.log( files[0].name )
		
		
		//파일이름 삽입
		for(var i=0; i<files.length; i++) {
			
			$("<input>").attr({
				class : "update_upload_name"
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

	function uploadDel( ntfNo ) {
			
		if( confirm("첨부파일을 삭제 하시겠습니까?") ) {
		
			$.ajax({
				type: "post"
				,url: "/notice/fileDelete"
				,dataType: "json"
				,data: {ntfNo: ntfNo}
				, success: function( res ) {
						
					if( res ) {
					$("[download-del='"+ntfNo+"']").remove();
					} else {
						alert("첨부파일이 삭제되지 않았습니다.")
					}
				}
				, error: function() {
// 					console.log("에이잭스 실패")
				}			
				
			})
			
		} // if( confirm("첨부파일을 삭제 하시겠습니까?") )
		
	}

</script>

<div class="container">

	<form action="/notice/update" method="post" enctype="multipart/form-data">
	<input type="hidden" name="ntNo" value="${notice.ntNo }" />
	
		<div class="view_box bor_rad_five">
		
			<div class="top">
				<h3 class="sub">
					<input class="box_shadow" type="text" id="ntTitle" name="ntTitle" placeholder="제목을 작성해주세요" value="${notice.ntTitle }"/>
				</h3>
			</div>
		
			
	
			
			<div class="write_info">
					<ul>
						<li><label for="ntDiv">구분</label></li>
						<li>|</li>
						<li>
							<select class="box_shadow" id="ntDiv" name="ntDiv">
								<option value="관리자" <c:if test="${notice.ntDiv eq '관리자' }">selected</c:if> >관리자</option>
								<option value="여행일정" <c:if test="${notice.ntDiv eq '여행일정' }">selected</c:if> >여행일정</option>
								<option value="여행경비" <c:if test="${notice.ntDiv eq '여행경비' }">selected</c:if> >여행경비</option>
								<option value="여행경비" <c:if test="${notice.ntDiv eq '커뮤니티' }">selected</c:if> >커뮤니티</option>
							</select>
						</li>
						<li>수정일</li>
						<li>|</li>
						<li><fmt:formatDate value="${now }" pattern="yy-MM-dd"/></li>
					</ul>
			</div>
			
			<div class="con"><textarea rows="10" style="width: 100%" id="ntContent" name="ntContent" >${notice.ntContent}</textarea></div>
			<hr style="margin-top: 5px; margin-bottom:0;">
			<p class="count ntContent_cnt"><span>0</span> / 3800</p>
			
		</div>
		
		<div class="rel mid">
		
			<div class="file_box"> 
	 			<label class="box_shadow bor_rad_ten" for="file">업로드</label> 
	 			<input multiple="multiple" type="file" id="file" name="file"/> 
			</div>
			
			<c:forEach var="noticeFileList" items="${noticeFileList }" varStatus="status">
				<div download-del="${noticeFileList.ntfNo }" class="update_download_name">
					<span><i class="fas fa-file"></i> . </span>
					<a href="#">${noticeFileList.ntfOgname }</a>
				</div>
					<span download-del="${noticeFileList.ntfNo }" class="cursor" onclick="uploadDel( ${noticeFileList.ntfNo })">
						<i class="fas fa-trash-alt"></i>
					</span>
			</c:forEach>
			
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
		fCreator: "createSEditor2",


	})
	
	setTimeout(function() { 
		var ctntarea = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector(".se2_inputarea");
		
		ctntarea.addEventListener("keyup", function (e) { 
			
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
