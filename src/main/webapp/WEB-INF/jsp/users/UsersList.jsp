<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
<div class="row text-center">
    <p><h1><b>Users</b></h1>
    </br>
        <spring:url value="/users/new" var="newURL"></spring:url>
        <a href="${newURL}" class="btn btn-default">Crear nuevo jugador</a>
    </p>
</br>
</br>
</div>    

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Name</th>
            <th style="width: 200px;">Lastname</th>
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
                    <a href="http://localhost:8080/jugador/perfil/${user.jugador.id}">${user.username}</a>
                </td>
                <td>
                    <c:out value="${user.jugador.firstName}"/>
                </td>
                <td>
                    <c:out value="${user.jugador.lastName}"/>
                </td>   
                <td>
                    <c:out value="${user.getAuthorities()}"/>
                </td>
                <td>
                    <spring:url value="/jugador/delete/${user.jugador.id}" var="deleteURL"></spring:url>
                    <a href="${deleteURL}" class="btn btn-danger">Eliminar</a>
                </td>  
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
