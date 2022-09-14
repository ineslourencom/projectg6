//Section for sending info to the server
const prefixError = "<span style=\"color: red; \">";

//Get url variables
var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var userid = urlParams.get('userid');
var pwrd = urlParams.get('pwrd');


function confirmPassword() {
    var message = ""
    var cpwd = document.getElementById("confirm_passwordData").value;
    var pwd = document.getElementById("passwordData").value;
    if (cpwd !== pwd) {
        message = "Password does not match";
    }
    document.getElementById("confirm_match").innerHTML = prefixError + message;
}

function handleEnter(event) {
    if (event.key === "Enter") {
        const form = document.getElementById('form')
        const index = [...form].indexOf(event.target);
        form.elements[index + 1].focus();
        event.preventDefault();
    }
}

function validAccess(email, password, appAction){
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> login successful.";

            //redirect to InnerIndex with email and password-------------------------------
            const eData = userid
            const pData = pwrd;
            const url = "../indexInner.html?userid=" + eData.value + "&pwrd=" + pData.value;
            window.location.replace(url);

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

//Section for sending info to the server
function sendPasswordData(emailData, passwordData) {
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
    request.open("POST", "/cgi-bin/changePassword", true);
    console.log(emailData);
    request.send(emailData + "-" + name + "-" + passwordData + "-" + functionData);
}

function savePasswordChange(){
    const email = userid.value;
    const password = pwrd.value;
    const appAction = "Change Password";
    const errorMessage = prefixError + "One or more fields missing";
    if (password!="") {
        validAccess(userid, pwrd, appAction);
        sendPasswordData(email, password, appAction);
    }
    else {
        document.getElementById("confirm_result").innerHTML = errorMessage;
    }
}














// source for password strength validation
// https://www.section.io/engineering-education/password-strength-checker-javascript/

// timeout before a callback is called

let timeout;

// traversing the DOM and getting the input and span using their IDs

let password = document.getElementById('password')
let strengthBadge = document.getElementById('StrengthDisp')

// The strong and weak password Regex pattern checker

let strongPassword = new RegExp('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})')
let mediumPassword = new RegExp('((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{6,}))|((?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=.{8,}))')

function StrengthChecker(PasswordParameter) {
    // We then change the badge's color and text based on the password strength

    if (strongPassword.test(PasswordParameter)) {
        strengthBadge.style.backgroundColor = "green"
        strengthBadge.textContent = 'Strong'
    } else if (mediumPassword.test(PasswordParameter)) {
        strengthBadge.style.backgroundColor = 'blue'
        strengthBadge.textContent = 'Medium'
    } else {
        strengthBadge.style.backgroundColor = 'red'
        strengthBadge.textContent = 'Weak'
    }
}

// Adding an input event listener when a user types to the  password input

password.addEventListener("input", () => {

    //The badge is hidden by default, so we show it

    strengthBadge.style.display = 'block'
    clearTimeout(timeout);

    //We then call the StrengthChecker function as a callback then pass the typed password to it

    timeout = setTimeout(() => StrengthChecker(password.value), 500);

    //In case a user clears the text, the badge is hidden again

    if (password.value.length !== 0) {
        strengthBadge.style.display = 'block'
    } else {
        strengthBadge.style.display = 'none'
    }
});
