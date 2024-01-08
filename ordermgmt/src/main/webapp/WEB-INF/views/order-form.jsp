<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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

<title>Save Order</title>
</head>

<body>

	<div class="container">

		<h3>Orders Entry Form</h3>
		<hr>

		<p class="h4 mb-4">Order</p>

		<form action="/ordermgmt/orders/save" method="POST">

			<!-- Add hidden form field to handle update -->
			<input type="hidden" name="id" value="${order.id}" />

			<div class="form-inline">
				<input type="text" name="customerName" value="${order.customerName}"
					class="form-control mb-4 col-4" placeholder="Customer Name">



			</div>

			<div class="form-inline">

				<input type="text" name="orderPrice" value="${order.orderPrice}"
					class="form-control mb-4 col-4" placeholder="Order Price">



			</div>

			<div class="form-inline">

				<input type="text" name="orderDate" value="${order.orderDate}"
					class="form-control mb-4 col-4" placeholder="Order Date">



			</div>
			<div class="form-inline">

			</div>

			<button type="submit" class="btn btn-info col-2">Save</button>

		</form>

		<hr>
		<a href="/ordermgmt/orders/list">Back to Orders List</a>

	</div>
</body>

</html>










