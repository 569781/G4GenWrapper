<c:if test="${not empty errores}">
					 		
	<div class="alert alert-danger" role="alert">
	
		<strong>Han ocurrido los siguientes errores:</strong>
	 
		<c:forEach var="error" items="${errores}">			
		
			<ul>
			    <li>${error}</li>
			</ul>
			
		</c:forEach>
	
	</div>
					 		
</c:if>