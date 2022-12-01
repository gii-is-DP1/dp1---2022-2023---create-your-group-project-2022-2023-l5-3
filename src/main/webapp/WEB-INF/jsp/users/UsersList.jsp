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
            <th style="width: 200px;">Eliminar</th>
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
                <td>
                    <a href="http://localhost:8080/jugador/delete/${user.user.jugador.id}" class="btn btn-danger"> Eliminar</a>
                </td>  
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table border="1">

        <tr>
            <c:if test="${hasPrevious}">
                <td><a
                    href="/players/list?firstName=${firstName}&page=${pageNumber - 1}"
                    class="btn btn-default">Previous</a></td>

            </c:if>

            <c:forEach begin="1" end="${totalPages+1}" var="i">

                <td><a href="/players/list?firstName=${firstName}&page=${i-1}">${i}</a></td>

            </c:forEach>

            <c:if test="${pageNumber != totalPages}">
                <td><a
                    href="/players/list?firstName=${firstName}&page=${pageNumber + 1}"
                    class="btn btn-default">Next</a></td>
            </c:if>

        </tr>
    </table>
</petclinic:layout>
