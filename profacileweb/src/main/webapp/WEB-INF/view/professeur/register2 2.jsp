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
							<div class="contact_info_title">Selectionner les matieres que vous professeurez </div>
					<% String error = (String)request.getAttribute("error");
					   int[] idmatieres = (int[])request.getAttribute("listematiereID");
					   String[] listematieres = (String[])request.getAttribute("listematiereIntitule");
					if(error == "true"){
						
					%>
					<p style='text-align:center;'><span style="color:red;"></span></p>
					<% } %>
					<p style="margin-top:1em;">
					<form method="POST" action="/profacileweb/professeur/register2" class="counter_form_content d-flex flex-column align-items-center justify-content-center">
							
							<% for(int i=0;i<listematieres.length;i++){ %>
							
							<div><input type="checkbox" name="matieres"  id="<%= idmatieres[i] %>" value="<%= idmatieres[i] %>"/>  <label for="<%= idmatieres[i] %>"><%= listematieres[i] %> </label> </div>
							
							
							<% } %>
							
							
						
							<input type="decimal" name="montant" placeholder="Votre Montant Horaire du cours" class="comment_input"/>
						
						 <textarea class="comment_input" name="apropos" placeholder="Apropos de vous..."></textarea>
						Votre CV <input type="file" name="cv" class="comment_input">
						<button type="submit" class="comment_button trans_200">Enregistrer</button>
						
						</form>
						
						</div>
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
							<div class="contact_info_title"></div>
							
									dsds
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
							<div class="newsletter_subtitle">Abonnez-vous aux derniares actualites sur les smartphones et aux meilleures offres que nous proposons</div>
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
