<body>
	<%@include file="menu.jsp"%>
	<div class="container mt-5 pt-5">
		<h3>Ajouter Rendez-vous</h3>
		<form action="rendezVous" method="post">
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="titre">title</label> <input type="text"
						class="form-control" name="titre" placeholder="titre" required>
				</div>
				<div class="form-group col-md-4">
					<label for="datedebut">date</label> <input type="date"
						class="form-control" id="datedebut" name="datedebut">
				</div>
				<div class="form-group col-md-4">
					<label for="duree">durée (minute)</label> <input type="number"
						class="form-control" id="duree" name="duree" min="10" max="30"
						required>
				</div>
			</div>
			<button type="submit" class="btn btn-primary" name="ajouter">ajouter</button>
		</form>


		<div class="container-message mt-5 pt-5">
			<c:choose>
				<c:when test="${ !empty listRendezVous }">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">titre</th>
								<th scope="col">date</th>
								<th scope="col">durée</th>
								<th scope="col">état</th>
								<th scope="col">supprimer</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rendezVous" items="${ listRendezVous }">

								<tr>
									<td>${ rendezVous.gettitreRendezVous() }</td>
									<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${rendezVous.getDateDebut()}" /></td>
									<td>${ rendezVous.getDuree() } minute</td>
									<td>
										<c:choose>
											<c:when test="${ rendezVous.getEtat() == 0 }">en attende</c:when>
											<c:when test="${ rendezVous.getEtat() == 1 }">accepter</c:when>
											<c:when test="${ rendezVous.getEtat() == 2 }">refusée</c:when>
										</c:choose>
									</td>
									<td>
										<form action="rendezVous?id=${ rendezVous.getIdRendezVous() }" method="post">
											<button type="submit" class="btn btn-danger" name="supprimer">supprimer</button>
										</form>
									</td>
										
								</tr>

							</c:forEach>
						</tbody>
					</table>

				</c:when>
				<c:otherwise>
					<h4 class="text-center">Aucune rendez-vous</h4>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script>
		document.getElementById("datedebut").valueAsDate = new Date();
		document.getElementById("datedebut").min = new Date().toISOString()
				.split("T")[0];
	</script>
</body>
</html>