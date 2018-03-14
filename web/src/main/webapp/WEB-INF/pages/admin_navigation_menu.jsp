<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-12">
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a type="button" href="${pageContext.request.contextPath}/admin/product/page/1">Products</a>
                    </li>
                    <li><a type="button" href="${pageContext.request.contextPath}/admin/news/page/1">News</a></li>
                    <li><a type="button" href="${pageContext.request.contextPath}/admin/orders/page/1">Orders</a></li>
                    <c:if test="${role=='ROLE_SUPER_ADMIN'}">
                        <li><a type="button" href="${pageContext.request.contextPath}/admin/users/page/1">Users</a></li>
                    </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a type="button" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>
