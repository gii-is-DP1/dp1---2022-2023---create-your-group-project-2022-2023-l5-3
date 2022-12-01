<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <div class="row">
        <div class="row-md-12">
            <spring:url value="/resources/images/logoPNG_3.png" htmlEscape="true" var="logoUS"/>
            <img class="img-responsive" src="${logoUS}"/>
        </div>
        <div class="row-md-12">    
            <h2>${title}</h2>
            <p><h2>Group ${group}</h2></p>
            <p>
                <ul>   
                    <c:forEach items="${persons}" var="person">
                        <li>${person.firstName} <br>${person.lastName}</li>
                    </c:forEach>
                </ul>
            </p>
        </div>
    </div>
        <div class="row">
            <h2 class = "justify-content-center">BIENVENIDO</h2>
        </div>
    </div>
</petclinic:layout>
