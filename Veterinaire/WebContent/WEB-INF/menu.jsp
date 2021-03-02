<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand"
			href="${pageContext.servletContext.contextPath}">Vétérinaire</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<c:choose>
				<c:when test="${! empty sessionScope.logged }">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.servletContext.contextPath}">Home</a></li>
						<li class="nav-item nav-item1"><a class="nav-link"
							href="chat">Question / Réponse</a></li>
						<li class="nav-item nav-item1"><a class="nav-link"
							href="rendezVous">Rendez vous</a></li>
					</ul>
					<form class="d-flex"
						style="width: 100%; display: inline-block !important;"
						action="deconnexion" method="get">
						<button class="btn btn-outline-success" style="float: right;"
							type="submit">Déconnexion</button>
					</form>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.servletContext.contextPath}">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="connexion">Connexion</a></li>
						<li class="nav-item"><a class="nav-link" href="inscription">Inscription</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>
