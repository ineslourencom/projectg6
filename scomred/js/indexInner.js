var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var userid = urlParams.get('userid');
var pwrd = urlParams.get('pwrd');


//
// function initData() {
//     var queryString = window.location.search;
//     var urlParams = new URLSearchParams(queryString);
//     var userid = urlParams.get('userid');
//     var pwrd = urlParams.get('pwrd');
//     console.log(userid);
//     console.log(pwrd);
// }

function validAccess1(){

    //redirect to AddResourceToProject with email and password
    // const eData = userid;
    // const pData = pwrd;
    // const url = "../addResourceToProject.html?userid="+ eData + "&pwrd=" + pData;
    // window.location.href = url;
    window.location.href = "../addResourceToProject.html";

    console.log(eData);
    console.log(pData);

}

//Event listeners
HTMLAnchorElement.getElementById(<li>id=link01</li>).addEventListener("click", validAccess1());