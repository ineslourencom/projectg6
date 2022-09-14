const prefixError = "<span style=\"color: red; \">";


/*function addEmail() {


    var email = document.getElementById(email).value;


    let request= new XMLHttpRequest();
    validateEmail()


    request.onload=function okCase(){
        if(this.status==200) document.getElementById("result1").innerHTML=this.responseText;
        else document.getElementById("result").innerHTML="ERROR " + this.status + " reported by the backend";
    }
    request.onerror=function errorCase() {
        document.getElementById("result").innerHTML="Network error";
    }
    request.ontimeout=function onTimeOut(){
        document.getElementById("result").innerHTML="No response from the web server";
    }
    request.open("GET", "/cgi-bin/accounts", true);
    request.timeout=5000;
    request.send();
}*/

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

function showTable() {
    const request = new XMLHttpRequest();
    request.open("GET", "/cgi-bin/getAccountInfo?emaildata=" + document.getElementById("email").value, true);

    request.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);

            var col = [];
            for (var i = 0; i < data.length; i++) {
                for (var key in data[i]) {
                    if (col.indexOf(key) === -1) {
                        col.push(key);
                    }
                }
            }
            var table = document.createElement("table");
            var tr = table.insertRow(-1);                   // TABLE ROW.

            for (var i = 0; i < col.length; i++) {
                var th = document.createElement("th");      // TABLE HEADER.
                th.innerHTML = col[i];
                tr.appendChild(th);
            }
            for (var i = 0; i < data.length; i++) {
                tr = table.insertRow(-1);
                for (var j = 0; j < col.length; j++) {
                    var tabCell = tr.insertCell(-1);
                    tabCell.innerHTML = data[i][col[j]];
                }
            }
            var divContainer = document.getElementById("showData");
            divContainer.innerHTML = "";
            divContainer.appendChild(table);
        } else
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





