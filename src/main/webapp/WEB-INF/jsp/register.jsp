<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="header.jsp" />

<h1>Zarejestruj się</h1> 
        <form:form method="POST" modelAttribute="newUser">
            <form:errors path="*" cssClass="error" element="div"/>
            <p><label>Imię </label>
            <form:input id="name" path="name" type="text" /></p>
            <p><label>Nazwisko: </label>
            <form:input id="surname" path="surname" type="text" /></p>
            <p><label>Telefon: </label>
                <form:input type="number" step="1" path="phone" maxlength="9"/></p>
            <p><label>Email: </label>
            <form:input type="email" path="email" /></p>
            <p><label>Login: </label>
            <form:input type="text" path="nickname" /></p>
            <p><label>Hasło: </label>
            <form:input type="password" path="password" /></p>
            <p><label>Miasto: </label>
            <form:input type="text" path="city" /></p>
            <p><label>Ulica: </label>
            <form:input type="text" path="street" /></p>
            <p><label>Numer domu/mieszkania: </label>
            <form:input type="text" path="houseNumber" /></p>
            <p><label>Kod pocztowy (bez myślnika): </label>
            <form:input type="text" path="postalCode" maxlength="5"/></p>
            <p> Pola opcjonalne: </p>
            <p><label>Firma: </label>
            <form:input type="text" path="company" /></p>
            <p><label>NIP: </label>
                <form:input type="number" step="1"  path="nip" maxlength="10"/></p>
            <input type="submit" value="Zarejestruj" />
        </form:form>

<jsp:include page="footer.jsp" />