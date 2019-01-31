function getSlike(){
    this.document.getElementById("something").innerHTML = "";
    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/slike/getSlike';
    Http.open("GET", url);
    Http.send();
    Http.onreadystatechange=(e)=>{
        this.document.getElementById("something").innerHTML = Http.responseText;
        console.log(Http.responseText)
    }
}

function postSlika()  {

    var xhttp = new XMLHttpRequest();

    var inputSlike = document.getElementById("inputSlike");
    var nazivSlike = document.getElementById("nazivSlike").value;
    var opisSlike = document.getElementById("opisSlike").value;
    var fileName = inputSlike.value.split(/(\\|\/)/g).pop();
    var ekstenzija = fileName.split('.').pop();
    if(ekstenzija == 'jpg' || ekstenzija == 'png') {
        var file = inputSlike.files[0];

        var fd = new FormData();
        fd.append("slika", file);
        fd.append("naziv", nazivSlike);
        fd.append("opisSlike", opisSlike);
        xhttp.open("POST", "http://localhost:8080/slike/addSlika", true);
        xhttp.send(fd);
        xhttp.onreadystatechange = function () {
            alert("success");
            //this.document.getElementById("prikazPretrage2").innerHTML = xhttp.response;
            if (this.readyState == 4 && this.status == 200) {
                //alert(this.responseText);
            }

        }
    }
    else {
        alert("extenzija nije dozvoljena");
    }
}

function pretragaSlika()  {
    var naziv = this.document.getElementById("nazivFajl").value;
    var params = "nazivSlike=" + naziv;

    var xhttp = new XMLHttpRequest();

    xhttp.open("GET", "http://localhost:8080/slike/getSlikeByNaziv" + "?"+params, true);

    xhttp.send();

    xhttp.onreadystatechange = (e) => {

        this.document.getElementById("prikazPretrage2").innerHTML = xhttp.response;
        if (this.readyState == 4 && this.status == 200) {

            //this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
        }
        else {
            //this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
        }
    }

}


function getVideo(){
    this.document.getElementById("something").innerHTML = "";
    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/video/getVideo';
    Http.open("GET", url);
    Http.send();
    Http.onreadystatechange=(e)=>{
        this.document.getElementById("something").innerHTML = Http.responseText;
        console.log(Http.responseText)
    }
}

function postVideo()  {

    var xhttp = new XMLHttpRequest();

    var inputVideo = document.getElementById("inputVideo");
    var nazivVideo = document.getElementById("nazivVideo").value;
    var opisVideo = document.getElementById("opisVideo").value;
    var fileName = inputVideo.value.split(/(\\|\/)/g).pop();
    var ekstenzija = fileName.split('.').pop();
    if(ekstenzija == 'jpg' || ekstenzija == 'png') {
        var file = inputSlike.files[0];

        var fd = new FormData();
        fd.append("video", file);
        fd.append("naziv", nazivVideo);
        fd.append("opisVideo", opisVideo);
        xhttp.open("POST", "http://localhost:8080/video/addVideo", true);
        xhttp.send(fd);
        xhttp.onreadystatechange = function () {
            alert("success");
            //this.document.getElementById("prikazPretrage2").innerHTML = xhttp.response;
            if (this.readyState == 4 && this.status == 200) {
                //alert(this.responseText);
            }

        }
    }
    else{
        alert("extenzija nije dozvoljena");
    }
}

function pretragaVideo()  {
    var naziv = this.document.getElementById("nazivFajl").value;
    var params = "nazivVideo=" + naziv;

    var xhttp = new XMLHttpRequest();

    xhttp.open("GET", "http://localhost:8080/video/getVideoByNaziv" + "?"+params, true);

    xhttp.send();

    xhttp.onreadystatechange = (e) => {

        this.document.getElementById("prikazPretrage3").innerHTML = xhttp.response;
        if (this.readyState == 4 && this.status == 200) {

            //this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
        }
        else {
            //this.document.getElementById("prikazPretrage").innerHTML = xhttp.response;
        }
    }

}