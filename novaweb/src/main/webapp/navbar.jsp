<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="/navbar.jsp" scope="request"/>

<c:if test="${sessionScope.isAuthenticated == true && requestScope.forwarded_to_getuserdata == null}">
    <jsp:forward page="/getuserdata" />
</c:if>

<c:if test="${requestScope.user_object.groupID == 4}">
    <!-- Create Modal -->
    <script>
        $(function () {
            $("#createmodal-placeholder").load("registermodal.jsp", function () {
                <c:if test="${sessionScope.failed_register == true}">
                $('#registerModal').modal('show');
                <c:remove var="failed_register" scope="session"/>
                </c:if>
            });
        });
        $(document).ready(function () {
            $("#create").click(function () {
                $('#registerModal').modal('show');
            });
        });
    </script>
    <div id="createmodal-placeholder"></div>
    <!-- End Create Modal -->
</c:if>
<!-- Navbar DIV -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
    <a class="navbar-brand" href="#">NOVA</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" id="home_link">
                <a class="nav-link" href="./">Home</a>
            </li>
            <c:if test="${sessionScope.isAuthenticated == true}">
                <li class="nav-item" id="account_link">
                    <a class="nav-link" href="account.jsp">Account</a>
                </li>
                <!-- Administrator views: admins are groupID number 4 -->
                <c:if test="${requestScope.user_object.groupID == 4}">
                    <li class="nav-item">
                        <a href="#" class="nav-link" id="create">Create Account</a>
                    </li>
                </c:if>
            </c:if>
        </ul>
        <ul class="navbar-nav mr-auto-p3">
            <c:if test="${sessionScope.isAuthenticated == null || sessionScope.isAuthenticated == false}">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
                    Login
                </button>
            </c:if>
            <c:if test="${sessionScope.isAuthenticated == true}">
                <form action="/novaweb/logout" method="POST" class="btn" style="margin: 0; padding: 0;">
                    <button type="submit" class="btn btn-secondary" style="width: 100%;">Logout</button>
                </form>
            </c:if>
        </ul>
    </div>
</nav>