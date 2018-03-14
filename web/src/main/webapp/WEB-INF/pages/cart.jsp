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
    <title>cart</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <div class="container_news">
                    <form method="post" action="${pageContext.request.contextPath}/user/addOrder" id="addOrder">
                        <c:forEach items="${productInCart}" var="productInCart">
                            <div class="container_news">

                                <div>Name: ${productInCart.nameProduct}</div>
                                <div><input type="hidden" value="${productInCart.id}" name="productId"/></div>
                                <div>Price: ${productInCart.price}</div>
                                <div class="">Description: ${productInCart.description}</div>
                                <div class="form-group">
                                    <label for="quantity">Quantity: </label>
                                    <select name="quantity" id="quantity" class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" value="${productInCart.id}" name="products"
                                                  form="deleteProductInCart"/>Check for delete</label>
                                </div>
                            </div>
                        </c:forEach>
                    </form>
                    <button type="submit" class="btn btn-default" form="addOrder">add Order</button>
                    <form method="post" action="${pageContext.request.contextPath}/user/deleteProductInCart"
                          id="deleteProductInCart">
                        <button type="submit" class="btn btn-default" form="deleteProductInCart">delete</button>
                    </form>


                </div>
            </div>
        </div>
    </div>
</body>
</html>
