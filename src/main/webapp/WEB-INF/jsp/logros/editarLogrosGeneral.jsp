<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidaWin">

    <div class="m-0 row justify-content-center">
        
        <div class="mx-auto" style="justify-content: center; display: flex;">
            <a style="font-size: 3em;" class="btn btn-primary">EDITAR LOGROS</a>
        </br>
    </br>
        </div>
    </br>
</br>
        <c:forEach items="${logros}" var="logro">    
            <div class="mx-auto" style="justify-content: center; display: flex;">
                <a style="font-size: 2em; color: black;">${logro.description}</a>
            </br>
                <a href="/jugador/logros/editar/${logro.id}" style="font-size: 2em; padding-left: 5%; color: rgb(170, 27, 27);">EDITAR</a>
            </div>
        </br>
    </br>
</br>
        </c:forEach>
    </div>


</petclinic:layout>
