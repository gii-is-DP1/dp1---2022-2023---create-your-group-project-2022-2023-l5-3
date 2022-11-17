<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="jugador">

    <h2>Owner Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Firs Name</th>
            <td><b><c:out value="${jugador.firstName}"/></b></td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td><b><c:out value="${jugador.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Usuario</th>
            <td><c:out value="${jugador.user.username}"/></td>
        </tr>
    </table>

        <tr>
            <spring:url value="/jugador/{id}/edit" var="editUrl">
                <spring:param name="id" value="${id}"/>
            </spring:url>
            <a href="${editUrl}" class="btn btn-default">Editar Jugador</a>
        </tr>
   
        <tr>
            <spring:url value="{id}/estadisticas" var="statsUrl">
                <spring:param name="id" value="${id}"/>
            </spring:url>
            <a href="${fn:escapeXml(statsUrl)}" class="btn btn-default">Estadísticas</a>
        </tr>
    </table>

</petclinic:layout>