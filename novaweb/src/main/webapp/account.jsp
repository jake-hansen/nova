<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="/account.jsp" scope="request"/>

<c:if test="${sessionScope.checkedsession == null}">
    <jsp:forward page="/checksession"/>
</c:if>

<c:if test="${sessionScope.isAuthenticated == false}">
    <c:redirect url="/"/>
</c:if>

<c:if test="${requestScope.forwarded_to_getuserdata == null}">
    <jsp:forward page="/getuserdata" />
</c:if>

<c:if test="${requestScope.forwarded_to_getgroupdata == null}">
    <c:set var="group_id" value="${requestScope.user_object.groupID}" scope="request"/>
    <jsp:forward page="/getgroupdata" />
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

    <title>NOVA | Account</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Favicon Settings -->
    <link rel="apple-touch-icon" sizes="180x180" href="favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="favicon/favicon-16x16.png">
    <link rel="manifest" href="favicon/site.webmanifest">
</head>

<body style="background-color: #d0d6e2">

<!-- Navbar DIV -->
<div id="nav-placeholder"></div>
<script>
    $(function () {
        $("#nav-placeholder").load("navbar.jsp", function () {
            jQuery("#account_link").addClass("active");
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

<div class="container">
    <div class="row justify-content-center">
        <div class="col">
            <div class="card">
                <div class="card-header bg-dark">
                    <h3 class="text-light">Hi, <c:out value="${requestScope.user_object.firstName}"/></h3>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Account Info</h5>
                    <div class="border-top my-3"></div>
                    <h6>First Name</h6>
                    <p><c:out value="${requestScope.user_object.firstName}"/></p>
                    <h6>Last Name</h6>
                    <p><c:out value="${requestScope.user_object.lastName}"/></p>
                    <h6>Email</h6>
                    <p><c:out value="${requestScope.user_object.email}"/></p>
                    <h6>Group</h6>
                    <p><c:out value="${requestScope.group_object.groupName}"/></p>
                </div>
            </div>
        </div>
    </div>


    <div class="row justify-content-center">
        <div class="col">
            <div class="card my-3">
                <div class="card-header bg-dark">
                    <h3 class="text-light">Emergency Contacts</h3>
                </div>
                <div class="card-body">
                    <h5 class="card-title">All</h5>
                    <div class="border-top my-3"></div>
                </div>
            </div>
        </div>
    </div>

</div>

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