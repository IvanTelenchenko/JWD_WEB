<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="mytag" uri="brand-name-tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleCar.css" rel="stylesheet" type="text/css">
<%@include file="locale.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	
	<div id="container">
		<article class="content">
		<div class="mytitle">
		<h1>${car.name}</h1>
		</div>

	<div class="picture">
		<img class="img-my" src="image/${car.photo}" alt="not found">
	</div>
	
		<c:choose>
			<c:when test="${car.transmission == 'AUTOMATIC'}">
				<c:set var="trans" value="${transauto}"/>
			</c:when>
			<c:when test="${car.transmission == 'MANUAL'}">
				<c:set var="trans" value="${transmanual}"/>
			</c:when>
			<c:when test="${car.transmission == 'ROBOT'}">
				<c:set var="trans" value="${transrobot}"/>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${car.fuel == 'GASOLINE'}">
				<c:set var="fuelName" value="${fuelg}"/>
			</c:when>
			<c:when test="${car.fuel == 'DIESEL'}">
				<c:set var="fuelName" value="${fueld}"/>
			</c:when>
			<c:when test="${car.fuel == 'ELECTRIC'}">
				<c:set var="fuelName" value="${fuele}"/>
			</c:when>
			<c:when test="${car.fuel == 'HYBRID'}">
				<c:set var="fuelName" value="${fuelh}"/>
			</c:when>
		</c:choose>
		<c:choose>                                                    
						<c:when test="${car.classAuto eq 'PREMIUM'}">              
							<c:set var="classCarName" value="${classp}"/>          
						</c:when>                                                  
						<c:when test="${car.classAuto == 'BUSINESS'}">
							<c:set var="classCarName" value="${classb}"/>
						</c:when>
						<c:when test="${car.classAuto == 'MEDIUM'}">
							<c:set var="classCarName" value="${classm}"/>
						</c:when>
						<c:when test="${car.classAuto == 'ECONOMY'}">
							<c:set var="classCarName" value="${classe}"/>
						</c:when>
						</c:choose>
			<div class="info">
				<%-- <table>
							<p class="price"> <i class="fas fa-money-bill-wave"></i> $${car.price}</p>
							<p> ${car.name}</p>
							<p> <i class="fas fa-cogs"></i> ${trans}</p>
							<p>	<i class="fas fa-gas-pump"></i> ${fuelName}</p>
							<p> <i class="fas fa-charging-station"></i> ${car.engineCapacity}</p>
							<p> <i class="fas fa-male"></i> ${car.numbOfSeats}</p>
				</table> --%>
				
								<table class="table">
			<thead>
				<tr>
					<th>${brand} <i class="fas fa-car"></i></th>
					<th>${transmission} <i class="fas fa-cogs"></i></th>
					<th>${typeofclass} <i class="fas fa-car-side"></i></th>
					<th>${fuel} <i class="fas fa-gas-pump"></i> </th>
					<th>${enginecapacity}  <i class="fas fa-charging-station"></i></th>
					<th>${costday} <i class="fas fa-money-bill-wave"></i></th>
					<th>${numofseats}<i class="fas fa-male"></i></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><mytag:brandname brand="${car.brand}"/> </td>
					<td>${trans}</td>
					<td>${classCarName}</td>
					<td>${fuelName}</td>
					<td>${car.engineCapacity }</td>
					<td>$${car.price }</td>
					<td>${car.numbOfSeats}</td>
				</tr>
			</tbody>
		</table>
			</div>
				<form action="Controller" method="get" name="car">
				<input type="hidden" name="carid" value="${car.id}">  
				<c:if test="${admin == false || admin == null}">
					<input type="hidden" name="command" value="gotoverificationpage">
					<input class="button1" type="submit" name="button1" value="${book}">
				</c:if>      
				<c:if test="${admin == true }">
					<input type="hidden" name="command" value="deletecar">
					<input class="button1" type="submit" name="button1" value="${buttondeletecar}">
				</c:if>      
				</form>
			
			<div class="message">				
				<c:if test="${param.isAuthMessage != null}">
					<%-- ${isAuthMessage } --%>
					${messagecarbook}
				</c:if>
			</div>					
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>