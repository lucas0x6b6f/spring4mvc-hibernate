<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style>
th, tr, td {
	border-bottom: 1pt groove grey;
	margin: 0pt;
	padding: 5pt;
}
</style>



</head>
<title></title>
<body>


	<h3>================= Count Team =================</h3>
	<table>
		<tr>

			<th>Team</th>
			<th>Count</th>
		</tr>

		<c:forEach var="count" items="${counts}">
			<tr>
			
				<td>${count.team}</td>
				<td>${count.count}</td>

			</tr>
		</c:forEach>
	</table>


</body>
</html>
