<!-- Jsp page for registration of user
Used Bootstrap version 4.6 for css
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<title>Register|User</title>
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
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
			</ul>
		</div>
	</nav>

	<!-- End Navbar  -->

	<!-- Login form  -->

	<div class="container p-2">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body"></div>


					<form:form class="border border-light pt-1 pb-3 px-5"
						action="/regUser" method="post" modelAttribute="user">

						<p class="h4 mb-4 text-center">User Registration</p>



						<!-- Full Name -->
						<!-- <input type="text" name="ufullName" class="form-control mb-4"
							placeholder="Full Name"> -->
						<div class="mb-4">
							<form:input path="ufullName" class="form-control"
								placeholder="Full Name" />
							<form:errors path="ufullName" cssStyle="color: #ff0000;" />
						</div>



						<!-- Age -->
						<!-- <input type="number" name="uage" class="form-control mb-4"
							placeholder="Age"> -->
						<div class="mb-4">
							<form:input path="uage" class="form-control" placeholder="Age" />
							<form:errors path="uage" cssStyle="color: #ff0000;" />
						</div>



						<!-- Address 
						<textarea name="uaddress" class="form-control mb-4"
							placeholder="Address"></textarea> -->
						<div class="mb-4">
							<form:textarea path="uaddress" class="form-control"
								placeholder="Address" />
							<form:errors path="uaddress" cssStyle="color: #ff0000;" />
						</div>


						<!-- Email
						<input type="email" name="uemail" class="form-control mb-4"
							placeholder="E-mail"> -->
						<div class="mb-4">
							<form:input path="uemail" class="form-control"
								placeholder="Email" />
							<form:errors path="uemail" cssStyle="color: #ff0000;" />
							<span style="color: red">${errMsg}</span>
						</div>

						<!-- uvtype -->
						<div class="mb-4">
							<form:label path="uvtype">Vehicle Type</form:label>
							<form:select path="uvtype" name="uvtype" class="form-control">
								<form:option value="Two Wheeler">Two Wheeler</form:option>
								<form:option value="Three Wheeler">Three Wheeler</form:option>
								<form:option value="Four Wheeler">Four Wheeler</form:option>
							</form:select>
						</div>

						<!-- uvNumber -->
						<div class="mb-4">
							<form:input path="uvNumber" class="form-control"
								placeholder="Vehicle Number" />

						</div>

						<!-- Username
						<input type="text" name="uuserName" class="form-control mb-4"
							placeholder="Username"> -->
						<div class="mb-4">
							<form:input path="uuserName" class="form-control"
								placeholder="Username" />
							<form:errors path="uuserName" cssStyle="color: #ff0000;" />
						</div>


						<!-- password
						<input type="password" name="upassword" class="form-control mb-4"
							placeholder="Password">  -->
						<div class="mb-4">
							<form:password path="upassword" class="form-control"
								placeholder="Password" />
							<form:errors path="upassword" cssStyle="color: #ff0000;" />
						</div>


						<button class="btn btn-block btn-primary mt-4 mb-3" type="submit">Register</button>
					</form:form>


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