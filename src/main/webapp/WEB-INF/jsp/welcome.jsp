<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">

    <div class="m-0 row justify-content-center">
        <div class="col-auto p-5 text-center">
            <spring:url value="/resources/images/logowelcome.png" htmlEscape="true" var="logoUS"/>
            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${logoUS}"/>  
            <h2>${title}</h2>
            <p><h2>Group ${group}</h2></p>
            <p>
                <ul>   
                    <c:forEach items="${persons}" var="person">
                       ${person.firstName}    ${person.lastName}
                        </br>
                    </c:forEach>
                </ul>
            </p>
        </div>
    
    </div>

</petclinic:layout>
