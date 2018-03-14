<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style2.css"/>
    <link rel="icon" type="image/vnd.microsoft.icon"
          href="${pageContext.request.contextPath}/resources/images/favicon .ico">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <title>products</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <c:forEach items="${product}" var="product">
                    <div class="container_news">
                        <c:out value="${valid}"/>
                        <form method="post" action="${pageContext.request.contextPath}/user/productAddCart">
                            <div>${product.nameProduct}</div>
                            <div>${product.id}</div>
                            <div>${product.price}</div>
                            <div class="">${product.description}</div>
                            <input type="hidden" name="idProduct" value="${product.id}">
                            <button type="submit" class="btn btn-default">Add to cart</button>
                        </form>
                    </div>
                </c:forEach>
                <c:forEach var="pagination" items="${paginations}">
                    <form action="${pageContext.request.contextPath}/user/product/page/${pagination}">
                        <button type="submit" class="btn btn-default">${pagination}</button>
                    </form>
                </c:forEach>

            </div>
        </div>
    </div>
</div>


</body>
</html>
