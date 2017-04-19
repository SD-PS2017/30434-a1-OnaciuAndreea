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
      <li class="active"><a href="${contextPath}/editUser">Edit user info</a></li>
      <li><a href="${contextPath}/seeUsers">See users</a></li>
      <li><a href="${contextPath}/seeReport">See user report</a></li>
    </ul>
    <a href="${contextPath}/login"> <button class="btn btn-danger navbar-btn">
          Log out</button></a>
  </div>
</nav>

    <form method="POST" modelAttribute="editUser"  action="${contextPath}/editUser" class="form-signin">  
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
  
 <form name="dUser" id="dUser" method="POST" modelAttribute="editUser"  action="${contextPath}/editUser" class="form-signin">  
    <p> Username:
    	<input name="username"  type="text" type="text" value="${userToDelete.getUsername()}" class="form-control" placeholder="Username"
                   autofocus="true" /></p>
    <p>Password:</p>
    	<input name="password"  type="text" type="text" value="${userToDelete.getPassword()}" class="form-control" placeholder="Password"
                   autofocus="true"/>
    	<input name="name"  type="hidden" value="${userToDelete.getName()}" readonly class="form-control" placeholder="Name"
                   autofocus="true"/> 
                   <p></p>
    <p>Salary:</p>
    	<input name="salary"  type="number" value="${userToDelete.getSalary()}" class="form-control" placeholder="Salary"
                   autofocus="true"/>
                   <p></p>       
         <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>  
          <h2><span style="color:green">${finalMessage}</span></h2>    
    </form>
   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>


