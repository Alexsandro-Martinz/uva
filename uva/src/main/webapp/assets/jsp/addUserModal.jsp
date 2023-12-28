<div class="modal" id="addUserModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add New User</h5>
				<button id="close-btn" type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				
				<form id="addUserForm" autocomplete="off">
					<jsp:include page="/assets/jsp/userForm.jsp"></jsp:include>
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
		
		const data = {
			'username': username,
			'firstName': firstName,
			'lastName': lastName,
			'document': documentUser,
			'password': password,
		}
		
		console.log(data);
		fetch(url, {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify(data),
		    }).then((response) => {
		    	if(response.status == 201){
		    		alert("User added");
		    		formUser.reset();
		    		getAllUsers();
		    	}
		    });
		
	})

	
</script>