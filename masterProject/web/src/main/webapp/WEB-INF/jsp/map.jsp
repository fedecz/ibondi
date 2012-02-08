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

var beaches = [
     <c:forEach var="location" items="${locations}">
          ['${location.name}',${location.latitude},${location.longitude},${i+1}],
      </c:forEach>
];
var map;

 function initialize() {
	 var latSum = 0;
	 var longSum =0;
	   for (var i = 0; i < beaches.length; i++) {
	 	latSum=latSum+beaches[i][1];
	 	longSum=longSum+beaches[i][2];
	   }
	   var centerLat = 0;
	   var centerLong = 0;
	   if (latSum!=0)
		   centerLat=latSum / beaches.length;
	   if (longSum!=0)
		   centerLong=longSum / beaches.length;

	   		 
  var myOptions = {
    zoom: ${mapZoom},
    center: new google.maps.LatLng(${centerLat},${centerLong}),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
 map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
  setMarkers(map, beaches);

 }

   
  function setMarkers(map, locations) {


  for (var i = 0; i < locations.length; i++) {
    var beach = locations[i];
    var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: beach[0],
        zIndex: beach[3]
    });
  }
}

</script>
</head>
<body onload="initialize()">
<div id="map_canvas" style="width: 100%; height: 100%"></div>
</body>
</html>