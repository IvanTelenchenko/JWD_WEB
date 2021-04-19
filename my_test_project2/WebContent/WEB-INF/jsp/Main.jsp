<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleMain1.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<%@include file="locale.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="reg">
		<div class="mytitle">
				<h2>${rentalperiod}</h2>
		</div> 
		<c:if test="${dateUser == null }">
			<c:set var="dateStart" value="${dateDef.get(0)}"></c:set>
			<c:set var="dateEnd" value="${dateDef.get(1)}"></c:set>
		</c:if>
		
		<c:if test="${dateUser != null }">
			<c:set var="dateStart" value="${dateUser.get(0)}"></c:set>
			<c:set var="dateEnd" value="${dateUser.get(1)}"></c:set>
		</c:if>

		<form class="form" action="Controller" method="get" name="chooseDate">
			<input class="input-date" type="hidden" name="command" value="mainpagewithdateuser">
			<input class="input-date" type="date" name="dateStart" value ="${dateStart}" min="${dateDef.get(0)}" max="2025-01-01">
			<input class="input-date" type="date" name="dateEnd" value ="${dateEnd}" min="${dateDef.get(0)}" max="2025-01-01">
			<input class="butDate" type="submit" name="butDate" value="${butfind}">
 		</form>
 		<c:if test="${param.messageError != null}">
			<p class="error-message">${signupErrorMessage}</p>
		</c:if>
	</div>
	<div id="container">
		<nav class="sidebar">
			<form class="form-sidebar"action="Controller" method="get" name="filterCars">
				<input type="hidden" name="command" value="mainpagewithfilter">            
					<p class="title-filter"><a href="№">${brand}</a></p> 
					<ol>                                               
						<li class="filter" ><input type="checkbox" name="brand" value ="1"   ${filterCars.get('brand').contains('1') ? 'checked' :''}>Audi</li>         
						<li class="filter" ><input type="checkbox" name="brand" value ="2"   ${filterCars.get('brand').contains('2') ? 'checked' :''}>BMW</li>         
						<li class="filter" ><input type="checkbox" name="brand" value ="3"   ${filterCars.get('brand').contains('3') ? 'checked' :''}>Ford</li>        
						<li class="filter" ><input type="checkbox" name="brand" value ="4"   ${filterCars.get('brand').contains('4') ? 'checked' :''}>Honda</li>       
						<li class="filter" ><input type="checkbox" name="brand" value ="5"   ${filterCars.get('brand').contains('5') ? 'checked' :''}>Hyundai</li>     
						<li class="filter" ><input type="checkbox" name="brand" value ="6"   ${filterCars.get('brand').contains('6') ? 'checked' :''}>Kia</li>        
						<li class="filter" ><input type="checkbox" name="brand" value ="7"   ${filterCars.get('brand').contains('7') ? 'checked' :''}>Mazda</li>      
						<li class="filter" ><input type="checkbox" name="brand" value ="8"   ${filterCars.get('brand').contains('8') ? 'checked' :''}>Mercedes</li>   
						<li class="filter" ><input type="checkbox" name="brand" value ="9"   ${filterCars.get('brand').contains('9') ? 'checked' :''}>Nissan</li>     
						<li class="filter" ><input type="checkbox" name="brand" value ="10"  ${filterCars.get('brand').contains('10') ? 'checked' :''}>Rehault</li>   
						<li class="filter" ><input type="checkbox" name="brand" value ="11"  ${filterCars.get('brand').contains('11') ? 'checked' :''}>Skoda</li>      
						<li class="filter" ><input type="checkbox" name="brand" value ="12"  ${filterCars.get('brand').contains('12') ? 'checked' :''}>Tesla</li>      
						<li class="filter" ><input type="checkbox" name="brand" value ="13"  ${filterCars.get('brand').contains('13') ? 'checked' :''}>Toyota</li>    
						<li class="filter" ><input type="checkbox" name="brand" value ="14"  ${filterCars.get('brand').contains('14') ? 'checked' :''}>Mini</li>      
						<li class="filter" ><input type="checkbox" name="brand" value ="15"  ${filterCars.get('brand').contains('15') ? 'checked' :''}>Land Rover</li> 
						<li class="filter" ><input type="checkbox" name="brand" value ="16"  ${filterCars.get('brand').contains('16') ? 'checked' :''}>Infinity</li>   
						<li class="filter" ><input type="checkbox" name="brand" value ="17"  ${filterCars.get('brand').contains('17') ? 'checked' :''}>Volkswagen</li> 
						<li class="filter" ><input type="checkbox" name="brand" value ="18"  ${filterCars.get('brand').contains('18') ? 'checked' :''}>Porsche</li>    
					</ol>
					<p  class="title-filter"><a href="№">${typeofclass}</a></p>
					<ol>
						<li class="filter"><input  type="checkbox" name="class" value ="1" ${filterCars.get('class').contains('1') ? 'checked' :''}>${classe} </li>
						<li class="filter"><input  type="checkbox" name="class" value ="2" ${filterCars.get('class').contains('2') ? 'checked' :''}>${classm}</li>
						<li class="filter"><input  type="checkbox" name="class" value ="3" ${filterCars.get('class').contains('3') ? 'checked' :''}>${classb}</li>
						<li class="filter"><input  type="checkbox" name="class" value ="4" ${filterCars.get('class').contains('4') ? 'checked' :''}>${classp}</li>
					</ol>
				<input class="butFilter" type="submit" name="butFilter" value="${butfind}">
			</form>
	</nav>
		<article class="content">
		<!-- <article class="content-inner"> -->
		<c:if test="${countCars == 0}">
			<h2>${mainmessage}</h2>
		</c:if>
		<c:forEach var="car" items="${allCars}" >
						<div class="card">
						<div class="img1">
				  		<%-- 	<img src="image/${car.photo}" alt="not found" width=100% height=100%> --%>
				  			<img src="image/${car.photo}" alt="not found" width=230px height=129px>
						</div>
							<h3 class="carName">${car.name}</h3>
							<p class="price">$${car.price}</p>
							<p class="carName">${car.name}</p>
							<form action="Controller" method="get" name="car">
							<input type="hidden" name="command" value="gotocar">
							<input type="hidden" name="carid" value="${car.id}">
							<c:if test="${admin == true }">
								<input class="button" type="submit" name="but" value="${buttondeletecar}">
							</c:if>        
							<c:if test="${admin == false || admin == null}">
								<input class="button" type="submit" name="but" value=" ${book}">
							</c:if>        
							</form>
						</div>
		</c:forEach>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>