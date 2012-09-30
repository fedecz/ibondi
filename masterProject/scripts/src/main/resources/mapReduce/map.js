function(){
	emit(this.transportId,{items:[{x: this.lastKnownLocation.latitude, y: this.lastKnownLocation.longitude}]});
}