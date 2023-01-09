<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="logroEditar">
    <h2>
        <p class="align-center " style="font-size: 3em;"> EDITAR LOGRO</p>
    </h2>

</br>
</br>
</br>
</br>
    <form:form modelAttribute="logro" class="form-horizontal " id="add-jugador-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Numero de condicion" name="numCondicion" />
            <petclinic:inputField label="Signo de condicion" name="signoCondicion" />
            <input type="hidden" name="logro.name" value="${logro.name}">
            <input type="hidden" name="logro.description" value="${logro.description}">
            <input type="hidden" name="logro.is_unlocked" value="${logro.is_unlocked}">
            <input type="hidden" name="logro.image" value="${logro.image}">
            <input type="hidden" name="logro.jugador" value="${logro.jugador}">

        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Actualizar logro</button>

                <spring:url value="/jugador/logros/editar" var="editUrl"></spring:url>
                <a href="${editUrl}" class="btn btn-default">Volver</a>
            </div>
        </div>
        <form:errors></form:errors>
    </form:form>

</petclinic:layout>