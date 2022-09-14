const prefixError = "<span style=\"color: red; \">";

function validateIfProfileExists(){
    const requestProfile= new XMLHttpRequest();
    requestProfile.open("GET", "/cgi-bin/inputListOfProfiles", true);
    var validationtwo = true;

    requestProfile.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            for (let i = 0; i < data.length; i++) {
                console.log(data[i]['profilename']);
                if (data[i]['profilename']===document.getElementById("profileType").value){
                    validationtwo = false;
                    console.log(validationtwo);
                }
            }
            if (!validationtwo){
                document.getElementById("result").innerHTML = prefixError + "Profile type already exists.";
            }
            else{
                createProfileType()
            }
        }
        else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }
    requestProfile.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    requestProfile.timeout = 5000;
    requestProfile.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    requestProfile.send();
}


function createProfileType(){
    var message=""

    const profileType = document.getElementById("profileType").value;

    const description = document.getElementById("description").value;

    if(!validateString(profileType) || !validateString(description)){
        message = "Invalid input(s), profile Type not created"
        document.getElementById("result").innerHTML= message;
    }
    else{
        sendprofileType(profileType, description);
    }

    function validateString(element){
        var validated=false;
        if(element.value != ""){
            validated = true;
        }
        return validated;
    }

}

function sendprofileType(profileType, description) {
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> ProfileType successfully created";
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
    request.open("POST", "/cgi-bin/writeCreateProfileType", true);
    request.send("profileType=" + profileType + "_" + description);
}