function(){
	emit(this.transportId,{items:[{x: this.lastKnownLocation.lat, y: this.lastKnownLocation.long}]});
}