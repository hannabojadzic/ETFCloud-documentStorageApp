function posaljiZahtjev()  {

	var xhttp = new XMLHttpRequest();
	
	var inputDatoteke = document.getElementById("inputDatoteke");
	var nazivDatoteka = document.getElementById("nazivDatoteka").value;
	var opisDatoteke = document.getElementById("opisDatoteke").value;
	 var fileName = inputDatoteke.value.split(/(\\|\/)/g).pop();
	 var ekstenzija = fileName.split('.').pop();
	 if(ekstenzija == 'pdf') {
	 
	 var file = inputDatoteke.files[0];

	var fd = new FormData();
	  fd.append("inputDatoteke", file);
	  fd.append("nazivDatoteka", nazivDatoteka);
	  fd.append("opisDatoteke", opisDatoteke);
	xhttp.open("POST", "http://localhost:8080/fajl/DodajDatoteku", true);
	//xhttp.setRequestHeader("Content-Type", "multipart/x-mixed-replace");
	xhttp.send(fd);
	xhttp.onreadystatechange = function() {
		alert("success");
		this.document.getElementById("prikazPretrage2").innerHTML = xhttp.response;
		if (this.readyState == 4 && this.status == 200) {
			//alert(this.responseText);
		}
		
	}
	 }
	 else {
		 alert("extenzija nije dozvoljena");
	 }
	
}
function downloadFile() {
	
	var naziv = this.document.getElementById("nazivFajl").value;
	var params = "nazivFajl=" + naziv;
	  var link = document.createElement("a");
	    link.download = naziv;
	    link.href = "http://localhost:8080/fajl/downloadFile" + "?"+params;
	    link.click();
	
	/*
var xhttp = new XMLHttpRequest();
 	
 	xhttp.open("GET", "http://localhost:8080/fajl/downloadFile" + "?"+params, true);
 	
 	xhttp.send();
	
	xhttp.onreadystatechange = (e) => {
		
		this.document.getElementById("prikazPretrage").innerHTML =  xhttp.responseBody;
		
		}
		*/
}
function pretraga()  {
	var naziv = this.document.getElementById("nazivFajl").value;
	var params = "nazivFajl=" + naziv;
	
 	var xhttp = new XMLHttpRequest();
 	
 	xhttp.open("GET", "http://localhost:8080/fajl/getFajlByNaziv" + "?"+params, true);
 	
 	xhttp.send();
	
	xhttp.onreadystatechange = (e) => {
		
		this.document.getElementById("prikazPretrage").innerHTML =  xhttp.response;
		if (this.readyState == 4 && this.status == 200) {
			
			//this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
		}
		else {
		//this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
		}
	}
	
}

function pretraga2()  {
	var naziv = this.document.getElementById("nazivFajl").value;
	var params = "nazivFajl=" + naziv;
	
 	var xhttp = new XMLHttpRequest();
 	
 	xhttp.open("GET", 'http://localhost:8080/slike/getSlike' );
 	
 	xhttp.send();
	
	xhttp.onreadystatechange = (e) => {
		//console.log(xhttp.responseText);
		//console.log(xhttp.status);
		//alert(xhttp.responseText);
		this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
		if (this.readyState == 4 && this.status == 200) {
			
			this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
		}
		else {
		this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
		}
	}
	
}