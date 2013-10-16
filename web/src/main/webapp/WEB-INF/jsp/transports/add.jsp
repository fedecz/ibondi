<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<c:url var="backURL" value='/transports/show.html'/>
<c:url var="actionUrl" value="/transports/add.html"/>

<form:form method="post" action="${actionUrl}" commandName="transport">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><form:label path="name">Linea:</form:label></td>
        <td width="20%"><form:input path="name"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><form:label path="branch">Ramal:</form:label></td>
        <td width="20%"><form:input path="branch"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><form:label path="heading">Sentido:</form:label></td>
        <td width="20%">
        	<form:select path="heading">
   				<form:options/>
			</form:select>
		</td>
    </tr>
    <tr>
      <td align="right" width="20%"><form:label path="transportType">Tipo:</form:label></td>
        <td width="20%">
        	<form:select path="transportType">
   				<form:options/>
			</form:select>
		</td>
    </tr>
  </table>
  <input type="submit" value="Add Transport"/>
</form:form>
<a href="${backURL}">Back to the list...</a>
</body>
</html>