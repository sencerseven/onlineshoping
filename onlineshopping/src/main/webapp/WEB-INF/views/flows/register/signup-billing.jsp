<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp"%>
	
			<div class="container">
				<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Sign Up - Personel</h4>
					</div>
					
					<div class="panel-body">
						<sf:form method="POST" class="form-horizontal" id="registerForm" modelAttribute="billing">
							
							<div class="form-group">
								<label class="control-label col-md-4">Address Line One</label>
								<div class="col-md-8">
									<sf:input path="addressLineOne" type="text" class="form-control" placeholder="Adress Line One"/>
									<sf:errors path="addressLineOne" cssClass="help-block" element="em" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">Address Line Two</label>
								<div class="col-md-8">
									<sf:input path="addressLineTwo" type="text" class="form-control" placeholder="Adress Line Two"/>
									<sf:errors path="addressLineTwo" cssClass="help-block" element="em" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">city</label>
								<div class="col-md-8">
									<sf:input path="city" type="text" class="form-control" placeholder="city"/>
									<sf:errors path="city" cssClass="help-block" element="em" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">state</label>
								<div class="col-md-8">
									<sf:input path="state" type="text" class="form-control" placeholder="state"/>
									<sf:errors path="state" cssClass="help-block" element="em" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">country</label>
								<div class="col-md-8">
									<sf:input path="country" type="text" class="form-control" placeholder="country"/>
									<sf:errors path="country" cssClass="help-block" element="em" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">postalCode</label>
								<div class="col-md-8">
									<sf:input path="postalCode" type="text" class="form-control" placeholder="postalCode"/>
									<sf:errors path="postalCode" cssClass="help-block" element="em" />
								</div>
							</div>
							
						
							
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" class="btn btn-primary" name="_eventId_personal"><i class="fas fa-arrow-left"></i>Previous Personal</button>
									<button type="submit" class="btn btn-primary" name="_eventId_confirm">Next Confirm <i class="fas fa-arrow-right"></i></button>
								</div>
							</div>
							
						</sf:form>
					</div>
				</div>
			</div>	
		</div>
			</div>
<%@include file="../shared/flows-footer.jsp" %>	