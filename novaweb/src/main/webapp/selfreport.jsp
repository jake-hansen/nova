<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="/selfreport.jsp" scope="request"/>

<c:if test="${sessionScope.checkedsession == null}">
    <jsp:forward page="/checksession"/>
</c:if>

<c:if test="${sessionScope.isAuthenticated == false || sessionScope.isAuthenticated == null}">
    <c:redirect url="/"/>
</c:if>

<c:if test="${requestScope.forwarded_to_first_responder_updates == null}">
    <jsp:forward page="/getallfirstresponderupdates"/>
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
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

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

<!-- Report Status Toast -->
<div class="toast" data-autohide="false" style="position: absolute; bottom: 0; left: 50%; transform: translate(-50%, 0px); z-index: 9999;">
    <div class="toast-header">
        <strong class="mr-auto">Report Status</strong>
        <small>now</small>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body">
        <c:if test="${sessionScope.report_successful == true}">
            Your status was successfully reported.
        </c:if>
        <c:if test="${sessionScope.report_successful == false}">
            An error occurred. Your status was NOT reported. This could be because your status was already reported.
        </c:if>
    </div>
</div>
<c:if test="${sessionScope.report_successful != null}">
    <script>
        $(document).ready(function(){
            $('.toast').toast('show');
        });
    </script>
</c:if>
<%-- Remove session variable to reset toast display for next request --%>
<c:remove var="report_successful" scope="session"/>
<!-- End Report Status Toast -->


<!-- Student View -->
<div class="container">
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>Law Enforcement/EMS Updates</h5></div>
                <div class="card-body">
                    <p class="card-text">
                        <c:out value="${requestScope.first_responder_updates_list}"/>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>I Need Help</h5></div>
                <div class="card-body">
                    <p>I am injured and I need immediate assistance.</p>
                    <form role="form" method="POST" action="./selfreport">
                        <div class="form-group">
                            <label for="location">Location</label>
                            <input type="text" name="location" class="form-control" id="location" placeholder="Room number, relative location, surroundings, etc.">
                        </div>
                        <button type="submit" name="sos" class="btn btn-primary">Send SOS</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>I Am Lost</h5></div>
                <div class="card-body">
                    <p>I am not injured but I am not with other students/faculty.</p>
                    <form role="form" method="POST" action="./selfreport">
                        <button type="submit" name="cagm" class="btn btn-primary">Come And Get Me</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-header bg-dark text-light"><h5>I Am Okay</h5></div>
                <div class="card-body">
                    <p>I am not injured and I am with students/faculty at a designated safe location.</p>
                    <form role="form" method="POST" action="./selfreport">
                        <button type="submit" name="okay" class="btn btn-primary">Account For Me</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Popper.js-->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous">
</script>
</body>

</html>