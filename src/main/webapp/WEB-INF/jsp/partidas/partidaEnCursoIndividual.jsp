<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidaFinalizar">

    <div class="m-0 row justify-content-center">
        <div class="col-auto text-center">
            <h1 style="font-weight: bold;">TIENES UNA PARTIDA EN CURSO, FINALIZA LA PARTIDA PARA CREAR UNA NUEVA</h1>
        </br>
            <div class="mx-auto" style="justify-content: center; display: flex;">
                <a href="/partidas/derrota/${id}" class="btn btn-danger"> Finalizar partida en curso</a>
    
            </div>
        </br>
    </br>
        
        </div>
    
    </div>

</petclinic:layout>
