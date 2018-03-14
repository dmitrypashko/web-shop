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
    <jsp:include page="/WEB-INF/pages/menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <div class="container_news">

                    <img src="${pageContext.request.contextPath}/news/img/<c:out value="${detailedNews.id}"/>"
                         class="img-rounded" alt="img" width="200" height="200">
                    <div>Дата:<c:out value="${detailedNews.date}"/></div>
                    <div class="title_news">${detailedNews.header}</div>
                    <div>${detailedNews.content}</div>
                    <c:forEach var="comments" items="${comments}">
                        <div><input type="hidden" value="${comments.id}" name="idComment"></div>
                        <div><c:out value="${comments.user.name}"/>: ${comments.comment}</div>
                    </c:forEach>
                    <%--@elvariable id="Comment" type="com.gmail.dmitrypashko.dmitry.model.Comment"--%>
                    <form:form method="post" action="${pageContext.request.contextPath}/user/news/${detailedNews.id}"
                               modelAttribute="Comment">
                        <div class="form-group">
                            <div><input type="hidden" value="${detailedNews.id}" name="id"></div>
                            <label for="ta">Comments:</label>
                            <p CLASS="bg-danger"><form:errors path="comment"/></p>
                            <form:textarea class="form-control" name="comment" cols="30" rows="5" id="ta"
                                           path="comment"/>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
