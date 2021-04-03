<jsp:include page="header.jsp" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- Contact -->
<div class="contact">
		
		<!-- Contact Map -->

		<!-- Contact Info -->

		<div class="contact_info_container">
			<div class="container">
				<div class="row">
<% String nom = (String)request.getAttribute("nom");
String prenom = (String)request.getAttribute("prenom");
%>
					<!-- Contact Form -->
					<div class="col-lg-6">
						<div class="contact_form">
							<div class="contact_info_title">Bienvenue <%= prenom %>, Votre  planning de cours</div>
							<table class="tab">
								<c:forEach var="mescours" items="${mescours}">
									<tr>
										<td>Cours: <span style="font-weight:bold;">${mescours.offre.cours.intitule} </span><br/>Niveau:<span style="font-weight:bold;">${mescours.offre.niveau} </span><br/>eleve:<span style="font-weight:bold;">${mescours.offre.eleve.nom} ${mescours.offre.eleve.prenom}</br>Email:${mescours.offre.eleve.email}</br>Telephone:${mescours.offre.eleve.telephone}</span>
										</td>
									</tr>
									
								</c:forEach>
								</table>
						</div>
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
<div class="contact_info_title"> </div>
								 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel gravida arcu. Vestibulum feugiat, sapien ultrices fermentum congue, quam velit venenatis sem
								
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
