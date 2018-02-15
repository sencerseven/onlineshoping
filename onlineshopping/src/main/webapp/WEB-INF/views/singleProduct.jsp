<div class="container">
	<div class=row>
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
				<li class="breadcrumb-item"><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="breadcrumb-item active">${product.name }</li>
			</ol>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${images}/products/${product.code}.jpg" class="img img-responsive img-thumbnail">
			</div>
		</div>
		
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name}</h3>
			<hr/>
			
			<p>${product.description}</p>
			<hr />
			
			<h4>Price : <strong>${product.unitPrice} &#8378 /-</strong></h4>
			<hr />
			
			
			
			<c:choose>
				<c:when test="${product.quantity < 1}">
					<h6>Qty. Available <span style=" color:red ">Out of Stock!</span></h6>
				</c:when>
				<c:otherwise>
					<h6>Qty. Available ${product.quantity}</h6>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${product.quantity < 1}">
					<a href="javascript:void(0)" class="btn btn-success disabled"><strike><span class="fas fa-cart-plus"></span><span>Add To Cart</span></strike></a>
				</c:when>
				<c:otherwise>
					<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success"><span class="fas fa-cart-plus"></span><span>Add To Cart</span></a>
				</c:otherwise>
			</c:choose>
			
			
		<a href="${contextRoot}" class="btn btn-success"><span></span><span>Back</span></a>
		</div>
	</div>

</div>