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
						<div class="contact_form">
							<div class="contact_info_title">Mes cours</div>
								ici des cours....
								
								
								<h2>Vos offres en attente.</h2>
								<c:forEach items="${lesoffres}" var="em">
							        <li>0</li>
							        <li></li>
								 </c:forEach>
						</div>
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<div class="contact_info">
<div class="contact_info_title">Créer un cours </div>
								<form class="counter_form_content d-flex flex-column align-items-center justify-content-center" action="#">
							<select class="comment_input" name="counter_select" id="counter_select">
								<option>Votre niveau scolaire</option>
								<option>6eme</option>
								<option>5eme</option>
								<option>4eme</option>
							</select>
							<select name="counter_select" id="counter_select" class="comment_input">
								<option>Matiere</option>
								<option>Maths</option>
								<option>physique</option>
								<option>chimie</option>
							</select>
					
							<button type="submit" class="comment_button trans_200">Créer</button>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp" />
