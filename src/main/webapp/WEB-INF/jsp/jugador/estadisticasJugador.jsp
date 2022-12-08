<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<petclinic:layout pageName="stats" >



<div class="container" style="background-color:#717973;border-radius: 5%;border-color: black;border-width: 5px;">
    <div class="row border border-dark" style="font-size: 4.5rem;" align="center"><strong>Mis estadísticas</strong>
    </div>
    <div class="container" style="font-size: 3rem;">

        <div class="row" align="center">Partidas Jugadas: <c:out value="${jugador.getPartidasJugadas()}"/></div>

        <div class="row" align="center">Partidas Ganadas: <c:out value="${jugador.partidasGanadas}"/></div>
        
        <div class="row" align="center">Partidas Perdidas: <c:out value="${jugador.partidasNoGanadas}"/></div>
        
        <div class="row" align="center">Tiempo total jugado: <c:out value="${jugador.totalTiempoJugado}"/></div>
        
        <div class="row" align="center">Número total de movimientos: <c:out value="${jugador.numTotalMovimientos}"/></div>
        
        <div class="row" align="center">Número total de puntos: <c:out value="${jugador.numTotalPuntos}"/></div>

        <div class="row" align="center">Número máximo de movimientos en partida ganada: <c:out value="${jugador.numMaxMovimientosPartidaGanada}"/></div>

        <div class="row" align="center">Número mínimo de movimientos en partida ganada: <c:out value="${jugador.numMinMovimientosPartidaGanada}"/></div>

        <div class="row" align="center">Tiempo máximo en partida ganada: <c:out value="${jugador.maxTiempoPartidaGanada}"/></div>
        
        <div class="row" align="center">Tiempo mínimo en partida ganada: <c:out value="${jugador.minTiempoPartidaGanada}"/></div>
    </div>
</div>
</br>
</br>
</br>
<div class="container" style="background-color:#717973;border-radius: 5%;border-color: black;border-width: 5px;">
    <div class="row border border-dark" style="font-size: 4.5rem;" align="center"><strong>Estadísticas generales</strong>
    </div>
    <div class="container" style="font-size: 3rem;">

        <div class="row" align="center">Partidas Jugadas: <c:out value="${partidasTotalesJugadas}"/></div>

        <div class="row" align="center">Partidas Ganadas: <c:out value="${partidasGanadasTotales}"/></div>
        
        <div class="row" align="center">Partidas Perdidas: <c:out value="${partidasPerdidasTotales}"/></div>

        <div class="row" align="center">Puntos promedio: <c:out value="${puntosPromedio}"/></div>

        <div class="row" align="center">Movimientos promedio: <c:out value="${movimientosPromedio}"/></div>
    </div>
</div>


</petclinic:layout>
