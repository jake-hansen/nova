<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.checkedsession == null}">
    <jsp:forward page="/checksession"/>
</c:if>

<c:if test="${sessionScope.isAuthenticated == true && requestScope.forwarded_to_getuserdata == null && sessionScope.group_id == 1}">
    <c:set var="forwarded_to_getuserdata" value="${true}" scope="request"/>
    <jsp:forward page="/faculty.jsp" />
</c:if>

<c:if test="${sessionScope.isAuthenticated == true && requestScope.forwarded_to_getuserdata == null && sessionScope.group_id == 2}">
    <c:set var="forwarded_to_getuserdata" value="${true}" scope="request"/>
    <jsp:forward page="/student.jsp" />
</c:if>

<c:if test="${sessionScope.isAuthenticated == true && requestScope.forwarded_to_getuserdata == null && sessionScope.group_id == 3}">
    <c:set var="forwarded_to_getuserdata" value="${true}" scope="request"/>
    <jsp:forward page="/firstresponders.jsp" />
</c:if>

<c:if test="${sessionScope.isAuthenticated == true && requestScope.forwarded_to_getuserdata == null && sessionScope.group_id == 4}">
    <c:set var="forwarded_to_getuserdata" value="${true}" scope="request"/>
    <jsp:forward page="/administrators.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Import jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Required meta tags -->
    <meta charset="utf-8">

    <!-- Enable support for mobile devices -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>NOVA</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Favicon Settings -->
    <link rel="apple-touch-icon" sizes="180x180" href="favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="favicon/favicon-16x16.png">
    <link rel="manifest" href="favicon/site.webmanifest">
</head>

<body style="background-color: black">
<!-- Navbar DIV -->
<div id="nav-placeholder"></div>
<script>
    $(function () {
        $("#nav-placeholder").load("navbar.jsp", function () {
            jQuery("#home_link").addClass("active");
        });
    });
</script>
<!-- End Navbar DIV -->

<!-- Login Modal -->
<div id="loginmodal-placeholder"></div>
<script>
    $(function () {
        $("#loginmodal-placeholder").load("loginmodal.jsp", function () {
            <c:if test="${sessionScope.failedAuthentication == true}">
            $('#loginModal').modal('show');
            <c:remove var="failedAuthentication" scope="session"/>
            </c:if>
        });
    });
</script>
<!-- End Login Modal-->

<!-- Sign Up Modal -->
<div id="signupmodal-placeholder"></div>
<script>
    $(function () {
        $("#signupmodal-placeholder").load("registermodal.jsp", function () {
            <c:if test="${sessionScope.failed_register == true}">
            $('#registerModal').modal('show');
            <c:remove var="failed_register" scope="session"/>
            </c:if>
        });
    });
</script>
<!-- End Sign Up Modal -->

<!-- Home Page -->
<header class="homepage" style="height: 100vh; min-height: 500px; background-image: url('./images/homepage.jpg'); background-size: cover; background-position: center; background-repeat: no-repeat; overflow: hidden; color: white; display: block; text-shadow: 2px 2px black">
    <div class="container h-100">
        <div class="row h-100 align-items-center">
            <div class="col-12 text-center">
                <h3>Network of Verified Accountability</h3>
                <p class="lead">Real-time accountability management for use in the aftermath of a school-related crisis.</p>
            </div>
        </div>
    </div>
</header>

<!-- Optional JavaScript -->
<!-- Popper.js, then Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>

</html>