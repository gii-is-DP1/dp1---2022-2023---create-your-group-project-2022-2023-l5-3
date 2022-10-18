<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Cartas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Momento Inicio</th>
            <th>Momento Fin</th>
            <th>Duración</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
                <td>
                    <c:out value="${partida.momentoInicio}"/>
                </td>
                <td>
                    <c:out value="${partida.momentoFin}"/>
                </td>
                
                <td>
                    <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>