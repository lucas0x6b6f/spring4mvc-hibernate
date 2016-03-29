<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<style>
th, tr, td {
	border-bottom: 1pt groove grey;
	margin: 0pt;
	padding: 5pt;
}
</style>

<script language="JavaScript" type="text/JavaScript">
	function del(username) {
		$.ajax({
			url : 'delete',
			data : "username=" + username,
			processData : false,
			type : 'POST',
			async : false,
			success : function(data) {
				alert("ok");
				location.href = '/index';
			},
			error : function(data) {
				alert(data.responseText);
			}
		});

	}
</script>


</head>
<title></title>
<body>

	<p>
		Add User: &nbsp;&nbsp;<input type="button"
			onclick="location.href='add';" value="ADD" />
	</p>

	<p>
		View Count: &nbsp;&nbsp;<input type="button"
			onclick="location.href='count';" value="Count" />
	</p>

	<h3>================= List of User =================</h3>
	<table>
		<tr>
			<th>Update</th>
			<th>Username</th>
			<th>Gender</th>
			<th>Team</th>
			<th>Tel</th>
			<th>delete</th>
		</tr>

		<c:forEach var="user" items="${users}">
			<tr>
				<td><input type="button"
					onclick="location.href='update?username=${user.username}';"
					value="UPDATE" /></td>
				<td>${user.username}</td>
				<td>${user.gender}</td>
				<td>${user.team}</td>
				<td>${user.tel}</td>
				<td><input type="button" onclick="del('${user.username}');"
					value="DELETE" /></td>

			</tr>
		</c:forEach>
	</table>


</body>
</html>
