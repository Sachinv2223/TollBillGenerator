<!-- Jsp page for veiwing user feedback details by admin
Used Bootstrap version 4.6 for css
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">

<title>Admin|UserFeedback</title>
</head>
<body style="background-color: #f0f1f2;">

	<!-- Start Navbar  -->

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="#">Toll Bill Generator</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/toHomepageAdmin">Home <span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<a href="/logoutAdmin" class="btn btn-light my-2 my-sm-0"
					type="submit">Logout</a>
			</form>
		</div>
	</nav>

	<!-- End Navbar  -->

	<!-- Login form  -->

	<div class="container p-4">
		<div class="row">
			<div class="col-md-12 offset-md-0">
				<div class="card">


					<!-- Alert  -->
					<div class="mx-5 mt-3">

						<%-- <h5 style="color: green;" align="center">${logMsg}</h5> --%>

						<c:if test="${not empty logMsg}">
							<div class="alert alert-success text-center" role="alert">
								<strong><c:out value="${logMsg}" /></strong>
							</div>
							<c:set var="logMsg" value="" scope="session" />
						</c:if>


					</div>
					<!-- Alert  -->



					<!-- card header -->
					<div class="card-header">
						<a class="btn btn-success float-right"
							href="/downloadUserFeedbackAdmin">Download</a>

						<h4 class="text-primary text-center">User Feedback Details</h4>
					</div>

					<!-- card Body  -->
					<div class="card-body">

						<c:set var="count" value="0" scope="page" />
						<table class="table table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th class="text-center" scope="col">Sl.No</th>
									<th class="text-center" scope="col">User ID</th>
									<th class="text-center" scope="col">Full Name</th>
									<th class="text-center" scope="col">Response 01</th>
									<th class="text-center" scope="col">Response 02</th>
									<th class="text-center" scope="col">Response 03</th>
									<th class="text-center" scope="col">Response 04</th>
									<th class="text-center" scope="col">Response 05</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach var="m" items="${userFeedbackList}">
									<c:set var="count" value="${count + 1}" scope="page" />
									<tr>
										<td class="text-center">${count}</td>
										<td class="text-center">${m.uid}</td>
										<td class="text-center">${m.ufullName}</td>
										<td class="text-center">${m.resp01}</td>
										<td class="text-center">${m.resp02}</td>
										<td class="text-center">${m.resp03}</td>
										<td class="text-center">${m.resp04}</td>
										<td class="text-center">${m.resp05}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<a href="/toHomepageAdmin" class="btn btn-primary float-right">Back</a>

					</div>

				</div>
			</div>
		</div>
	</div>




	<!-- Login form  -->























	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2" crossorigin="anonymous"></script>
    -->
</body>
</html>