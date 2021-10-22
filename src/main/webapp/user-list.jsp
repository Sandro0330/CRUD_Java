<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Lista de usuários</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="https://github.com/Sandro0330" class="navbar-brand">App de gerenciamento de usuário</a>
			</div>			
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Usuários</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<div class="container">
			<h3 class="container">Lista de usuários</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-primary">Adicionar novo usuário</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOME</th>
						<th>EMAIL</th>
						<th>PAIS</th>
						<th>AÇÃO</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td><c:out value="${user.id}"></c:out></td>
							<td><c:out value="${user.nome}"></c:out></td>
							<td><c:out value="${user.email}"></c:out></td>
							<td><c:out value="${user.pais}"></c:out></td>
							<td>
								<a href="edit?id=<c:out value='${user.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="delete?id=<c:out value='${user.id}' />">Deletar</a>
							</td>					
						</tr>
					</c:forEach>	
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>