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
    <title>users</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/pages/admin_navigation_menu.jsp"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="modal-content">
                <jsp:include page="/WEB-INF/pages/admin_menu.jsp"/>
                <c:forEach items="${users}" var="user">
                    <div class="container_news">
                        <form action="${pageContext.request.contextPath}/admin/changeRole" method="post">
                            <div>${user.name}</div>
                            <div>${user.email}</div>
                            <input type="hidden" value="${user.id}" name="id">
                            <label for="role">Role: ${user.role}</label>
                            <select name="role" id="role" class="form-control">
                                <option>ROLE_USER</option>
                                <option>ROLE_ADMIN</option>
                                <option>ROLE_SUPER_ADMIN</option>
                            </select>
                            <button type="submit" class="btn btn-default">change role</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/changeStatusUser" method="post">
                            <input type="hidden" value="${user.id}" name="id">
                            <label for="status">Status: ${user.status}</label>
                            <select name="status" id="status" class="form-control">
                                <option>STATUS_UNBLOCKED</option>
                                <option>STATUS_BLOCKED</option>
                            </select>
                            <button type="submit" class="btn btn-default">change status</button>
                        </form>
                    </div>
                </c:forEach>
                <c:forEach var="pagination" items="${paginations}">
                    <form action="${pageContext.request.contextPath}/admin/users/page/${pagination}">
                        <button type="submit" class="btn btn-default">${pagination}</button>
                    </form>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


</body>
</html>

