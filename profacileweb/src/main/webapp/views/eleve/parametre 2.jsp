<jsp:include page="header.jsp" />
	<!-- Contact -->

	<div class="contact">
		
		<!-- Contact Map -->

		<!-- Contact Info -->

		<div class="contact_info_container">
			<div class="container">
				<div class="row">

					<!-- Contact Form -->
					<div class="col-lg-6">

<h4>Modifier Information personnelle</h4>
<div class="form">		
<form method="POST" action="/profacileweb/student/compte">				
<input type="text" class="comment_input" name="nom" placeholder="Nom" value="${eleve.nom}" required="required">
<input type="text" class="comment_input" name="prenom" placeholder="Prénom" value="${eleve.prenom}" required="required">
<input type="text" class="comment_input" name="adresse" value="${eleve.adresse}" placeholder="Adresse">
<input type="text" class="comment_input" name="telephone" value="${eleve.telephone}" placeholder="Téléphone">
<input type="text" class="comment_input" name="pays" value="${eleve.pays}" placeholder="Pays" >
<input type="text" class="comment_input" name="ville" value="${eleve.ville}" placeholder="Ville">
<input type="text" class="comment_input" name="classe" value="${eleve.classe}" placeholder="Classe" required="required">
<button type="submit" class="comment_button trans_200">Modifier</button>
</form>		
</div>	
<p>&nbsp;&nbsp;</p>			
<h4>Modifier Mot de passe</h4>
<p>Aucune information disponible pour l'instant</p>
<% 
String resp = (String) request.getAttribute("response");

if(resp==null){
	
	
}else{
	
	if(resp == "1"){
		out.println("<h4 style='color:red;'>Les nouveaux mots de passe ne correspondent pas</h4>");
	}
	if(resp == "2"){
		out.println("<h4 style='color:red;'>Ancien Mot de passe erroné</h4>");
	}
	if(resp == "0"){
		out.println("<h4 style='color:green;'>Mise à jour effectué</h4>");

	}
}
%>
<%-- } --%>


        
						<div class="form">	
							<form method="POST" action="/profacileweb/admin/parametre" class="counter_form_content d-flex flex-column align-items-center justify-content-center">
							<input type="password" class="comment_input" name="passwordold" placeholder="Ancien mot de passe" required="required">
							<input type="password" class="comment_input" name="passwordnew1" placeholder="Nouveau Mot de passe:" required="required">
							<input type="password" class="comment_input" name="passwordnew2" placeholder="Reprendre Nouveau Mot de passe:" required="required">
							
					
							<button type="submit" class="comment_button trans_200">Modifier</button>
						</form>
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
