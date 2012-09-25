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

<c:forEach var="transport" items="${transports}">
    	<tr>
    		<td align="right" bgcolor="#ffffff">${transport.id}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.name}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.branch}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.heading}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.transportType}	</td>
        </tr>
</c:forEach>

</body>
</html>