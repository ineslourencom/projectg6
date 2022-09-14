const prefixError = "<span style=\"color: red; \">";

function validateNumber(){
  
    var message="";
    if(document.getElementById("budget").value == 0){
        message = "Invalid number, cannot be zero";
        
    }else if(document.getElementById("budget").value < 0){
        message = "Negative value, absolute value assumed.";
        
    }
    document.getElementById("result").innerHTML= prefixError + message;
}
function validateSprintDuration(){
    
    var message="";
    if(document.getElementById("sprintDuration").value == 0){
        message = "Invalid number, cannot be zero";
    }
    if(document.getElementById("sprintDuration").value < 0){
        message = "Negative value, absolute value assumed.";
        
    }
    document.getElementById("result").innerHTML= message;
}

function validateDate() {
    var message="";
    var startDate = document.getElementById("startDate").value;
   
    if (startDate=="" ){
        message="Error: date must be selected"
    }
   
    document.getElementById("result").innerHTML= message;

}


function validateString(element){
    console.log("method initiated")
    var validated=false;

    if(element != ""){
        validated = true;
    }
    return validated;
}

function createProject(){
    var message=""

    const name = document.getElementById("name").value.trim();
    console.log(name);
    const description = document.getElementById("description").value.trim();
    const client = document.getElementById("client").value.trim();
    const businessSector = document.getElementById("businessSector").value.trim();
    const budget = Math.abs(document.getElementById("budget").value);
    const typology = document.getElementById("typologies").value;
    const sprintDuration = Math.abs(document.getElementById("sprintDuration").value);
    const startDate = document.getElementById("startDate").value;

if(!validateString(name) || !validateString(description)
|| !validateString(client) || !validateString(businessSector)|| budget == "0" || sprintDuration == "0" || startDate==""){
    message = "Invalid input(s), project not created"
    document.getElementById("result").innerHTML= message;
}
   else{
    sendProjectData(name, description, client, businessSector, budget, typology, sprintDuration, startDate);
}

}

function sendProjectData(name, description, client, businessSector, budget, typology, sprintDuration, startDate) {
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> Project created.";
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
    request.open("POST", "/cgi-bin/createProject", true);
    request.send("project=" + name + "_" + description + "_" + client + "_" + 
    businessSector + "_" + budget + "_" + typology + "_" + sprintDuration + "_" + startDate);
}


function initData() {

    const request= new XMLHttpRequest();
    request.open("GET", "/cgi-bin/inputListOfTypologies", true);

    request.onload = function okCase() {
        if (this.status == 200) {
            let dropdown = document.getElementById('typologies');
    dropdown.length = 0;

            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i]['typologyname'];
                option.value = data[i]['typologyname'];
                dropdown.add(option);
            }
            dropdown.selectedIndex = 0;
            
        }
        else
            document.getElementById("result").innerHTML = "ERROR " + this.status + " reported by the backend";
    }
    request.onerror = function errorCase() {
        document.getElementById("result").innerHTML = "Network error";
    }
    request.timeout = 5000;
    request.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = "No response from the web server";
    }
    request.send();
}
