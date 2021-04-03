<jsp:include page="header.jsp" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- Contact -->
<div class="contact">
		
		<!-- Contact Map -->

		<!-- Contact Info -->

		<div class="contact_info_container">
			<div class="container">
				<div class="row">

					<!-- Contact Form -->
					<div class="col-lg-6">
						<div class="contact_form">
							<div class="contact_info_title">Mes Enseignants</div>
									<table class="tab">
							
							<c:forEach var="listes" items="${lesenseigants}">
									<tr>
										<td> Enseignant : <b>${listes.coach.nom} ${listes.coach.prenom} </b><br/>Contact:<b>${listes.coach.telephone}</b></br> Email:<b>${listes.coach.email}</b>  <br/>Cours dispensé:<b>${listes.offre.cours.intitule}</b> <br/> <a href="#" style="text-decoration:underline;">Télecharger son CV</a></td>
										<td></td>
									</tr>
									
								</c:forEach>
								</table>
						</div>
					</div>

					<!-- siderbar -->
					<jsp:include page="siderbar.jsp" />
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
