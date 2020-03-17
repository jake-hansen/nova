<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Login</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form role="form" method="POST" action="./checkauth">
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                placeholder="Enter email" name="username">
              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" placeholder="Enter Password" name = "password">
              <c:if test="${sessionScope.isAuthenticated == false}">
                <p class="text-danger">Username or password is incorrect.</p>
              </c:if>
            </div>
            <div class="form-group mb-0">
              <button type="submit" class="btn btn-primary btn-block">Login</button>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          Don't have an account? <a href="/auth/register">Sign up</a>
        </div>
      </div>
    </div>
</div>