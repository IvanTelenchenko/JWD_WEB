<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleMain1.css" rel="stylesheet" type="text/css">
<%@include file="locale.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="mytitle">
</div>
	<div id="container">
		<article class="content">
			<h2>${changepd}</h2>
			
			<c:if test="${param.errorMessage != null}">
				<p class="error-message">${errormessage}</p>
			</c:if>
			
			<c:if test="${param.errorDuplicate != null }">
				<div class="error-message" >Duplicate email!</div>
			</c:if>
			
				<form class="form" action="Controller" method="post">
					<input type="hidden" name="command" value="changepd">
					<div class="change-input">			
								<input type="text" name="firstname" placeholder="${firstname}" value="${user.firstName}" required />
								<c:if test="${param.errorFirstname != null}">
									<div class="error-message" >${errormessagefirstname}</div>
								</c:if>
							
								<input type="text" name="lastname" placeholder="${lastname}" value="${user.lastName}" required />
								<c:if test="${param.errorLastname != null}">
									<div class="error-message" >${errormessagelastname}</div>
								</c:if>
								
								<input type="text" name="email" placeholder="${email}" value="${user.email}"required />
								<c:if test="${param.errorEmail != null}">
									<div class="error-message" >${errormessageemail}</div>
								</c:if>
								
								<input type="text" name="phone" placeholder="${phone}" value="${user.phoneNumber}"required />
								<c:if test="${param.errorPhone != null}">
									<div class="error-message" >${errormessagephone}</div>
								</c:if>
								
					</div>
					<input class="button-change" type="submit" name="button" value="${changepd}" />
				</form>
			<p><a href="Controller?command=gotopersonaldata">${back}</a></p>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>