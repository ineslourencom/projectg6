function createTypology(){
    var message=""

    const name = document.getElementById("name").value.trim();
    
    const description = document.getElementById("description").value.trim();
    
if(!validateString(name) || !validateString(description)){
    message = "Invalid input(s), typology not created"
    document.getElementById("result").innerHTML= message;
}
   else{
    sendTypologyData(name, description);
}

function validateString(element){

    var validated=false;

    if(element != ""){
        validated = true;
    }
    return validated;
}

}

function sendTypologyData(name, description) {
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> Project Typology created.";
        }   else {
            document.getElementById("result").innerHTML= this.responseText;
        }
    }
    request.onerror=function errorCase() {
        document.getElementById("result").innerHTML= "Network error";
    }
    request.ontimeout=function onTimeOut(){
        document.getElementById("result").innerHTML= "No response from the web server";
    }
    request.timeout=5000;
    request.open("POST", "/cgi-bin/writeTypology", true);
    request.send("Typology=" + name + "_" + description);
}