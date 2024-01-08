<div class="modal" id="addUserModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add New User</h5>
				<button id="close-btn" type="button" class="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<form id="addUserForm" autocomplete="off">

					<div class="mb-3 row">
						<label for="username" class="col-sm-3 col-form-label">Username</label>
						<div class="col-sm">
							<input type="text" autocomplete="off" class="form-control"
								id="username" name="username" required="required">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="firstName" class="col-sm-3 col-form-label">First
							Name</label>
						<div class="col-sm">
							<input type="text" class="form-control" id="firstName"
								name="firstName" required="required">
						</div>
					</div>
					<div class="mb-3 row">
						<label for="lastName" class="col-sm-3 col-form-label">Last
							Name</label>
						<div class="col-sm">
							<input type="text" class="form-control" id="lastName"
								name="lastName" required="required">
						</div>
					</div>
					<div class="mb-3 row">
						<label for="document" class="col-sm-3 col-form-label">Document</label>
						<div class="col-sm">
							<input type="text" class="form-control" id="document"
								name="document" required="required">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="password" class="col-sm-3 col-form-label">Password</label>
						<div class="col-sm">
							<input type="password" class="form-control" id="password"
								name="password" required="required">
						</div>
					</div>
					<div class="mb-3 row">
						<select id="userType" name="userType" class="form-select col-sm-2 m-auto text-center" aria-label="Default select example">
							<option value="3" selected>User</option>
							<%if ( request.getSession().getAttribute("user").toString().contains("administrator")){%>
							<option value="2">Suport</option>
							<option value="1">Administration</option>
							<%} %>
							
						</select>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	const formUser = document.getElementById("addUserForm");
	
	formUser.addEventListener("submit", async event => {
		event.preventDefault();
		
		const url = "<%=request.getContextPath()%>/api/users";
		
		const username = document.getElementById("username").value;
		const firstName = document.getElementById("firstName").value;
		const lastName = document.getElementById("lastName").value;
		const documentUser = document.getElementById("document").value;
		const password = document.getElementById("password").value;
		const userType = document.getElementById("userType").value;
		
		const data = {
			'username': username,
			'firstName': firstName,
			'lastName': lastName,
			'document': documentUser,
			'password': password,
			'userType': userType,
		}
		
		fetch(url, {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify(data),
		    }).then((response) => {
		    	if(response.status == 201){
		    		alert("User added");
		    		formUser.reset();
		    		getAllUsers();
		    	}else if(response.status == 400){
		    		alert("Usename already exists, choice new one!");
		    	}
		    });
		
	})

	
</script>