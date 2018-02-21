<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Online Shopping - ${title}</title>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<link href="${css}/bootstrap-materia-theme.min.css" rel="stylesheet">
<!-- Custom styles for this template -->


<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<link href="${css}/myapp.css" rel="stylesheet">
<script>
	window.menu = '${title}';
	window.contextRoot = '${pageContext.request.contextPath}';
</script>
</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->

			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="${contextRoot}/home">Home</a>
					</div>
				</div>
			</nav>

		<!-- Page Content -->
		<div class="content">
		<div class="row">
		
			<div class="col-md-6 col-md-offset-3">
			<c:if test="${not empty message}">
				 <div class="col-md-12 col-xs-12">
				 	<div class="alert alert-warning">
				 		${message}
				 	</div>
				 </div>
			</c:if>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Login</h4>
					</div>
					
					<div class="panel-body">
						<form action="${contextRoot}/login" method="POST" class="form-horizontal" id="loginForm" >
							
							<div class="form-group">
								<label class="control-label col-md-4" for="username">Email</label>
								<div class="col-md-8">
									<input name="username" id="username" type="text" class="form-control" placeholder="Email"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4" for="password">Password</label>
								<div class="col-md-8">
									<input name="password" type="text" class="form-control" placeholder="Last Name"/>
								</div>
							</div>
					
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" class="btn btn-primary" value="login" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>	
		</div>

		</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.min.js"></script>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- Jquery Validate -->
		<script src="${js}/jquery.validate.js"></script>
		
		<!-- Fontawesome icon -->
		<script src="${js}/fontawesome-all.js"></script>
		

		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
