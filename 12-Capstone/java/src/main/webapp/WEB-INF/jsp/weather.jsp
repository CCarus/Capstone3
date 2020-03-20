<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>

<title>Weather Forecast</title>
</head>
<body id="weather">
<h1> <c:out value = '${parkName}'/></h1>
	<section id="main-content">
		<div id="flex-box">
			<c:forEach items='${forecast}' var='dailyForecast'>
				<%-- Outer c:choose, if daily forecast is 1, or every other day --%>
				<c:choose>
					<%-- For first day --%>
					<c:when test='${dailyForecast.fiveDayForecastValue == 1}'>
						<div id="today">

							<h1>${dailyForecast.dayLabel}</h1>
							<c:url value="/img/weather/${dailyForecast.imageName}.png"
								var="forecastImg" />
							<img src="${forecastImg}" />


							<c:choose>
								<%-- When the session attribute "tempType" is True for Fahrenheit --%>
								<c:when test="${tempType == true}">
									<p>
										<span>High <c:out value='${dailyForecast.highTemp}' />°F
										</span> <span>Low <c:out value='${dailyForecast.lowTemp}' />°F
										</span>
									</p>
								</c:when>
								<%-- Otherwise, the session attribute "tempType" is False for Celsius and a conversion is done --%>
								<c:otherwise>
									<p>
										<span>High <c:set
												value="${(dailyForecast.highTemp - 32) * (5/9)}"
												var="celsiusHighTemp" /> <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${celsiusHighTemp}" />°C
										</span> <span>Low <c:set
												value="${(dailyForecast.lowTemp - 32) * (5/9)}"
												var="celciusLowTemp" /> <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${celciusLowTemp}" />°C
										</span>
									</p>
								</c:otherwise>
							</c:choose>
							<%-- Calling String array of recommendations based on weather conditions --%>
							<c:forEach items='${forecastStatement}' var='forecastStatement'>
								<span class="blinking"><b><c:out
											value="${forecastStatement}" /></b></span>
							</c:forEach>

						</div>
					</c:when>

					<%-- All other days --%>

					<c:otherwise>
						<div id="other-days">
							<h3>${dailyForecast.dayLabel}</h3>
							<c:url value="/img/weather/${dailyForecast.imageName}.png"
								var="forecastImg" />
							<img src="${forecastImg}" />


							<c:choose>
								<c:when test="${tempType == true}">
									<p>
										<span>High <c:out value='${dailyForecast.highTemp}' />°F
										</span> <span>Low <c:out value='${dailyForecast.lowTemp}' />°F
										</span>
									</p>
								</c:when>
								<c:otherwise>
									<p>
										<span>High <c:set
												value="${(dailyForecast.highTemp - 32) * (5/9)}"
												var="celsiusHighTemp" /> <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${celsiusHighTemp}" />°C
										</span> <span>Low <c:set
												value="${(dailyForecast.lowTemp - 32) * (5/9)}"
												var="celciusLowTemp" /> <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${celciusLowTemp}" />°C
										</span>
									</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>


		<%-- Radio buttons for temperature toggle--%>
		<div id="temp-toggle">
			<c:url value="/tempToggle" var="tempToggleUrl" />
			<form action="${tempToggleUrl}" method="GET">
				<input type="radio" id="F" name="tempType" value=true> <label
					for="F">Fahrenheit</label><br> <input type="radio" id="C"
					name="tempType" value=false> <label for="C">Celsius</label><br>
				<input type="submit" value="Switch Temperature Type" />
			</form>
		</div>
	</section>
</body>
</html>