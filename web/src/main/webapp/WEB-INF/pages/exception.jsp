<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>login</title>

    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="icon" type="image/vnd.microsoft.icon"
          href="${pageContext.request.contextPath}/resources/images/favicon .ico">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="Title">
                The website electronic products
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h1 CLASS="bg-danger">${errorMsg}</h1>
            <form method="get" action="${pageContext.request.contextPath}/logout">
                <button type="submit" class="btn btn-default">to return to the login page</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
