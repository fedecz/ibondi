<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
Show all transport list:<br />

<table><tr><th>Id</th><th>Name</th><th>branch</th><th>Heading</th><th>Type</th></tr>
<c:forEach items="${transportList}" var="transport">
    	<tr>
    		<td align="right" bgcolor="#ffffff">${transport.id}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.name}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.branch}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.heading}	</td>
        	<td align="right" bgcolor="#ffffff">${transport.transportType}	</td>
        </tr>
</c:forEach>
</table>

</body>
</html>