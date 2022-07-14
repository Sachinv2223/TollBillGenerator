<!-- Jsp page for viewing adding tolldetails form manager
Used Bootstrap version 4.6 for css
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>Add Toll|Manager</title>
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
					href="/toHomepageManager">Home <span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<a href="/logoutManager" class="btn btn-light my-2 my-sm-0"
					type="submit">Logout</a>
			</form>
		</div>
	</nav>

	<!-- End Navbar  -->

	<!-- Login form  -->

	<div class="container p-2">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">


					<!-- card header -->
					<div class="card-header">
						<h4 class="text-primary text-center">Add Toll Detail</h4>
					</div>



					<!-- card Body  -->
					<div class="card-body">

						<form class="border border-light pt-1 pb-3 px-5"
							action="/saveTollDetail" method="post">


							<!-- From -->
							<div class="mb-4">
								<input name="fromLocation" class="form-control"
									placeholder="From Location" />
							</div>

							<!-- To -->
							<div class="mb-4">
								<input name="toLocation" class="form-control"
									placeholder="To Location" />
							</div>


							<!-- Vehicle Type -->
							<div class="mb-4">
								<select name="vechtype" class="form-control">
									<option value="" disabled selected hidden="">Choose
										Vehicle Type</option>
									<option value="Two Wheeler">Two Wheeler</option>
									<option value="Three Wheeler">Three Wheeler</option>
									<option value="Four Wheeler">Four Wheeler</option>
								</select>
							</div>

							<!-- Fee -->
							<div class="mb-4">
								<input name="fee" type="number" class="form-control"
									placeholder="Fee" />
							</div>


							<!-- Approval  -->
							<input type="hidden" name="tapproval" value="NOT APPROVED" />


							<button class="btn btn-block btn-primary mt-4 mb-3" type="submit">Save</button>
						</form>
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