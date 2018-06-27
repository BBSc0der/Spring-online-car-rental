<%-- 
    Document   : editVehicle
    Created on : 2017-05-24, 09:14:21
    Author     : Bolek
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

        <h1>Edytuj auto</h1>
        <form:form method="POST" modelAttribute="editedVehicle">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <p><label>Model: </label>
            <form:hidden path="idVehicles" value="${editedVehicle.idVehicles}" />
            <form:hidden path="status" value="${editedVehicle.status}" />
            <form:input id="model" path="model" type="text" value="${editedVehicle.model}"/></p>
            <p><label>Marka: </label>
            <form:select path="brands.idBrands" >
                <form:options items="${allBrands}" itemValue="idBrands" itemLabel="name"/>
            </form:select></p>
            <p><label>Rok produkcji: </label>
            <form:input type="date" path="yearOfProduction" value="${editedVehicle.yearOfProduction}"/></p>
            <p><label>Rodzaj paliwa: </label>
            <form:input type="text" path="fuelType" value="${editedVehicle.fuelType}"/></p>
            <p><label>Pojemność silnika: </label>
            <form:input type="number"  step="0.1"  path="engineCapacity" value="${editedVehicle.engineCapacity}"/></p>
            <p><label>Przebieg: </label>
            <form:input type="number" step="1" path="carMileage" value="${editedVehicle.carMileage}"/> KM</p>
            <input type="submit" value="Zapisz zmiany" />
        </form:form>

<jsp:include page="footer.jsp" />
