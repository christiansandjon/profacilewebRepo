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
<form method="POST" action="/profacileweb/professeur/compte">
<input type="text" class="comment_input" name="nom" placeholder="Nom" value="${professeur.nom}" required="required">
<input type="text" class="comment_input" name="prenom" placeholder="Prenom" value="${professeur.prenom}" required="required">
<input type="text" class="comment_input" name="adresse" value="${professeur.adresse}" placeholder="Adresse">
<input type="text" class="comment_input" name="telephone" value="${professeur.telephone}" placeholder="Telephone">
<input type="text" class="comment_input" name="pays" value="${professeur.pays}" placeholder="Pays" >
<input type="text" class="comment_input" name="ville" value="${professeur.ville}" placeholder="Ville">
	
</div>	
<p>&nbsp;&nbsp;</p>			
<% 
String resp = (String) request.getAttribute("response");
String[] listecours = (String[]) request.getAttribute("listecours2");

if(resp==null){
	
	
}else{
	
	if(resp == "1"){
		out.println("<h4 style='color:red;'>Les nouveaux mots de passe ne correspondent pas</h4>");
	}
	if(resp == "2"){
		out.println("<h4 style='color:red;'>Ancien Mot de passe errone</h4>");
	}
	if(resp == "0"){
		out.println("<h4 style='color:green;'>Mise e jour effectue</h4>");

	}
}
%>
<%-- } --%>


        
						<div class="form" style="display:none;">	
							

						</div>												
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
							<div class="contact_info_title"></div>
							<div class="contact_info_text">
								<div class="form">		
								<h4>Matieres enseignees</h4>
								

							<% for (int i=0;i<listecours.length;i++){ %>
							<div><label style="font-weight:bold;"> <%= listecours[i] %></label> </div>
						
							<% } %>
							
							<input type="decimal" name="montant" placeholder="Votre Montant Horaire du cours" class="comment_input" value="${professeur.montant}"/>
						
						 <textarea class="comment_input" name="description" placeholder="Apropos de vous...">${professeur.description}</textarea>
						Votre CV <input type="file" name="cv" class="comment_input">
							
								<button type="submit" class="comment_button trans_200">Modifier</button>


</form>		
</div>	
							
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
							<div class="newsletter_title">Inscrivez vous a notre Newsletter et recevez des promotions</div>
							<div class="newsletter_subtitle">Abonnez-vous aux dernieres actualites sur les smartphones et aux meilleures offres que nous proposons</div>
						</div>

						<!-- Newsletter Form -->
						<div class="newsletter_form_container ml-lg-auto" style="display:none;">
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
