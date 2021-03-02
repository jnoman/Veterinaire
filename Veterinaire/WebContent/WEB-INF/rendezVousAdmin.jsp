<body>
	<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
	<%@include file="menu.jsp"%>
	<div class="container mt-5 pt-5">
		<c:choose>
			<c:when test="${ !empty listRendezVous }">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">nom client</th>
							<th scope="col">titre</th>
							<th scope="col">date</th>
							<th scope="col">durée</th>
							<th scope="col">accepter</th>
							<th scope="col">refusée</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rendezVous" items="${ listRendezVous }">

							<tr>
								<th scope="row">${ rendezVous.getUser().getNomComplet() }</th>
								<td>${ rendezVous.gettitreRendezVous() }</td>
								<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${rendezVous.getDateDebut()}" /></td>
								<td>${ rendezVous.getDuree() } minute</td>
								<td>
									<form action="rendezVous?id=${ rendezVous.getIdRendezVous() }" method="post">
										<button type="submit" class="btn btn-primary" name="accepter">accepter</button>
									</form>
								</td>
								<td>
									<form action="rendezVous?id=${ rendezVous.getIdRendezVous() }" method="post">
										<button type="submit" class="btn btn-danger" name="refusee">refusée</button>
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
</body>
</html>