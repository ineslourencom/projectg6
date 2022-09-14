const prefixError = "<span style=\"color: red; \">";


function initData() {
    let dropdown = document.getElementById('profiles');
    dropdown.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Choose a profile';

    dropdown.add(defaultOption);
    dropdown.selectedIndex = 0;

    const request= new XMLHttpRequest();
    request.open("GET", "/cgi-bin/inputListOfProfiles", true);

    request.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i]['profilename'];
                option.value = data[i]['profilename'];
                dropdown.add(option);
            }
        }
        else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }
    request.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    request.timeout = 5000;
    request.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    request.send();
}

function validateEmail(){
    const request= new XMLHttpRequest();
    request.open("GET", "/cgi-bin/inputListOfAccounts", true);
    var validation = false;

    request.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            for (let i = 0; i < data.length; i++) {
                if (data[i]['emaildata']===document.getElementById("email").value){
                    validation = true;
                    console.log(validation);
                }
            }
            if (validation===false){
                document.getElementById("result").innerHTML = prefixError + "Email not found, please enter a valid email.";
            }
            else{
                document.getElementById("result").innerHTML = "";
            }
        }
        else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }
    request.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    request.timeout = 5000;
    request.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    request.send();

}

function updateProfile(){
    const emaildata = document.getElementById("email").value;
    const profile = document.getElementById("profiles").value;
    if (emaildata=="" || profile=="" || profile=="Choose a profile"){
        document.getElementById("result").innerHTML = prefixError + "Please fill all fields before submitting changes";
    }
    else{
        sendNewProfile(emaildata, profile);
    }
}


function sendNewProfile(emaildata, profile) {
    var request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML= "Record updated";
        }
        else {
            document.getElementById("result").innerHTML= prefixError + this.responseText;
        }
    }

    request.open("POST", "/cgi-bin/updateAccountProfile", true);
    request.send("emaildata=" + emaildata + "&profile=" + profile );
}




