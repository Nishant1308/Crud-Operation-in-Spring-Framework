<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<h1 class="text-center mb-3">Update the Product details</h1>
				<!-- Display success message if available -->
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/handle-product"
					method="post">
					<input type="hidden" name="id" value="${product.id}">

					<div class="form-group">
						<label for="name">Product Name</label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Enter Product Name here" value="${product.name}">
					</div>

					<div class="form-group">
						<label for="description">Product Description</label>
						<textarea class="form-control" id="description" rows="5"
							name="description" placeholder="Enter Product description">${product.description}</textarea>
					</div>

					<div class="form-group">
						<label for="price">Product Price</label> <input type="text"
							class="form-control" id="price" name="price"
							placeholder="Enter Product Price" value="${product.price}">
					</div>

					<div class="container text-center">
						<a href="${pageContext.request.contextPath}/"
							class="btn btn-outline-danger">Back</a>
						<button type="submit" class="btn btn-primary">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
