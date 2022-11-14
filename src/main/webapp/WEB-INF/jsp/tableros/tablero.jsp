<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    
    <jsp:body>
   		
   		
        <div class="row" align="center"  >
        <h2 >
           Tablero Creado
        </h2> 
        </div> 
        <div class="container" style="background-color:#51C967;">
        <div class="row">
        <div class="col-md-1">Mazo inter. 1
        </div>
        <div class="col-md-1">Mazo inter. 2
        </div>
        <div class="col-md-1">Mazo inter. 3
        </div>
        <div class="col-md-1">Mazo inter. 4
        </div>
         <div class="col-md-1">Mazo inter. 5
        </div>
         <div class="col-md-1">Mazo inter. 6
        </div>
         <div class="col-md-1">Mazo inter. 7
        </div>
        <div class="col-md-2">
        </div>
        <div class="col-md-1">Mazo INICIAL
        </div>
        
        
        </div>
       
        
        <br><br><br>
        </div>
        <div class="container" style="background-color:#51C967;">
        <div class="row">
        <div class="col-md-2">Mazo final 1
        </div>
        <div class="col-md-2">Mazo final 2
        </div>
        <div class="col-md-2">Mazo final 3
        </div>
        <div class="col-md-2">Mazo final 4
        </div>
        <div class="col-md-2">
        </div>
        <div class="col-md-4 form-group">
	       MOVER CARTA
        </div>
        
        
        </div>
        </div>
        
         
    </jsp:body>
</petclinic:layout>
