<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chat</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="/webjars/webstomp-client/1.2.6/dist/webstomp.min.js"></script>
</head>
<body>
	<input type="hidden" value="${chatRoom.roomId}" id="roomId">
	<input type="hidden" value="${sessionScope.login}" id="login">
	<div>
        <h1 id="roomName">${chatRoom.name}</h1>
        <div>
            <div id="msgArea"></div>
                <div class="input-group mb-3">
                    <input type="text" id="msg">
                    <div>
                        <button type="button" id="button-send">전송</button>
                    </div>
                </div>
        </div>
     </div>
</body>

<script>
	$(document).ready(function(){
		const roomName = $("#roomName").text();
		const roomId = $("#roomId").val();
		const username = $("#login").val();
		
		console.log("roomName: " + roomName);
		console.log("roomId: " + roomId);
		console.log("username: " + username);
		
		/* const websocket = new WebSocket("ws://localhost:8080/ws/chat");
		console.log("websocket");
		console.log(websocket); */
		// console.log(websocket);
		
		let sockJs = new SockJS("/stomp/chat"); // registerStompEndpoints - registry.addEndpoint("/stomp/chat")
		// var sockJs = new SockJS("http://localhost:8080/ws/chat", null, {transports: ["websocket", "xhr-streaming", "xhr-polling"]});
		console.log("sockJs");
		console.log(sockJs);
		
		var stomp = webstomp.over(sockJs);
		console.log(stomp);
		
		
		// 2. connection이 이루어지면 실행할 콜백함수
		stomp.connect({}, function(){
			
			console.log("STOMP connected!");
			//3. send(path, header, message)로 메세지를 보낼 수 있음
            stomp.send('/pub/chat/enter', JSON.stringify({roomId: roomId, writer: username}));
			
			//4. subscribe(path, callback)으로 메세지를 받을 수 있음
            stomp.subscribe("/sub/chat?roomId=" + roomId, function (chat) {
            	console.log("subscribe!!!");
            	console.log("chat");
            	console.log(chat.body);
            	
                var content = JSON.parse(chat.body);
            	console.log(content);

                var writer = content.writer;
                let message = content.message;
                console.log("SUBSCRIBE")
                console.log(writer + ": " + message);
                var str = '';

                if(writer === username){
                    str = "<div>";
                    str += "<div>";
                    str += "<b>" + writer + " : " + message + "</b>";
                    str += "</div></div>";
                } else {
                    str = "<div>";
                    str += "<div>";
                    str += "<b>" + writer + " : " + message + "</b>";
                    str += "</div></div>";
                }
                
                $("#msgArea").append(str);
		}); 
		
        $("#button-send").on("click", function(e){
                var msg =  $("#msg").val();
                console.log(username + ":" + msg);
                
                stomp.send('/pub/chat/message', JSON.stringify({roomId: roomId, message: msg, writer: username}));
                $("#msg").val('');
            });
        });
/*         
        function send(){
        	let msg = document.getElementById("msg");
        	
        	console.log(username + ": " + msg.value);
        	sockJs.send(username + ":" + msg.value);
        	msg.value = '';
        }
        
        function onClose(evt) {
            const str = username + "님이 퇴장하였습니다."
            sockJs.send(str);
        }
        
        function onOpen(evt) {
	      	const str = username + "님이 입장하였습니다."
	      	sockJs.send(str);
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
       } */
	});

</script>
</html>