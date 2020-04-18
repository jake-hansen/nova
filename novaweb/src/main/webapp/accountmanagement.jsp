<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.checkedsession == null}">
    <jsp:forward page="/checksession"/>
</c:if>

<c:set var="requester" value="accountmanagement.jsp" scope="request"/>
<c:if test="${requestScope.forwarded_to_getallgroups == null}">
    <c:set var="forwarded_to_getallgroups" value="${true}" scope="request"/>
    <jsp:forward page="/getallgroups" />
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

<body style="background-color: #d0d6e2">
<!-- Navbar DIV -->
<div id="nav-placeholder"></div>
<script>
    $(function () {
        $("#nav-placeholder").load("navbar.jsp", function () {
            jQuery("#accountmanagement_link").addClass("active");
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

<!-- Accountability View -->
<div class="container">
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Create An Account</h5></div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm">
                            <form role="form" method="POST" action="./register">
                                <div class="form-group">
                                    <label for="firstname">First Name</label>
                                    <input type="text" class="form-control" id="firstname" name="firstname">
                                </div>
                                <div class="form-group">
                                    <label for="lastname">Last Name</label>
                                    <input type="text" class="form-control" id="lastname" name="lastname">
                                </div>
                                <div class="form-group">
                                    <label for="registeremail">Email</label>
                                    <input type="email" class="form-control" id="registeremail" aria-describedby="emailHelp" name="username">
                                    <c:if test="${sessionScope.username_available == false}">
                                        <p class="text-danger">Email not available.</p>
                                        <c:remove var="username_available" scope="session"/>
                                    </c:if>
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                <div class="form-group">
                                    <label for="groupname">Group</label>
                                    <select class="form-control" id="groupname" name="groupname">
                                        <c:forEach var="group" items="${requestScope.group_list}">
                                            <option><c:out value="${group.groupName}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="registerpassword">Password</label>
                                    <input type="password" class="form-control" id="registerpassword" name = "password">
                                </div>
                                <div class="form-group">
                                    <label for="passwordconfirmation">Confirm Password</label>
                                    <input type="password" class="form-control" id="passwordconfirmation">
                                </div>
                                <div class="form-group mb-0">
                                    <button id="registersubmit" type="submit" class="btn btn-primary btn-block">Create Account</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Delete An Account</h5></div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm">
                            <form>
                                <div class="form-group">
                                    <label for="accountForName">Enter the name of a person who's account you wish to delete.</label>
                                    <input type="text" class="form-control" id="accountForName" placeholder="Student/Faculty Member Name">
                                </div>
                                <button type="submit" class="btn btn-primary">Delete Account</button>
                            </form>
                        </div>
                    </div>
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