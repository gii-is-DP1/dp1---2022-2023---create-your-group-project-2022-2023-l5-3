

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="logros">
   
	<div class="container text-center">
		<h1><b>Logros</b></h1>
	</br>
	</div>
			<table id="usersTable" class="table table-striped">
				<thead>
				<tr>
					<th style="width: 150px;">Logro</th>
					<th style="width: 200px;">Descripcion</th>
					<th style="width: 200px;">Desbloqueado?</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${logros}" var="logro">
					<c:if test="${logro.is_unlocked == false}">
						<tr> 
							<td>
								<c:out value="${logro.name}"/>
							</td>
							<td>
								<c:out value="${logro.description}"/>
							</td>
							<td>
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</td>
						</tr>
					</c:if>
                	<c:if test="${logro.is_unlocked == true}">
						<tr>
							<td>
								<c:out value="${logro.name}"/>
							</td>
							<td>
								<c:out value="${logro.description}"/>
							</td>
							<td>
								<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
							</td>
						</tr>
					</c:if>
					
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>