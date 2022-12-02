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
            
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Rol</th>
            <th style="width: 200px;">Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                
                <td>
                    <c:out value="${user.jugador.id}"/>
                </td>  
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    <c:out value="${user.getAuthorities()}"/>
                </td>
                <td>
                    <a href="http://localhost:8080/jugador/delete/${user.jugador.id}" class="btn btn-danger"> Eliminar</a>
                </td>  
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
