<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="partidas">

    <jsp:body>


        <div class="row" align="center">
            
        </div>


        <div class="container" style="background-color:#51C967;">

            <!-- MAZO INICIAL -->

            <div class="row">
                <div class="col-md-1">
                    <spring:url value="${mazInicial[0].carta.imagen}" htmlEscape="true" var="carta" />
                    <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                </div>

                <div class="col-md-1">
                    <c:if test="${fn:length(mazInicial) != 0}">
                        <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                        <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                    </c:if>
                </div>

                <button class="btn btn-default">PASAR CARTA</button>
            </div>


            </br>
            </br>
            </br>

            <!-- MAZOS INTERMEDIOS -->
            <div class="row">

                <div class="col-md-1">

                    <c:forEach items="${mazInt1}" var="cp1">
                        <c:if test="${cp1.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp1.isShow == true}">
                            <spring:url value="${cp1.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>

                <div class="col-md-1" style="text-align: center;">

                    <c:forEach items="${mazInt2}" var="cp2">
                        <c:if test="${cp2.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp2.isShow == true}">
                            <spring:url value="${cp2.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt3}" var="cp3">
                        <c:if test="${cp3.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp3.isShow == true}">
                            <spring:url value="${cp3.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt4}" var="cp4">
                        <c:if test="${cp4.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp4.isShow == true}">
                            <spring:url value="${cp4.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt5}" var="cp5">
                        <c:if test="${cp5.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp5.isShow == true}">
                            <spring:url value="${cp5.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt6}" var="cp6">
                        <c:if test="${cp6.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp6.isShow == true}">
                            <spring:url value="${cp6.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">

                    <c:forEach items="${mazInt7}" var="cp7">
                        <c:if test="${cp7.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp7.isShow == true}">
                            <spring:url value="${cp7.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>

        <!--MAZOS FINALES-->
                <div class="col-md-2" style="padding-left: 100px;">
                
                    <spring:url value="/resources/images/cards/fondoCorazon.png" htmlEscape="true" var="mazoCorazones" />
                    <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoCorazones}" />
                    
                    <spring:url value="/resources/images/cards/fondoPica.png" htmlEscape="true" var="mazoPicas" />
                    <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoPicas}" />
                
                    <spring:url value="/resources/images/cards/fondoDiamante.png" htmlEscape="true" var="mazoDiamantes" />
                    <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoDiamantes}" />
                    
                    <spring:url value="/resources/images/cards/fondoTrebol.png" htmlEscape="true" var="mazoTreboles" />
                    <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoTreboles}" />
                
                </div>



        </div>

        <div class="row">
            
            <table id="board1Game" class="table table-striped">
                <thead>
                    <tr>
                        <th>Cartas que se pueden mover:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <c:forEach items="${cartasPosiblesAMover}" var="cartaPartida">
                            <td>
                                <spring:url value="${cartaPartida.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="100" height="100" src="${carta}" />
                            </td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
            
            <table id="board1Game" class="table table-striped">
                <thead>
                    <tr>
                        <th>Mazo destino</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Mazo intermedio 1</td>
                        <td>Mazo intermedio 2</td>
                        <td>Mazo intermedio 3</td>
                        <td>Mazo intermedio 4</td>
                        <td>Mazo intermedio 5</td>
                        <td>Mazo intermedio 6</td>
                        <td>Mazo intermedio 7</td>
                        <td>Mazo final Corazones</td>
                        <td>Mazo final Picas</td>
                        <td>Mazo final Tréboles</td>
                        <td>Mazo final Diamantes</td>
                    </tr>
                </tbody>
            </table>
        </div>


        <br><br><br>
        </div>
      
        <a href="http://localhost:8080/partidas/finish/${partida.id}" class="btn btn-danger"> Finalizar partida LOST</a>
        <a href="http://localhost:8080/partidas/finish2/${partida.id}" class="btn btn-danger"> Finalizar partida WIN</a>


    </jsp:body>
</petclinic:layout>