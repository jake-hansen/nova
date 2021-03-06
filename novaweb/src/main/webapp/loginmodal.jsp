<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="loginmodal.jsp" scope="request"/>

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modal-body">
                <form role="form" method="POST" action="./login">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                               placeholder="Enter Email" name="username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter Password"
                               name="password">
                        <c:if test="${sessionScope.isAuthenticated == false}">
                            <p class="text-danger">Username or password is incorrect.</p>
                            <c:remove var="isAuthenticated" scope="session"/>
                        </c:if>
                    </div>
                    <div class="form-group mb-0">
                        <button type="submit" class="btn btn-primary btn-block">Login</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="modal-footer"></div>
        </div>
    </div>
</div>