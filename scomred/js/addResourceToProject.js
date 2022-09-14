const prefixError = "<span style=\"color: red; \">";

//Section for getting data from the server
function initData() {
    //Projects data
    let dropdownProjects = document.getElementById('projects');
    dropdownProjects.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Choose project';

    dropdownProjects.add(defaultOption);
    dropdownProjects.selectedIndex = 0;

    const requestProjects= new XMLHttpRequest();
    requestProjects.open("GET", "/cgi-bin/inputListOfProjects", true);

    requestProjects.onload = function okCase() {
        if (this.status == 200) {
            const data = JSON.parse(this.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = "Project name: " + data[i]['projectname'];
                option.value = data[i]['id'];
                dropdownProjects.add(option);
            }
        }
        else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }
    requestProjects.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    requestProjects.timeout = 5000;
    requestProjects.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    requestProjects.send();

    //Accounts data
    let dropdownAccounts = document.getElementById('accounts');
    dropdownAccounts.length = 0;

    let defaultOptionAccounts = document.createElement('option');
    defaultOptionAccounts.text = 'Choose an account';

    dropdownAccounts.add(defaultOptionAccounts);
    dropdownAccounts.selectedIndex = 0;

    const requestAccounts= new XMLHttpRequest();
    requestAccounts.open("GET", "/cgi-bin/inputListOfAccounts", true);

    requestAccounts.onload = function okCase() {
        if (this.status == 200) {
            const data = JSON.parse(this.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = "Name: " + data[i]['account'] + ", Is Active? " + data[i]['status'] + ", Email: " + data[i]['emaildata'];
                option.value = data[i]['username'];
                dropdownAccounts.add(option);
            }
        }
        else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }


    requestAccounts.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    requestAccounts.timeout = 5000;
    requestAccounts.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    requestAccounts.send();
}





//Section for sending info to the server

function createResource() {
    const email = document.getElementById("accounts").value;
    const project = document.getElementById("projects").value;
    const role = document.getElementById("role").value;
    const percAlloc = document.getElementById("percAllocation").value;
    const costHour = document.getElementById("costPerHour").value;
    var errorMessage;

    if (email==="Choose an account" || project==="Choose project"){
        errorMessage = prefixError + "Please select an option from the lists";
        document.getElementById("result").innerHTML = errorMessage;
    }
    else if (!validateRole() || !validatePercent() || !validateNumber()){
        errorMessage = prefixError + "Invalid inputs";
        document.getElementById("result").innerHTML = errorMessage;
    }
    else if (email==="" || project=="" || role=="" || percAlloc==""
        || costHour==="") {
        errorMessage = prefixError + "One or more fields missing";
        document.getElementById("result").innerHTML = errorMessage;
    }
    else {
        sendResourceData(email, project, role, percAlloc, costHour);
    }
}

function sendResourceData(email, project, role, percAlloc, costHour) {
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> Resource successfully added";
        }   else {
            document.getElementById("result").innerHTML=prefixError + this.responseText;
        }
    }
    request.onerror=function errorCase() {
        document.getElementById("result").innerHTML=prefixError + "Network error";
    }
    request.ontimeout=function onTimeOut(){
        document.getElementById("result").innerHTML=prefixError + "No response from the web server";
    }
    request.timeout=5000;
    request.open("POST", "/cgi-bin/writeResource", true);
    console.log("test");
    request.send("resource=" + email + "_" + project + "_" + role + "_" + percAlloc + "_" + costHour);
}


//Section for updating endDate on server
//PUT
function resetEndDate() {

}



//Validation section--------------------------------------------------------
//We don't have to validate numbers, because the input type "number" already does that for us
//we just have to ensure that the percAllocation is not bigger than 100 nor negative
//and that the cost per hour is not negative
//further validations will be done later
function validateNumber(){
    var validation = true;
    var message=""
    if(document.getElementById("costPerHour").value <= 0 || Number.isNaN(document.getElementById("costPerHour").value)){
        message = "Please enter a cost per hour higher than 0EUR/hour.";
        document.getElementById("result").innerHTML=prefixError + message;
        validation = false;
    }
    return validation;
}

function validatePercent(){
    var validation = true;
    var message=""
    var perc=document.getElementById("percAllocation").value;
    if(perc <= 0 || perc>100 || Number.isNaN(document.getElementById("percAllocation").value)){
        message = "Please enter a percentage between 0 and 100";
        document.getElementById("result").innerHTML=prefixError + message;
        validation = false;
    }
    return validation;
}

//verifies that the user has selected a role and not entered something else
function validateRole(){
    var validation;
    var message="";
    var role = document.getElementById("role").value
    var optOne = "Project Manager";
    var optTwo = "Product Owner";
    var optThree = "Scrum Master";
    var optFour = "Developer";

    if(role!=optOne && role!=optTwo && role!=optThree && role!=optFour){
        message = "Please select a valid role";
        document.getElementById("result").innerHTML=prefixError + message;
        validation = false;
    }
    else{
        validation = true;
    }

    return validation;
}

