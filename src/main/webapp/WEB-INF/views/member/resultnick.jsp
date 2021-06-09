<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>


<script type="text/javascript">

//변경 완료 & 중복 alert 띄워주기
var message = "${msg}";
var url = "${url}";
alert(message);
document.location.href = url;

		
</script>
