<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="/assets/jsp/bootstrap.jsp"></c:import>
<title>Uva | Home</title>
</head>
<body>
	<div class="container-xxl p-0">

		<c:import url="/assets/jsp/nav.jsp"></c:import>

		<div class="container">

			<div class="row justify-content-center">
				<div class="col-auto">
					<h1>Hello every body</h1>
					<h2>${user.role }</h2>
				</div>
			</div>

		</div>

	</div>

	<c:import url="/assets/jsp/footer.jsp"></c:import>
	<c:import url="/assets/jsp/scripts.jsp"></c:import>

</body>
</html>