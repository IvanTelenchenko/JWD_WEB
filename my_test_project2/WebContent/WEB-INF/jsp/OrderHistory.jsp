<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleMain2.css" rel="stylesheet" type="text/css">
<%@include file="locale.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div id="container">
		<article class="content">
			
			<h2>${linkorders }</h2>
			<c:if test="${orders.size() eq 0}">
				<div class="message">${messagedontorder}</div>
			</c:if>
			<c:if test="${param.messageComplete != null }">
				<div class="message">${messagecomplete}</div>
			</c:if>
			<c:if test="${param.messageCancel != null }">
				<div class="message">${messagecancel}</div>
			</c:if>
			<c:if test="${param.messageAccept != null }">
				<div class="message">${messageaccept}</div>
			</c:if>
			
			<c:if test="${orders != null }">
			<table class="table">
			<thead>
				<tr>
					<th>${ordernumb}</th>
					<th>${userid}</th>
					<th>${cartitle}</th>
					<th>${datestart }</th>
					<th>${dateend}</th>
					<th>${totalcost}</th>
					<th>${orderstatus}</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach var="order" items="${orders}">
				<c:choose>
						<c:when test="${order.key.status == 'CREATED'}">
							<c:set var="status" value="${statusCreated}"/>
							<c:set var="isCurrentOrder" value="true"/>
						</c:when>
						<c:when test="${order.key.status == 'BOOKED'}">
							<c:set var="status" value="${statusBooked}"/>
							<c:set var="isCurrentOrder" value="true"/>
						</c:when>
						<c:when test="${order.key.status == 'COMPLETED'}">
							<c:set var="status" value="${statusCompleted}"/>
							<c:set var="isCurrentOrder" value="false"/>
						</c:when>
						<c:when test="${order.key.status == 'CANCELEDUSER'}">
							<c:set var="status" value="${statusCanceledUser}"/>
							<c:set var="isCurrentOrder" value="false"/>
						</c:when> 
						<c:when test="${order.key.status == 'CANCELEDADMIN'}">
							<c:set var="status" value="${statusCanceledAdmin}"/>
							<c:set var="isCurrentOrder" value="false"/>
						</c:when> 
					</c:choose>
			<tbody>		
				<tr>
					<td>${order.key.id}</td>
					<td>${order.key.userId}</td>
					<td>${order.value }</td>
					<td><fmt:formatDate type="date" value="${order.key.start}"/></td>
					<td><fmt:formatDate type="date" value="${order.key.finish}"/></td>
					<td><fmt:formatNumber value="${order.key.totalCost}" type="number" /></td>
					<td>${status}</td>
					
					<c:if test="${order.key.status == 'CREATED' || order.key.status == 'BOOKED' }">
						<td>
						<c:if test="${admin eq true && order.key.status == 'CREATED'}">
							<!-- <td> -->
								<form class="acceptlOrder" action="Controller" method="post">
									<input type="hidden" name="command" value="acceptorder">
									<input type="hidden" name="orderidaccept" value="${order.key.id}">
									<input class="butAcceptOrder" type="submit" value="${accept}"/>
								</form>						
							<!-- </td> -->
						</c:if>
						<c:if test="${admin eq true && order.key.status == 'BOOKED'}">
							<!-- <td> -->
								<form class="acceptlOrder" action="Controller" method="post">
									<input type="hidden" name="command" value="completeorder">
									<input type="hidden" name="orderid" value="${order.key.id}">
									<input class="butAcceptOrder" type="submit" value="${complete}"/>
								</form>						
							<!-- </td> -->
						</c:if>
							<form class="cancelOrder" action="Controller" method="post">
								<input type="hidden" name="command" value="cancelorder">
								<input type="hidden" name="orderidcancel" value="${order.key.id}">
								<input class="butCancelOrder" type="submit" value="${buttonCancelOrder}" />
							</form>						
						</td>
					</c:if>
				</tr>
			</tbody>
			</c:forEach>
			</table>
			</c:if>
			<a href="Controller?command=gotoaccount"> ${back} </a>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>