<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Orders List</title>
</head>

<body>

	<div class="container">

		<h3>Orders List</h3>
			
		<hr>

		<!-- Add a search form -->

	<!-- 	 -->
			<form action="/ordermgmt/orders/search" class="form-inline">
			 Add a New Order
				<a href="/ordermgmt/orders/showFormForAdd"
					class="btn btn-primary btn-sm mb-3"> Add Order </a> 
			
			</form>  
		
	

		<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Customer Name</th>
					<th>Order Price</th>
					<th>Order Date</th>
					<th>Action</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${orders}" var="tempOrder">
					<tr>
						<td><c:out value="${tempOrder.customerName}" /></td>
						<td><c:out value="${tempOrder.orderPrice}" /></td>
						<td><c:out value="${tempOrder.orderDate}" /></td>

						<td>
							<!-- Add "update" button/link --> <a
							href="/ordermgmt/orders/showFormForUpdate?orderId=${tempOrder.id}"
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="/ordermgmt/orders/delete?orderId=${tempOrder.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this Order?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>

</body>
</html>



