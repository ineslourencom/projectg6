const prefixError = "<span style=\"color: red; \">";

function activateAccount(){
    const emaildata = window.location.href;
    if (emaildata==""){
        document.getElementById("result").innerHTML = prefixError + "Please fill all fields before submitting changes";
    }
    else{
        sendNewStatus(emaildata);
    }
}


function sendNewStatus(emaildata) {
    var request = new XMLHttpRequest();

    request.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML= "Account is now active!";
        }
        else {
            document.getElementById("result").innerHTML= prefixError + this.responseText;
        }
    }

    request.open("POST", "/cgi-bin/updateAccountStatus", true);
    request.send("email=" + emaildata);
}




