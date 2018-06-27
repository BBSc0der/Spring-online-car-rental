
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

        
        <h1>Dodaj auto</h1>
        <form:form method="POST" modelAttribute="newVehicle">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <form:hidden path="status" value="dostępny" />
            <p><label>Model: </label>
            <form:input id="model" path="model" type="text" /></p>
            <p><label>Marka: </label>
            <form:select path="brands.idBrands">
                <form:options items="${allBrands}" itemValue="idBrands" itemLabel="name"/>
            </form:select></p>
            <p><label>Rok produkcji: </label>
            <form:input type="date" path="yearOfProduction" /></p>
            <p><label>Rodzaj paliwa: </label>
            <form:input type="text" path="fuelType" /></p>
            <p><label>Pojemność silnika: </label>
            <form:input type="number" step="0.1" path="engineCapacity" /></p>
            <p><label>Przebieg: </label>
            <form:input type="number" step="1" path="carMileage" /> KM</p>
            <input type="submit" value="Dodaj" />
        </form:form>
            
<jsp:include page="footer.jsp" />
