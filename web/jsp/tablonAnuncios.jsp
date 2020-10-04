<script type="text/javascript" src="../js/tablon-anuncios.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="tablon_anuncios" type="com.gestion400.util.TablonDeAnuncios" scope="request"/>


<div class="divTablonAnuncios">

	<table class="tabTituloTablon">
			<tr>
				<td><i style="margin-bottom: 11px;" class="mdi mdi-home colorBase" /></td>
				<td><h2 class="tituloTablon"><c:out value = "${tablon_anuncios.tituloTablon}"/></h2></td>
			</tr>
	</table>

	<div id="divAnuncios">
	
		<c:forEach var="anuncio" items="${tablon_anuncios.anuncios}">
		
			<div class='anuncioTablon'>
				<p>
					<label class="asuntoAnuncioLink" style='cursor: pointer;'> <c:out value = "${anuncio.asunto}"/> </label>
				</p>
			
				<p>
					<label class='fechaAnuncio'> <c:out value = "${tablon_anuncios.ayuntamiento}"/> (<c:out value = "${anuncio.fechaAnuncio}"/>)</label>
				</p>
				
				<p><c:out value = "${anuncio.descripcionBreve}"/></p>
				
				<div class='detalleAnuncio' style="display: none;">
					<p><c:out value = "${anuncio.textoAnuncio}" escapeXml="false"/></p>
				</div>
				
			</div>
			
		</c:forEach>
		
	</div>
</div>