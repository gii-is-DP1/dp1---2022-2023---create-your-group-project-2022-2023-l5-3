<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugador">

    <h2>Owner Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${jugador.firstName} ${jugador.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Usuario</th>
            <td><c:out value="${jugador.user.username}"/></td>
        </tr>
    </table>

    <spring:url value="{id}/edit" var="editUrl">
        <spring:param name="id" value="${id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Owner</a>


    </table>

</petclinic:layout>