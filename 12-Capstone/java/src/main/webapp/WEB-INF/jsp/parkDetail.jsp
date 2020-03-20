<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Park Details</title>
</head>
<body>

	<section id="main-content">
		<h1>
			<c:out value="${park.parkName}, ${park.state}" />
		</h1>
		<div id=flex-box>
			<div id="park-info">

				<c:url value="/img/parks/${park.parkCode}.jpg" var="parkImg" />
				<img src="${parkImg}" />


				<p>
					<c:out value="Entry Fee: $${park.entryFee}" />
				</p>
				<c:url value="/weather" var="weatherUrl">
					<c:param name="parkCode" value="${park.parkCode}" />
				</c:url>
				<a id="forecast-link" href="${weatherUrl}"> Five day weather
					forecast</a>

			</div>

			<div id="park-stats">
				<p>
					<b> Park Stats:</b>
				</p>
				<ul>
					<li>Acreage: <c:out value="${park.acreage}" /> Acres
					</li>
					<li>Elevation: <c:out value="${park.elevationFeet}" /> feet
					</li>
					<li><c:out value="${park.milesOfTrail}" /> miles of trail</li>
					<li><c:out value="${park.numberOfCampsites}" /> campsites</li>
					<li><c:out value="${park.climate}" /> climate</li>
					<li>Founded in: <c:out value="${park.yearFounded}" />
					</li>
					<li><c:out value="${park.annualVisitorCount}" /> visitors per
						year</li>
					<li><c:out value="${park.numberOfAnimalSpecies}" /> types of
						animals</li>
				</ul>
				<p>
					<c:out value="${park.parkDescription}" />
				</p>
			</div>
		</div>
		<h1 id="quote">
			<c:out value="${park.inspirationalQuote}" />
		</h1>
		<h3 id="author">
			<c:out value="~ ${park.inspirationalQuoteSource}" />
		</h3>



	</section>

</body>
</html>