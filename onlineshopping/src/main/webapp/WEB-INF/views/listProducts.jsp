<div class="container">

	<div class="row">

		<!-- Would be to display SideBar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		<!-- to display the actual products -->
		<div class="col-md-9">

			<!-- BreadCrumb componend -->
			<c:if test="${userClickAllProducts == true}" >
			<div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="${contextRoot}/Home">Home</a></li>
						<li class="breadcrumb-item active">All Products</li>
					</ol>
				</div>
			</div>
			</c:if>
			
			<c:if test="${userClickCategoryProducts == true}" >
			<div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="${contextRoot}/Home">Home</a></li>
						<li class="breadcrumb-item" >Category</li>
						<li class="breadcrumb-item" class="active">${category.name}</li>
					</ol>
				</div>
			</div>
			</c:if>
		</div>

	</div>


</div>