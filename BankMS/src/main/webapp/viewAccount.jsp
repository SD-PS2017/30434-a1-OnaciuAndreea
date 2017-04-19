<!-- 
<div class="container"> 
        <h2>Welcome,  ${pageContext.request.userPrincipal.name} !<span>${username}</span></h2>
        <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Bank Management</a>
    </div>
    <ul class="nav navbar-nav">
     <li>
 		<div class="dropdown">
  		<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Client Operations
  		<span class="caret"></span></button>
  			<ul class="dropdown-menu">
    		<li><a href="${contextPath}/employee">Add client</a></li>
    		<li><a href="${contextPath}/updateClient">Update client</a></li>
    		<li><a href="${contextPath}/viewClient">View client</a></li>
  			</ul>
		</div>
     </li>
     <li>-    </li>
     <li>
 		<div class="dropdown">
  		<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Account Operations
  		<span class="caret"></span></button>
  			<ul class="dropdown-menu">
    		<li><a href="${contextPath}/createAccount">Create account</a></li>
    		<li><a href="${contextPath}/updateAccount">Update account</a></li>
    		<li><a href="${contextPath}/deleteAccount">Delete account</a></li>
  			</ul>
		</div>		
     </li>
     
     <li><a href="${contextPath}/transferMoney">Transfer money</a></li>
     <li><a href="${contextPath}/processUtilities">Process utilities</a></li>
    </ul>
    <a href="${contextPath}/login"> <button class="btn btn-danger navbar-btn">
          Log out</button></a>
  </div>
</nav>

    
  <form method="POST" modelAttribute="updateAccount"  action="${contextPath}/updateAccount" class="form-signin" >    
   <p>  Insert CNP: </p>
    	<input name="cnp"  type="text"  class="form-control" placeholder="CNP"
                   autofocus="true"/> 
    <p></p>
    <p> Insert the Account ID: </p>
	   <input name="id"  type="number"  class="form-control" placeholder="ACCOUNT ID"
                   autofocus="true"/>  
                   </form>
   
  <form method="POST" modelAttribute="newAccount"  action="${contextPath}/updateAccount" class="form-signin" >                        
       <button class="btn btn-lg btn-primary btn-block" type="submit">Search account</button>
       <h2><span style="color:red" >${error1}</span></h2>
       <input name="type" readonly type="text"  class="form-control" placeholder="ACCOUNT TYPE"
                   autofocus="true"/>
	<p></p>
    	<div name="type" class="form-group">
    		  <select class="form-control" id="sel1">
			    <option>Savings</option>
			    <option>Deposit</option>
			    <option>Checking</option>
			  </select>
			</div>
       <input name="money"  type="number"   class="form-control" placeholder="MONEY"
                   autofocus="true"/>  
                   <button class="btn btn-lg btn-primary btn-block" type="submit">Update account</button>   
                    <h2><span style="color:red" >${error}</span></h2>
                    <h2><span style="color:green" >${message}</span></h2>
    </form>
-->


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

    <title>Employee page</title>

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
        <h2>Welcome,<span style="color:red" >${user}</span>!</h2>
        <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Bank Management</a>
    </div>
    <ul class="nav navbar-nav">
     <li>
 		<div class="dropdown">
  		<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Client Operations
  		<span class="caret"></span></button>
  			<ul class="dropdown-menu">
    		<li><a href="${contextPath}/employee">Add client</a></li>
    		<li><a href="${contextPath}/updateClient">Update client</a></li>
    		<li><a href="${contextPath}/viewClient">View client</a></li>
  			</ul>
		</div>
     </li>
     <li>-    </li>
     <li>
 		<div class="dropdown">
  		<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Account Operations
  		<span class="caret"></span></button>
  			<ul class="dropdown-menu">
    		<li><a href="${contextPath}/createAccount">Create account</a></li>
    		<li><a href="${contextPath}/updateAccount">Update account</a></li>
    		<li><a href="${contextPath}/deleteAccount">Delete account</a></li>
    		<li><a href="${contextPath}/viewAccount">View account</a></li>
  			</ul>
		</div>		
     </li>
     
     <li><a href="${contextPath}/transferMoney">Transfer money</a></li>
     <li><a href="${contextPath}/processUtilities">Process utilities</a></li>
    </ul>
    <a href="${contextPath}/login"> <button class="btn btn-danger navbar-btn">
          Log out</button></a>
  </div>
</nav>

    
        <form method="POST" modelAttribute="viewAccount"  action="${contextPath}/viewAccount" class="form-signin" >    

    	<input name="name" type="hidden" type="hidden" value=" " class="form-control" placeholder="Username"
                   autofocus="true" />

    	<input name="identityCardNumber"  type="hidden" type="text" value=" " class="form-control" placeholder="Password"
                   autofocus="true"/>
		Insert CNP:
    	<input name="cnp"   type="text"  class="form-control" placeholder="CNP"
                   autofocus="true"/> 
    	
    	<input name="address"  type="hidden" value=" " class="form-control" placeholder="Salary"
                   autofocus="true"/>	
    	<p></p>
    	<button class="btn btn-lg btn-primary btn-block" type="submit">View accounts</button>
    	 <font size="4"><span style="color:red" >${error}</span></font>
    	</form>
    	<table id="accounts" action="" method="GET" class="table table-striped table-bordered table-hover">
   		 <thead>
      		  <tr>
            	<th>Id</th>
            	<th>Cnp</th>
            	<th>Type</th>
            	<th>Money</th>
        	 </tr>
    	  </thead>
    	<tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.getId()}</td>
                <td>${account.getCnp()}</td>
                <td>${account.getType()}</td>
                <td>${account.getMoney()}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>



