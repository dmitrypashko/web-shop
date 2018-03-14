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
    <title>products</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/admin_navigation_menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <jsp:include page="/WEB-INF/pages/admin_menu.jsp"/>
                <%--@elvariable id="Product" type="com.gmail.dmitrypashko.dmitry.model.Product"--%>
                <form:form method="post" action="${pageContext.request.contextPath}/admin/addProduct/page/1"
                           modelAttribute="product">
                    <label for="nameProduct">Name product:</label>
                    <p CLASS="bg-danger"><form:errors path="nameProduct"/></p>
                    <form:input type="text" class="form-control" id="nameProduct" name="nameProduct"
                                path="nameProduct"/>
                    <label for="price">Price:</label>
                    <p CLASS="bg-danger"><form:errors path="price"/></p>
                    <form:input type="text" class="form-control" id="price" name="price" path="price"/>
                    <label for="descriptionProduct">Description:</label>
                    <p CLASS="bg-danger"><form:errors path="description"/></p>
                    <form:input type="text" class="form-control" id="descriptionProduct" name="descriptionProduct"
                                path="description"/>
                    <button type="submit" class="btn btn-default">Add</button>
                </form:form>
                <c:forEach items="${products}" var="product">
                    <div class="container_news">
                        <div>${product.nameProduct}</div>
                        <div>${product.id}</div>
                        <div>${product.price}</div>
                        <div class="">${product.description}</div>
                    </div>
                </c:forEach>
                <c:forEach var="pagination" items="${paginations}">
                    <form action="${pageContext.request.contextPath}/admin/addProduct/page/${pagination}">
                        <button type="submit" class="btn btn-default">${pagination}</button>
                    </form>
                </c:forEach>

            </div>
        </div>
    </div>
</div>


</body>
</html>