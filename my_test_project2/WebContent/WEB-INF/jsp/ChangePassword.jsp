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
			<h2>${changepassword}</h2>
			
			<c:if test="${param.errorMessage != null}">
				<p class="error-message">${errormessage}</p>
			</c:if>
			
				<form class="form" action="Controller" method="post">
					<input type="hidden" name="command" value="changepassword">
					<div class="change-input">
						
						<input type="password" name="oldpassword" placeholder="${oldpassword}" required />
						<c:if test="${param.messageErrorNotValid != null}">
							<div class="validMessage" >${errormessagepassword}</div>
						</c:if>
						
						<c:if test="${param.messageErrorOldPasswordNotEqual != null }">
							<div class="validMessage" >${errormessagepassword}</div>
						</c:if>
						
						<input type="password" name="newpassword" placeholder="${newpassword}" required />
						<c:if test="${param.messageErrorNotValid != null}">
							<div class="validMessage" >${errormessagepassword}</div>
						</c:if>	
						
						<c:if test="${param.messageErrorPasswordsNotEqual != null }">
							<div class="validMessage" >${messagepasswordsnotequal}</div>
						</c:if>
						
						<input type="password" name="newpasswordrepeat" placeholder="${passwordrepeat}" required />
						<c:if test="${param.messageErrorNotValid != null}">
							<div class="validMessage" >${errormessagepassword}</div>
						</c:if>		
					</div>
					<input class="buttonCh" type="submit" name="button" value="${changepassword}" />
				</form>
		<p><a href="Controller?command=gotopersonaldata">Back</a></p>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>