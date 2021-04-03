<jsp:include page="header.jsp" />
	<div class="home">
		<div class="breadcrumbs_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="breadcrumbs">
							<ul>
								<li><a href="index.html">Home</a></li>
								<li>Contact</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>			
	</div>

	<!-- Contact -->

	<div class="contact">
		
		<!-- Contact Map -->

		<div class="contact_map">

			<!-- Google Map -->
			
			<div class="map">
				<div id="google_map" class="google_map">
					<div class="map_container">
						<div id="map"></div>
					</div>
				</div>
			</div>

		</div>

		<!-- Contact Info -->

		<div class="contact_info_container">
			<div class="container">
				<div class="row">

					<!-- Contact Form -->
					<div class="col-lg-6">
						<div class="contact_form">
							<div class="contact_info_title">Remplir le formulaire de contact</div>
							<form action="#" class="comment_form">
								<div>
									<div class="form_title">Nom</div>
									<input type="text" class="comment_input" required="required">
								</div>
								<div>
									<div class="form_title">Email</div>
									<input type="text" class="comment_input" required="required">
								</div>
								<div>
									<div class="form_title">Message</div>
									<textarea class="comment_input comment_textarea" required="required"></textarea>
								</div>
								<div>
									<button type="submit" class="comment_button trans_200">Envoyer</button>
								</div>
							</form>
						</div>
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
							<div class="contact_info_title">Contact Info</div>
							<div class="contact_info_text">
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel gravida arcu. Vestibulum feugiat, sapien ultrices fermentum congue, quam velit venenatis sem.</p>
							</div>
							<div class="contact_info_location">
								<div class="contact_info_location_title">Camerron Office</div>
								<ul class="location_list">
									<li>Douala,rue foch</li>
									<li>+237 655744484</li>
									<li>info@gmail.com</li>
								</ul>
							</div>
							<div class="contact_info_location">
								<div class="contact_info_location_title">Australia Office</div>
								<ul class="location_list">
									<li>Forrest Ray, 191-103 Integer Rd, Corona Australia</li>
									<li>1-234-567-89011</li>
									<li>info@gmail.com</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />