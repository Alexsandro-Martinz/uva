<div class="modal" id="updateUserModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Update User</h5>
				<button id="close-btn" type="button" class="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<form id="updateUserForm" autocomplete="off">
					<div class="mb-3 row">
						<label for="userId" class="col-sm-3 col-form-label">Id</label>
						<div class="col-sm">
							<input type="text" readonly class="form-control-plaintext"
								id="userId" name="userId">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="username" class="col-sm-3 col-form-label">Username</label>
						<div class="col-sm">
							<input type="text" autocomplete="off" class="form-control"
								id="username" name="username" >
						</div>
					</div>

					<div class="mb-3 row">
						<label for="firstName" class="col-sm-3 col-form-label">First
							Name</label>
						<div class="col-sm">
							<input type="text" class="form-control" id="firstName"
								name="firstName" >
						</div>
					</div>
					<div class="mb-3 row">
						<label for="lastName" class="col-sm-3 col-form-label">Last
							Name</label>
						<div class="col-sm">
							<input type="text" class="form-control" id="lastName"
								name="lastName" >
						</div>
					</div>
					<div class="mb-3 row">
						<label for="document" class="col-sm-3 col-form-label">Document</label>
						<div class="col-sm">
							<input type="text" class="form-control" id="document"
								name="document" >
						</div>
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
	
	const updateUserForm = document.getElementById("updateUserForm");
	
	updateUserForm.addEventListener("submit", async event => {
		event.preventDefault();
		
		
		const url = "<%=request.getContextPath()%>/api/users";
		
		const data = {
			'id': updateUserForm[0].value,
			'username': updateUserForm[1].value,
			'firstName': updateUserForm[2].value,
			'lastName': updateUserForm[3].value,
			'document': updateUserForm[4].value,
		}
		
		console.log(data);
		
		if(data.username == "" && data.firstName == "" && data.lastName == "" && data.document == ""){
			alert("Edit some field for update");
			return;
		}
		
		fetch(url, {
		      method: "PUT",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify(data),
		    }).then((response) => {
		    	if(response.status == 200){
		    		alert("User updated");
		    		updateUserForm.reset();
		    		getAllUsers();
		    		$('#updateUserModal').modal('hide');
		    	}
		    });
		
	})
		
	function showUpdateUserModal(data) {
		updateUserForm[0].value = data.id;
		updateUserForm[1].placeholder = data.username;
		updateUserForm[2].placeholder = data.firstName;
		updateUserForm[3].placeholder = data.lastName;
		updateUserForm[4].placeholder = data.document;
		
		$('#updateUserModal').modal('show');
	}

	
</script>