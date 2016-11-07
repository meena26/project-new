 <%@page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp"%>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js">

</script>
<script src="<c:url value="/resources/js/controller.js"></c:url>"></script>
<head>
<meta shttp-equiv="Content-Type"content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container-wrapper">
<div class="container">
<section>
<div class="jumbotron">
<div class="container">
<h3>Cart</h3>
<p>All the selected books in your shopping cart</p>
</div>
</div>
</section>
<div ng-app="myapp" ng-controller="bookController">

<div ng-init="getCart(${cartId})">
<br>
List of books purchased
<div>
<a class="btn btn-danger pull-left" ng-click="clearCart()">
<span class="glyphicon glyphicon-remove-sign"></span> Clear Cart
</a>
</div>
<table class="table table-hover">
<thead>



</body>
</html>