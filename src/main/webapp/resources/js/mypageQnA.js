$(document).ready(function(){
//서브 메뉴바 불러오기
$('#qnaBtn').click(function(){
	$(this).attr("style","background : #8bd7d2;")
	console.log("클릭함")
	var content = '<div class="qnaMenu">'
					+ '<button class="question">작성한 질문 </button>'
					+ '<button class="answer">작성한 답변글 </button>'
					+ '<button class="liked">좋아요한 글</button>'
					+'</div>'
					+'<div class="qna_board_content"></div>'
	$('.user_board_content').children().html('')
	$('.user_board_content').html(content);
	
	//서브 메뉴 이벤트
	
	//질문글 조회
	$('.question').click(function(){
		console.log($(this).nextAll())
	$(this).attr("style","background : #8bd7d2;")
	$(this).nextAll().attr("style","background : #ddd;")
		
		$.ajax({
			type : "get"
				, url : "/qna/mypage/question"
				, data : {}
				, dataType : "html"
				, success : function(res){
					console.log("qna Ajax 성공")
					$('.qna_board_content').html(res)
					
				}
				, error : function(){
					console.log("qna Ajax 실패")					
				}
			})//$.ajax() end
		})//$('.question').click end
	//답변글 조회
	$('.answer').click(function(){
	$(this).next().attr("style","background : #ddd;")
	$(this).prev().attr("style","background : #ddd;")
	$(this).attr("style","background : #8bd7d2;")
		$.ajax({
			type : "get"
				, url : "/qna/mypage/answer"
				, data : {}
				, dataType : "html"
				, success : function(res){
					console.log("qna Ajax 성공")
					$('.qna_board_content').html(res)
				}
				, error : function(){
					console.log("qna Ajax 실패")					
				}
			})//$.ajax() end
		})//$('.answer').click end
		
	//좋아요한 글 조회
	$('.liked').click(function(){
		$(this).attr("style","background : #8bd7d2;")
		$(this).prevAll().attr("style","background : #ddd;")
		$.ajax({
			type : "get"
				, url : "/qna/mypage/like"
				, data : {}
				, dataType : "html"
				, success : function(res){
					console.log("qna Ajax 성공")
					$('.qna_board_content').html(res)
				}
				, error : function(){
					console.log("qna Ajax 실패")					
				}
			})//$.ajax() end
	})//$('.liked').click end
	})//$('#qnaBtn').click end	
})//$(document).ready end
function pagingSelect(pagingNumber){
	$.ajax({
		type : "get"
			, url : "/qna/mypage/question"
			, data : { 
				curPage:pagingNumber
			}
			, dataType : "html"
			, success : function(res){
				console.log("qna Ajax 성공")
				$('.qna_board_content').html(res)
			}
			, error : function(){
				console.log("qna Ajax 실패")					
			}
		})//$.ajax() end	
}














