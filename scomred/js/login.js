//Section for sending info to the server
const prefixError = "<span style=\"color: red; \">";

// function handleEnter(event) {
//     if (event.key === "Enter") {
//         const form = document.getElementById('form')
//         const index = [...form].indexOf(event.target);
//         form.elements[index + 1].focus();
//         event.preventDefault();
//     }
// }

function validAccess(email, password, appAction){
    let request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML="<span style=\"color: green; \"> login successful.";

            //redirect to InnerIndex with email and password-------------------------------
            const eData = document.getElementById("emailData");
            const pData = document.getElementById("passwordData");
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

function logInAccount(){
    const appAction = "Login";
    const email = document.getElementById("emailData").value;
    const password = document.getElementById("passwordData").value;
    const errorMessage = prefixError + "One or more fields missing";
    if (email!="" && password!="" ) {
        console.log(email);
        console.log(password);
        console.log(appAction);
        validAccess(email, password, appAction);

    }
    else {
        document.getElementById("result").innerHTML = errorMessage;
    }
}