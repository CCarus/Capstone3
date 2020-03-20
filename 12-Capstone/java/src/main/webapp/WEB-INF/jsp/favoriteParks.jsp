<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>

	<section id="main-section">

		<h1>Our Viewers' Favorite Parks</h1>
		<div id="survey">
			<table>
				<tr>
					<th>Park Name</th>
					<th>Number Of Surveys</th>
				</tr>
				<c:forEach items="${surveys}" var="survey">
					<tr>
						<td><c:out value="${survey.parkName}" /></td>
						<td><c:out value="${survey.surveyCount}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>

</body>
</html>