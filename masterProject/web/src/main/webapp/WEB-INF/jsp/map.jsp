<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
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
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false">
</script>
<script type="text/javascript">

<c:set var="i" value="1"/>

var transports = [
     <c:forEach var="location" items="${locations}">
          ['${location.transportId}',${location.location.latitude},${location.location.longitude}],
      </c:forEach>
];
var map;

 function initialize() {
	   		 
  var myOptions = {
    zoom: ${mapZoom},
    center: new google.maps.LatLng(${centerLat},${centerLong}),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
 map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
  setMarkers(map, transports);

 }

   
  function setMarkers(map, locations) {


  for (var i = 0; i < locations.length; i++) {
    var transport = locations[i];
    var myLatLng = new google.maps.LatLng(transport[1], transport[2]);
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: transport[0]
    });
  }
}

</script>
</head>
<body onload="initialize()">
<div id="map_canvas" style="width: 100%; height: 100%"></div>
</body>
</html>