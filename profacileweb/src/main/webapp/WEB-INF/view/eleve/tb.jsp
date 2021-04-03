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
							<table>
								<c:forEach var="mescours" items="${mescours}">
									<tr>
										<td>Cours: <span style="font-weight:bold;">${mescours.offre.cours.intitule} </span><br/>
										Niveau:<span style="font-weight:bold;">${mescours.offre.niveau} </span><br/>
										Enseignant:<span style="font-weight:bold;">${mescours.professeur.nom} ${mescours.professeur.prenom}</span><br/>
										<a href="#" style="text-decoration:underline;">Telecharger son CV</a>
										</td>
										<td><span style='font-weight:bold;color:green;'>${mescours.professeur.montant} / H</span></td>
									</tr>
									
								</c:forEach>
								</table>
						</div>
					</div>

					<!--siderbar -->
					
					<jsp:include page="siderbar.jsp" />
					
					
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
