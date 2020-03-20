<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<!DOCTYPE html>
<html>
<head>

<title>Survey</title>
<style>
.error {
	color:red;
	}
</style>
</head>
<body>

	<section id="main-content">
		<h3 class="centered">Submit your favorite park</h3>

		<c:url value='/processSurvey' var='surveyUrl' />

		<form:form id="survey" action='${surveyUrl}' method='POST'
			modelAttribute="surveyResult">
			<div>
				<label for="parkCode">Choose your favorite park</label>
				<form:select path="parkCode">
					<form:option value="CVNP"> Cuyahoga Valley National Park </form:option>
					<form:option value="ENP"> Everglades National Park </form:option>
					<form:option value="GCNP"> Grand Canyon National Park </form:option>
					<form:option value="GNP"> Glacier National Park</form:option>
					<form:option value="GSMNP"> Great Smoky Mountains National Park</form:option>
					<form:option value="GTNP">Grand Teton National Park</form:option>
					<form:option value="MRNP"> Mount Rainier National Park</form:option>
					<form:option value="RMNP"> Rocky Mountain National Park </form:option>
					<form:option value="YNP">Yellowstone National Park</form:option>
					<form:option value="YNP2"> Yosemite National Park</form:option>
				</form:select>
			</div>

			<div>
				<label for="email">Please enter your email</label>
				<form:input path="email" />
				<form:errors path="email" cssClass="error" />

			</div>

			<div class="formInputGroup">
				<label for="state">State of residence:</label>
				<form:select path="state">
					<form:option value="AL">Alabama</form:option>
					<form:option value="AK">Alaska</form:option>
					<form:option value="AZ">Arizona</form:option>
					<form:option value="AR">Arkansas</form:option>
					<form:option value="CA">California</form:option>
					<form:option value="CO">Colorado</form:option>
					<form:option value="CT">Connecticut</form:option>
					<form:option value="DE">Delaware</form:option>
					<form:option value="DC">District Of Columbia</form:option>
					<form:option value="FL">Florida</form:option>
					<form:option value="GA">Georgia</form:option>
					<form:option value="HI">Hawaii</form:option>
					<form:option value="ID">Idaho</form:option>
					<form:option value="IL">Illinois</form:option>
					<form:option value="IN">Indiana</form:option>
					<form:option value="IA">Iowa</form:option>
					<form:option value="KS">Kansas</form:option>
					<form:option value="KY">Kentucky</form:option>
					<form:option value="LA">Louisiana</form:option>
					<form:option value="ME">Maine</form:option>
					<form:option value="MD">Maryland</form:option>
					<form:option value="MA">Massachusetts</form:option>
					<form:option value="MI">Michigan</form:option>
					<form:option value="MN">Minnesota</form:option>
					<form:option value="MS">Mississippi</form:option>
					<form:option value="MO">Missouri</form:option>
					<form:option value="MT">Montana</form:option>
					<form:option value="NE">Nebraska</form:option>
					<form:option value="NV">Nevada</form:option>
					<form:option value="NH">New Hampshire</form:option>
					<form:option value="NJ">New Jersey</form:option>
					<form:option value="NM">New Mexico</form:option>
					<form:option value="NY">New York</form:option>
					<form:option value="NC">North Carolina</form:option>
					<form:option value="ND">North Dakota</form:option>
					<form:option value="OH">Ohio</form:option>
					<form:option value="OK">Oklahoma</form:option>
					<form:option value="OR">Oregon</form:option>
					<form:option value="PA">Pennsylvania</form:option>
					<form:option value="RI">Rhode Island</form:option>
					<form:option value="SC">South Carolina</form:option>
					<form:option value="SD">South Dakota</form:option>
					<form:option value="TN">Tennessee</form:option>
					<form:option value="TX">Texas</form:option>
					<form:option value="UT">Utah</form:option>
					<form:option value="VT">Vermont</form:option>
					<form:option value="VA">Virginia</form:option>
					<form:option value="WA">Washington</form:option>
					<form:option value="WV">West Virginia</form:option>
					<form:option value="WI">Wisconsin</form:option>
					<form:option value="WY">Wyoming</form:option>
				</form:select>
			</div>

			<div class="formInputGroup">
				<label for="activityLevel">Level of physical activity:</label>
				<form:select path="activityLevel">
					<form:option value="inactive">Inactive</form:option>
					<form:option value="sedentary">Sedentary</form:option>
					<form:option value="active">Active</form:option>
					<form:option value="extremelyActive">Extremely active</form:option>
				</form:select>
			</div>

			<input type="submit" value="Submit" />

		</form:form>
	</section>
</body>
</html>