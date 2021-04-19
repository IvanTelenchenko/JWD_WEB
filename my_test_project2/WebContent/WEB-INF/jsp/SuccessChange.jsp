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
	<div id="container">
		<article class="content">
		<div class="successmessage">
			<%-- <c:if test="${param.messageDeleteCar != null }">
				<div class="message">${messagedeletecar}</div>
			</c:if> --%>
			<c:if test="${param.messageCreateOrder != null }">
				<div class="message">${messagecreateorder}</div>
			</c:if>
			<%-- <c:if test="${param.messageChangePD != null }">
				<div class="message">${messagechangepd}</div>
			</c:if>
			<c:if test="${param.messageChangePassword != null }">
				<div class="message">${messagechangepassword}</div>
			</c:if> --%>
			<%-- <c:if test="${param.messageAddCar != null }">
				<div class="message">${messageaddcar}</div>
			</c:if> --%>
			<%-- <c:if test="${param.messageEditCar != null }">
				<div class="message">${messageeditcar}</div>
			</c:if> --%>
		</div>
			<a href="Controller?command=gotopersonaldata">${personalacc}</a>
			<a href="Controller?command=mainpage">${products}</a>
		</article>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>