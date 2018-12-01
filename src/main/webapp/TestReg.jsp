<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="main" style="margin:0 auto;width:500px;">
	<form id="reg" action="${pagecontext.request.contextPath}/Register/test" method="post">
		<p>	E-mail：<input type="text" class="input" name="email" id="email"></p>
		<p>	username：<input type="text" class="input" name="username" id="username"></p>
		<p>	密 码：<input type="password" class="input" name="pwd" id="pwd"></p>
		<p>	<input type="submit" class="btn" value="提交注册" >	</p>
	</form>
</div>

</body>
</html>