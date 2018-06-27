<%-- 
    Document   : login
    Created on : 2017-05-29, 20:20:40
    Author     : Bolek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="header.jsp" />


<c:if test="${not empty error}">
    <div class="error">
        <spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br />
    </div>
</c:if>
<form action="<c:url value="/login"></c:url>" method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
    <p>
        <label>login: </label><input class="form-control"  name='username' type="text">
    </p>
    <p>
        <label>hasło: </label><input class="form-control"  type="password" name='password'>
    </p>
    <input class="btn btn-lg btn-success btn-block" type="submit" 
           value="Zaloguj się">

</form>

<jsp:include page="footer.jsp" />