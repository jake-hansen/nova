<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="/accountability.jsp" scope="request"/>

<c:if test="${sessionScope.checkedsession == null}">
    <jsp:forward page="/checksession"/>
</c:if>

<c:if test="${sessionScope.isAuthenticated == false || sessionScope.isAuthenticated == null}">
    <c:redirect url="/"/>
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
            jQuery("#accountability_link").addClass("active");
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
                <div class="card-header bg-dark text-light"><h5>Search for Student/Faculty Member</h5></div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm">
                            <form role="form" method="POST" action="./searchuserbyname">
                                <div class="form-group">
                                    <label for="first_name_search">Enter name of person to search for.</label>
                                    <input type="text" name="first_name_search" class="form-control"
                                           id="first_name_search" placeholder="First Name">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="last_name_search" class="form-control"
                                           id="last_name_search" placeholder="Last Name">
                                </div>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </form>
                        </div>
                        <div class="col-sm">

                            <div>
                                    <h5>Search Result</h5>
                                    <hr>
                                    <c:if test="${sessionScope.search_found_user == true}">
                                        <c:set var="user_id_search" value="${sessionScope.found_user_object.id}" scope="request"/>
                                        <c:if test="${requestScope.forwarded_to_getemergencycontacts == null}">
                                            <jsp:forward page="/getemergencycontacts"/>
                                        </c:if>
                                        <p><strong>Name: </strong><c:out value="${sessionScope.found_user_object.firstName}" /> <c:out value="${sessionScope.found_user_object.lastName}" /></p>
                                        <p><strong>Email: </strong><c:out value="${sessionScope.found_user_object.email}" /></p>
                                        <p>
                                        <strong>Status: </strong> <c:choose>
                                                <c:when test="${sessionScope.found_user_object_status.statusCode == 1}">
                                                    <span class="text-danger">SOS</span> - <c:out value="${sessionScope.found_user_object_status.location}"/>
                                                </c:when>
                                                <c:when test="${sessionScope.found_user_object_status.statusCode == 2}">
                                                    <span class="text-warning">Lost</span> - <c:out value="${sessionScope.found_user_object_status.location}"/>
                                                </c:when>
                                                <c:when test="${sessionScope.found_user_object_status.statusCode == 3}">
                                                    <span class="text-success">Okay</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-warning">Not Accounted</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <strong>Emergency Contacts</strong>
                                        <hr>
                                        <c:forEach var="emergency_contact" items="${requestScope.emergency_contact_list}">
                                            <p>Name: <c:out value="${emergency_contact.firstName}"/> <c:out
                                                    value="${emergency_contact.lastName}"/></p>
                                            <p>Relationship: <c:out value="${emergency_contact.relationship}"/></p>
                                            <p>Primary Phone: <c:out value="${emergency_contact.primaryPhone}"/></p>
                                            <p>Secondary Phone: <c:out value="${emergency_contact.secondaryPhone}"/></p>
                                            <p>Email: <c:out value="${emergency_contact.email}"/></p>
                                            <div class="border-top my-3"></div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${sessionScope.search_found_user == false}">
                                        <p class="text-danger">No result found</p>
                                    </c:if>

                                    <c:remove var="found_user_object" scope="session"/>
                                    <c:remove var="search_found_user" scope="session"/>
                                    <c:remove var="found_user_object_status" scope="session"/>
                                </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Account for Student/Faculty Member</h5></div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm">
                            <form role="form" method="POST" action="./accountformember">
                                <div class="form-group">
                                    <label for="accountForName">Enter the name of a person you wish to account
                                        for.</label>
                                    <input type="text" class="form-control" id="accountForName" name="accountForName"
                                           placeholder="Student/Faculty Member Name">
                                </div>
                                <button type="submit" class="btn btn-primary">Account For Student/Faculty Member
                                </button>
                                <c:if test="${sessionScope.failed_lookup == false}">
                                    <p class="text-success">Accounted for Member</p>
                                    <c:remove var="failed_lookup" scope="session"/>
                                </c:if>
                                <c:if test="${sessionScope.failed_lookup == true}">
                                    <p class="text-danger">Member Does Not Exist</p>
                                    <c:remove var="failed_lookup" scope="session"/>
                                </c:if>
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