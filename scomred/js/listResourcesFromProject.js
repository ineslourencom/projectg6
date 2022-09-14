const prefixError = "<span style=\"color: red; \">";

function showProjects(){
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

function loadResources() {
    const request = new XMLHttpRequest();
    var project = document.getElementById("projects").value;
    request.open("GET", "/cgi-bin/inputListOfResourcesFromProject?project=" + project, true);

    request.onload = function okCase() {
        if (this.status == 200) {
            console.log(this.responseText);
            const data = JSON.parse(this.responseText);
            if (data.length===0){
                document.getElementById("result").innerHTML = prefixError + "There are no resources allocated to this project yet.";
            }
            else{
                document.getElementById("result").innerHTML = "";
            }
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