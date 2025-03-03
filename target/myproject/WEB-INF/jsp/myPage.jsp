<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./base.jsp"%>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to Product App</h1>
				 <!-- Display success message if available -->
                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                        ${message}
                    </div>
                </c:if>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">S.No.</th>
							<th scope="col">Product Name</th>
							<th scope="col">Product Description</th>
							<th scope="col">Price</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${product }" var="p">
						<tr>
							<th scope="row">${p.id }</th>
							<td>${p.name }</td>
							<td>${p.description }</td>
							<td>&#x20B9; ${p.price }</td>
							<td>
							<a href="${pageContext.request.contextPath}/delete/${p.id }"><i class="fas fa-trash text-danger"></i></a>
							<a href="${pageContext.request.contextPath}/update/${p.id }"><i class="fas fa-pen-nib"></i></a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="container text-center">
				<a href="addProduct" class="btn btn-outline-success">Add Product</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
