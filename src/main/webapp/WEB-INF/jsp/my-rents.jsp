
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<h2>Historia wypożyczeń:</h2>
<c:choose>
    <c:when test="${empty rents}">
        <h4>Historia wypożyczeń jest pusta</h4>
    </c:when>
    <c:otherwise>

        <table class="statistics">
            <thead>
                <tr>
                    <th>Lp</th>
                    <th>Marka</th>
                    <th>Model</th>
                    <th>Data wypożyczenia</th>
                    <th>Data zwrotu</th>
                    <th>Status</th>
                    <th>Szczegóły</th>
                </tr>
            </thead>
            <tbody>


                <c:forEach items="${rents}" var="rent" varStatus="loop">
                    <fmt:formatDate var="onlyDateOfRental" value="${rent.dateOfRental}" pattern="yyyy-MM-dd" />
                    <fmt:formatDate var="onlyDateOfReturn" value="${rent.dateOfReturn}" pattern="yyyy-MM-dd" />
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${rent.vehicles.brands.name}</td>
                        <td>${rent.vehicles.model}</td>
                        <td>${onlyDateOfRental}</td>
                        <td>${onlyDateOfReturn}</td>
                        <td></td>
                        <td>więcej</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:otherwise>
    </c:choose>
</table>
<jsp:include page="footer.jsp" />