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
<% 
Integer size = (Integer)request.getAttribute("size");
String error = (String)request.getAttribute("is");
%>

<p><a href="../coach/offres?is">Retour aux offres disponibles </a></p>
<h2>Offres Postulées</h2>

		<% if(size!=0){ %>
								<table class="tab">
							
							<c:forEach var="listes" items="${lesoffres}">
									<tr>
										<td>Cours ${listes.cours.intitule} <br/>Niveau:${listes.niveau} <br/>publier le:${listes.datepub}<br/>Par ${listes.eleve.nom} ${listes.eleve.prenom}<br/></td>
										<td></td>
									</tr>
									
								</c:forEach>
								</table>
							
							
		<% }else{ %>	
			<span style="color:black;">pas d'offre disponible actuellement</span>
		<% } %>
	

<div class="form">			
</div>	

				</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
							<div class="contact_info_title"></div>
							<div class="contact_info_text">
								<p>Bienvenue sur Profacile Administration. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel gravida arcu. Vestibulum feugiat, sapien ultrices fermentum congue, quam velit venenatis sem</p>

							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter" style="display:none;">
		<div class="newsletter_background" style="background-image:url(${pageContext.request.contextPath}/resources/images/newsletter_background.jpg)"></div>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter_container d-flex flex-lg-row flex-column align-items-center justify-content-start">

						<!-- Newsletter Content -->
							<div class="newsletter_content text-lg-left text-center">
							<div class="newsletter_title">Inscrivez vous à notre Newsletter et recevez des promotions</div>
							<div class="newsletter_subtitle">Abonnez-vous aux dernières actualités sur les smartphones et aux meilleures offres que nous proposons</div>
						</div>

						<!-- Newsletter Form -->
						<div class="newsletter_form_container ml-lg-auto">
					<form method="POST" action="/profacileweb/parametre" class="counter_form_content d-flex flex-column align-items-center justify-content-center">
							<input type="password" class="comment_input" name="password" placeholder="Ancien mot de passe" required="required">
							<input type="password" class="comment_input" name="password" placeholder="Nouveau Mot de passe:" required="required">
							<input type="password" class="comment_input" name="password" placeholder="Reprendre Nouveau Mot de passe:" required="required">
							
					
							<button type="submit" class="comment_button trans_200">Modifier</button>
						</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
