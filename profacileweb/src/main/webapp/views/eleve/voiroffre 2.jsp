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
							<div class="contact_info_title">
							<h2>Offre cours de ${offre.cours.intitule} ${offre.niveau}.</h2>
						
						<table class="tab">
							
							<c:forEach var="listes" items="${postulant}">
									<tr>
										<td>${listes.nom} ${listes.prenom} <br/><span style="color:green;font-weight:bold;">${listes.montant} / Heure</span> <br/><span style='color:#ccc;font-size:13px;'><i>${listes.description}</i></span> <br/>${listes.email} <br/>${listes.telephone}<br/><a href="#" style="text-decoration:underline"> Telecharger CV ${listes.cv}</a> </td>
										<td><a href="../student/valideroffre?l=${offre.id}&email=${listes.email}"> Accepter</a></td>
									</tr>
									
							</c:forEach>
								</table>
							
							
						</div>
					</div>
			</div>
					<!--siderbar -->
					
					<jsp:include page="siderbar.jsp" />
					
					
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
