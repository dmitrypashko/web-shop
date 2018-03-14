<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>login</title>

    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="icon" type="image/vnd.microsoft.icon" href="${pageContext.request.contextPath}/resources/images/favicon .ico">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="Title">
                The website electronic productss
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="login">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <c:if test="${param['error']}">
                        <p CLASS="bg-danger">Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
                    </c:if>
                    <div class="form-group">
                        <label for="username">Mail:</label>
                        <input type="email" class="form-control" id="username" name="username" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                    <a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/registration">Registration</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
