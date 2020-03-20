<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NP GEEK</title>
    <c:url value="/CSS/npGeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="/img/logo.png" var="logoSrc" />
		<a href="${homePageHref}"> <img src="${logoSrc}"
			alt="NP Geek logo" />
		</a>

		<div>
			<nav id="navButtons">
				<ul>
					<c:url value="/homepage" var="homepage" />
					<c:url value="/survey" var="survey" />

					<li><a href="${homePageHref}">Home</a></li>
					<li><a href="${survey}">Take our Survey</a></li>

				</ul>
			</nav>
		</div>
	</header>
</body>