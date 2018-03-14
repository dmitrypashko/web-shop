<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="news" items="${news}">
    <div class="container_news">
        <form method="get" action="${pageContext.request.contextPath}/user/news/${news.id}">
            <img src="${pageContext.request.contextPath}/news/img/<c:out value="${news.id}"/>" class="img-rounded"
                 alt="img" width="200" height="200">
            <div>Дата: ${news.date}</div>
            <div class="title_news">${news.header}</div>
            <button type="submit" class="btn btn-default">detailed</button>
        </form>
    </div>
</c:forEach>
