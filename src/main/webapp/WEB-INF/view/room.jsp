<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="/webjars/webstomp-client/1.2.6/dist/webstomp.min.js"></script>
</head>
<body>
	<h1>${room.name}</h1>
	<input type="text" id="msg" class="form-control">
    <div>
        <button type="button" id="button-send">전송</button>
    </div>
</body>

<script>
	$(document).ready(function(){
		
		const roomName = ${room.name};
		const roomId = ${room.roomId};
		const username = 
		
		
	});

</script>


</html>