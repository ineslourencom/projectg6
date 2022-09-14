//Section for sending info to the server
const prefixError = "<span style=\"color: red; \">";

//Get url variables
var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var userid = urlParams.get('userid');
var pwrd = urlParams.get('pwrd');


function initData() {
//get Account info from server
    const request= new XMLHttpRequest();
    request.open("GET", "/cgi-bin/getAccountData", true);

    request.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            // unpack data (JSON) from server
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

function validAccess(email, password, appAction){
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> login successful.";


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
    request.open("POST", "/cgi-bin/validAccount", true);
    request.send(email + "-" + password + "-" + appAction);
    console.log(email);
    console.log(password);
    console.log(appAction);
}

}

function updateAccount(){
    const emaildata = document.getElementById("email").value;
    const functionData = document.getElementById("functionData").value;
    const appAction = "Update Account Data";
    validAccess(userid, pwrd, appAction)
    sendUpdateAccount(emaildata, functionData);
}


function sendUpdateAccount(emaildata, functionData) {
    let request = new XMLHttpRequest();


    request.onload = function okCase() {
        if (this.status==200) {
            console.log("test1");
            document.getElementById("result").innerHTML = "<span style=\"color: green; \">Record Updated";
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
    request.open("POST", "/cgi-bin/updateAccountData", true);
    console.log(emaildata);
    request.send(emaildata + "-" + functionData);
}




