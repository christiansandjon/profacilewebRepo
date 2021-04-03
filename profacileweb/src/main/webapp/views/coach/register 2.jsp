<jsp:include page="headerlog.jsp" />
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
							<div class="contact_info_title">Créer un compte Profacile Coach</div>
					<% String error = (String)request.getAttribute("error");
					
					if(error == "true"){
						
					%>
					<p style='text-align:center;'><span style="color:red;">Cet Email est déjà utilisé</span></p>
					<% } %>
					<form method="POST" action="/profacileweb/coach/register1" class="counter_form_content d-flex flex-column align-items-center justify-content-center">
							<input type="text" class="comment_input" name="nom" placeholder="Votre Nom (Obligatoire):" required="required">
							<input type="text" class="comment_input" name="prenom" placeholder="Votre Prenom :" required="required">
							<input type="email" class="comment_input" name="email" placeholder="Votre Email (Obligatoire):" required="required">
						
							
							<input type="password" class="comment_input" name="motpasse" placeholder="Votre Mot de passe (Obligatoire):" required="required">
							
					
							<button type="submit" class="comment_button trans_200">Continuer ></button>
							<p><a href="/profacileweb/coach/">Déjà inscrit? Se connecter</a></p>
						</form>
						</div>
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
							<div class="contact_info_title"></div>
							<div class="contact_info_text">
								<p>Bienvenue sur Profacile Coach. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel gravida arcu. Vestibulum feugiat, sapien ultrices fermentum congue, quam velit venenatis sem</p>
								<p><a href="../student/">Devenez Élève</a></p>
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
							<form action="#" id="newsletter_form" class="newsletter_form d-flex flex-row align-items-center justify-content-center">
								<input type="email" class="newsletter_input" placeholder="Votre Email" required="required">
								<button type="submit" class="newsletter_button">S'inscrire</button>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
