<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="partidas">
    
    <jsp:body>
   		
   		
        <div class="row" align="center">
            <h2 >
            Tablero Creado
            </h2> 
            </div> 

            
            
        <!--MAZOS INTERMEDIOS-->        
        <div class="container" style="background-color:#51C967;">
            <div class="row">    
                <c:forEach var="i" begin="1" end="7">
                <div class="col-md-1">
                    Mazo ${i}
                </div>    
                </c:forEach>
            </div>
            <div class="row">
                
                <div class="col-md-1">

                    <c:forEach items="${mazInt1}" var="cp1">
                        <c:if test="${cp1.posCartaMazo != fn:length(mazInt1)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp1.posCartaMazo == fn:length(mazInt1)}">
                                <spring:url value="${cp1.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>

                <div class="col-md-1">

                    <c:forEach items="${mazInt2}" var="cp2">
                        <c:if test="${cp2.posCartaMazo != fn:length(mazInt2)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp2.posCartaMazo == fn:length(mazInt2)}">
                                <spring:url value="${cp2.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt3}" var="cp3">
                        <c:if test="${cp3.posCartaMazo != fn:length(mazInt3)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp3.posCartaMazo == fn:length(mazInt3)}">
                                <spring:url value="${cp3.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt4}" var="cp4">
                        <c:if test="${cp4.posCartaMazo != fn:length(mazInt4)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp4.posCartaMazo == fn:length(mazInt4)}">
                                <spring:url value="${cp4.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt5}" var="cp5">
                        <c:if test="${cp5.posCartaMazo != fn:length(mazInt5)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp5.posCartaMazo == fn:length(mazInt5)}">
                                <spring:url value="${cp5.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt6}" var="cp6">
                        <c:if test="${cp6.posCartaMazo != fn:length(mazInt6)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp6.posCartaMazo == fn:length(mazInt6)}">
                                <spring:url value="${cp6.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt7}" var="cp7">
                        <c:if test="${cp7.posCartaMazo != fn:length(mazInt7)}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp7.posCartaMazo == fn:length(mazInt7)}">
                                <spring:url value="${cp7.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                    </c:forEach>

                </div>

            </div>        
        </div>
    
    
    <br><br><br>
    </div>


<!--MAZOS FINALES-->        
        <div class="container" style="background-color:#51C967;">
        <div class="row">
        <div class="col-md-2">
            <spring:url value="/resources/images/cards/fondoCorazon.png" htmlEscape="true" var="mazoCorazones"/>
            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoCorazones}"/>  
        </div>
        <div class="col-md-2"><spring:url value="/resources/images/cards/fondoPica.png" htmlEscape="true" var="mazoPicas"/>
            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoPicas}"/>  
        </div>
        <div class="col-md-2"><spring:url value="/resources/images/cards/fondoDiamante.png" htmlEscape="true" var="mazoDiamantes"/>
            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoDiamantes}"/>  
        </div>
        <div class="col-md-2"><spring:url value="/resources/images/cards/fondoTrebol.png" htmlEscape="true" var="mazoTreboles"/>
            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoTreboles}"/>  
        </div>
        <div class="col-md-2">
        </div>
        <div class="col-md-4 form-group">
	       MOVER CARTA
        </div>
        
        
        </div>
        </div>
            <a href="http://localhost:8080/partidas/finish/${partida.id}" class="btn btn-danger"> Finalizar partida LOST</a>
            <a href="http://localhost:8080/partidas/finish2/${partida.id}" class="btn btn-danger"> Finalizar partida WIN</a>

         
    </jsp:body>
</petclinic:layout>
