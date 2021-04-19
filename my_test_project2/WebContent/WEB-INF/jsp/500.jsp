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
		${errormessage500}
		<img class="img-my" src="image/500.png" alt="not found"  style="width: 100%">
			<a href="Controller?command=mainpage">${products}</a>
		</article>
	</div>
		</article>
	<%@include file="footer.jsp"%>
</body>
</html>