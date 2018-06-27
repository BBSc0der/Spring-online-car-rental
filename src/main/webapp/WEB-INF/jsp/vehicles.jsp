<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri= "http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri= "http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />
<c:url var="search_url" value='/vehicles/query' />

<h1>Wyszukaj auto!</h1>
<form method="GET" action="${search_url}">
    <label>Model: </label>
    <input id="model" name="model" type="text" />
    <label>Marka: </label>
    <select name="brands">
        <option value="0" >nie wybrano</option>
        <c:forEach items="${brSelect2}" var="brand">
            <option value="${brand.idBrands}" >${brand.name}</option>
        </c:forEach>
    </select>
    <input type="hidden" value="3" name="maxResults" />
    <input type="hidden" value="0" name="offset" />
    <input type="submit" value="Wyszukaj" />
</form>
<c:if test="${searchedVehicles != null}">
    <c:forEach items="${searchedVehicles}" var="vehicle" >
        <fmt:formatDate var="year" value="${vehicle.yearOfProduction}" pattern="yyyy" />
        <div class="veh_spec">
            <h4>Model: ${vehicle.model}</h4>
            <h4>Marka: ${vehicle.brands.name}</h4>
            <ul>
                <c:choose>
                    <c:when  test="${vehicle.status == 'dostępny'}">
                        <li style="color: forestgreen">status: 

                            ${vehicle.status} 
                        </c:when>    
                        <c:otherwise>
                        <li style="color: red">status: 
                            wypożyczony
                        </c:otherwise>

                    </c:choose>   </li>

                <li>rok produkcji: ${year} </li>
                <li>rodzaj paliwa: ${vehicle.fuelType} </li>
                <li>silnik: ${vehicle.engineCapacity} </li>
                <li>przebieg: ${vehicle.carMileage} </li>
            </ul>
            <p> 
                <a href="<c:url value='/rent/${vehicle.idVehicles}' />">wynajmnij</a>

                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">

                    <a href="<c:url value='/vehicles/edit/${vehicle.idVehicles}' />">edytuj</a>

                    <c:url var="delete_url" value='/vehicles/${vehicle.idVehicles}' />
                    <form:form method="DELETE" action="${delete_url}">
                        <input type="submit" name="btnSubmit" value="usuń"/>
                    </form:form>
                </sec:authorize>
            </p>

        </div>
    </c:forEach>
    <div>
        <c:if test="${offset != 0}" >
            <a href="<c:url value='/vehicles/query' >
                   <c:param name = "model" value = "${model}"/>
                   <c:param name = "brands" value = "${brands}"/>
                   <c:param name = "maxResults" value = "${maxResults}"/>
                   <c:param name = "offset" value = "${offset - 1}"/>
               </c:url>">poprzednie</a>
        </c:if>
        <c:if test="${pages > 1}" >
            <c:choose>
                <c:when test="${pages > 9}">
                    <c:set var = "end" value = "${9}"/>
                    <c:choose>
                        <c:when test="${offset > 4}">
                            <c:choose>
                                <c:when test="${offset + 5 <  pages}">
                                    <c:set var = "begin" value = "${offset - 3}"/>
                                    <c:set var = "end" value = "${offset + 5}"/>
                                </c:when>    
                                <c:otherwise>
                                    <c:set var = "begin" value = "${pages - 8}"/>
                                    <c:set var = "end" value = "${pages}"/>
                                </c:otherwise>
                            </c:choose>
                        </c:when>    
                        <c:otherwise>
                            <c:set var = "begin" value = "${1}"/>
                        </c:otherwise>
                    </c:choose>
                </c:when>    
                <c:otherwise>
                    <c:set var = "begin" value = "${1}"/>
                    <c:set var = "end" value = "${pages}"/>
                </c:otherwise>
            </c:choose>
            <c:if test="${begin > 1}">
                ...
            </c:if>
            <c:forEach var = "pn" begin = "${begin}" end = "${end}">
                <a href="<c:url value='/vehicles/query' >
                       <c:param name = "model" value = "${model}"/>
                       <c:param name = "brands" value = "${brands}"/>
                       <c:param name = "maxResults" value = "${maxResults}"/>
                       <c:param name = "offset" value = "${pn - 1}"/>
                   </c:url>" <c:if test="${pn == offset + 1}">class="active"</c:if> >${pn}</a>
            </c:forEach>
            <c:if test="${end < pages}">
                ...
            </c:if>
        </c:if>
        <c:if test="${offset + 1 < pages}" >
            <a href="<c:url value='/vehicles/query' >
                   <c:param name = "model" value = "${model}"/>
                   <c:param name = "brands" value = "${brands}"/>
                   <c:param name = "maxResults" value = "${maxResults}"/>
                   <c:param name = "offset" value = "${offset + 1}"/>
               </c:url>">następne</a>
        </c:if>

    </div>
</c:if>

<jsp:include page="footer.jsp" />
