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


function createNewProfileRequest() {

    var profileType = document.getElementById("profiles").value;
    var description = document.getElementById("description").value;
    if(validateDescription() && profileType!="Choose a profile"){
        sendRequest(profileType, description);
    }
    else{
        document.getElementById("result").innerHTML = prefixError + "Please review your inputs";

    }
}

function sendRequest(profileType, description){
    let request = new XMLHttpRequest();

    request.onload = function okCase() {
        if (this.status == 200) {
            document.getElementById("result").innerHTML = "<span style=\"color: green; \"> Resource successfully add";
        } else {
            document.getElementById("result").innerHTML = prefixError + this.responseText;
        }
    }
    request.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    request.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    request.timeout = 5000;
    request.open("POST", "/cgi-bin/writeProfileType", true);
    console.log("test");
    request.send(profileType + "_" + description);
}


//----------------Input Validation---------

//verifies that the user has selected a profile and not entered something else

//get a list of all values in select box
function listProfileType() {
    var x = document.getElementById("profileType");
    var txt = "All options: ";
    var i;
    for (i = 0; i < x.length; i++) {
        txt = txt + "\n" + x.options[i].value;
    }
    alert(txt);
}

function validateDescription() {
    var description = document.getElementById("description").value;
    var message = "";
    var validation = true;

    if (description.length > 255 && description!="") {
        message = "The description cannot have more than 255 characters";
        document.getElementById("result").innerHTML = message;
        document.getElementById("result").style.color = "red";
        validation = false;
    }

    return validation;

}