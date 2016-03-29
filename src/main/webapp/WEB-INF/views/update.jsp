<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<style>
th, tr, td {
	border: 1pt groove grey;
	margin: 0pt;
	cellpadding:0pt;
}
</style>

<script language="JavaScript" type="text/JavaScript">
	function update() {
			$.ajax({
				url : '/update',
				data : $('#dataForm').serialize(),
				processData : false,
				type : 'POST',
				async : false,
				success : function(data) {
					alert("ok");
					location.href='/index';
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

	<h3>Update Form</h3>
	<form id="dataForm" modelAttribute="user" >
		<table>
			<tr>
				<td>username:</td>
				<td>
				${user.username}	<input type="hidden" id="username" name="username"
					value="${user.username}" /></td>
			</tr>
			<tr>
				<td>gender:</td>
				<td>
				<select id="gender" name="gender">
				<option value="1" <c:if test="${user.gender == true}">selected</c:if>  >male</option>
				<option value="0" <c:if test="${user.gender == false}">selected</c:if> >female</option>
				</select>
				
				</td>
			</tr>
			<tr>
				<td>team:</td>
				<td><input id="team" name="team" value="${user.team}" />
				</td>
			</tr>
			<tr>
				<td>tel:</td>
				<td><input id="tel" name="tel" value="${user.tel}" />
				</td>
			</tr>
		</table>
	</form>


	<input type="submit"  id="submit" onclick="update();" value="submit" />
	${msg}
</body>
</html>
