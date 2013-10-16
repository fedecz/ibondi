<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#map_canvas {
	height: 100%
}
</style>
<script type="text/javascript" 	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">

function initialize() {
  var myLatLng = new google.maps.LatLng(${center.latitude}, ${center.longitude});
  var mapOptions = {
    zoom: 15,
    center: myLatLng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

  var transportPathLocations = [
		<c:forEach var="location" items="${session.locations}">
			new google.maps.LatLng(${location.latitude}, ${location.longitude}),
		</c:forEach>
  ];
  var transportPath = new google.maps.Polyline({
    path: transportPathLocations,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

  transportPath.setMap(map);
}
</script>
</head>
<body onload="initialize()">
<div id="map_canvas" style="width: 100%; height: 100%"></div>
</body>
</html>