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
			<h2>${addnewcar }</h2>
				
			<form action="image" method="post" enctype="multipart/form-data">
				
				  <form action="Controller" method="post">
						<input type="hidden" name="command" value="addnewcar">
						<table>
						<tr>
							<td> ${brand} </td>
							<td>
								<p>
   									<select size="1" name="carbrand" required>
		   							  <%--  <option selected disabled>${makechoice}</option> --%>
									   <option value="1">Audi</option>
									   <option value="2">BMW</option>
									   <option value="3">Ford</option>
									   <option value="4">Honda</option>
									   <option value="5">Hyundai</option>
									   <option value="6">Kia</option>
									   <option value="7">Mazda</option>
									   <option value="8">Mercedes</option>
									   <option value="9">Nissan</option>
									   <option value="10">Rehault</option>
									   <option value="11">Skoda</option>
									   <option value="12">Tesla</option>
									   <option value="13">Toyota</option>
									   <option value="14">Mini</option>
									   <option value="15">Land Rover</option>
									   <option value="16">Infinity</option>
									   <option value="17">Volkswagen</option>
									   <option value="18">Porsche</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${carbody} </td>
							<td>
								<p>
   									<select size="1" name="carbody" required>
		   							  <%--  <option disabled>${makechoice}</option> --%>
									   <option value="1">${body1}</option>
									   <option value="2">${body2}</option>
									   <option value="3">${body3}</option>
									   <option value="4">${body4}</option>
									   <option value="5">${body5}</option>
									   <option value="6">${body6}</option>
									   <option value="7">${body7}</option>
									   <option value="8">${body8}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${typeofclass} </td>
							<td>
								<p>
   									<select size="1" name="carclass" required>
		   							   <%-- <option disabled>${makechoice}</option> --%>
									   <option value="1">${classe}</option>
									   <option value="2">${classm}</option>
									   <option value="3">${classb}</option>
									   <option value="4">${classp}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${transmission} </td>
							<td>
								<p>
   									<select size="1" name="cartrans" required>
		   							   <%-- <option disabled>${makechoice}</option> --%>
									   <option value="1">${transmanual}</option>
									   <option value="2">${transauto}</option>
									   <option value="3">${transrobot}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${fuel} </td>
							<td>
								<p>
   									<select size="1" name="carfuel" required>
		   							  <%--  <option disabled>${makechoice}</option> --%>
									   <option value="1">${fuelg}</option>
									   <option value="2">${fueld}</option>
									   <option value="3">${fuele}</option>
									   <option value="4">${fuelh}</option>
						   			</select>
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${carname}</td>
							<td>
								<p>
   									<input type="text" name="carname" placeholder="${carname}" required />
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${costday}</td>
							<td>
								<p>
   									<input type="text" name="costday" placeholder="${costday}" required />
								</p> 
							</td>
						</tr>
						<tr>
							<td> ${enginecapacity}</td>
							<td>
								<p>
   									<input type="text" name="enginecapacity" placeholder="${enginecapacity}" required />
								</p> 
							</td>
						</tr>
						<tr>
							<td>${numofseats}</td>
							<td>
								<p>
   									<input type="text" name="numofseats" placeholder="${numofseats}" required />
								</p> 
							</td>
						</tr>
						<tr>
							<td>${photo}</td>
							<td>
								<p>
   									<input type="file" name="photo" accept="image/*" required />
   								<!-- 	<div>
  										<label for="files" class="btn">Select Image</label>
 										<input id="files" style="visibility:hidden;" type="file">
									</div> -->
								</p> 
							</td>
						</tr>
						</table>
   						
						   <p><input class="button" type="submit" value="${buttonaddcar}"></p>
  				</form>
			</form>
		<p><a href="Controller?command=gotocarsbase">${back}</a></p>
	</article>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>