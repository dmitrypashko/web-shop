<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="news" items="${news}">
    <div class="container_news">
        <img src="${pageContext.request.contextPath}/news/img/<c:out value="${news.id}"/>" class="img-rounded" alt="img"
             width="200" height="200">
        <div>Дата: ${news.date}</div>
        <div class="title_news">${news.header}</div>
        <div class="row">
            <div class="col-sm-4">
                <form method="get" action="${pageContext.request.contextPath}/admin/news/${news.id}">
                    <button type="submit" class="btn btn-default">detailed</button>
                </form>
            </div>
            <div class="col-sm-4">
                <form method="post" action="${pageContext.request.contextPath}/admin/deleteNews">
                    <div><input type="hidden" value="${news.id}" name="idNews"></div>
                    <button type="submit" class="btn btn-default">delete</button>
                </form>
            </div>
            <div class="col-sm-4">
                <form method="get" action="${pageContext.request.contextPath}/admin/changeNews/${news.id}">
                    <button type="submit" class="btn btn-default">change</button>
                </form>
            </div>
        </div>
    </div>
</c:forEach>
