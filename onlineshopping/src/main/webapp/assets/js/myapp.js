$(function(){
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;

	case 'Contact':
		$('#contact').addClass('active');
		break;
		
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
		
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
		
	default:
		if(menu == 'Home') break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
		}
	
	// code for jquery dataTable
	//create a dataset
	
	
	var $table = $('#productListTable');


	//execute the below code only where we have this table
	if($table.length){
		var jsonUrl = '';
		if(categoryId ==''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}else{
			
			jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products';
		}
		
		$table.DataTable({
			lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','All Records']],
			pageLength:5,
			ajax:{
				url:jsonUrl,
				dataSrc: ''
			},
			columns:[
				{
					data:'code',
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/products/'+data+'.jpg" class="dataTablesImages"/>';
					}
				},
				{
					data:'name',
					
				},
				{
					data:'brand',
					
					
				},
				{
					data:'unitPrice',
					mRender: function(data,type,row){
						return  data +' &#8378;';
					}
					
				},
				{
					data:'quantity',
					mRender: function(data,type,row){
						
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
					
				},
				{
					data: 'id',
					mRender: function(data,type,row){
						var str = '';
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="fas fa-eye"> </span></a> &#160';
						
						if(row.quantity < 1 ){
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="fas fa-cart-plus"> </span></a>';
							
						}else{
							str += '<a href="'+window.contextRoot+'/card/add/'+data+'/product" class="btn btn-success"><span class="fas fa-cart-plus"> </span></a>';
						}
						
						
						return str;
					}
				}
			]
			
		});
	}
	
	//dissmissing the alert after 3 seconds
	
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function()  {
			console.log($alert);
			$alert.fadeOut('slow');
		}, 3000);
	}
	
	
	
});