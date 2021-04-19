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
			<h2>${titleeditcar}</h2>
			
			<c:if test="${param.errorMessage != null}">
				<p class="error-message">${errormessage}</p>
			</c:if>
				
			<form action="image" method="post" enctype="multipart/form-data">
				  <form action="Controller" method="post">
						<input type="hidden" name="command" value="editcar">
						<input type="hidden" name="carid" value="${car.id}">
						<table>
						<tr>
							<td> ${brand} </td>
							<td>
								<p>
   									<select size="1" name="carbrand" required>
		   							  <%--  <option selected>${makechoice}</option> --%>
									   <option ${car.brand eq 'AUDI' ? 'selected' :''} value="1">Audi</option> 
									   <option ${car.brand eq 'BMW' ? 'selected' :''} value="2">BMW</option>
									   <option ${car.brand eq 'FORD' ? 'selected' :''} value="3">Ford</option>
									   <option ${car.brand eq 'HONDA' ? 'selected' :''} value="4">Honda</option>
									   <option ${car.brand eq 'HYUNDAI' ? 'selected' :''} value="5">Hyundai</option>
									   <option ${car.brand eq 'KIA' ? 'selected' :''} value="6">Kia</option>
									   <option ${car.brand eq 'MAZDA' ? 'selected' :''} value="7">Mazda</option>
									   <option ${car.brand eq 'MERCEDES' ? 'selected' :''} value="8">Mercedes</option>
									   <option ${car.brand eq 'NISSAN' ? 'selected' :''} value="9">Nissan</option>
									   <option ${car.brand eq 'RENAULT' ? 'selected' :''} value="10">Rehault</option>
									   <option ${car.brand eq 'SKODA' ? 'selected' :''} value="11">Skoda</option>
									   <option ${car.brand eq 'TESLA' ? 'selected' :''} value="12">Tesla</option>
									   <option ${car.brand eq 'TOYOTA' ? 'selected' :''} value="13">Toyota</option>
									   <option ${car.brand eq 'MINI' ? 'selected' :''} value="14">Mini</option>
									   <option ${car.brand eq 'LANDROVER' ? 'selected' :''} value="15">Land Rover</option>
									   <option ${car.brand eq 'INFINITY' ? 'selected' :''} value="16">Infinity</option>
									   <option ${car.brand eq 'VOLKSWAGEN' ? 'selected' :''} value="17">Volkswagen</option>
									   <option ${car.brand eq 'PORSCHE' ? 'selected' :''} value="18">Porsche</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${carbody} </td>
							<td>
								<p>
   									<select size="1" name="carbody" required>
		   							  <%--  <option >${makechoice}</option> --%>
									   <option ${car.body eq 'HATCHBACK' ? 'selected' :''} value="1">${body1}</option>
									   <option ${car.body eq 'STATIONWAGON' ? 'selected' :''} value="2">${body2}</option>
									   <option ${car.body eq 'SEDAN' ? 'selected' :''} value="3">${body3}</option>
									   <option ${car.body eq 'MINIVAN' ? 'selected' :''} value="4">${body4}</option>
									   <option ${car.body eq 'CABRIOLET' ? 'selected' :''} value="5">${body5}</option>
									   <option ${car.body eq 'OFF_ROAD' ? 'selected' :''} value="6">${body6}</option>
									   <option ${car.body eq 'CROSSOVER' ? 'selected' :''} value="7">${body7}</option>
									   <option ${car.body eq 'COUPE' ? 'selected' :''} value="8">${body8}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${typeofclass} </td>
							<td>
								<p>
   									<select size="1" name="carclass" required>
		   							   <%-- <option >${makechoice}</option> --%>
									   <option ${car.classAuto eq 'ECONOMY' ? 'selected' :''} value="1">${classe}</option>
									   <option ${car.classAuto eq 'MEDIUM' ? 'selected' :''} value="2">${classm}</option>
									   <option ${car.classAuto eq 'BUSINESS' ? 'selected' :''} value="3">${classb}</option>
									   <option ${car.classAuto eq 'PREMIUM' ? 'selected' :''} value="4">${classp}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${transmission} </td>
							<td>
								<p>
   									<select size="1" name="cartrans" required>
		   							   <%-- <option >${makechoice}</option> --%>
									   <option ${car.transmission eq 'MANUAL' ? 'selected' :''} value="1">${transmanual}</option>
									   <option ${car.transmission eq 'AUTOMATIC' ? 'selected' :''} value="2">${transauto}</option>
									   <option ${car.transmission eq 'ROBOT' ? 'selected' :''} value="3">${transrobot}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${fuel} </td>
							<td>
								<p>
   									<select size="1" name="carfuel" required>
		   							  <%--  <option >${makechoice}</option> --%>
									   <option ${car.fuel eq 'GASOLINE' ? 'selected' :''} value="1">${fuelg}</option>
									   <option ${car.fuel eq 'DIESEL' ? 'selected' :''} value="2">${fueld}</option>
									   <option ${car.fuel eq 'ELECTRIC' ? 'selected' :''} value="3">${fuele}</option>
									   <option ${car.fuel eq 'HYBRID' ? 'selected' :''} value="4">${fuelh}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${carname}</td>
							<td>
								<p>
   									<input type="text" name="carname" value="${car.name}"<%-- placeholder="${carname}" --%> required />
								</p> 
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<c:if test="${param.errorLength != null}">
									<div class="error-message" >${errorlength}</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td> ${costday}</td>
							<td>
								<p>
   									<input type="text" name="costday" value="${car.price}" <%-- placeholder="${costday}" --%> required />
								</p> 
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<c:if test="${param.errorDouble != null}">
									<div class="error-message" >${errordouble}</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td> ${enginecapacity}</td>
							<td>
								<p>
   									<input type="text" name="enginecapacity" value="${car.engineCapacity}" <%-- placeholder="${enginecapacity}" --%> required />
								</p> 
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<c:if test="${param.errorDouble != null}">
									<div class="error-message" >${errordouble}</div>
								</c:if>
							</td>
						</tr>	
						<tr>
							<td>${numofseats}</td>
							<td>
								<p>
   									<input type="text" name="numofseats" value="${car.numbOfSeats}" <%-- placeholder="${numofseats}" --%> required />
								</p> 
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<c:if test="${param.errorInteger != null}">
									<div class="error-message" >${errorinteger}</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>${photo}</td>
							<td><img src="image/${car.photo}" alt="not found" width=230px height=129px></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<p>
   									<input type="file" name="photo" accept="image/*" value="${car.photo}"/>
								</p> 
							</td>
						</tr>
						</table>
					<p><input class="button" type="submit" value="${editcar}"></p>
  				</form>
			</form>
		<p><a href="Controller?command=gotocarsbase">${back}</a></p>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>