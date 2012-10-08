<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
Show all transport list:<br />

<table>
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>branch</th>
	<th>Heading</th>
	<th>Type</th>
</tr>
<c:forEach items="${transportList}" var="transport">
    	<tr>
    		<td>${transport.id}</td>
        	<td>${transport.name}</td>
        	<td>${transport.branch}</td>
        	<td>${transport.heading}</td>
        	<td>${transport.transportType}</td>
        	<td><a href="<c:url value="/transports/show/${transport.id}.html"/>">Show</a></td>
        	<td><a href="<c:url value="/transports/delete/${transport.id}.html"/>">Delete</a></td>
        </tr>
</c:forEach>
</table>
<a href="<c:url value="/transports/add.html"/>">Add</a>

</body>
</html>