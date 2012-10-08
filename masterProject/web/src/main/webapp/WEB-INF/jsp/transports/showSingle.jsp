<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
Show transport (aca podria ir mas informacion):<br />

<table>
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>branch</th>
	<th>Heading</th>
	<th>Type</th>
</tr>
    	<tr>
    		<td>${transport.id}</td>
        	<td>${transport.name}</td>
        	<td>${transport.branch}</td>
        	<td>${transport.heading}</td>
        	<td>${transport.transportType}</td>
        	<td><a href="<c:url value="/transports/delete/${transport.id}.html"/>">Delete</a></td>
        </tr>
</table>
<a href="<c:url value="/transports/show.html"/>">Back to List</a>

</body>
</html>