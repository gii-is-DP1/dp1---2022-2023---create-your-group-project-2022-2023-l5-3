<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

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
                <c:forEach items="${cartasPartida}" var="cp">
                  <c:forEach var="mazo" items="${mazosOrder}">
                    <c:if test="${cp.mazo.id == mazo}">
                        <div class="col-md-1" style="display: flex; flex-direction: column; align-items: center; justify-content: center;">    
                    <c:if test="${cp.posCartaMazo != 1}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                            <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/>
                        </c:if>
                        <c:if test="${cp.posCartaMazo == 1}">
                                <spring:url value="${cp.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}"/>
                        </c:if>
                        </div>
                      
                    </c:if>
                  </c:forEach>
               

<!--MAZO INICIAL-->
                        <c:if test="${cp.mazoInicial.id == cp.partida.id && cp.posCartaMazo == 1}">
                            <div class="col-md-1" style="display: flex; justify-content: space-between;">
                                <spring:url value="${cp.carta.imagen}" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" style="margin: 0 10px 0 70px;" width="300" height="300" src="${carta}"/>
                                <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta"/>
                                <img class="img-responsive mx-auto d-block" style="margin: 0 10px;" width="300" height="300" src="${carta}"/> 
                            </div>
                        </c:if>
                    
                </c:forEach>
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
