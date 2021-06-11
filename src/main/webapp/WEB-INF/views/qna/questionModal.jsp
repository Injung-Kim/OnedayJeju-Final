<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
/* 모달 그림자 창 너비 설정 */
.fade{
	max-width: none;
}
.thumbnail{
	min-height : 30px;
}
.thumbnail span{
	padding : 10px;
	color : #00BD9D;
}
/* 각 태그 요소 */
.tag span{
	border : 1px solid #02b6de;
	border-radius: 30px;
	padding : 8px 12px;
}
/* 태그 작성 버튼  */
.addTag{
	line-height : 35px;
	border-radius: 30px;
	padding : 0px 14px;
	background : #02b6de;
	border : none;
	color : white;
}
.tagForm{
	float : left;
}
/*태그 작성란  */
.tagForm input[name="tagName"]{
	width : 220px;
	border : 1px solid #02B6DE;
	border-radius : 30px;
	display:inline-block;
}
/* 태그 삭제버튼  */
.deleteTag{
	border : none;
	background : #fff;
	color : #ccc;
	padding : 0px 0px 0px 5px;
}
</style>
<script src="/resources/js/questionModal.js"></script>
<!-- 모달창  -->
<div class="modal fade -modal-lg" id="writeQstModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
   		<div class="modal-content">
     		<div class="modal-header">
       		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       		<h4>질문하기</h4>
     		</div>
     		<div class="modal-body">
     		<!--질문글 작성 폼  -->
     		<form action="/qna/write/question" method="post" id="qstForm" enctype="multipart/form-data">
     		<div class="form-group">
     		<label for="title" class="sr-only">제목</label>
       		<input type="text" placeholder="제목을 입력하세요" name="title"  class="form-control" style="width : 80%; display: inline-block; margin-right : 10px;">
			<img src="/resources/image/image.png" alt="업로드 이미지" id="btnImg" style="width : 35px; height : 35px; cursor : pointer;"/><br>
			</div>
			<div class="form-group">
       		<textarea placeholder="내용을 입력하세요" name="qstContent" class="form-control" rows="8"></textarea>
       		</div>
			<div class="fileup">
			<input multiple="multiple" type="file" id="uploadFiles" name="upload" style="display:none;"/>
			</div>
			<div class="writeQst">
			</div>
       		</form>
       		<!--질문글 작성 폼 end  -->
       		
     	 	<div class="thumbnail">
     	 	
     	 	</div>
     	 	<div class="tag" style="text-align: right;">
     	 		<!-- 태그 작성 폼  -->
     	 		<form action="/qna/insert/tag" method="post" class="tagForm">
     	 		<input type="text" id="makeTag" name="tagName" placeholder="추가할 태그를 입력하세요" class="form-control" style="display:none;" required="required"/>
     	 		<button type="button" class="tagSubmit addTag" style="display:none;">태그 추가</button>     	 		
     	 		</form>
     	 		<div class="tagbox" style="display : inline-block;">
     	 		</div>
     	 		<button type="button" class="addTag" id="addTag">태그 추가</button>     	 		
     	 	</div>
     	 	</div>
      		<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">작성취소</button>
        	<button type="button" class="btn btn-primary sendQst">질문하기</button>
     	 	</div>
    	</div><!--modal.content  -->
  	</div><!--modal.dialog  -->
</div><!--modal.fade  -->
