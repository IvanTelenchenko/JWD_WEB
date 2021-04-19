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
			<h2>${verification}</h2>
			<div class="info">
				
			<table class="table">
				<tr>
					<td>${datestart}</td> 
					<td><fmt:formatDate type="date" value="${date.get(0)}"/> </td> 
				</tr>
				<tr>
					<td>${dateend}</td> 
					<td><fmt:formatDate type="date" value="${date.get(1)}"/> </td> 
				</tr>
				<tr>
					<td>${carname}</td> 
					<td>${car.name}</td> 
				</tr>
				<tr>
					<td>${totalcost}</td> 
					<td>$${cost}</td> 
				</tr>
			</table>
			</div>
		<h3>${messageclickbook}</h3>
		<div class="change-buttons">	
				<form class="form" action="Controller" method="get">
					<input type="hidden" name="command" value="createorder">
					<input type="hidden" name="userid" value="${SessionScope.userId}">
					<input type="hidden" name="carid" value="${car.id}">
					<input type="hidden" name="dateStart" value="${date.get(0)}">
					<input type="hidden" name="dateEnd" value="${date.get(1)}">
					<input type="hidden" name="totalCost" value="${cost}">
					<input class="button" type="submit" name="button" value="${book}" />
				</form>
		</div>
<p><a href="Controller?command=gotocar&id_car=${car.id}">${back}</a></p>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>