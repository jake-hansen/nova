<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requester" value="/registermodal.jsp" scope="request"/>

<c:if test="${sessionScope.isAuthenticated == true && requestScope.forwarded_to_getuserdata == null}">
    <jsp:forward page="/getuserdata" />
</c:if>

<c:if test="${requestScope.user_object.groupID != 4}">
    <c:redirect url="/"/>
</c:if>

<c:if test="${requestScope.forwarded_to_getallgroups == null}">
    <c:set var="forwarded_to_getallgroups" value="${true}" scope="request"/>
    <jsp:forward page="/getallgroups" />
</c:if>
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Create Account</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
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
                                <option><c:out value="${group.groupName}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="registerpassword">Password</label>
                        <input type="password" class="form-control" id="registerpassword" name="password">
                    </div>
                    <div class="form-group">
                        <label for="passwordconfirmation">Confirm Password</label>
                        <input type="password" class="form-control" id="passwordconfirmation">
                    </div>
                    <div class="form-group mb-0">
                        <button id="registersubmit" type="submit" class="btn btn-primary btn-block">Register</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="modal-footer"></div>
        </div>
    </div>
</div>