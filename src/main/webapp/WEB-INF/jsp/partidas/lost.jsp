<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidaWin">

    <div class="m-0 row justify-content-center">
        <div class="col-auto text-center">
            <h1 style="font-size: 3em; font-weight: bold;">HAS PERDIDO LA PARTIDA</h1>
        </br>
            <div class="mx-auto" style="justify-content: center; display: flex;">
                <a href="/partidas/create" class="btn btn-default">Volver a jugar</a>
    
            </div>
        
        </div>
    
    </div>


</petclinic:layout>
