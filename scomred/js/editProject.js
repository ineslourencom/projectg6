function initData() {
    //Projects data
    let dropdownProjects = document.getElementById('projects');
    dropdownProjects.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Choose project';

    dropdownProjects.add(defaultOption);
    dropdownProjects.selectedIndex = 0;

    const requestProjects = new XMLHttpRequest();
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
        } else
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

function showDetails() {
    const requestProjectData = new XMLHttpRequest();
    requestProjectData.open("GET", "/cgi-bin/inputProjectDetailsForEdition?project=" + document.getElementById("projects").value, true);


    requestProjectData.onload = function okCase() {
        if (this.status == 200) {
            const data = JSON.parse(this.responseText);
            console.log(this.responseText);
            console.log("hello");
            console.log(data[0]['id']);
            document.getElementById("idProject").innerHTML = data[0]['id'];
            document.getElementById("projectName").innerHTML = data[0]['project'];
            document.getElementById("description").innerHTML = data[0]['description'];
            document.getElementById("client").innerHTML = data[0]['client'];
            document.getElementById("sector").innerHTML = data[0]['sector'];
            document.getElementById("budget").innerHTML = data[0]['budget'];
            document.getElementById("typology").innerHTML = data[0]['typology'];
            document.getElementById("sprintDuration").value = data[0]['sprintduration'];
            document.getElementById("startDate").value = data[0]['startdate'];
            document.getElementById("endDate").value = data[0]['enddate'];
            document.getElementById("plannedSprints").value = data[0]['plannedsprints'];
            document.getElementById("status").innerHTML = data[0]['status'];

        } else
            document.getElementById("result").innerHTML = prefixError + "ERROR " + this.status + " reported by the backend";
    }


    requestProjectData.onerror = function errorCase() {
        document.getElementById("result").innerHTML = prefixError + "Network error";
    }
    requestProjectData.timeout = 5000;
    requestProjectData.ontimeout = function onTimeOut() {
        document.getElementById("result").innerHTML = prefixError + "No response from the web server";
    }
    requestProjectData.send();

}


function validateDates() {
    var validation = true;
    var message = ""
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;

    if (startDate === "") {
        message = "Error: start date must be selected"
        validation = false;
    } else if (endDate !== "" && startDate > endDate) {
        message = "Error: start date is after end date";
        validation = false;
    } else if (startDate === endDate) {
        message = "Error: dates coincide";
        validation = false;
    }

    document.getElementById("result").innerHTML = message;
    return validation;
}



function validateNumber() {
    var validation = true;
    var message = "";

    if (document.getElementById("plannedSprints").value !== "") {
        if (document.getElementById("plannedSprints").value <= 0) {
            message = "Invalid number, cannot be zero or negative";
            validation = false;
        }
    }
    document.getElementById("result").innerHTML = message;
    return validation;

}

function validateDuration() {
    var validation = true;
    var message = "";
    if (document.getElementById("sprintDuration").value <= 0 || document.getElementById("sprintDuration").value ==="") {
        message = "Invalid number, cannot be zero or negative";
        validation = false;
    }
    document.getElementById("result").innerHTML = message;
    return validation;
}


function saveChanges() {
    var message = ""
    const id = document.getElementById("idProject").textContent;
    console.log(id);
    const sprintDuration = document.getElementById("sprintDuration").value;
    const startDate = document.getElementById("startDate").value;
    const endDate = document.getElementById("endDate").value;
    const plannedSprints = document.getElementById("plannedSprints").value;
    const status = document.getElementById("newstatus").value;


    if (!validateNumber() || !validateDuration() || !validateDates()) {
        message = "Invalid input(s), project not edited"
        document.getElementById("result").innerHTML = message;
    } else {
        let requestEditProject = new XMLHttpRequest();

        requestEditProject.onload = function okCase() {
            if (this.status == 200) {
                document.getElementById("result").innerHTML = "<span style=\"color: green; \"> Project data successfully changed.";
            } else {
                document.getElementById("result").innerHTML = this.responseText;
            }
        }
        requestEditProject.onerror = function errorCase() {
            document.getElementById("result").innerHTML = "Network error";
        }
        requestEditProject.ontimeout = function onTimeOut() {
            document.getElementById("result").innerHTML = "No response from the web server";
        }
        requestEditProject.timeout = 5000;
        requestEditProject.open("POST", "/cgi-bin/editProject", true);
        requestEditProject.send(sprintDuration + "_" + startDate + "_" + endDate + "_" + plannedSprints + "_" + status + "_" + id);

    }

}
