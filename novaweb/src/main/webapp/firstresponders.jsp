<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="/firstresponders.jsp" scope="request"/>

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

<!-- Send Update Status Toast -->
<div class="toast" data-autohide="false" style="position: absolute; bottom: 0; left: 50%; transform: translate(-50%, 0px); z-index: 9999;">
    <div class="toast-header">
        <strong class="mr-auto">Informational Update</strong>
        <small>now</small>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body">
        <c:if test="${sessionScope.update_published == true}">
            Your update was published successfully.
        </c:if>
        <c:if test="${sessionScope.update_published == false}">
            An error occurred. Your update was NOT published successfully.
        </c:if>
    </div>
</div>
<c:if test="${sessionScope.update_published != null}">
    <script>
        $(document).ready(function(){
            $('.toast').toast('show');
        });
    </script>
</c:if>
<%-- Remove session variable to reset toast display for next request --%>
<c:remove var="update_published" scope="session"/>
<!-- Send Update Status Toast -->

<!-- Faculty View -->
<div class="container">
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Law Enforcement/EMS Updates</h5></div>
                <div class="card-body">
                    <p class="card-text">Most recent updates will be shown here...</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Send Informational Update</h5></div>
                <div class="card-body">
                    <form role="form" method="POST" action="./setfirstresponderupdate">
                        <div class="form-group">
                            <label for="informational_update">Type your update...</label>
                            <input type="text" name="informational_update" class="form-control" id="informational_update" placeholder="Current status of crisis.">
                        </div>
                        <button type="submit" class="btn btn-primary">Send Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Emergency Dashboard</h5></div>
                <div class="card-body">
                    <div style="overflow-x:auto; max-height: 400px">
                        <table id="emergencyDashboard" class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Emergency Type</th>
                                    <th>Student/Faculty Name</th>
                                    <th>Relative Location</th>
                                </tr>
                            </thead>
                            <c:forEach var="table_row" items="${requestScope.injured_and_lost_list}">
                                <tr>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                </tr>
<%--                                <tr>--%>
<%--                                    <td><c:out value="${table_row.get(0)}"/></td>--%>
<%--                                    <td><c:out value="${table_row.get(1)}"/></td>--%>
<%--                                    <td><c:out value="${table_row.get(2)}"/></td>--%>
<%--                                </tr>--%>
                            </c:forEach>
                        </table>
                    </div>
                    <form role="form" method="GET" action="./getalllostandinjured">
                        <button type="submit" class="btn btn-primary">Refresh</button>
                    </form>
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