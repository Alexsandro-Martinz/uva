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

<style>
<
style>.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>
<body>

	<div class="container-xxl p-0">

		<c:import url="/assets/jsp/nav.jsp"></c:import>

		<div
			class="position-relative overflow-hidden p-3 p-md-3 m-md-3 text-center bg-light">
			<div class="col-md-5 p-lg-5 mx-auto my-5">
				<h1 class="display-3 fw-normal">Uva Control</h1>
				<p class="lead fw-normal">Em um mundo conectado e dinâmico,
					oferecemos uma experiência única em controle de usuários. Descubra
					as vantagens exclusivas que tornam o nosso site a escolha ideal
					para você:</p>
			</div>
			<div class="product-device shadow-sm d-none d-md-block"></div>
		</div>

		<div class="d-md-flex flex-md-equal w-100 my-md-3 ps-md-3">

			<div
				class="bg-primary me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
				<div class="my-3 p-3">
					<h2 class="display-6">Facilidade de Uso:</h2>
					<p class="lead">Navegue de forma intuitiva em um ambiente
						amigável e acessível. Projetamos nosso site pensando na sua
						comodidade, garantindo uma experiência descomplicada do início ao
						fim.</p>
				</div>

			</div>

			<div
				class="bg-primary me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
				<div class="my-3 p-3">
					<h2 class="display-6">Cadastro Simples e Rápido</h2>
					<p class="lead">Diga adeus a processos complicados de
						inscrição. Com apenas alguns cliques, você estará pronto para
						explorar tudo o que [Nome do Seu Site] tem a oferecer.</p>
				</div>

			</div>

			<div
				class="bg-primary me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
				<div class="my-3 p-3">
					<h2 class="display-6">Segurança em Primeiro Lugar</h2>
					<p class="lead">Sua segurança é nossa prioridade. Implementamos
						as mais recentes medidas de segurança para garantir que seus dados
						e informações pessoais estejam sempre protegidos.</p>
				</div>

			</div>


		</div>

	</div>

	<!-- Background image -->
	<!-- Background image -->
	<c:import url="/assets/jsp/scripts.jsp"></c:import>

</body>
</html>