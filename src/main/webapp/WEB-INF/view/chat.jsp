<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chat</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
</head>
<body>
	<input type="hidden" value="${sessionScope.login}" id="login">
	<div>
        <div class="col-6">채팅방</div>
        <div>
            <div id="msgArea"></div>
                <div class="input-group mb-3">
                    <input type="text" id="msg">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button-send">전송</button>
                    </div>
                </div>
        </div>
     </div>
</body>

<script>
	$(document).ready(function(){
		const username = $("#login").val();
		console.log("username: " + username);
		
		const websocket = new WebSocket("ws://localhost:8080/ws/chat");
		console.log("websocket");
		console.log(websocket);
		// console.log(websocket);
		
		let sockJs = new SockJS("http://localhost:8080/ws/chat");
		// var sockJs = new SockJS("http://localhost:8080/ws/chat", null, {transports: ["websocket", "xhr-streaming", "xhr-polling"]});
		console.log("sockJs");
		console.log(sockJs);
		
		
		websocket.onmessage = onMessage;
        websocket.onopen = onOpen;
        websocket.onclose = onClose;
        
        $("#button-send").on("click", (e) => {
        	console.log("button-send 클릭");
        	send();
        });
        
        function send(){
        	let msg = document.getElementById("msg");
        	
        	console.log(username + ": " + msg.value);
        	websocket.send(username + ":" + msg.value);
        	msg.value = '';
        }
        
        function onClose(evt) {
            const str = username + "님이 퇴장하였습니다."
            websocket.send(str);
        }
        
        function onOpen(evt) {
	      	const str = username + "님이 입장하였습니다."
	      	websocket.send(str);
        }
        
       function onMessage(msg){
    	   console.log("onMessage function");
    	   
    	   let data = msg.data;
    	   let sessionId = "유저1"; // 로그인한 척 한 값을 넣고 있음
    	   let message = null;
    	   let arr = data.split(":");
    	   
    	   for(let i = 0; i < arr.length; i++){
    		   console.log('arr[' + i + ']: ' + arr[i]);
    	   }
    	   
    	   let cur_session = username;
    	   sessionId = arr[0];
    	   message = arr[1];
    	   
    	   
           if(sessionId == cur_session){
				var str = "<div class='col-6'>";
				str += "<div class='alert alert-secondary'>";
				str += "<b>" + sessionId + " -- " + message + "</b>";
				str += "</div></div>";
				
				$("#msgArea").append(str);
           } else {
				var str = "<div class='col-6'>";
				str += "<div class='alert alert-warning'>";
				str += "<b>" + sessionId + " -- " + message + "</b>";
				str += "</div></div>";
				
				$("#msgArea").append(str);
           }
    	   
       }
       
       
	});

</script>
</html>