<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleReg1.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<%@include file="locale.jsp"%>
<body>
	<%@include file="header.jsp"%>
	<div class="reg">
		<div class="mytitle">
			<h1>${titleSignIn}</h1>
		</div>
		<c:if test="${param.messageError != null}">
			<article class="error-message">
				<p>${signinErrorMessage}</p>
			</article>
		</c:if>
		<c:if test="${param.messageReg != null}">
			<article class="success-message">
				<p>${messageReg}</p>
			</article>
		</c:if>
		<form class="form" action="Controller" method="post" name="authorization">
			<input type="hidden" name="command" value="authorization">
			<div class="fields">
				<input type="text" name="email" placeholder="${email}" required autofocus />
				<input type="password" name="password" placeholder="${password}" required />
				<input class="mybut" type="submit" name="button" value="${butSignIn}" />
				<div class="link_signin">
					${create}<a href="Controller?command=gotoregistration">${titleSignUp}</a>
				</div>
			</div>
		</form>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>