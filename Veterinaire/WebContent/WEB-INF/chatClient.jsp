<body>
	<%@include file="menu.jsp"%>

	<div class="container mt-5 pt-5">
		<div class="row g-0">
			<div class="col-sm-5 col-md-6 connexion-img">
				<img src="images/vector2.png">
			</div>
			<div class="col-6 col-md-6 pt-5">
				<div class="div-chat">
					<div class="div-username">
						<p>doctor</p>
					</div>
					<div class="div-message">
						<ul class="list-message">
							<c:forEach var="message" items="${ ListMessage }">
								<c:choose>
									<c:when test="${ message.isSender() }">
										<li class="message1"><p>${ message.getText() }</p></li>
									</c:when>
									<c:otherwise>
										<li class="message2"><p>${ message.getText() }</p></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
					<input type="text" class="text-message"
						placeholder="Écrivez votre message ...">
					<button class="btn-submit">
						<i class="fa fa-paper-plane" aria-hidden="true"></i>
					</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		var requete;
		var textMessage;
		$(function() {
			$('.btn-submit').click(
					function() {
						textMessage = $('.text-message').val()
						if(textMessage) {
							$.post("chat?textMessage=" + textMessage).always(
									function(resp) {
										ws.send(textMessage);
									});
						}

					});
		});

		$(".div-message").animate({ scrollTop: $(".div-message")[0].scrollHeight}, 1000);
		var ws = new WebSocket("ws://localhost:8080/Veterinaire/ChatRoomEndPoint/user"+${sessionScope.logged.getId()});
		ws.onmessage = function processMessage(message){
			var jsonData = JSON.parse(message.data);
			$(".text-message").val('');
			$(".text-message").focus();
			$(".div-message").animate({ scrollTop: $(".div-message")[0].scrollHeight}, 1000);
			if(jsonData.role == "client"){
				$('.list-message').append('<li class="message1"><p>'+jsonData.message+'</p></li>');
			} 
			else {
				$('.list-message').append('<li class="message2"><p>'+jsonData.message+'</p></li>');
			}
		};
	</script>
</body>
</html>