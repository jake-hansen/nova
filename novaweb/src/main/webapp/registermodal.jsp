<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function() {
        $("#signin").click(function () {
            $('#registerModal').modal('hide');
            $('#loginModal').modal('show');
        });
    });
</script>
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Sign up</h5>
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
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="username">
                        <c:if test="${sessionScope.username_available == false}">
                            <p class="text-danger">Email not available.</p>
                            <c:remove var="username_available" scope="session"/>
                        </c:if>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div class="form-group">
                        <label for="groupid">Group</label>
                        <select class="form-control" id="groupid" name="groupid">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name = "password">
                    </div>
                    <div class="form-group">
                        <label for="passwordConfirmation">Confirm Password</label>
                        <input type="password" class="form-control" id="passwordConfirmation">
                    </div>
                    <div class="form-group mb-0">
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="modal-footer">
                Already have an account? <a href="#" id="signin">Sign in</a>
            </div>
        </div>
    </div>
</div>