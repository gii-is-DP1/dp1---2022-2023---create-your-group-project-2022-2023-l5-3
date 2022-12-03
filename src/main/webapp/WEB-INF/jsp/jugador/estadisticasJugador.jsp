<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<petclinic:layout pageName="estadisticasJugador" >



<div class="container" style="background-color:#717973;border-radius: 5%;border-color: black;border-width: 5px;">
    <div class="row border border-dark" style="font-size: 4.5rem;" align="center"><strong>Estadísticas</strong></div>
    <div class="container" style="font-size: 3rem;">

        <div class="row" align="center">Partidas Jugadas: <c:out value="${jugador.getPartidasJugadas()}"/></div>

        <div class="row" align="center">Partidas Ganadas: <c:out value="${jugador.partidasGanadas}"/></div>
        
        <div class="row" align="center">Partidas Perdidas: <c:out value="${jugador.partidasNoGanadas}"/></div>
        
        <div class="row" align="center">Tiempo total jugado: <c:out value="${jugador.totalTiempoJugado}"/></div>
        
        <div class="row" align="center">Número total de movimientos: <c:out value="${jugador.numTotalMovimientos}"/></div>
        
        <div class="row" align="center">Número total de puntos: <c:out value="${jugador.numTotalPuntos}"/></div>
        
        <div class="row" align="center">Máximo número de movimientos: <c:out value="${jugador.numMaxMovimientosPartidaGanada}"/></div>
        
        <div class="row" align="center">Mínimo número de movimientos: <c:out value="${jugador.numMinMovimientosPartidaGanada}"/></div>
        
        <div class="row" align="center">Máximo tiempo en obtener una victoría: <c:out value="${jugador.maxTiempoPartidaGanada}"/></div>
        
        <div class="row" align="center">Mínimo tiempo en obtener una victoría: <c:out value="${jugador.minTiempoPartidaGanada}"/></div>
    </div>
</div>


</petclinic:layout>
