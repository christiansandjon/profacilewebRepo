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
<h1>Param?tres</h1>
<p>Aucune information disponible pour l'instant</p>
<% 
String resp = (String) request.getAttribute("response");

if(resp==null){
	
	
}else{
	
	if(resp == "1"){
		out.println("<h4 style='color:red;'>Les nouveaux mots de passe ne correspondent pas</h4>");
	}
	if(resp == "2"){
		out.println("<h4 style='color:red;'>Ancien Mot de passe erron?</h4>");
	}
	if(resp == "0"){
		out.println("<h4 style='color:green;'>Mise ? jour effectu?</h4>");

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
								<p>Bienvenue sur Profacile. Le site de soutien ? domicile con?u pour des ?tudiants en difficult?s. Grace ? nos remises ? niveau tr?s ?fficaces, votre enfant pourra reussir son ann?e  Administration</p>
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
							<div class="newsletter_title">Inscrivez vous ? notre Newsletter et recevez des promotions</div>
							<div class="newsletter_subtitle">Abonnez-vous aux derni?res actualit?s sur le soutien scolaire et aux meilleures offres que nous proposons</div>
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
