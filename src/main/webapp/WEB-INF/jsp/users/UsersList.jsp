<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h2>Users</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">User Id</th>
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Rol</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.user.jugador.id}"/>
                </td>  
                <td>
                    <c:out value="${user.user.username}"/>
                </td>
                <td>
                    <c:out value="${user.user.getAuthorities()}"/>
                </td>  
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
