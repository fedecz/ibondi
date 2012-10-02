<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
<form action="addTransport.htm" method="post" >
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%">Linea:</td>
        <td width="20%">
          <input name="name"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">Ramal:</td>
        <td width="20%">
          <input name="branch"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">Sentido (IDA, REGRESO):</td>
        <td width="20%">
          <input name="heading"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%"S>Transport type (BUS, TREN):</td>
        <td width="20%">
          <input name="transportType"/>
        </td>
    </tr>
  </table>
  <br>
  <input type="submit" value="Execute">
</form>
</body>
</html>