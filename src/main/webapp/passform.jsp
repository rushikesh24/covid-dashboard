<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="/coviddashboard/css/component.css">
	<link rel="stylesheet" href="/coviddashboard/css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/component.css">
	<style>
		.row-content{
			height: auto;
		}
	</style>

</head>

<body onload="loadStateList()">

	<nav class="navbar navbar-dark navbar-expand-md fixed-top">
		<div class="container">
			<a class="navbar-brand mr-3" href="index.jsp">Covid Dashboard</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="Navbar">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="index.jsp"><span class="fa fa-home fa-lg"></span>
							Home</a></li>
					<li class="nav-item"><a class="nav-link" href="districtwisedata.html"><span
								class="fa fa-info fa-lg"></span>Districts wise</a></li>

					<li class="nav-item dropdown active">
						<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pass</a>
						<div class="dropdown-menu">
							<a href="passform.jsp" class="dropdown-item">Generate Pass</a>
							<a href="passform.jsp#danny" class="dropdown-item">Check Status</a>
						</div>
					</li>
					<li class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Get Report</a>
						<div class="dropdown-menu">
							<a href="downloadexcelfiile" class="dropdown-item">Download now!</a>
							<a href="#" id="getreportmail" class="dropdown-item">Get Report on Email</a>
						</div>
					</li>
				</ul>
				<ul class="nav navbar-nav ml-auto" id="login">
					<li class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Admin</a>
						<div class="dropdown-menu dropdown-menu-right">
							<a href="login.html" class="dropdown-item">Login</a>
							<a href="login.html" class="dropdown-item">Sign up</a>
						</div>
					</li>
				</ul>
				<ul class="nav navbar-nav ml-auto" id="logout" style="display: none;">
					<li class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Admin</a>
						<div class="dropdown-menu dropdown-menu-right">
							<a href="admin/displaypasslist" class="dropdown-item">Approve Pass</a>
							<a href="admin/displayuserlist" class="dropdown-item">Approve User</a>
							<div class="dropdown-divider"></div>
							<a href="logout" class="dropdown-item">Logout</a>
						</div>
					</li>
				</ul>

			</div>
		</div>
	</nav>
	<div id="getmailmodal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Get Detailed document on Email</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<h6 id="status" style="color: rgb(0, 153, 115);"></h6>
					<form>
						<div class="form-row">
							<div class="form-group col-12">
								<label class="sr-only col-2" for="exampleInputEmail3">Email address</label>
								<input type="email" class="form-control form-control-sm mr-1" id="email"
									placeholder="Enter email">
							</div>
						</div>
						<div class="form-row">
							<button type="button" class="btn btn-secondary ml-auto" data-dismiss="modal">Cancel</button>
							<button onclick="sendMail()" class="btn btn-primary ml-1">Send mail</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>




	<div class="container">

		<div class="row row-content justify-content-center">
			<div class="col-12 col-md-10 col-lg-8">
				<div class="row align-items-center  mb-3">
					<div class="col-12">
						<h2>Admin Section</h2>
					</div>
				</div>
				<ul class="nav nav-tabs p-0 ">
					<li class="nav-item col-6 p-0 justify-content-center">
						<a class="nav-link active text-center" href="#peter" role="tab" data-toggle="tab">Check Pass Status</a>
					</li>
					<li class="nav-item col-6 p-0 justify-content-center">
						<a class="nav-link text-center" href="#danny" role="tab" data-toggle="tab">Generate Pass</a>
					</li>

				</ul>


				<div class="tab-content">
					<div role="tabpanel" class="tab-pane dafe show active" id="peter">
						<div class="row row-content">
							<div class="col-12">
								<h4 style="color: orange;"><b>${ message }</b></h4>
							</div>
							<div class="col-12">
								<form action="pass_status" method="POST">
									<div class="form-group row">
										<label for="firstname" class="col-md-3 col-form-label">Pass ID</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="passID"
												placeholder="Enter Pass ID assigned to you">
										</div>
									</div>
									<div class="form-group row">
										<label for="lastname" class="col-md-3 col-form-label">Aadhar Number</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="aadharnumber" name="aadharnumber"
												placeholder="Enter 12 Digit Aadhar Number">
										</div>
									</div>
									
									<div class="form-group row">
										<div class="col-md-9 ml-auto">
											<button type="submit" class="btn btn-primary">
												Check Status
											</button>
										</div>
									</div>
								</form>
							</div>
						
						</div>
					</div>
					<div role="tabpanel" class="tab-pane dafe" id="danny">
						<div class="row row-content">
							<div class="col-12">
								<h4  style="color: orange;"><b>${ message }</b></h4>
							</div>
							<div class="col-12">
								<form action="generatepass" method="POST" id="form">
									<div class="form-group row">
										<label for="firstname" class="col-md-3 col-form-label">Name</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="name"
												placeholder="Name must be matched to aadhar card">
										</div>
									</div>
									<div class="form-group row">
										<label for="lastname" class="col-md-3 col-form-label">Aadhar Number</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="lastname" name="aadharnumber"
												placeholder="Enter 12 Digit Aadhar Number">
										</div>
									</div>
									<div class="form-group row">
										<label for="telnum" class="col-12 col-md-3 col-form-label">Source State</label>
										<div class="col-12 col-md-9">
											<select name="sourceStateList" id="sourceStateList" name="sourceStateList"
												class="form-control" onchange="loadSourceDistrictList(this)">
												<option value="select">select</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="telnum" class="col-12 col-md-3 col-form-label">Source Districts</label>
										<div class="col-md-9">
											<select select name="sourceDistrictList" id="sourceDistrictList" class="form-control" >
												<option value="select">select</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="telnum" class="col-12 col-md-3 col-form-label">Source State</label>
										<div class="col-12 col-md-9">
											<select name="destinationStateList" id="destinationStateList" name="sourceStateList"
												class="form-control" onchange="loadDestinationDistrictList(this)">
												<option value="select">select</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="telnum" class="col-12 col-md-3 col-form-label">Source Districts</label>
										<div class="col-md-9">
											<select select name="destinationDistrictList" id="destinationDistrictList" class="form-control" >
												<option value="select">select</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="email" class="col-md-3 col-form-label">Reason to Travel</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="reason" name="reason"
												placeholder="State the Reason">
										</div>
									</div>
									<div class="form-group row">
										<label for="email" class="col-md-3 col-form-label">Reason to Travel</label>
										<div class="col-md-9">
											<input type="date" class="form-control" id="date" name="date"
												placeholder="">
										</div>
									</div>
									<div class="form-group row">
										<label for="feedback" class="col-md-3 col-form-label">Your feedback</label>
										<div class="col-md-9">
											<textarea class="form-control" id="comment" name="comment"
												placeholder="Enter comment if any in 100 words" rows="7"></textarea>
										</div>
									</div>
									<div class="form-group row">
										<div class="offset-md-3 col-md-9">
											<button type="submit" class="btn btn-primary">
												Send Request
											</button>
										</div>
									</div>
								</form>
							</div>
						
						</div>

					</div>
				</div>
			</div>
		</div>

		<pre>




				</dfn>
			</dfn></aside>
		</pre>



		<script src="js/jquery.min.js" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
		<script src="js/bootstrap.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
		<script src="/coviddashboard/js/passform.js"></script>
		<script src="/coviddashboard/js/script.js"></script>


</body>

</html>