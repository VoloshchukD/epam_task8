<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <%@ include file="jsp/header.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<h3>Main page</h3>
<c:if test="${ not empty candies }">
    <table>
        <tr>
            <th>Name</th>
            <th>Energy</th>
            <th>productionDate</th>
            <th>expirationDate</th>
            <th>types</th>
            <th>ingredients</th>
            <th>value</th>
            <th>production</th>
        </tr>
        <c:forEach items="${candies}" var="candy">
            <tr>
                <td>
                    <c:out value="${candy.name}"/>
                </td>
                <td>
                    <c:out value="${candy.energy}"/>
                </td>
                <td>
                    <c:out value="${candy.productionDate}"/>
                </td>
                <td>
                    <c:out value="${candy.expirationDate}"/>
                </td>
                <td>
                    <c:if test="${ not empty candy.types }">
                        <c:out value="${candy.types}"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${ not empty candy.ingredients }">
                        <table>
                            <tr>
                                <th>Name</th>
                                <th>weight</th>
                                <th>kind</th>
                            </tr>
                            <c:forEach items="${candy.ingredients}" var="ingredient">
                                <tr>
                                    <td>
                                        <c:out value="${ingredient.name}"/>
                                    </td>
                                    <td>
                                        <c:out value="${ingredient.weight}"/>
                                    </td>
                                    <td>
                                        <c:out value="${ingredient.kind}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </td>
                <td>
                    <c:if test="${ not empty candy.value }">
                        <table>
                            <tr>
                                <th>proteins</th>
                                <th>fats</th>
                                <th>carbohydrates</th>
                            </tr>
                            <tr>
                                <td>
                                    <c:out value="${candy.value.proteins}"/>
                                </td>
                                <td>
                                    <c:out value="${candy.value.fats}"/>
                                </td>
                                <td>
                                    <c:out value="${candy.value.carbohydrates}"/>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </td>
                <td>
                    <c:out value="${candy.production}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${ not empty valid }">
    <c:if test="${ valid == true }">
        <c:out value="FILE IS VALID"/>
    </c:if>
    <c:if test="${ valid == false }">
        <c:out value="FILE NOT VALID"/>
    </c:if>
</c:if>

</body>
</html>