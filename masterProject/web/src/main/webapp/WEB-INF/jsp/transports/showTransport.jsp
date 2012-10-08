<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
The following transport has been added:<br />

id: ${transport.id} <br />
name: ${transport.name} <br />
branch: ${transport.branch} <br />
heading: ${transport.heading}<br />
type: ${transport.transportType}<br />
<a href="<c:url value="/transports/delete/${transport.id}.html"/>">Delete</a><br/>
<a href="<c:url value="/transports/show.html"/>">Back to List</a><br/>
</body>
</html>

