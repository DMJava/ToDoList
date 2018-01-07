<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Show all deals</title>
    <base href="${pageContext.request.contextPath}">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/app-resources/css/style.css"/>
    <script src="${pageContext.request.contextPath}/app-resources/js/lib/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/app-resources/js/window.js"></script>
</head>
<body>

<div id="parent">
    <div id="header">
        <ul>
            <li><a class="menu" href="/viewForm/isNotDone">Невыполненные</a></li>
            <li><a class="menu" href="/viewForm/isDone">Выполненные</a></li>
            <li><a class="menu" href="/viewForm/all">Все</a></li>
        </ul>
    </div>

    <div id="content">
        <table id="tableContent">
            <tr class="row">
                <td>Id</td>
                <td>Имя</td>
                <td>Создан</td>
                <td>Описание</td>
                <td></td>
            </tr>

            <c:forEach  var="task" items="${deals}">
                <tr align="top">
                    <td><c:out value="${task.id}"/></td>
                    <td><c:out value="${task.name}"/></td>
                    <td><c:out value="${task.createdDate}"/></td>
                    <td><c:out value="${task.description}"/></td>
                    <td class="column">
                        <a class="buttonCustom" href="${pageContext.request.contextPath}/deleteTask?id=${task.id}">Удалить</a>
                        <a class="buttonCustom" href="${pageContext.request.contextPath}/dealById?id=${task.id}">Править</a>
                        <c:if test="${task.done==false}">
                            <a class="buttonCustom" href="${pageContext.request.contextPath}/taskIsDone?id=${task.id}">Выполнил</a>
                        </c:if>
                    </td>
                </tr>

            </c:forEach>

            <tr>
                <td>
                    <a href="/viewForm/window" rel="#overlay" class="buttonCustom" type="button">Добавить</a>
                </td>

        </table>

        <table class="pageTable">
            <tr>
                <td>
                    <a href="/previousPage" class="buttonPage">Сюда</a>

                    <c:forEach var="pageNum" begin="1" end="${maxPage}">
                        <c:if test="${pageNum == page}">
                            <a class="buttonActivePage" href="${pageContext.request.contextPath}/getPage?page=${pageNum}">
                                <c:out value="${pageNum}"/>
                            </a>
                        </c:if>
                        <c:if test="${pageNum != page}">
                            <a class="buttonPage" href="${pageContext.request.contextPath}/getPage?page=${pageNum}">
                                <c:out value="${pageNum}"/>
                            </a>
                        </c:if>
                    </c:forEach>

                    <a href="/nextPage" class="buttonPage">Туда</a>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>