function(key, values){
	var puntos = [];
	for(var i =0;i<values.length;i++){
		var items = values[i].items;
		puntos = items.concat(puntos);
	}
	var solitarios = [];
	var clusters = [];
	var visitados = {};
	var eps = 0.00045045; //en grados, equivalente a unos 50 metros. (1 grado = 111km)
	var minPts = 1;
	var asignados = {};
		
	var buscarVecinos = function(p, eps, puntos){
		var vecinosP = [];
		for(var i = 0; i < puntos.length; i++){
			var posibleVecino = puntos[i];
			if(posibleVecino!=p){
				var dist = Math.sqrt(Math.pow((posibleVecino.x - p.x),2) + Math.pow((posibleVecino.y - p.y),2));
				if(dist <= eps){
					vecinosP.push(posibleVecino);
				}
			}
		}	
		return vecinosP;
	}
	var hash = function(p){
		return "x:" +p.x + ",y:" + p.y;
	}
	
	for(var i = 0; i < puntos.length; i++){
		var p = puntos[i];
		print("analizando punto: " + tojson(p));
		if(visitados[hash(p)]!=undefined) continue;
		visitados[hash(p)] = true;
		
		var vecinosP = buscarVecinos(p,eps, puntos);
		
		print("vecinos de " + tojson(p) + ": " + tojson(vecinosP));
		
		if(vecinosP.length < minPts){
			print(tojson(p) + "=> solitario");
			solitarios.push(p);
		}else{
			var cluster = [];
			cluster.push(p);
			print(tojson(p) +" agregado al cluster");
			asignados[hash(p)] = clusters.length;
			for (var j = 0; j < vecinosP.length ; j++){
				var pp = vecinosP[j];
				if(visitados[hash(pp)] == undefined){
					visitados[hash(pp)] = true;
					var vecinosPP = buscarVecinos(pp, eps, puntos);
					if(vecinosPP.length >= minPts){
						vecinosP = vecinosP.concat(vecinosPP);
					}
				}
				if(asignados[hash(pp)] == undefined){
					cluster.push(pp);
					asignados[hash(pp)] = clusters.length;
				}
			}
			clusters.push(cluster);
		}
	}
//	print("clusters: " + tojson(clusters));
	print("Solitarios: " + tojson(solitarios));
	for (var i = 0; i < solitarios.length; i++){
		var p = solitarios[i];
		var newCluster = [];
		newCluster.push(p);
		asignados[hash(p)] = clusters.length;
		clusters.push(newCluster);
	}
//	print("clusters ahora: " + tojson(clusters));
	var result = {items:[]};
	for (var i = 0 ; i < clusters.length ; i++){
		var cluster = clusters[i];
		var sumx = 0;
		var sumy = 0;
		for (var j = 0; j < cluster.length; j++){
			var p = cluster[j];
			sumx += p.x;
			sumy += p.y;
		}
		var avgx = sumx / cluster.length;
		var avgy = sumy / cluster.length;
		result.items.push({x:avgx, y:avgy});
	}
	print("result: " + tojson(result));
	return result;
}