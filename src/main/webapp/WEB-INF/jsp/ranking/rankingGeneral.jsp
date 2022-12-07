<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ranking">
    <h2>Ranking partidasGanadas</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Partidas ganadas</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresWIN}" var="jugador">
            <tr>
                
                <td>
                    <c:out value="${jugador.id}"/>
                </td>  
                <td>
                    <c:out value="${jugador.user.username}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidasGanadas}"/>
                </td> 
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</br>
</br>
</br>
    <h2>Ranking puntos ganados</h2>
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Puntos ganados</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresPTN}" var="jugador2">
            <tr>
                
                <td>
                    <c:out value="${jugador2.id}"/>
                </td>  
                <td>
                    <c:out value="${jugador2.user.username}"/>
                </td>
                <td>
                    <c:out value="${jugador2.numTotalPuntos}"/>
                </td> 
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</br>
</br>
</br>
<h2>Ranking movimientos alcanzados</h2>
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Rol</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresMOV}" var="jugador3">
            <tr>
                
                <td>
                    <c:out value="${jugador3.id}"/>
                </td>  
                <td>
                    <c:out value="${jugador3.user.username}"/>
                </td>
                <td>
                    <c:out value="${jugador3.numTotalMovimientos}"/>
                </td> 
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
