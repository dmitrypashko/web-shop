<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>registration</title>
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
            <div class="login">
                <%--@elvariable id="user" type="com.gmail.dmitrypashko.dmitry.model.User"--%>
                <form:form action="${pageContext.request.contextPath}/registration" method="post" modelAttribute="user">
                    <div class="form-group">
                        <p><form:errors path="name"/></p>
                        <label for="name">Name:</label>
                        <form:input type="text" class="form-control" id="name" name="name" path="name"/>
                    </div>
                    <div class="form-group">
                        <p><form:errors path="surname"/></p>
                        <label for="surname">Surname:</label>
                        <form:input type="text" class="form-control" id="surname" name="surname" path="surname"/>
                    </div>
                    <div class="form-group">
                        <p><form:errors path="email"/></p>
                        <label for="email">email:</label>
                        <form:input type="email" class="form-control" id="email" name="email" path="email"/>
                        <p>ввод в формате: dasdasd@gmail.com</p>
                    </div>
                    <div class="form-group">
                        <p><form:errors path="password"/></p>
                        <label for="pwd">Password:</label>
                        <form:input type="password" class="form-control" id="pwd" name="password" path="password"/>
                        <p>Строчные и прописные латинские буквы, цифры</p>
                    </div>
                    <div class="form-group">
                        <label for="rpwd">Repeat password</label>
                        <form:input type="password" class="form-control" id="rpwd" name="repeatPassword"
                                    path="confirmPassword"/>
                        <p>Строчные и прописные латинские буквы, цифры</p>
                    </div>
                    <div class="form-group">
                        <p><form:errors path="phoneNumber"/></p>
                        <label for="pn">Phone number:</label>
                        <form:input type="text" class="form-control" id="pn" name="phoneNumber" path="phoneNumber"/>
                    </div>
                    <div class="form-group">
                        <p><form:errors path="address"/></p>
                        <label for="country">Country:</label>
                        <form:input type="text" class="form-control" id="country" name="country"
                                    path="address.country"/>
                        <label for="city">City:</label>
                        <form:input type="text" class="form-control" id="city" name="city" path="address.city"/>
                        <label for="street">Street:</label>
                        <form:input type="text" class="form-control" id="street" name="street" path="address.street"/>
                        <label for="building">Building:</label>
                        <form:input type="text" class="form-control" id="building" name="building"
                                    path="address.building"/>
                        <label for="apartmentNumber">Apartment number:</label>
                        <form:input type="text" class="form-control" id="address" name="apartmentNumber"
                                    path="address.apartmentNumber"/>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
