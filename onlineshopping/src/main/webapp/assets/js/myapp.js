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
	
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		console.log("tokenlar geldi");
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
		
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
							if(userRole == 'ADMIN'){
								str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-success"><span class="fas fa-edit"> </span></a>';
							}else{
								str += '<a href="'+window.contextRoot+'/card/add/'+data+'/product" class="btn btn-success"><span class="fas fa-cart-plus"> </span></a>';
							}
							
							
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
	
	
	// -----------------------------------------
	
$('.switch input[type="checkbox"]').on('change',function(){
		
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked) ? 'You want to activate the product ? ': 
								'You want to deactivate the product ?';
		
		var value = checkbox.prop('value');
		bootbox.confirm({
			size:'medium',
			title:'Product Activation & Deactivation',
			message: dMsg,
			callback: function(confirmed) {
				if(confirmed){
					
					console.log(value);
					bootbox.alert({
						size:'medium',
						title:'Information',
						message:'You are going to perform operation on product' + value
					});
					
				}else{
					checkbox.prop('checked',!checked);
				}
			}
		});
		
	});
	
	//------------------
	//--data table for admin
	//------------------
	
	
	var $adminProductsTable = $('#adminProductsTable');

	//execute the below code only where we have this table
	if($adminProductsTable.length){
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		console.log(jsonUrl);
		$adminProductsTable.DataTable({
			lengthMenu:[[10,30,50,-1],['10 Records','30 Records','50 Records','All Records']],
			pageLength:30,
			ajax:{
				url:jsonUrl,
				dataSrc: ''
			},
			columns:[
				{
					data:'id',
				},
				{
					data:'code',
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/products/'+data+'.jpg" class="adminDataTableImg"/>';
					}
				},
				{
					data:'name',
					
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
					data:'unitPrice',
					mRender: function(data,type,row){
						return  data +' &#8378;';
					}
					
				},
				{
					data: 'active',
					bSortable:false,
					mRender:function(data,type,row){
						var str = '';
						str +=	'<label class="switch">';
						if(data){
							str +=	'<input type="checkbox" checked="checked" value="'+row.id+'"/>';	
						}else{
							str +=	'<input type="checkbox" value="'+row.id+'"/>';	
						}
						
						str +=	'<div class="slider round"></div>'+
								'</label>';
						return str;
						
					}
				},
				{
					data:'id',
					bSortable:false,
					mRender:function(data,type,row){
						var str= '';
						str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str += '<span class="fas fa-edit"></span></a>';
						return str;
					}
				}
			],
			
			initComplete: function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked) ? 'You want to activate the product ? ': 
											'You want to deactivate the product ?';
					
					var value = checkbox.prop('value');
					bootbox.confirm({
						size:'medium',
						title:'Product Activation & Deactivation',
						message: dMsg,
						callback: function(confirmed) {
							if(confirmed){
							
								
								var activationUrl = window.contextRoot + '/manage/product/'+value+'/activation';
								
								$.post(activationUrl,function(data){
									
									bootbox.alert({
										size:'medium',
										title:'Information',
										message: data
									});	
								});
								
								
								
								
							}else{
								checkbox.prop('checked',!checked);
							}
						}
					});
					
				});
			}
			
		});
	}
	//-----------------
	//validation code
	//-------------
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		
		$categoryForm.validate({
			rules:{
				name:{
					required: true,
					minlength: 2
				},
				description:{
					required: true
				}
			},
			messages:{
				name:{
					required: 'Please add the category name!',
					minlength:'The category name should be less than 2 characters'
				},
				description:{
					required:'Please add the category description'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element){
				error.addClass('help-block');
				error.insertAfter(element);
				console.log(error);
			}
		});
		
	}
	
	//------------------------
	
	//-----------------------	
	// Validation Login Page
	//----------------------
	
	var $loginForm = $('#loginForm');
	
	if($loginForm.length){
		
		$loginForm.validate({
			rules:{
				username:{
					required: true,
					minlength: 2
				},
				password:{
					required: true
				}
			},
			messages:{
				username:{
					required: 'Please enter username!'
				},
				password:{
					required:'Please enter password'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element){
				error.addClass('help-block');
				error.insertAfter(element);
				console.log(error);
			}
		});
		
	}
	
});