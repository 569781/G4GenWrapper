<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="cabeceraBean" type="com.gestion400.http.CabeceraBean" scope="request"/>

<jsp:useBean id="loginBean" type="com.gestion400.http.LoginBean" scope="request"/>

<!doctype html>
<html lang="es">
<head>

<meta charset="UTF-8">
<title><c:out value = "${cabeceraBean.titulo}"/></title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	
</head>

<body>

		<%@include file="cabecera.jsp" %>
		
		<div class="row mt-5">
		
			<div class="col-3"></div>
		
			<div class="col-6">
			
				<div class="card">
				
					<div class="card-header">
					
    					Login usuario/contraseña
    					
  					</div>
			
					 <div class="card-body">
					 
					 	<form method="post" action="${loginBean.urlValidarUsuarioPassword}">				 
					 		
					 		<c:set var = "errores" value = "${loginBean.erroresLogin}"/>
					 		
					 		<%@include file="errores.jsp" %>
					 	
					  		<div class="form-group">
					  		
					    		<label for="usuario">Usuario</label>
					    		
					    		<input type="text" class="form-control" id="usuario" name="usuario" required placeholder="Introduce el usuario">
					    		
					  		</div>
					  		
					  		<div class="form-group">
					  		
					    		<label for="password">Password</label>
					    		
					    		<input type="password" class="form-control" id="password" name="password" required placeholder="Introduce la contraseña">
					  		</div>
					  		
					 
							<button type="submit" class="btn btn-lg btn-primary btn-block">Login</button>			
						
						</form>
					 </div>
			
				</div>
				
			</div>
		</div>
		
	</div>
</body>
</html>

