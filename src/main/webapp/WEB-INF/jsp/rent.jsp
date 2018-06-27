<%-- 
    Document   : rent
    Created on : 2017-06-11, 18:46:25
    Author     : Bolek
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri= "http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri= "http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<fmt:formatDate var="year" value="${vehicle.yearOfProduction}" pattern="yyyy" />
<h1>Wynajmujesz samochód:</h1>
<div >
    <h4>${vehicle.model}</h4>
    <h4>${vehicle.brands.name}</h4>
    <ul>
        <li>rok produkcji: ${year} </li>
        <li>rodzaj paliwa: ${vehicle.fuelType} </li>
        <li>silnik: ${vehicle.engineCapacity} </li>
        <li>przebieg: ${vehicle.carMileage} km </li>
    </ul>
    <h4>Najemca: ${user.name} ${user.surname}</h3>
        <c:if test="${activeRents.size() > 0 }" >
            Auto niedostępne w dniach:
            <ul>
                <c:forEach items="${activeRents}" var="rent" >
                    <fmt:formatDate var="dateOfRental" value="${rent.dateOfRental}" pattern="yyyy-MM-dd" />
                    <fmt:formatDate var="dateOfReturn" value="${rent.dateOfReturn}" pattern="yyyy-MM-dd" />
                    <li>
                        ${dateOfRental} - ${dateOfReturn}
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <form:form method="POST" modelAttribute="newRent">
            <form:errors path="*" element="div" cssClass="error"/>
            <form:hidden path="vehicles.idVehicles" value="${newRent.vehicles.idVehicles}" />
            <h4>Na okres: </h3>
                <p>od: <form:input type="date" path="dateOfRental" /> </p>  
                <p>do: <form:input type="date" path="dateOfReturn" /> </p>  
                <input type="submit" value="Wypożycz" />
            </form:form>
            </div>
            <jsp:include page="footer.jsp" />
