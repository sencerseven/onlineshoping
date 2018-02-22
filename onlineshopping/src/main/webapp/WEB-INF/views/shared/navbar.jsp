<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          
            <li id="about">
              <a class="nav-link" href="${contextRoot}/about">About</a>
            </li>
            <li id="listProducts">
              <a class="nav-link" href="${contextRoot}/show/all/products">View Products</a>
            </li>
            <li id="contact">
              <a class="nav-link" href="${contextRoot}/contact">Contact</a>
            </li>
            
            <!--  ADMIN MANAGEMENT TABS -->
            <security:authorize access="hasAuthority('ADMIN')">
	            <li id="manageProducts">
	              <a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a>
	            </li>
            </security:authorize>
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
          <security:authorize access="isAnonymous()">
          	<li id="register">
              <a class="nav-link" href="${contextRoot}/register">Sign Up</a>
            </li>
            <li id="Login">
              <a class="nav-link" href="${contextRoot}/login">Login</a>
            </li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
	            <li class="nav-item dropdown">
	            		<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="download" aria-expanded="false">${userModel.fullName}<span class="caret"></span></a>
	            		
	  
	            		<div class="dropdown-menu" aria-labelledby="download">
	            		
	            			<security:authorize access="hasAuthority('USER')">
			                <a class="dropdown-item" href="${contextRoot}/cart">
			                	<span class="fas fa-shopping-cart"></span>
			                0</a>
		                
		                		<div class="dropdown-divider"></div>
		               </security:authorize>
		               <a class="dropdown-item" href="${contextRoot}/logout">Logout</a>
		               
	              </div>
	            </li>
            </security:authorize>
          </ul>
          
        </div>
      </div>
    </nav>
	<script>
		window.userRole = '${userModel.role}';
	</script>