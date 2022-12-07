<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ranking">
    <h2>Ranking partidas</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            
            <th style="width: 150px;">Clasificacion</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Partidas ganadas</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresWIN}" var="jugador" varStatus="status">
            <tr>
                
                <td>
                    <c:if test="${status.count == 1}"><a class="btn btn-warning"> 1</a></c:if>
                    <c:if test="${status.count == 2}"><a class="btn btn-default"> 2</a></c:if>
                    <c:if test="${status.count == 3}"><a class="btn btn-info"> 3</></c:if>
                    <c:if test="${status.count > 3}"><c:out value="${status.count}"/></c:if>
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
            
            <th style="width: 150px;">Clasificacion</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Puntos ganados</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresPTN}" var="jugador2"  varStatus="status">
            <tr>
                
                <td>
                    <c:if test="${status.count == 1}"><a class="btn btn-warning"> 1</a></c:if>
                    <c:if test="${status.count == 2}"><a class="btn btn-default"> 2</a></c:if>
                    <c:if test="${status.count == 3}"><a class="btn btn-info"> 3</></c:if>
                    <c:if test="${status.count > 3}"><c:out value="${status.count}"/></c:if>
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
            
            <th style="width: 150px;">Clasificacion</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Movimientos realizados</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresMOV}" var="jugador3" varStatus="status">
            <tr>
                
                <td>
                    <c:if test="${status.count == 1}"><a class="btn btn-warning"> 1</a></c:if>
                    <c:if test="${status.count == 2}"><a class="btn btn-default"> 2</a></c:if>
                    <c:if test="${status.count == 3}"><a class="btn btn-info"> 3</></c:if>
                    <c:if test="${status.count > 3}"><c:out value="${status.count}"/></c:if>
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
