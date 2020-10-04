<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="cabeceraBean" type="com.gestion400.http.CabeceraBean" scope="request"/>

<jsp:useBean id="urlRestablecerPassword" type="java.lang.String" scope="request"/>

<!doctype html>
<html lang="es">
<head>

<meta charset="UTF-8">
<title><c:out value = "${cabeceraBean.titulo}"/></title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<script type="text/javascript" src="../js/restablecer-password.js"></script>
	
</head>

<body>

		<%@include file="cabecera.jsp" %>

		<div class="row mt-5">
		
			<div class="col-3"></div>
		
			<div class="col-6">
			
				<div class="card">
				
					<div class="card-header">
					
    					Cambio de contraseña requerido
    					
  					</div>
			
					 <div class="card-body">
					 
					 	<form id="formPassword" method="post" action="${urlRestablecerPassword}">				 
					 	
					  		<div class="form-group">
					  		
					    		<label for="pass1">Nueva contraseña</label>
					    		
					    		<input id="pass1" name="pass1" type="password" onkeyup="validarDimension()" class="form-control" required placeholder="Escriba la contraseña">
					    		
					    		<div class="invalid-feedback">La contraseña debe tener al menos 8 caracteres</div>
					    		
					  		</div>
					  		
					  		<div class="form-group">
					    		
					    		<label for="pass2">Repita la contraseña</label>
					    		
					    		<input  id="pass2" name="pass2" type="password" onkeyup="validarPasswords()" class="form-control" required placeholder="Vuelva a escribir la contraseña">  		
					  			
					  			<div class="invalid-feedback">La contraseña no coincide con la anterior</div>
					  		
					  		</div>
					  		
					 
							<button type="submit" class="btn btn-lg btn-primary btn-block">Cambiar contraseña</button>			
						
						</form>
					 </div>
			
				</div>
				
			</div>
			
</body>
</html>