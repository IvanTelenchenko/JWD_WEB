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
		<div>
			${notfound}
		</div>
		
		<img class="img-my" src="image/404.png" alt="not found" style="width: 100%">
			<a href="Controller?command=mainpage">${products}</a>
		</article>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>