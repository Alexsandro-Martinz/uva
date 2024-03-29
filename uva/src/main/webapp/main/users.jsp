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
						<form id="searchForm" class="d-flex">
							<input class="form-control me-2" id="search-input" type="search"
								placeholder="Search" aria-label="Search">
							<button type="submit" id="searchBtn"
								class="btn btn-outline-success">Search</button>
						</form>
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
					<div class="row justify-content-center">
						<nav aria-label="..." class="col-auto">
							<ul id="pages" class="pagination">

							</ul>
						</nav>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	
	let lastSearch = "";
	
function setPages(pages){
	const pagesUl = document.getElementById("pages");
	$("#pages > li").remove();
	for( let i = 1; i<= pages ; i++){
		const li = document.createElement("li");
		const span = document.createElement("span");
		span.className = "page-link"
		span.appendChild(document.createTextNode(i));
		li.className = "page-item";
		li.appendChild(span);
		
		li.onclick = () => {
			getAllUsers(lastSearch, i);
		}
		
		pagesUl.appendChild(li);
	}
}
function setUsersTable(users) {
	let count = 1;
	const tblBody = document.getElementById("usersTable");
	$('#usersTable > tr').remove();
	
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
		btnUpdate.className = "btn btn-info fw-bold btn-sm m-0 p-1 ";
		btnUpdate.style = "font-size: 12px"
		btnUpdate.innerHTML = "UPDATE";
		
		btnUpdate.onclick = () => {
			showUpdateUserModal(u);
		}
		
		const d1 = document.createElement("div");
		d1.className = "col-auto";
		d1.appendChild(btnUpdate);
		
		const btn = document.createElement("button");
		btn.className = "btn btn-danger fw-bold btn-sm m-0 p-1 ";
		btn.style = "font-size: 12px"
		btn.innerHTML = "DEL";
		btn.onclick = () => delUser(u.id, u.username);
		
		const d2 = document.createElement("div");
		d2.className = "col-auto";
		d2.appendChild(btn);
		
		const c6 = document.createElement("td");
		c6.className = "row justify-content-around m-0";
		c6.appendChild(d1);
		c6.appendChild(d2);
		row.appendChild(c6);
		
		tblBody.appendChild(row);
		count++;
	});
}

function getAllUsers(search = "", page = 1) {
	fetch("<%=request.getContextPath()%>/api/users?search=" + search + "&page="+page)
		.then(data => {
			return data.json();
		})
		.then(users => {
		
			setUsersTable(users[1]);
			setPages(users[0]);
		});
}

getAllUsers();

const searchForm = document.querySelector("#searchForm");

searchForm.addEventListener("submit", (event) => {
	event.preventDefault();
	lastSearch = document.getElementById("search-input").value; 
	getAllUsers(lastSearch);
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
	<c:import url="/assets/jsp/scripts.jsp"></c:import>
</body>
</html>