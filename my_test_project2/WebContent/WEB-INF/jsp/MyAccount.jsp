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
	<c:if test="${admin == true }">
	<div id="container">
			<nav class="sidebar">
				<p><a href="Controller?command=gotopersonaldata">${personaldata}</a></p>
				<p><a href="Controller?command=gotoorderhistory">${linkorders}</a></p>
				<p><a href="Controller?command=gotocarsbase">${linkcars}</a></p>
			</nav>
		<article class="content">
			<div class="mytitle">
				<h1>${personalacc}</h1>
			</div>
			
			<c:if test="${orders.size() eq 0}">
				<div class="message">${messagedontorder}</div>
			</c:if>
			
		<c:if test="${orders.size() > 0}">
			<c:forEach var="order" items="${orders}">
			<table class="table">	
				<tr>
					<td>${ordernumb}</td> 
					<td>${order.key.id}</td> 
				</tr>	
				<tr>
					<td>${userid }</td> 
					<td>${order.key.userId}</td> 
				</tr>	
				<tr>
					<td>${cartitle}</td> 
					<td>${order.value}</td> 
				</tr>	
				<tr>
					<td>${datestart }</td> 
					<td><fmt:formatDate type="date" value="${order.key.start}"/> </td> 
				</tr>	
				<tr>
					<td>${dateend}</td> 
					<td><fmt:formatDate type="date" value="${order.key.finish}"/> </td> 
				</tr>
				
				<c:if test="${order.key.status == 'CREATED'}">
				<tr>
					<td>
						<form class="acceptlOrder" action="Controller" method="post">
							<input type="hidden" name="command" value="acceptorder">
							<input type="hidden" name="orderidaccept" value="${order.key.id}">
							<input class="butAcceptOrder" type="submit" value="${accept}"/>
						</form>
					</td>
					<td>
					<form class="cancelOrder" action="Controller" method="post">
						<input type="hidden" name="command" value="cancelorder">
						<input type="hidden" name="orderidcancel" value="${order.key.id}">
						<input class="butCancelOrder" type="submit" value="${buttonCancelOrder}" />
					</form>
					</td>
				</tr>
				</c:if>
				<c:if test="${order.key.status == 'BOOKED'}">
				<tr>
					<td>
						<form class="acceptlOrder" action="Controller" method="post">
							<input type="hidden" name="command" value="completeorder">
							<input type="hidden" name="orderid" value="${order.key.id}">
							<input class="butAcceptOrder" type="submit" value="${complete}"/>
						</form>
					</td>
					<td>
					<form class="cancelOrder" action="Controller" method="post">
						<input type="hidden" name="command" value="cancelorder">
						<input type="hidden" name="orderidcancel" value="${order.key.id}">
						<input class="butCancelOrder" type="submit" value="${buttonCancelOrder}" />
					</form>
					</td>
				</tr>
				</c:if>
			</table>	
			</c:forEach>
		</c:if>
	</article>
</div>
	
	</c:if>
	<c:if test="${admin == false }">
	<div id="container">
			<nav class="sidebar">
				<p><a href="Controller?command=gotopersonaldata">${personaldata}</a></p>
				<p><a href="Controller?command=gotoorderhistory">${linkorders}</a></p>
			</nav>
		<article class="content">
			<div class="mytitle">
				<h1>${personalacc }</h1>
			</div>
			
			<c:if test="${param.messageCreateOrder != null }">
				<div class="message">${messagecreateorder}</div>
			</c:if>
			
			<c:if test="${param.messageCteateOrderRepeat != null }">
				<div class="message">${messageCteateOrderRepeat}</div>
			</c:if>
			
			<c:if test="${orders.size() eq 0}">
				<div class="message">${messagedontorder}</div>
			</c:if>
			
		<c:if test="${orders.size() > 0}">
			<c:forEach var="order" items="${orders}">
			<table class="table">	
				<tr>
					<td>${ordernumb}</td> 
					<td>${order.key.id}</td> 
				</tr>	
				<tr>
					<td>${cartitle}</td> 
					<td>${order.value}</td> 
				</tr>	
				<tr>
					<td>${datestart }</td> 
					<td><fmt:formatDate type="date" value="${order.key.start}"/> </td> 
				</tr>	
				<tr>
					<td>${dateend}</td> 
					<td><fmt:formatDate type="date" value="${order.key.finish}"/> </td> 
				</tr>
				<tr>
					<td></td>	 
						<c:if test="${order.key.status == 'CREATED' || order.key.status == 'BOOKED'}">
							<td>
							<form class="cancelOrder" action="Controller" method="post">
								<input type="hidden" name="command" value="cancelorder">
								<input type="hidden" name="orderidcancel" value="${order.key.id}">
								<input class="butCanlcelOrder" type="submit" value="${buttonCancelOrder}" />
							</form>
							</td>
						</c:if>	
				</tr>
			</table>	
			</c:forEach>
		</c:if>
	</article>
</div>
</c:if>
	<%@include file="footer.jsp"%>
</body>
</html>