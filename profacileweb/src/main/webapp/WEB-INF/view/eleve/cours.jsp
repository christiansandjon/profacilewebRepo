<jsp:include page="header.jsp" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<!-- Contact -->
<div class="contact">
<% 
%>		
		<!-- Contact Map -->

		<!-- Contact Info -->

		<div class="contact_info_container">
			<div class="container">
				<div class="row">

					<!-- Contact Form -->
					<div class="col-lg-6">
						<div class="contact_form">
							<div class="contact_info_title">Mes cours</div>
								<table>
								<c:forEach var="mescours" items="${mescours}">
									<tr>
										<td>
										Cours: <span style="font-weight:bold;">${mescours.offre.cours.intitule} </span><br/>
										Niveau:<span style="font-weight:bold;">${mescours.offre.niveau} </span><br/>
										Enseignant:<span style="font-weight:bold;">${mescours.professeur.nom} ${mescours.professeur.prenom}</span>
										<br/>
										</td>
										<td><span style='font-weight:bold;color:green;'>${mescours.professeur.montant} / H</span></td>
									</tr>
									
								</c:forEach>
								</table>	
								<h2>Mes offres en attente</h2>
								<table>
								<c:forEach var="liste" items="${lesoffres}">
									<tr>
										<td>
										Cours:<b>${liste.cours.intitule}</b> <br/>
										Niveau:<b>${liste.niveau} </b><br/>
										publier le:<b>${liste.datepub}</b></td>
										<td><a href="../../student/voiroffre?l=${liste.id}">Professeurs postules</a></td>
									</tr>
									
								</c:forEach>
								</table>
							
						</div>
					</div>

					<!-- Siderbar -->
					<jsp:include page="siderbar.jsp" />
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
