
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style2.css"/>
    <link rel="icon" type="image/vnd.microsoft.icon" href="${pageContext.request.contextPath}/resources/images/favicon .ico">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <title>news</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <jsp:include page="/WEB-INF/pages/container_news.jsp"/>
                <c:forEach var="pagination" items="${paginations}">
                    <form action="${pageContext.request.contextPath}/user/news/page/${pagination}">
                       <button type="submit"class="btn btn-default">${pagination}</button>
                    </form>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


</body>
</html>
