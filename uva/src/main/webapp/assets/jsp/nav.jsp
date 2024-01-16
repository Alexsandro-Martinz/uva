<nav class="navbar navbar-expand-lg p-0 navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">UVA</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarScroll" aria-controls="navbarScroll"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarScroll">
			<ul id="navOptions"
				class="navbar-nav me-auto my-1 align-items-center my-lg-0 navbar-nav-scroll"
				style="--bs-scroll-height: 100px;">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%=request.getContextPath()%>/main/home.jsp">Home</a></li>

				<% String userData = request.getSession().getAttribute("user").toString();%>
				<%if ( userData.contains("ADMINISTRATOR") || userData.contains("SUPPORT")){%>
					
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%=request.getContextPath()%>/main/users.jsp">Users</a></li>
					<%} %>
			</ul>
			<ul
				class="navbar-nav ms-auto my-1 my-lg-0 align-items-center navbar-nav-scroll"
				style="--bs-scroll-height: 100px;">
				
				<li class="nav-item dropdown"><a
					class="nav-link active dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						${user.username}</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#">Profile</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item"
							href="<%=request.getContextPath()%>/logout">Logout</a></li>
					</ul></li>
				<li class="nav-item item-0"><a class="nav-link active"
					aria-current="page" href="#">
					<img
						class="bg-light object-fit-none img-fluid rounded-circle border border-dark border-3"
						style="width: 40px; height: 40px" alt=""
						src="${user.photo}"></a></li>
			</ul>
		</div>
	</div>
</nav>


