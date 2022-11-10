<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    
    <jsp:body>
        <h2>
            Nueva Partida
        </h2>
        <form:form modelAttribute="partida"
                   class="form-horizontal">
            <form:hidden  path="id" />
            <form:hidden  path="momentoInicio" />
            <form:hidden  path="numMovimientos" />
            <form:hidden  path="victoria" />
          
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    
					<button class="btn btn-default" type="submit">Crear Partida</button>                        
                </div>
            </div>
        </form:form>
          
    </jsp:body>
</petclinic:layout>
