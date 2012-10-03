function(key, value){
	print("Finalize: key-value:" + tojson(key) + "-" + tojson(value));
	var result= {items:[]};
	for(var i = 0 ; i< value.items.length; i++){
		var item = value.items[i];
		result.items.push({id:0, transportId: key, lastKnownLocation: {lat:item.x, long:item.y}});
	}
	print("Finalize: result: " + tojson(result));
	return result;
}