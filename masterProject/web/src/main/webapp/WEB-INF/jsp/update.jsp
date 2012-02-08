<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

</head>
<body>
<c:if test="${!empty msg}">
<p>${msg}</p>
</c:if>

<form action="update.htm" method="post" >
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%">name:</td>
        <td width="20%">
          <input name="name"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">latitude:</td>
        <td width="20%">
          <input name="latitude"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">longitud:</td>
        <td width="20%">
          <input name="longitude"/>
        </td>
    </tr>
  </table>
  <br>
  <input type="submit" value="Execute">
</form>
</body>
</html>