db.runCommand(
		{ mapreduce : <collection>,
			map : <mapfunction>,
			reduce : <reducefunction>,
			out : <see output options below>
			[, query : <query filter object>]
			[, sort : <sorts the input objects using this key. Useful for optimization, like sorting by the emit key for fewer reduces>]
			[, limit : <number of objects to return from collection, not supported with sharding>]
			[, keeptemp: <true|false>]
			[, finalize : <finalizefunction>]
			[, scope : <object where fields go into javascript global scope >]
			[, jsMode : true]
			[, verbose : true]
			 }
);


m = function(){
	emit(this.transportId,{x: this.latitude, y: this.longitude});
}

r = function(key, values){
	var dbScan = new DBSCAN(values,distancia,10,1);
	dbScan.run();
	var cluster = dbScan.cluster;
	
	var result = [cluster.length];
	
	for(var i = 0; i < cluster.length; i++){
		var item = cluster[i][0];
		result.push(item);
	}
	
	return result;

}

function distancia (p1,p2){
	return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
}


function DBSCAN (D, dist, eps, MinPts) {
	    this.D = D;           
	    this.dist = dist;     
	    this.eps = eps;       
	    this.MinPts = MinPts; 
	    this.assigned = [];    
	    this.cluster = [];    
	    this.run = dbscanRun; 
	    this.getNeighbors = dbscanGetNeighbors;  
	    this.expandCluster = dbscanExpandCluster;
}

function dbscanRun(eps, MinPts) {
	    if (eps) this.eps = eps;
	    if (MinPts) this.MinPts = MinPts;
	    this.assigned = new Array(this.D.length);
	    this.cluster = new Array();
	    for (var P in this.D) {
		        if (this.assigned[P] === undefined) {  
			            var N = this.getNeighbors(P);
		                var C = this.cluster.length;  
		                this.cluster[C] = [];         
		                this.expandCluster(P, N, C);
		        }
		    }
}

function dbscanGetNeighbors(P) {
	    var neighbors = [];
	    for (var i in this.D) {
		        if (i == P) continue;
		        if (this.dist(P, i) <= this.eps)
			            neighbors.push(i);
		    }
	    return neighbors;
}

function dbscanExpandCluster(P, N, C) {
	    this.cluster[C].push(P);  
	    this.assigned[P] = C;     
	    for (var PP = 0; PP < N.length; PP++) {  
		        if (this.assigned[N[PP]] === undefined) {  
			            var NP = this.getNeighbors(N[PP]);    
			            if (NP.length + 1 >= this.MinPts) {
				                N = N.concat(NP.filter(function(p) { return p != P && N.indexOf(p) == -1 } ));
				            }
			        }
	        if (!(this.assigned[N[PP]] > -1)) {  
		            this.cluster[C].push(N[PP]);  
		            this.assigned[N[PP]] = C;     
		        }
	    }
}



===================================================
	
	
m = function(){
	emit(this.transportId,{id: this._id, x: this.latitude, y: this.longitude});
}

r = function(key, values){
	var dbScanf =  function (D, eps, MinPts) {
		    this.D = D;           
		    this.dist = function  (p1,p2){
			return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
		};
		    this.eps = eps;       
		    this.MinPts = MinPts; 
		    this.assigned = [];    
		    this.cluster = [];    
		    this.run = function (eps, MinPts) {
			    if (eps) this.eps = eps;
			    if (MinPts) this.MinPts = MinPts;
			    this.assigned = new Array(this.D.length);
			    this.cluster = new Array();
			    for (var P in this.D) {
				        if (this.assigned[P] === undefined) {  
					            var N = this.getNeighbors(P);
				                var C = this.cluster.length;  
				                this.cluster[C] = [];         
				                this.expandCluster(P, N, C);
				        }
				    }
		}; 
		    this.getNeighbors = function (P) {
			    var neighbors = [];
			    for (var i in this.D) {
				        if (i == P) continue;
				        if (this.dist(P, i) <= this.eps)
					            neighbors.push(i);
				    }
			    return neighbors;
		};  
		    this.expandCluster = function (P, N, C) {
			    this.cluster[C].push(P);  
			    this.assigned[P] = C;     
			    for (var PP = 0; PP < N.length; PP++) {  
				        if (this.assigned[N[PP]] === undefined) {  
					            var NP = this.getNeighbors(N[PP]);    
					            if (NP.length + 1 >= this.MinPts) {
						                N = N.concat(NP.filter(function(p) { return p != P && N.indexOf(p) == -1 } ));
						            }
					        }
			        if (!(this.assigned[N[PP]] > -1)) {  
				            this.cluster[C].push(N[PP]);  
				            this.assigned[N[PP]] = C;     
				        }
			    }
		};
	}
	var dbScan = new dbScanf(values,1,1);
	dbScan.run();
	var cluster = dbScan.cluster;
	
	var result = {items:[]};
	
	for(var i = 0; i < cluster.length; i++){
		var item = cluster[i][0];
		result.items.push(item);
	}
	
	return result;

}








