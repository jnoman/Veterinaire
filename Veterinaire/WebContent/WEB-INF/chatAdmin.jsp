<body>
	<%@include file="menu.jsp"%>
	<div class="container-message mt-5 pt-5">
		<div class="row g-0">
			<div class="col-sm-5 col-md-5 connexion-img">
				<img src="images/vector2.png">
			</div>
			<div class="col-6 col-md-7 pt-5">
				<div class="div-user1">
					<ul class="list-user">
						<c:forEach var="user" items="${ ListUsers }">
							<li class="userClient" value="${ user.getId() }"><p>${ user.getNomComplet() }</p></li>
						</c:forEach>
					</ul>
				</div>
				<div class="div-chat">
					<div class="div-username">
						<p class="div-username1">sélectionner un client</p>
					</div>
					<div class="div-message">
						<ul class="list-message"></ul>
					</div>
					<input type="text" class="text-message"
						placeholder="Écrivez votre message ...">
					<button class="btn-submit" >
						<i class="fa fa-paper-plane" aria-hidden="true"></i>
					</button>
				</div>
			</div>
		</div>
	</div>
	<script>
	var idUser;
	var ws;
	$(function() {
		$('.userClient').click(
				function() {
					idUser = $(this).attr('value');
					$('.div-username1').text( $(this).find('p').text());
					if(idUser) {
						ws = new WebSocket("ws://localhost:8080/Veterinaire/ChatRoomEndPoint/user"+idUser);
						ws.onmessage = function processMessage(message){
							var jsonData = JSON.parse(message.data);
							$(".text-message").val('');
							$(".text-message").focus();
							$(".div-message").animate({ scrollTop: $(".div-message")[0].scrollHeight}, 1000);
							if(jsonData.role == "client"){
								$('.list-message').append('<li class="message2"><p>'+jsonData.message+'</p></li>');
							} 
							else {
								$('.list-message').append('<li class="message1"><p>'+jsonData.message+'</p></li>');
							}
						};
						$('.list-message').empty()
						$.ajax({    
							   url: "chatAdmin?id=" + idUser,
							   type: 'POST',
							   dataType: 'json',
							   contentType: 'application/json; charset=utf-8',
							   success: function (data) {
								   for (var i in data){
								      if(data[i].sender){
											$('.list-message').append('<li class="message2"><p>'+data[i].text+'</p></li>');
										} 
										else {
											$('.list-message').append('<li class="message1"><p>'+data[i].text+'</p></li>');
										}
								   }
							   },
							});

						$(".text-message").val('');
						$(".text-message").focus();
						$(".div-message").animate({ scrollTop: $(".div-message")[0].scrollHeight}, 1000);
					}

				});
	});
	$(function() {
		$('.btn-submit').click(
				function() {
					textMessage = $('.text-message').val()
					if(textMessage) {
						$.post("chat?textMessage=" + textMessage + "&id=" + idUser).always(
								function(resp) {
									ws.send(textMessage);
								});
					}

				});
	});
	</script>
</body>
</html>