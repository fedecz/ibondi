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
<script type="text/javascript" 	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script type="text/javascript">

<c:set var="i" value="1"/>
var map;

var transports = [
     <c:forEach var="location" items="${locationsList.transports}">
          ['${location.transportId}',${location.location.latitude},${location.location.longitude}],
      </c:forEach>
];

function initialize() {
   		 
 var myOptions = {
   zoom: ${locationsList.zoom},
   center: new google.maps.LatLng(${locationsList.center.latitude},${locationsList.center.longitude}),
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
        title: transport[0],
        icon: "static/img/iconbus.png"
    });
  }
}

</script>
</head>
<body onload="initialize()">
<div id="map_canvas" style="width: 100%; height: 100%"></div>
</body>
</html>