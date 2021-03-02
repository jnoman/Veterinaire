<body>
	<%@include file="WEB-INF/menu.jsp"%>

	<div class="container mt-5 pt-5">
	
	<c:if test="${ !empty erreur }">
			<p style="color: red;">
				<c:out value="${ erreur }" />
			</p>
		</c:if>

		<c:if test="${ !empty sessionScope.succes }">
			<p style="color: green;">
				<c:out value="${ sessionScope.succes }" />
				<c:remove var="succes" scope="session" />
			</p>
		</c:if>
		<div class="row g-0">
			<div class="col-sm-6 col-md-8 connexion-img">
				<img src="images/vector1.jpg">
			</div>
			<div class="col-6 col-md-4 pt-5">
				<div class="register-show">
				<h2>Connexion</h2>
				<form action="connexion" method="post">
					<input type="text" placeholder="Email" name="email" required>
					<input type="password" placeholder="Mot de passe" name="password" minlength="8" required>
					<button type="submit" class="btn btn-primary">Connexion</button>
				</form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>