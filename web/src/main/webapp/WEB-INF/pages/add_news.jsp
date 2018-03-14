<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style2.css"/>
    <link rel="icon" type="image/vnd.microsoft.icon"
          href="${pageContext.request.contextPath}/resources/images/favicon .ico">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <title>news</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/admin_navigation_menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <jsp:include page="/WEB-INF/pages/admin_menu.jsp"/>
                <%--@elvariable id="News" type="com.gmail.dmitrypashko.dmitry.model.News"--%>
                <form:form method="post" action="${pageContext.request.contextPath}/admin/addNews/page/1"
                           enctype="multipart/form-data" modelAttribute="News">
                    <label for="imageNews">Image news:</label>
                    <input type="file" id="imageNews" name="imageNews" accept="image/*">
                    <label for="date">Date:</label>
                    <p CLASS="bg-danger"><form:errors path="date"/></p>
                    <form:input type="date" class="form-control" id="date" name="date" path="date"/>
                    <label for="header">Title News:</label>
                    <p CLASS="bg-danger"><form:errors path="header"/></p>
                    <form:input type="text" class="form-control" id="header" name="header" path="header"/>
                    <label for="content">Description:</label>
                    <p CLASS="bg-danger"><form:errors path="content"/></p>
                    <form:input type="text" class="form-control" id="content" name="content" path="content"/>
                    <button type="submit" class="btn btn-default">Add</button>
                </form:form>
                <jsp:include page="/WEB-INF/pages/admin_container_news.jsp"/>
                <c:forEach var="pagination" items="${paginations}">
                    <form action="${pageContext.request.contextPath}/admin/addNews/page/${pagination}" method="get">
                        <button type="submit" class="btn btn-default">${pagination}</button>
                    </form>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


</body>
</html>
