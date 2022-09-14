const prefixError = "<span style=\"color: red; \">";

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

}

function loadAccounts() {
    let dropdownAccounts = document.getElementById('accounts');
    dropdownAccounts.length = 0;

    let defaultOptionAccounts = document.createElement('option');
    defaultOptionAccounts.text = 'Choose an account';

    dropdownAccounts.add(defaultOptionAccounts);
    dropdownAccounts.selectedIndex = 0;

    const requestAccounts= new XMLHttpRequest();
    requestAccounts.open("GET", "/cgi-bin/inputListOfDevAccounts?project=" + document.getElementById("projects").value, true);


    requestAccounts.onload = function okCase() {
        if (this.status == 200) {
            const data = JSON.parse(this.responseText);
                console.log(this.responseText);
                console.log("hello");

            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = "ID: " + data[i]['username'] + " Name: " + data[i]['account'] + " Is active: " + data[i]['status'];
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



function promoteToSM(){
        const account = document.getElementById("accounts").value;
        const project = document.getElementById("projects").value;
        if (account==="Choose an account" || project==="Choose project" || account==="" || project===""){
            document.getElementById("result").innerHTML = prefixError + "Please select an option";
        }
        else{
            sendNewRole(account, project);
        }
}

function sendNewRole(account, project) {
    var requestSM = new XMLHttpRequest();

    requestSM.onload=function okCase(){
        if(this.status==200){
            document.getElementById("result").innerHTML= "Developer promoted to SM";
        }
        else {
            document.getElementById("result").innerHTML= prefixError + this.responseText;
        }
    }

    requestSM.open("POST", "/cgi-bin/promoteDevToSM", true);
    requestSM.send(account + "&" + project );
}
