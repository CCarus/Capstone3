<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
</c:import>


<!DOCTYPE html>
<html>


<head>

<title>Insert title here</title>
</head>
<body>

	<section id="main-content">

		<c:forEach items='${parks}' var='park'>

			<div id="park">
				<div>
					<c:url value="/parkDetail" var="parkDetailsUrl">
						<c:param name="parkCode" value="${park.parkCode}" />
					</c:url>
					<a href="${parkDetailsUrl}"> <c:url
							value="/img/parks/${park.parkCode}.jpg" var="parkImg" /> <img
						src="${parkImg}" />
					</a>

				</div>

				<div id="parkInfo">

					<h2>
						<c:out value="${park.parkName}" />
					</h2>

					<p>
						<c:out value="${park.parkDescription}" />
					</p>

				</div>
			</div>

		</c:forEach>

	</section>
</body>
</html>