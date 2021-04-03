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
								
								
								<h2>Mes offres de cours</h2>
								<c:forEach items="${lesoffres}" var="em">
							        <li>0</li>
							        <li></li>
								 </c:forEach>
						</div>
					</div>

					<!-- Contact Info -->
					<div class="col-lg-6">
						<!-- Siderbar -->
					<jsp:include page="siderbar.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp" />