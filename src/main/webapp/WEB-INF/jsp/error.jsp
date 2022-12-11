<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">
    <div class="text-center">
        <h1><b>ERROR</b></h1>
        
        <h1>${message}</h1>
    
        <spring:url value="/" var="homeURL"></spring:url>
        <a href="${homeURL}" class="btn btn-default">Back home</a>
    </div>


</petclinic:layout>
