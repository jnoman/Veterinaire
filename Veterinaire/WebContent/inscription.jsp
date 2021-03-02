<body>
	<%@include file="WEB-INF/menu.jsp"%>

	<div class="container mt-5 pt-5">
		<div class="row g-0">
			<div class="col-sm-6 col-md-8 connexion-img">
				<img src="images/vector1.jpg">
			</div>
			<div class="col-6 col-md-4 pt-5">
				<div class="register-show">
				<h2>Inscription</h2>
				<form action="inscription" method="post">
					<input type="text" placeholder="Nom complet" name="nomComplet" minlength="4" required>
					<input type="text" placeholder="Email" name="email" required>
					<input type="password" placeholder="Mot de passe" name="password" minlength="8" required>
					<button type="submit" class="btn btn-primary">Inscription</button>
				</form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>