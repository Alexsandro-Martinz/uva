<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="/assets/jsp/bootstrap.jsp"></c:import>
<title>Uva | Users</title>
</head>
<body>
	<div class="container-xxl p-0">

		<c:import url="/assets/jsp/nav.jsp"></c:import>

		<div class="container">
			<div class="row text-center pt-3 pb-3">
				<h1 class="fw-bold">Users Admistration</h1>
			</div>
			<div
				class="row border border-secondary rounded p-3 mb-3 justify-content-center">
				<nav class="navbar navbar-light bg-light m-0">
					<div
						class="container-fluid justify-content-around align-items-center">
						<div class="d-flex">
							<input class="form-control me-2" id="search-input" type="search"
								placeholder="Search" aria-label="Search">
							<button id="searchBtn" class="btn btn-outline-success">Search</button>
						</div>
						<button type="button" data-bs-toggle="modal"
							data-bs-target="#addUserModal" class="btn btn-success">Add
							a new user</button>
					</div>
				</nav>

				<div class="table-responsive">
					<table style="font-size: 14px"
						class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">ID</th>
								<th scope="col">Username</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Document</th>
							</tr>
						</thead>
						<tbody id="usersTable" name="usersTable">
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
function setUsersTable(users) {
	const tblBody = document.getElementById("usersTable");
	tblBody.innerHTML = '';
	let count = 1;
	
	users.forEach(u => {

		const row = document.createElement("tr");

		const c0 = document.createElement("td");
		c0.className = "text-secondary";
		c0.appendChild(document.createTextNode(count));
		row.appendChild(c0);

		const c1 = document.createElement("td");
		c1.appendChild(document.createTextNode(u.id));
		row.appendChild(c1);

		const c2 = document.createElement("td");
		c2.appendChild(document.createTextNode(u.username));
		row.appendChild(c2);

		const c3 = document.createElement("td");
		c3.appendChild(document.createTextNode(u.firstName));
		row.appendChild(c3);

		const c4 = document.createElement("td");
		c4.appendChild(document.createTextNode(u.lastName));
		row.appendChild(c4);

		const c5 = document.createElement("td");
		c5.appendChild(document.createTextNode(u.document));
		row.appendChild(c5);

		const btnUpdate = document.createElement("button");
		btnUpdate.className = "btn btn-info btn-sm m-0 p-1 ";
		btnUpdate.style = "font-size: 12px"
		btnUpdate.innerHTML = "UPDATE";
		
		btnUpdate.onclick = () => {
			showUpdateUserModal(u);
		}

		const c6 = document.createElement("td");
		c6.appendChild(btnUpdate);
		row.appendChild(c6);

		const btn = document.createElement("button");
		btn.className = "btn btn-warning btn-sm m-0 p-1 mx-auto ";
		btn.style = "font-size: 12px"
		btn.innerHTML = "DEL";
		btn.onclick = () => delUser(u.id, u.username);

		const c7 = document.createElement("td");
		c7.appendChild(btn);
		row.appendChild(c7);

		tblBody.appendChild(row);
		count++;
	});

}

function getAllUsers(search = "") {
	fetch("<%=request.getContextPath()%>/api/users?search=" + search)
		.then(data => {
			return data.json();
		})
		.then(users => {
			setUsersTable(users);
		});
}

getAllUsers();

const searchBtn = document.querySelector("#searchBtn");

searchBtn.addEventListener("click", () => {
	getAllUsers(document.getElementById("search-input").value);
});

function updateUser(id) {
	if (confirm(" " + username + "?")) {

		fetch("<%=request.getContextPath()%>/api/users?id=" + id,
			{ method: "PUT" })
			.then(() => {
				alert("Delele successful");
				getAllUsers();
			});
	}

}

function delUser(id, username) {
	if (confirm("Delete " + username + "?")) {

		fetch("<%=request.getContextPath()%>/api/users?id=" + id,
			{ method: "DELETE" })
			.then(() => {
				alert("Delele successful");
				getAllUsers();
			});
	}

}

</script>
	<c:import url="/assets/jsp/addUserModal.jsp"></c:import>
	<c:import url="/assets/jsp/updateUserModal.jsp"></c:import>
	<c:import url="/assets/jsp/footer.jsp"></c:import>
	<c:import url="/assets/jsp/scripts.jsp"></c:import>
</body>
</html>