<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navbar DIV -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3" style="font-size: larger">
    <a class="navbar-brand" href="#">NOVA</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item" id="home_link">
          <a class="nav-link" href="./">Home</a>
        </li>
        <c:if test="${sessionScope.isAuthenticated == true}">
          <li class="nav-item" id="account_link">
            <a class="nav-link" href="account.jsp">Profile</a>
          </li>
        </c:if>
        <c:if test="${sessionScope.isAuthenticated == true && sessionScope.group_id != 2}">
          <li class="nav-item" id="accountability_link">
            <a class="nav-link" href="accountability.jsp">Accountability</a>
          </li>
        </c:if>
        <c:if test="${sessionScope.isAuthenticated == true && sessionScope.group_id == 4}">
          <li class="nav-item" id="accountmanagement_link">
            <a class="nav-link" href="accountmanagement.jsp">Account Management</a>
          </li>
        </c:if>
      </ul>
      <ul class="navbar-nav mr-auto-p3">
        <c:if test="${sessionScope.isAuthenticated == null || sessionScope.isAuthenticated == false}">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
          Login
        </button>
        </c:if>
        <c:if test="${sessionScope.isAuthenticated == true}">
          <form action="/novaweb/logout" method="POST" class="btn" style="margin: 0; padding: 0;">
            <button type="submit" class="btn btn-secondary" style="width: 100%">Logout</button>
          </form>
        </c:if>
      </ul>
    </div>
  </nav>