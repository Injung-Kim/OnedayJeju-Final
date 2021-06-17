<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" href="/resources/css/notice.css" type="text/css">

<script type="text/javascript">
$(document).ready(function() {
	
	
    $('#ntcContent').on('keyup', function() {
    	console.log(this)
    	
    	fnChkByte(this, 1000);
    });
    
    
})

function reportClick( ntcNo ){
	
	menuOpenClose( ntcNo );
	
	$("[data-ntcNo='"+ntcNo+"'] + .modal_wrap").css('display','flex').hide().fadeIn();
	
}

function modalClose( ntcNo ){
	$("[data-ntcNo='"+ntcNo+"'] + .modal_wrap").fadeOut();
}

function report( ntcNo ) {
	console.log(ntcNo);

	if( confirm("신고 하시겠습니까?") ) {
		
		$.ajax({
			type: "post"
			, url: "/notice/comment/report"
			, dateType: "json"
			, data: {
				ntcNo:ntcNo
				, ntcrContent: $("[type='radio']:checked").val()
			}
			, success: function(data) {
				
					
				if(data.isReport) {
						
					alert("신고가 완료 되었습니다")
					
					
				}
					
			}
			
			, error: function() {
				console.log("error");
			}
			
		})
		
		modalClose( ntcNo );
		$("[data-ntcNo='"+ntcNo+"'] .urd_btn").hide();
		
	}
	
}


function downLoad( ntfNo ) {
	
	if ( confirm("다운로드를 하시겠습니까?") ) {
	
		location.href="/notice/download?ntfNo="+ntfNo;
	 
	}
	
	
}

function fnChkByte(obj, maxByte) {
	var maxByte = 1000;
	var str = obj.value
	var str_len = str.length;
	
	var rbyte = 0;
	var rlen = 0;
	var one_char = "";
	var str2 = "";
	
	for (var i=0; i<str_len; i++) {
		one_char = str.charAt(i);
		
		if (escape(one_char).length > 4) {
			rbyte +=3;//한글
		} else {
			rbyte++//영어
		}
		
		if(rbyte <= maxByte) {
			rlen = i +1; //return할 문자열 갯수				
		}
	}
	
	
	
	if(rbyte > maxByte) {
		
		str2 = str.substr(0, rlen);
		obj.value = str2;
		fnChkByte(obj,maxByte)
	} else {
		
			document.getElementById('content_cnt').innerHTML = rbyte + " / " + 1000;
		
	}
	
	
	
}

function menuOpenClose( ntcNo ) {
	
	
	
	if ( $("[data-ntcNo='"+ntcNo+"'] .menu_box").hasClass("hide") === true ){
	
		$("[data-ntcNo='"+ntcNo+"'] .menu_box").removeClass("hide");
	
	} else {
		
		$("[data-ntcNo='"+ntcNo+"'] .menu_box").addClass("hide");
	}
}

function cmtDelYN(ntcNo) {
	
	menuOpenClose( ntcNo );
	
	$.ajax({
		type: "post"
		, url: "/notice/comment/delyn"
		, dateType: "json"
		, data: { ntcNo: ntcNo }
		, success: function(data) {
			
			if(data.ntcDelete == 'yes') {
				
				if(confirm("삭제 하시겠습니까?")) {
					
					$("[data-ntcNo='"+ntcNo+"']")
						.html( $("<div>").addClass("rel ntcviewdel_box")
							.append($("<span>").text("작성자에 의해 삭제되었습니다") )
							.append($("<button>").attr("onclick","cmtDelYN("+ntcNo+")").addClass("abs").text("복구") ) )
				}
				
			} else {
				
				if(confirm("복구 하시겠습니까?")) {
					location.reload();	
				}
				
			}
			
		}
		, error: function() {
			console.log("error")
			

		}
	
	})
	
}

function ntUpdate( ntNo ) {
	
	if(confirm("수정 하시겠습니까?")) {
		location.href='/notice/update?ntNo='+${notice.ntNo}
	}
}
function ntDelete( ntNo ) {
	
	if(confirm("삭제 하시겠습니까?")) {
		location.href='/notice/delete?ntNo='+${notice.ntNo}
	}
}

function cmtUpd( ntcNo ) {
	
	var ntcContent = $("[data-ntcNo='"+ntcNo+"'] .ntc_content_box span").text();
	
		$("[data-ntcNo='"+ntcNo+"'] .ntc_content_box")
			.html( $("<textarea>").addClass("ntcUpdContent").text(ntcContent) )
			.append( $("<hr>").css({"margin-top":"10px", "margin-bottom":"0"}))
			.append( $("<button>").attr("onclick", "ntcUpd("+ntcNo+")").addClass("abs cmtupd_btn box_shadow bor_rad_ten").text("수정") )
			
	menuOpenClose( ntcNo );
	
}
	
function ntcUpd( ntcNo ) {
	
	if(confirm("댓글 수정을 하시겠습니까?")) {
		$.ajax({
			type: "post"
			, url: "/notice/comment/update"
			, dateType: "json"
			, data: {
				ntcNo:ntcNo
				, ntcContent: $("[data-ntcNo='"+ntcNo+"'] .ntcUpdContent").val()
			}
			, success: function(data) {
				
// 				$("[data-ntcNo='"+ntcNo+"'] .ntc_content_box")
// 					.html($("<span>").text( data.ntc.ntcContent ) )
				
				location.reload();
					
			}
			
			, error: function() {
				console.log("error");
			}
			
		})
	
	}
	
}

function likeCk( ntcNo ) {
	
	console.log("likeCk" + ntcNo)
	
	$.ajax({
		type: "post"
		, url: "/notice/like"
		, dateType: "json"
		, data: {
			ntcNo:ntcNo
			, userId: '${id }'
		}
		, success: function(data) {
			
			if( data.result ) {//좋아요
				$("[data-ntcNo='"+ntcNo+"'] .rec_box")
					.html($("<img>").attr("src", "/resources/image/like_click.png") );
			
			} else { //좋아요 취소
				$("[data-ntcNo='"+ntcNo+"'] .rec_box")
					.html($("<img>").attr("src", "/resources/image/like.png") );
				
			}
			
			$("[data-ntcNo='"+ntcNo+"'] .recCnt_box span").text(data.cnt);
			
		}
		, error: function() {
				console.log("error");
		}
	})
	
}

function disLikeCk( ntcNo ) {
	
	console.log("likeCk" + ntcNo)
	
	$.ajax({
		type: "post"
		, url: "/notice/dislike"
		, dateType: "json"
		, data: {
			ntcNo:ntcNo
			, userId: '${id }'
		}
		, success: function(data) {

			if( data.result ) {//싫어요
				$("[data-ntcNo='"+ntcNo+"'] .nonrec_box")
					.html($("<img>").attr("src", "/resources/image/dislike_click.png") );
			
			} else { //싫어요 취소
				$("[data-ntcNo='"+ntcNo+"'] .nonrec_box")
					.html($("<img>").attr("src", "/resources/image/dislike.png") );
				
			}

			$("[data-ntcNo='"+ntcNo+"'] .nonrecCnt_box span").text(data.cnt);
			
		}
		, error: function() {
				console.log("error");
		}
	})
	
}
	

</script>




<div class="container">
	
	<div class="view_box bor_rad_five">
	
		<div class="top">
			<h3 class="sub">${notice.ntTitle }</h3>
		</div>
	
		<div class="view_info">
				<ul>
					<li>${notice.ntNo }</li>
					<li>|</li>
					<li>${notice.ntDiv }</li>
					<li>|</li>
					<li><fmt:formatDate value="${notice.ntUtime }" pattern="yy-MM-dd HH:mm:ss" /></li>
					<li>조회수</li>
					<li>|</li>
					<li>${notice.hit }</li>
				</ul>
		</div>
		
		<div class="con">${notice.ntContent }</div>
	
	</div>
	
	<div class="rel mid">
	
	<c:forEach var="noticeFileList" items="${noticeFileList }" varStatus="status">
		<div class="download_name">
			<span><c:out value="${status.count }" /> . </span><a onclick="downLoad( ${noticeFileList.ntfNo } )" href="#">${noticeFileList.ntfOgname }</a>
		</div>
	</c:forEach>
		
		
		<button class="abs list_btn box_shadow bor_rad_ten" onclick="location.href='/notice/list'">목록</button>
		
		<c:if test="${grade < 2 }">
		<button class="abs update_btn box_shadow bor_rad_ten" onclick="ntUpdate(${notice.ntNo})">수정</button>
		<button class="abs delete_btn box_shadow bor_rad_ten" onclick="ntDelete(${notice.ntNo})">삭제</button>
		</c:if>
		
	</div>
	
	<form action="/notice/comment/insert" method="post">
		<input type="hidden" name="ntNo" value="${notice.ntNo }">
		<input type="hidden" name="ntcId" value="${id }">
			<div class="rel cmt_box bor_rad_five">
				<div class="cnt_box">
					<img src="/resources/image/user.png">
					<span>${id }</span><br>
					<textarea rows="3" cols="30" id="ntcContent" name="ntcContent" placeholder="댓글 내용을 적어주세요"></textarea>
				</div>
				<hr style="margin-top: 10px; margin-bottom:0;">
				<span id="content_cnt"> 0 / 1000 </span>
				<button class="abs write_btn box_shadow bor_rad_ten" id="cmtbtn">등록</button>
		</div>
	</form>

	<hr style="border: 1px solid #8BD7D2;">
	
	<h4 style="margin-left: 20px;">댓글 : ${paging.totalCount }</h4>

	<hr style="border: 1px solid #8BD7D2;">
	
	
	<div id="cmtviewAll_box">
	
	
		<c:forEach var="cmtList" items="${noticeCommentList }" varStatus="status" >
			<c:choose>
				<c:when test="${cmtList.ntcDelete ne 'yes' }">
					<div class="rel cmtview_box" data-ntcno="${cmtList.ntcNo }" >
					
						<c:if test="${login }">
						
							<c:if test="${isNr[status.index] || id eq cmtList.ntcId }">
								<div class="abs urd_btn cursor" onclick="menuOpenClose(${cmtList.ntcNo })">
									<img src="/resources/image/ntc_menu.png"/>
								</div>
							</c:if>

						</c:if>
					
						<div class="profile_box">
							
							<div class="pp_img_box">
								<img src="/resources/image/user.png">
							</div>
							
							<span class="block" id="ntc_id">${fn:substring(cmtList.ntcId,0,4)}****</span>
							<c:choose>
							
								<c:when test="${empty cmtList.ntcUtime }">
									<span class="block"><fmt:formatDate value="${cmtList.ntcCtime }" pattern="yy-MM-dd , HH:mm:ss"/></span>
								</c:when>
								
								<c:otherwise>
									<span class="block"><fmt:formatDate value="${cmtList.ntcUtime }"/></span>
								</c:otherwise>
								
							</c:choose>
							
						</div>
						
						<div class="ntc_content_box">
							<span>${cmtList.ntcContent }</span>
						</div>
						
						<div class="abs menu_box hide" >
							<ul>
						
							<c:choose>
								<c:when test="${id eq cmtList.ntcId }">
									<li>
										<button id="cmtbtn_del" onclick="cmtDelYN(${cmtList.ntcNo})">삭제</button>
									</li>
									
									<li>
										<button id="cmtbtn_upd" onclick="cmtUpd( ${cmtList.ntcNo} )">수정</button>
									</li>
								</c:when>					
									
								<c:otherwise>
									<li>
										<button id="cmtbtn_rep" onclick="reportClick( ${cmtList.ntcNo} )">신고</button>
									</li>
								</c:otherwise>	
							</c:choose>
							
							</ul>
						</div>
						
						<div class="rel ntrc_rec_nonrec_btn">
							
							<div onclick="likeCk(${cmtList.ntcNo })" class="abs rec_box cursor">
							
								<c:choose>
									<c:when test="${isLike[status.index] }">
										<img src="/resources/image/like_click.png"/>
									</c:when>
									
									<c:otherwise>
										<img src="/resources/image/like.png">
									</c:otherwise>
	
								</c:choose>
							
							</div>
							
							<div class="abs recCnt_box">
								<span>${cmtList.likeCnt }</span>
							</div>
							
							<div onclick="disLikeCk(${cmtList.ntcNo })" class="abs nonrec_box cursor">
								<c:choose>
								
									<c:when test="${isDisLike[status.index] }">
										<img src="/resources/image/dislike_click.png"/>
									</c:when>
									
									<c:otherwise>
										<img src="/resources/image/dislike.png"/>
									</c:otherwise>
	
								</c:choose>
							</div>	
				
							<div class="abs nonrecCnt_box">
								<span>${cmtList.disLikeCnt }</span>
							</div>
						</div>
					</div>
				</c:when>
				
				<c:otherwise>
					<div class="rel cmtview_box" data-ntcno="${cmtList.ntcNo }">
						<div class="rel ntcviewdel_box">
							<span>작성자에 의해 삭제되었습니다</span>
							<button onclick="cmtDelYN(${cmtList.ntcNo})" class="abs">복구</button>
						</div>
					</div>
				</c:otherwise>
				
			</c:choose>
			

		<div class="modal_wrap" style="z-index: 1;" >
			
			<div class="modal_box box_shadow bor_rad_ten">
				
				<h3>신고 하기</h3>
			
				<div class="cmt_info">
							
					<div><span>작성자 : </span><span>${fn:substring(cmtList.ntcId,0,4)}****</span></div>
					<div><span>내용 : </span><span>${cmtList.ntcContent }</span></div>
			
				</div>
			
				<h4>사유 선택</h4>
			
			
			
				<div class="rep_content_box bor_rad_five">
			
					<label>
						<input type="radio" name="content" value="스팸홍보/도배글입니다." checked="checked"/>스팸홍보/도배글입니다.
					</label><br>
					<hr style="margin: 5px 0; border-color: #ccc;">
				
					<label>
						<input type="radio" name="content" value="음란물입니다." />음란물입니다.
					</label><br>
					<hr style="margin: 5px 0; border-color: #ccc;">
				
					<label>
						<input type="radio" name="content" value="불법정보를 포함하고 있습니다." />불법정보를 포함하고 있습니다.
					</label><br>
					<hr style="margin: 5px 0; border-color: #ccc;">
				
					<label>
						<input type="radio" name="content" value="청소년에게 유해한 내용입니다." />청소년에게 유해한 내용입니다.
					</label><br>
					<hr style="margin: 5px 0; border-color: #ccc;">
					
					<label>
						<input type="radio" name="content" value="욕설/생명경시/혐오/차별적 표현입니다." />욕설/생명경시/혐오/차별적 표현입니다.
					</label><br>
					<hr style="margin: 5px 0; border-color: #ccc;">
					
					<label>
						<input type="radio" name="content" value="개인정보 노출 게시물입니다." />개인정보 노출 게시물입니다.
					</label><br>
					<hr style="margin: 5px 0; border-color: #ccc;">
					
					<label>
						<input type="radio" name="content" value="불쾌한 표현이 있습니다." />불쾌한 표현이 있습니다.
					</label><br>
				
				
				</div>
				<hr style="border-color: #ccc;">
				
				<div class="rel rep_btn_box">
					<button class="abs report_btn box_shadow bor_rad_five" onclick="report(${cmtList.ntcNo })">신고 하기</button>
					<input type="button" class="abs repcancle_btn box_shadow bor_rad_five" onclick="modalClose( ${cmtList.ntcNo } )" value="창 닫기"/>
				</div>
				
			</div>
		
		</div>
		
		</c:forEach>	

	</div>

<%-- 페이징 JSP --%>
<jsp:include page="/WEB-INF/views/util/ntcPaging.jsp" />

</div><!-- .container -->



<c:import url="/WEB-INF/views/layout/footer.jsp" />

