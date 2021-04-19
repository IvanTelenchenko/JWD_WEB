<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="mytag" uri="table-tag.tld"%>
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
		<c:if test="${admin == true }">
			<nav class="sidebar">
				<p><a href="Controller?command=gotopersonaldata">${personaldata}</a></p>
				<p><a href="Controller?command=gotoorderhistory">${linkorders}</a></p>
				<p><a href="Controller?command=gotocarsbase">${linkcars}</a></p>
			</nav>
		</c:if>
		<c:if test="${admin == false }">
			<nav class="sidebar">
				<p><a href="Controller?command=gotopersonaldata">${personaldata}</a></p>
				<p><a href="Controller?command=gotoorderhistory">${linkorders}</a></p>
			</nav>
		</c:if>
		<article class="content">
		
		<div class="mytitle">
			<h1>${personaldata}</h1>
		</div>
		
			<c:if test="${param.messageChangePD != null }">
				<div class="message">${messagechangepd}</div>
			</c:if>
			<c:if test="${param.messageChangePassword != null }">
				<div class="message">${messagechangepassword}</div>
			</c:if>
		
		<div class="info">
			<table class="table">
				<tr>
					<td><p>${firstname}</p></td>
					<td>${user.firstName}</td> 
				</tr>
				<tr>
					 <td><p>${lastname}</p></td>
					 <td>${user.lastName}</td>
				</tr>
				<tr>
					 <td><p>${phone}</p></td>
					 <td>${user.phoneNumber}</td>
				</tr>
				<tr>
					 <td><p>${email}</p></td>
					 <td>${user.email}</td>
				</tr>
			</table>
			</div>
			
			<%-- <div class="info">
				<mytag:tableuser user="${user}"/>
			</div> --%>
			
			
		<div class="change-buttons">		
				<form class="form" action="Controller" method="get">
					<input type="hidden" name="command" value="gotochangepd">
					<input class="button-change" type="submit" name="button" value="${changepd}" />
				</form>
				<form class="form" action="Controller" method="get">
					<input type="hidden" name="command" value="gotochangepassword">
					<input class="button-change" type="submit" name="button" value="${changepassword}" />
				</form>
		</div>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>