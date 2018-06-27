<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <spring:url value="/resources/style.css" var="styleCSS" />
        <link href="${styleCSS}" rel="stylesheet" />
    </head>

    <body>
        <div id="header">
            <div id="bar">
                <h2>WYPOŻYCZALNIA</h2>
                <h2>SAMOCHODÓW</h2>
            </div>
            <div id="nav">
                <div id="left-nav">
                    <ul>
                        <li>
                            <a href="<c:url value='/vehicles' />">WYBIERZ SAMOCHÓD</a>
                        </li>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <li>
                            <a href="<c:url value='/vehicles/add' />">DODAJ POJAZD</a>    
                        </li> 
                        </sec:authorize>
                        
                        <sec:authorize access="isAuthenticated()">
                            <li>
                                <a href="<c:url value='/rent/my-rents' />">MOJE WYPOŻYCZENIA</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <li>
                                <a href="<c:url value='/register' />">REJESTRACJA</a>
                            </li>
                        </sec:authorize>
                    </li>
                </ul>
            </div>
            <div id="right-nav">
                <sec:authorize access="isAuthenticated()">
                    <c:url value="/logout" var="logout" />
                    <form:form action="${logout}" method="POST">
                        <input type="submit" value="WYLOGUJ" />
                    </form:form>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <a href="<c:url value='/login' />" >ZALOGUJ SIĘ </a>
                </sec:authorize>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>

    <div id="wrapper">

        <div id="content">