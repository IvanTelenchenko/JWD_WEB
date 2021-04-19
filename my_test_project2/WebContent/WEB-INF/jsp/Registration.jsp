<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleReg1.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<%@include file="locale.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="reg">
		<div class="mytitle">
			<h1>${titleSignUp}</h1>
		</div>
		<c:if test="${param.errorEmailBusy != null}">
			<p class="error-message">${signupErrorMessageEmailUsing}</p>
		</c:if>
		<c:if test="${param.errorMessage != null}">
			<p class="error-message">${errormessage}</p>
		</c:if>
		<form class="form" action="Controller" method="post" name="registration">
			<input type="hidden" name="command" value="registration">
			<div class="fields">
			
				<input type="text" name="firstname" placeholder="${firstname}" required autofocus />
					<c:if test="${param.errorFirstname != null}">
						<div class="validMessage" >${errormessagefirstname}</div>
					</c:if>
				
				<input type="text" name="lastname" placeholder="${lastname}" required />
					<c:if test="${param.errorLastname != null}">
						<div class="validMessage" >${errormessagelastname}</div>
					</c:if>
				
				<input type="text" name="email" placeholder="${email}" required />
					<c:if test="${param.errorEmail != null}">
						<div class="validMessage" >${errormessageemail}</div>
					</c:if>
				
				<input type="password" name="password" placeholder="${password}" required />
					<c:if test="${param.errorPassword != null}">
						<div class="validMessage" >${errormessagepassword}</div>
					</c:if>
				
				<input type="text" name="phonenumber" placeholder="${phone}" required />
					<c:if test="${param.errorPhone != null}">
						<div class="validMessage" >${errormessagephone}</div>
					</c:if>
				
				
				<input class="mybut" type="submit" name="button" value="${butSignUp}" />
			<div class="link_signin">
				${alreadyregistered}<a href="Controller?command=gotosignin">${titleSignIn}</a>
			</div>
			</div>
		</form>
</div>

<%-- <div id="container">
		<article class="content">
		<div class="mytitle">
			<h1>${car.name}<i class="fas fa-user-circle"></i></h1>
		</div>
		<div class="mytitle">
			<h1>${titleSignUp}</h1>
	</div> <c:if test="${param.messageErrorUsing != null}">
			<p class="error-message">${signupErrorMessageEmailUsing}</p>
		</c:if>
		<c:if test="${param.messageErrorEmailIncorrectly != null}">
			<p class="error-message">${signupErrorMessageEmailIncorrectly}</p>
		</c:if>
		<form style="text-align: center;" class="form" action="Controller" method="post" name="registration">
			<input type="hidden" name="command" value="registration">
			<div class="fields">
				<input type="text" name="firstname" placeholder="${firstname}" required autofocus />
				<input type="text" name="lastname" placeholder="${lastname}" required />
				<input type="text" name="email" placeholder="${email}" required />
				<input type="password" name="password" placeholder="${password}" required />
				<input type="text" name="phonenumber" placeholder="${phone}" required />
				<input class="mybut" type="submit" name="button" value="${butSignUp}" />
				<div class="link_signin">
					${alreadyregistered}<a href="Controller?command=gotosignin">${titleSignIn}</a>
				</div>
			</div>
		</form>
		</article>
</div> --%>


	<%@include file="footer.jsp"%>
</body>
</html>