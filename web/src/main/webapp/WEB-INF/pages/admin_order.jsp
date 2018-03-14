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
    <title>orders</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/admin_navigation_menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <jsp:include page="/WEB-INF/pages/admin_menu.jsp"/>
                <c:forEach items="${orders}" var="order">
                    <div class="container_news">
                        <form action="${pageContext.request.contextPath}/admin/changeStatusOrder" method="post">
                            <label for="status">Status: </label>
                            <select name="status" id="status" class="form-control">
                                <option>${order.status}</option>
                                <option>SENT</option>
                                <option>RECEIVED</option>
                            </select>
                            <input type="hidden" value="${order.id}" name="orderId">
                            <c:forEach items="${order.productInOrderDTO}" var="product">
                                <div>Price:${product.price}</div>
                                <div>Name: ${product.name}</div>
                                <div>Quantity: ${product.quantity}</div>
                            </c:forEach>
                            <button type="submit" name="pagination" class="btn btn-default">change</button>
                        </form>
                    </div>
                </c:forEach>
                <c:forEach var="pagination" items="${paginations}">
                    <form action="${pageContext.request.contextPath}/admin/orders/page/${pagination}">
                        <button type="submit" class="btn btn-default">${pagination}</button>
                    </form>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


</body>
</html>
