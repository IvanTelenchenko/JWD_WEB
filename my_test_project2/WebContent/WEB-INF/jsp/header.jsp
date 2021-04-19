<header>
	<div class="header-left">
	<div class="formButLang">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="local" />
				<input type="hidden" name="local" value="en" />
				<input type="hidden" name="pageURL" value="${param}" />
				<input type="submit" name="local" value="${but_en}" />
			</form>
	</div>
		<div class="formButLang">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="local">
				<input type="hidden" name="local" value="ru" />
				<input type="hidden" name="pageURL" value="${param}">
				<input type="submit" name="local" value="${but_ru}"/>
			</form>
	</div>
	</div>
	<div class="header-center">
		<nav>
			<ul>
				<li><a href="Controller?command=mainpage">${products}</a></li>
				<%-- <li><a href="#">${news}</a></li>
				<li><a href="#">${reviews}</a></li>
				<li><a href="#">${contacts}</a></li> --%>
		</ul>
	</nav>
</div>
	<div class="header-right">
	<%-- 	<div class="formButLang">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="local" />
				<input type="hidden" name="local" value="en" />
				<input type="hidden" name="pageURL" value="${param}" />
				<input type="submit" name="local" value="${but_en}" />
			</form>
	</div>
		<div class="formButLang">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="local">
				<input type="hidden" name="local" value="ru" />
				<input type="hidden" name="pageURL" value="${param}">
				<input type="submit" name="local" value="${but_ru}" />
			</form>
	</div> --%>
	<div class="formButMenu">
		<form action="Controller" method="get" name="hi">
			<c:if test="${sessionScope.auth != null}">
				<div class="dropdown">
					<button type="button" onclick="myFunction()" class="dropbtn">${butAccount}
				</button>
					<div id="myDropdown" class="dropdown-content">
						<a href="Controller?command=gotoaccount"> ${butAccount}</a>
						<a href="Controller?command=logout">${logout}</a>
					</div>
				</div>
			</c:if>
			<c:if test="${sessionScope.auth == null}">
				<button class="dropbtn" type="submit" name="command" value="gotosignin">${butSignIn}</button>
			</c:if>
		</form>
	</div>
</div>
</header>