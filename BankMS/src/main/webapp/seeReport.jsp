<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin page</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container"> 
        <h2>Welcome, ADMIN !<span>${username}</span></h2>
        <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Bank Management</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${contextPath}/welcome">Add user</a></li>
      <li><a href="${contextPath}/deleteUser">Delete user</a></li>
      <li><a href="${contextPath}/editUser">Edit user info</a></li>
      <li><a href="${contextPath}/seeUsers">See users</a></li>
      <li class="active"><a href="${contextPath}/seeReport">See user report</a></li>
    </ul>
    <a href="${contextPath}/login"> <button class="btn btn-danger navbar-btn">
          Log out</button></a>
  </div>
</nav>

    <form method="POST" modelAttribute="employee"  action="${contextPath}/seeReport" class="form-signin">  
		<font size="3">Insert the name of the employee:</font>
    	<input name="username"  type="hidden" type="text" value=" " class="form-control" placeholder="Username"
                   autofocus="true" />
    	<input name="password"  type="hidden" type="text" value=" " class="form-control" placeholder="Password"
                   autofocus="true"/>
    	<input name="name"   type="text" value=" " class="form-control" placeholder="Name"
                   autofocus="true"/> 
    	<input name="salary"  type="hidden" value="0" class="form-control" placeholder="Salary"
                   autofocus="true"/>
                   <p></p>        
               
         <button class="btn btn-lg btn-primary btn-block" type="submit">Search employee</button>  
                    
    	<font size="5"><span style="color:red">${error}</span></font>
    </form>
         <table id="activities" action="" method="POST" class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>ID</th>
            <th>USER</th>
            <th>DATE</th>
            <th>ACTIVITY_TYPE</th>
            <th>ADDITIONAL INFORMATION</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="activity" items="${activities}">
            <tr>
                <td>${activity.getId()}</td>
                <td>${activity.getUser().getName()}</td>
                <td>${activity.getTime()}</td>
                <td>${activity.getActivityType().toString()}</td>
                <td>${activity.getAdditionalInfo()}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>

    

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>


