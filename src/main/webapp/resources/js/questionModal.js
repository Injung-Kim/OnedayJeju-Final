$(document).ready(function(){
	$('#btnImg').click(function(){
		$('input[name="upload"]').click()
	})
	$('#uploadFiles').change(function(){
		var files = $('#uploadFiles')[0].files
		for(var i = 0; i < files.length ;i++){
			console.log(files[i].name)
			$('.thumbnail').append(
					$('<span></span>').attr('id',i).html(files[i].name)
					.append($('<button class="deleteTag">&times;</button>').attr('id', i)))
		}
		
	})
	
	//태그추가 폼 보여주기
	 $('.addTag').click(function(){
		if(!$('#makeTag').is(':visible'))
			$('#makeTag').show(1000)
			$('.tagSubmit').show(1000)
			$('#addTag').hide()
	})
	// 태그 폼 ajax
	$('.tagSubmit').click(function(){
		var tagName = $('input[name="tagName"]').val()
		$.ajax({
			type: "get"
			, url: "/qna/insert/tag"
			, data : {'tagName' : tagName}
			, dataType : "json"
			, success : function(data){
				console.log("ajax성공")
				console.log(data.hashtag.tagName)
				//태그이름 보여주기
				$('.tagbox').append(
					$('<span></span>').attr('id',data.hashtag.tagNo).html('#' + data.hashtag.tagName).fadeIn(800)
					.append($('<button class="deleteTag">&times;</button>').attr('id', data.hashtag.tagNo))
				)
				//질문글 폼에 태그번호 전달
				$('.writeQst').append(
					$('<input type="checkbox"/>').attr({'name':'tagNo'
														, 'value':data.hashtag.tagNo
														, 'style':'display:none;'
														, 'checked':'checked'})
				)
				//지우기 버튼 클릭시 태그번호, 태그이름 모두 삭제
				$('.deleteTag[id='+ data.hashtag.tagNo + ']').click(function(){
					$('#'+data.hashtag.tagNo).remove()
					$('input[value='+ data.hashtag.tagNo +']').remove()
				})	
				
			}
			, error : function(){
				console.log("ajax실패")

			}
		})
		//태그 추가 후 초기화
		 $('input[name="tagName"]').val("")
	})//$('.tagSubmit') ajax end
	
	$('.sendQst').click(function(){
		var check = confirm('질문을 등록하시겠습니까?')
		if(check){
			console.log('질문 등록 확인')
			$('#qstForm').submit()
		}
	})
	
	
})//$(document).ready end
