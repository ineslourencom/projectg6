export const URL_API = 'http://localhost:8080';

export function makeHTTPRequest(url, request, success, failure) {

    fetch(url, request)
        .then(res => res.json())
        .then(res => {
            if (res.errorMessage) {
                // make the promise be rejected if we didn't get a 2xx response
                const err = new Error(res.errorMessage);
                err.response = res;
                throw err;
            } else {
                console.log(res);
                return res
            }
        })
        .then(res => success(res))
        .catch(err => failure(err.message))
        ;
}


export function newProfileHTTPRequest(url, request, success, failure){
    fetch(url, request)
        .then(res => res.text())
        .then(res => success(res))
        .catch(err => failure(err.message))
    ;
}