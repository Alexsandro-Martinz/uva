<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Uva | Login</title>
<c:import url="/assets/jsp/bootstrap.jsp"></c:import>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center align-items-center"
			style="height: 900px">
			<div class="col-auto align-items-center">

				<form class="border p-3 rounded mb-3"
					action="<%=request.getContextPath()%>/login" method="post">
					<h1 class="display-4 mb-3 fw-bold text-center">Login</h1>
					<div class="mb-3">
						<label for="username" class="form-label">Username</label> <input
							 type="text" class="form-control" name="username">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control"
							name="password">
					</div>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1">
						<label class="form-check-label" for="exampleCheck1">Check
							me out</label>
					</div>
					<button type="submit" class="btn w-100 btn-primary">Submit</button>
					<input type="hidden" name="url"
						value="<%=request.getAttribute("url")%>" />
				</form>
				<%
				if (request.getAttribute("message") != null) {
				%>
				<div id="message" class="alert alert-danger" autofocus="autofocus"
					role="alert">${message}!</div>
				<script type="text/javascript">
						setTimeout(() => {
							document.getElementById("message").remove();
						}, 2000);
					</script>
				<%
				}
				%>
			</div>
		</div>
	</div>

	<c:import url="/assets/jsp/scripts.jsp"></c:import>
</body>
</html>