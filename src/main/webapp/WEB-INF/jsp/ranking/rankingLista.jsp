<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<petclinic:layout pageName="ranking">
<div class="container">
<div class="row">
<div class="col-sm-12 margin-b2">
<h1>Ranking</h1></div>

<div class="col-sm-4">

<h2>Ranking Victories</h2>
	
<div class="col-sm-4">

<h2>Ranking Points</h2>
	
   
<c:forEach items="${pointsRank}" var="jugador">
    <div class="contenedor-borde">
    <div class="col-md-7">
    <c:out value="${jugador.user.username}"/>
    <c:out value="${jugador.user.username}"/>
    </br>@<c:out value="${jugador.user.username}"/>
    
    </div>
    <div class="col-md-5 text-right">
     Points:
    <c:out value="${jugador.numTotalPuntos}"/>
    </div>

    
    </div>
    </c:forEach>
    </div>


