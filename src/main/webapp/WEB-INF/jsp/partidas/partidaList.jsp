<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Partidas</h2>
    <a href="/partidas/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Crear Partida Nueva</a>
    <br></br>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Momento Inicio</th>
            <th>Momento Fin</th>
            <th>Victoria</th>
            <th>N�mero de Movimientos</th>
            <th>Puntos</th>
            <th>Duraci�n</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
                <td>
                    <c:out value="${partida.momentoInicio}"/>
                </td>
                <td>
                	<c:if test="${partida.momentoFin == null}"><c:out value="Partida en curso"/></c:if>
                	<c:if test="${partida.momentoFin != null}"><c:out value="${partida.momentoFin}"/></c:if>
                    
                </td>
                <td>
                    <c:out value="${partida.victoria}"/>
                </td>
                <td>
                    <c:out value="${partida.numMovimientos}"/>
                </td>
                <td>
                    <c:out value="${partida.puntos()}"/>
                </td>
                
                <td>
                    <c:out value="${partida.duracion()}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>