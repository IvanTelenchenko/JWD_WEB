<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="mytag" uri="brand-name-tag.tld"%>
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

		<%-- <nav class="sidebar">
				<p><a href="Controller?command=gotopersonaldata">${personaldata}</a></p>
				<p><a href="Controller?command=gotoorderhistory">${linkorders }</a></p>
				<p><a href="Controller?command=gotocarsbase">${linkcars }</a></p>
			</nav>
 --%>
		<article class="content">
			<h2>${titlecarsbase}</h2>
			<c:if test="${cars.size() eq 0}">
				${notcars}
			</c:if>
			
			<c:if test="${param.errorDeleteCar != null}">
				<div class="error-message">
						${messageDelete}
				</div>
			</c:if>
			
			<c:if test="${param.messageEditCar != null }">
				<div class="message">${messageeditcar}</div>
			</c:if>
			
			<c:if test="${param.messageAddCar != null }">
				<div class="message">${messageaddcar}</div>
			</c:if>
			
			<c:if test="${param.messageDeleteCar != null }">
				<div class="message">${messagedeletecar}</div>
			</c:if>
						
						<form class="goToAddNewCar" action="Controller" method="get">
							<input type="hidden" name="command" value="gotoaddnewcar">
							<input class="buttonAddCar" type="submit" value="${addnewcar}" />
						</form>		
			
			<c:if test="${cars.size() > 0}">
			<div class="table-responsive">
			<table class="table">
			<thead>
				<tr>
					<th>${carid}</th>
					<th>${brand }</th>
					<th>${transmission}</th>
					<th>${typeofclass}</th>
					<th>${fuel}</th>
					<th>${costday}</th>
					<th style="width:20%;">${carname}</th>
					<th>${enginecapacity}</th>
					<th class="headcol"/>
				</tr>
			</thead>
			<c:forEach var="car" items="${cars}">
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
						
<%-- 						<c:when test="${car.brand == 'HONDA'}">
							<c:set var="carName" value="Honda"/>
						</c:when>
						<c:when test="${car.brand == 'HYUNDAI'}">
							<c:set var="carName" value="Hyundai"/>
						</c:when>
						<c:when test="${car.brand == 'KIA'}">
							<c:set var="carName" value="Kia"/>
						</c:when>
						<c:when test="${car.brand == 'MAZDA'}">
							<c:set var="carName" value="Mazda"/>
						</c:when>
						<c:when test="${car.brand == 'MERCEDES'}">
							<c:set var="carName" value="Mercedes"/>
						</c:when>
						<c:when test="${car.brand == 'NISSAN'}">
							<c:set var="carName" value="Nissan"/>
						</c:when>
						<c:when test="${car.brand == 'RENAULT'}">
							<c:set var="carName" value="Renault"/>
						</c:when>
						<c:when test="${car.brand == 'SKODA'}">
							<c:set var="carName" value="Skoda"/>
						</c:when>
						<c:when test="${car.brand == 'TESLA'}">
							<c:set var="carName" value="Tesla"/>
						</c:when>
						<c:when test="${car.brand == 'TOYOTA'}">
							<c:set var="carName" value="Toyota"/>
						</c:when>
						<c:when test="${car.brand == 'MINI'}">
							<c:set var="carName" value="Mini"/>
						</c:when>
						<c:when test="${car.brand == 'LANDROVER'}">
							<c:set var="carName" value="Land Rover"/>
						</c:when>
						<c:when test="${car.brand == 'INFINITY'}">
							<c:set var="carName" value="Infinity"/>
						</c:when>
						<c:when test="${car.brand == 'VOLKSWAGEN'}">
							<c:set var="carName" value="Volkswagen"/>
						</c:when>
						<c:when test="${car.brand == 'PORSCHE'}">
							<c:set var="carName" value="Porsche"/>
						</c:when> --%>
		
				</c:choose>
				<tbody>
				<tr>
					<td>${car.id}</td>
					<td><mytag:brandname brand="${car.brand}"/>
					<td>${trans}</td>
					<td>${classCarName}</td>
					<td>${fuelName}</td>
					<td>${car.price }</td>
					<td style="width:20%;">
						<div style="width:100%; max-height:100px; overflow:auto; text-align: center; margin: 0 auto">
							${car.name}
						</div> 
					</td>
					<td>${car.engineCapacity }</td>
	
					<td>
						
						<form action="Controller" method="get">
								<input type="hidden" name="command" value="gotoeditcar">
								<input type="hidden" name="carid" value="${car.id}">
								<input class="butEditCar" type="submit" value="${editcar}" />
						</form>	
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="deletecar">
							<input type="hidden" name="carid" value="${car.id}">
							<input class="butDeleteCar" type="submit" value="${buttondeletecar}" />
						</form>						
					</td>
				</tr>
			</tbody>
			</c:forEach>
			</table>
			</div>
			</c:if>
			<a href="Controller?command=gotoaccount"> ${back} </a>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>