<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

</head>
<body>
<form:form method="post" commandName="loc">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%">name:</td>
        <td width="20%">
          <form:input path="name"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">latitude:</td>
        <td width="20%">
          <form:input path="location.latitude"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">longitud:</td>
        <td width="20%">
          <form:input path="location.longitude"/>
        </td>
    </tr>
  </table>
  <br>
  <input type="submit" align="center" value="Execute">
</form:form>
</body>
</html>