const prefixError = "<span style=\"color: red; \">";

function createNewAccount() {
    console.log("test0");
    const emailData = document.getElementById("emailData").value;
    console.log(emailData);
    const name = document.getElementById("name").value;
    const passwordData = document.getElementById("passwordData").value;
    const functionData = document.getElementById("functionData").value;
    const confirmationPassword = document.getElementById("confirmationpassword").value;
    const errorMessage = prefixError + "One or more fields missing";
    if (emailData!="" && name!="" && passwordData!="" && confirmationPassword!=""
         && functionData!="" && confirmPassword()) {
        sendAccountData(emailData, name, passwordData, functionData);
        sendActivationEmail(emailData);
    }
    else {
         document.getElementById("result").innerHTML = errorMessage;
    }


}

function sendAccountData(emailData, name, passwordData, functionData) {
    let request = new XMLHttpRequest();


    request.onload = function okCase() {
        if (this.status==200) {
           console.log("test1");
           document.getElementById("result").innerHTML = "<span style=\"color: green; \">Account successfully created";
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
    request.open("POST", "/cgi-bin/writeAccount", true);
    console.log(emailData);
    request.send(emailData + "-" + name + "-" + passwordData + "-" + functionData);
}


//Validation section--------------------------------------------------------
//We don't have to validate numbers, because the input type "number" already does that for us
//we just have to ensure that the confirmationPassword is equal to password

function confirmPassword() {
    var message = ""
    var validation =  true;
    var cpwd = document.getElementById("confirmationpassword").value;
    var pwd = document.getElementById("passwordData").value;
    if (cpwd !== pwd) {
        message = "Password does not match";
        validation = false;
    }
    else{
        document.getElementById("result").innerHTML = ""; }

    document.getElementById("result").innerHTML = prefixError + message;
    return validation;
}


function sendActivationEmail(emailData){
    let request = new XMLHttpRequest();

    request.onload = function okCase() {
        if (this.status==200) {
            console.log("test1");
            document.getElementById("result2").innerHTML = "<span style=\"color: green; \">Please access your email box to activate your account. An email with more information has been sent.";
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
    request.open("POST", "/cgi-bin/sendActivationEmail", true);
    console.log(emailData);
    request.send("emaildata=" + emailData );
}

function verifyIfAccountExists(){
    const requestAccount= new XMLHttpRequest();
    requestAccount.open("GET", "/cgi-bin/inputListOfAccounts", true);
    var validationtwo = true;

    requestAccount.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            for (let i = 0; i < data.length; i++) {
                console.log(data[i]['emaildata']);
                if (data[i]['emaildata']===document.getElementById("emailData").value){
                    validationtwo = false;
                    console.log(validationtwo);
                }
            }
            if (!validationtwo){
                document.getElementById("result").innerHTML = prefixError + "Account already exists.";
            }
            else{
                createNewAccount()
            }
        }
        else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }
    requestAccount.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    requestAccount.timeout = 5000;
    requestAccount.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    requestAccount.send();


}