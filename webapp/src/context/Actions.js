import {makeHTTPRequest, newProfileHTTPRequest} from '../services/Service';

export const FETCH_PROJECTS_STARTED = 'FETCH_PROJECTS_STARTED';
export const FETCH_PROJECTS_SUCCESS = 'FETCH_PROJECTS_SUCCESS';
export const FETCH_PROJECTS_FAILURE = 'FETCH_PROJECTS_FAILURE';

export function fetchProjects(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProjectsSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProjectsFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchProjectsStarted() {
    return {
        type: FETCH_PROJECTS_STARTED,

    }
}

export function fetchProjectsSuccess(projects) {
    return {
        type: FETCH_PROJECTS_SUCCESS,
        payload: {
            data:
                [...projects]
        }

    }
}

export function fetchProjectsFailure(message) {
    return {
        type: FETCH_PROJECTS_FAILURE,
        payload: {
            error: message
        }
    }
}

//-----------------------

export const FETCH_PROFILES_STARTED = 'FETCH_PROFILES_STARTED';
export const FETCH_PROFILES_SUCCESS = 'FETCH_PROFILES_SUCCESS';
export const FETCH_PROFILES_FAILURE = 'FETCH_PROFILES_FAILURE';

export function fetchProfiles(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProfilesSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProfilesFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchProfilesStarted() {
    return {
        type: FETCH_PROFILES_STARTED,

    }
}

export function fetchProfilesSuccess(projects) {
    return {
        type: FETCH_PROFILES_SUCCESS,
        payload: {
            data:
                [...projects]
        }

    }
}

export function fetchProfilesFailure(message) {
    return {
        type: FETCH_PROFILES_FAILURE,
        payload: {
            error: message
        }
    }
}

//------------US034

export const SUBMIT_USCATEGORY_FORM_SUCCESS = 'SUBMIT_USCATEGORY_FORM_SUCCESS';
export const SUBMIT_USCATEGORY_FORM_FAILURE = 'SUBMIT_USCATEGORY_FORM_FAILURE';

export function submitUSCategoryFormSuccess(info) {
    return {
        type: SUBMIT_USCATEGORY_FORM_SUCCESS,
        payload: {
            data: info
        }
    }
}

export function submitUSCategoryFormFailure(message) {
    return {
        type: SUBMIT_USCATEGORY_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitUSCategoryForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitUSCategoryFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitUSCategoryFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}
//----------------------


export const FETCH_PROJECT_INFO_STARTED = 'FETCH_PROJECT_INFO_STARTED';
export const FETCH_PROJECT_INFO_SUCCESS = 'FETCH_PROJECT_INFO_SUCCESS';
export const FETCH_PROJECT_INFO_FAILURE = 'FETCH_PROJECT_INFO_FAILURE';
export const DELETE_PROJECT_INFO = 'DELETE_PROJECT_INFO';

export function fetchProjectInfo(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProjectInfoSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProjectInfoFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchProjectInfoStarted(id) {
    return {
        type: FETCH_PROJECT_INFO_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function fetchProjectInfoSuccess(info) {
    return {
        type: FETCH_PROJECT_INFO_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchProjectInfoFailure(message) {
    return {
        type: FETCH_PROJECT_INFO_FAILURE,
        payload: {
            error: message
        }
    }
}

export function deleteProjectInfo() {
    return {
        type: DELETE_PROJECT_INFO,
    }
}


export const SUBMIT_PROJECT_FORM_STARTED = 'SUBMIT_PROJECT_FORM_STARTED';
export const SUBMIT_PROJECT_FORM_SUCCESS = 'SUBMIT_PROJECT_FORM_SUCCESS';
export const SUBMIT_PROJECT_FORM_FAILURE = 'SUBMIT_PROJECT_FORM_FAILURE';

export function submitProjectEditionFormStarted(id) {
    return {
        type: SUBMIT_PROJECT_FORM_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function submitProjectEditionFormSuccess(info) {
    return {
        type: SUBMIT_PROJECT_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitProjectEditionFormFailure(message) {
    return {
        type: SUBMIT_PROJECT_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitProjectEditionForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitProjectEditionFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitProjectEditionFormFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}


export const SUBMIT_US_FORM_SUCCESS = 'SUBMIT_US_FORM_SUCCESS';
export const SUBMIT_US_FORM_FAILURE = 'SUBMIT_US_FORM_FAILURE';
export const SUBMIT_US_FORM_RESET = 'SUBMIT_US_FORM_RESET';

export function submitUSCreationFormSuccess(info) {
    return {
        type: SUBMIT_US_FORM_SUCCESS,
        payload: {
            data: info
        }
    }
}

export function submitUSCreationFormFailure(message) {
    return {
        type: SUBMIT_US_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}

export function resetUSCreation() {
    return {
        type: SUBMIT_US_FORM_RESET
    }
}


export function submitUSCreationForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitUSCreationFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitUSCreationFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}


//------------US023

export const SUBMIT_SPRINTBACKLOGITEM_FORM_SUCCESS = 'SUBMIT_SPRINTBACKLOGITEM_FORM_SUCCESS';
export const SUBMIT_SPRINTBACKLOGITEM_FORM_FAILURE = 'SUBMIT_SPRINTBACKLOGITEM_FORM_FAILURE';
export const SUBMIT_SPRINTBACKLOGITEM_FORM_RESET = 'SUBMIT_SPRINTBACKLOGITEM_FORM_RESET';

export function submitSPRINTBACKLOGITEMCreationFormSuccess(info) {
    return {
        type: SUBMIT_SPRINTBACKLOGITEM_FORM_SUCCESS,
        payload: {
            data: info
        }
    }
}

export function submitSPRINTBACKLOGITEMCreationFormFailure(message) {
    return {
        type: SUBMIT_SPRINTBACKLOGITEM_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}

export function resetSPRINTBACKLOGITEMCreation() {
    return {
        type: SUBMIT_SPRINTBACKLOGITEM_FORM_RESET
    }
}


export function submitSPRINTBACKLOGITEMCreationForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitSPRINTBACKLOGITEMCreationFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitSPRINTBACKLOGITEMCreationFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}


//--------------

export const FETCH_ACCOUNT_INFO_STARTED = 'FETCH_ACCOUNT_INFO_STARTED';
export const FETCH_ACCOUNT_INFO_SUCCESS = 'FETCH_ACCOUNT_INFO_SUCCESS';
export const FETCH_ACCOUNT_INFO_FAILURE = 'FETCH_ACCOUNT_INFO_FAILURE';

/*
export function postProfileForm(dispatch, NewProfileInfoDTO){
  const success = (res) => dispatch()

  const failure = (err) =>

}

 */

export function fetchAccountInfoStarted(email) {
    return {
        type: FETCH_ACCOUNT_INFO_STARTED,
        payload:{
            accountID: email
        }
    }
}


export function fetchAccountInfoSuccess(info) {
    return {
        type: FETCH_ACCOUNT_INFO_SUCCESS,
        payload: {
            data: info
        }
    }
}


export function fetchAccountInfoFailure(message) {
    return {
        type: FETCH_ACCOUNT_INFO_FAILURE,
        payload: {
            error: message
        }
    }
}


export function fetchAccountInfo(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchAccountInfoSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchAccountInfoFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function resetProjectCreation() {
    return {
        type: SUBMIT_PROJ_FORM_RESET
    }
}

//--------------

export const SEARCH_ACCOUNT_STARTED = 'SEARCH_ACCOUNT_STARTED';
export const SEARCH_ACCOUNT_SUCCESS = 'SEARCH_ACCOUNT_SUCCESS';
export const SEARCH_ACCOUNT_FAILURE = 'SEARCH_ACCOUNT_FAILURE';


export function searchAccountStarted(email) {
    return {
        type: SEARCH_ACCOUNT_STARTED,
        payload:{
            accountID: email
        }
    }
}


export function searchAccountSuccess(info) {
    return {
        type: SEARCH_ACCOUNT_SUCCESS,
        payload: {
            data: info
        }
    }
}


export function searchAccountFailure(message) {
    return {
        type: SEARCH_ACCOUNT_FAILURE,
        payload: {
            error: message
        }
    }
}


export function searchAccount(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(searchAccountSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(searchAccountFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


//---------------------------------------------------------

export const FETCH_SPRINTS_STARTED = 'FETCH_SPRINTS_STARTED';
export const FETCH_SPRINTS_SUCCESS = 'FETCH_SPRINTS_SUCCESS';
export const FETCH_SPRINTS_FAILURE = 'FETCH_SPRINTS_FAILURE';

export function fetchAllSprints(url, request, dispatch) {
    const success = (res) => dispatch(fetchAllSprintsSuccess(res));
    const failure = (err) => dispatch(fetchAllSprintsFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchAllSprintsStarted(id) {
    return {
        type: FETCH_SPRINTS_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function fetchAllSprintsSuccess(sprints) {
    return {
        type: FETCH_SPRINTS_SUCCESS,
        payload: {
            data:
                [...sprints]
        }

    }
}

export function fetchAllSprintsFailure(message) {
    return {
        type: FETCH_SPRINTS_FAILURE,
        payload: {
            error: message
        }
    }
}



//-----------------------------------------------------------------------------------------

export const FETCH_RESOURCES_STARTED = 'FETCH_RESOURCES_STARTED';
export const FETCH_RESOURCES_SUCCESS = 'FETCH_RESOURCES_SUCCESS';
export const FETCH_RESOURCES_FAILURE = 'FETCH_RESOURCES_FAILURE';

export function fetchResources(url, request, dispatch) {
    const success = (res) => dispatch(fetchResourcesSuccess(res));
    const failure = (err) => dispatch(fetchResourcesFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchResourcesStarted(id) {
    return {
        type: FETCH_RESOURCES_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function fetchResourcesSuccess(resources) {
    return {
        type: FETCH_RESOURCES_SUCCESS,
        payload: {
            data: [...resources]
        }

    }
}

export function fetchResourcesFailure(message) {
    return {
        type: FETCH_RESOURCES_FAILURE,
        payload: {
            error: message
        }
    }
}


//---------------------------------------------------------------------------------------

export const SUBMIT_UPDATE_ACCOUNT_FORM_STARTED = 'SUBMIT_UPDATE_ACCOUNT_FORM_STARTED';
export const SUBMIT_UPDATE_ACCOUNT_FORM_SUCCESS = 'SUBMIT_UPDATE_ACCOUNT_FORM_SUCCESS';
export const SUBMIT_UPDATE_ACCOUNT_FORM_FAILURE = 'SUBMIT_UPDATE_ACCOUNT_FORM_FAILURE';

export function submitUpdateAccountInfoFormStarted(email) {
    return {
        type: SUBMIT_UPDATE_ACCOUNT_FORM_STARTED,
        payload: {
            email: email
        }
    }
}
export function submitUpdateAccountInfoFormSuccess(info) {
    return {
        type: SUBMIT_UPDATE_ACCOUNT_FORM_SUCCESS,
        payload: {
            data: info
        }
    }
}
export function submitUpdateAccountInfoFormFailure(message) {
    return {
        type: SUBMIT_UPDATE_ACCOUNT_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}

export function submitUpdateAccountInfo(url, request, dispatch) {
    //executed in case of success
    const success = (res) => dispatch(submitUpdateAccountInfoFormSuccess(res));
    //executed in case something fails
    const failure = (err) => dispatch(submitUpdateAccountInfoFormFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

//---------------------------------------------------------------------------------------


export function submitProjCreationFormSuccess(info) {
    return {
        type: SUBMIT_PROJ_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitProjCreationFormFailure(message) {
    return {
        type: SUBMIT_PROJ_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitProjCreationForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitProjCreationFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitProjCreationFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}



export const SUBMIT_PROJTYP_FORM_SUCCESS = 'SUBMIT_PROJTYP_FORM_SUCCESS';
export const SUBMIT_PROJTYP_FORM_FAILURE = 'SUBMIT_PROJTYP_FORM_FAILURE';

export function submitProjTypologyCreationFormSuccess(info) {
    return {
        type: SUBMIT_PROJTYP_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}


export function submitProjTypologyCreationFormFailure(message) {
    return {
        type: SUBMIT_PROJTYP_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitProjTypologyCreationForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitProjTypologyCreationFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitProjTypologyCreationFormFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}


export const SUBMIT_UPDATE_PROFILE_FORM_STARTED = 'SUBMIT_UPDATE_PROFILE_FORM_STARTED';
export const SUBMIT_UPDATE_PROFILE_FORM_SUCCESS = 'SUBMIT_UPDATE_PROFILE_FORM_SUCCESS';
export const SUBMIT_UPDATE_PROFILE_FORM_FAILURE = 'SUBMIT_UPDATE_PROFILE_FORM_FAILURE';

export function submitUpdateProfileEditionFormStarted(id) {
    return {
        type: SUBMIT_UPDATE_PROFILE_FORM_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function submitUpdateProfileEditionFormSuccess(info) {
    return {
        type: SUBMIT_UPDATE_PROFILE_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitUpdateProfileEditionFormFailure(message) {
    return {
        type: SUBMIT_UPDATE_PROFILE_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export const SUBMIT_PROJ_FORM_SUCCESS = 'SUBMIT_PROJ_FORM_SUCCESS';
export const SUBMIT_PROJ_FORM_FAILURE = 'SUBMIT_PROJ_FORM_FAILURE';
export const SUBMIT_PROJ_FORM_RESET = 'SUBMIT_PROJ_FORM_RESET';
export function submitUpdateProfileEditionForm(url, request, dispatch){
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitUpdateProfileEditionFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitUpdateProfileEditionFormFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export const SUBMIT_CREATE_RESOURCE_FORM_STARTED = 'SUBMIT_RESOURCE_FORM_STARTED';

export const SUBMIT_CREATE_RESOURCE_FORM_SUCCESS = 'SUBMIT_RESOURCE_FORM_SUCCESS';
export const SUBMIT_CREATE_RESOURCE_FORM_FAILURE = 'SUBMIT_RESOURCE_FORM_FAILURE';

export function submitCreateResourceFormSuccess(info) {
    return {
        type: SUBMIT_CREATE_RESOURCE_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}


export function submitCreateResourceFormFailure(message) {
    return {
        type: SUBMIT_CREATE_RESOURCE_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}
export function submitCreateResourceFormsStarted() {
    return {
        type: SUBMIT_CREATE_RESOURCE_FORM_STARTED,

    }
}


export function submitCreateResourceForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitCreateResourceFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitCreateResourceFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}
//--------------------------------------------------------------------------------------------------------

export const SUBMIT_CREATE_SPRINT_FORM_SUCCESS = 'SUBMIT_SPRINT_FORM_SUCCESS';
export const SUBMIT_CREATE_SPRINT_FORM_FAILURE = 'SUBMIT_SPRINT_FORM_FAILURE';

export function submitCreateSprintFormSuccess(info) {
    return {
        type: SUBMIT_CREATE_SPRINT_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}


export function submitCreateSprintFormFailure(message) {
    return {
        type: SUBMIT_CREATE_SPRINT_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitCreateSprintForm(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitCreateSprintFormSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitCreateSprintFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}
//----------------------------------------------------------------------------------------------------------
export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';

export function login(data) {
    return {
        type: LOGIN,
        payload: {
            data: data
        }
    }

}

export function logout() {
    return {
        type: LOGOUT
    }
}


//------------------------------US030

export const FETCH_SCRUMBOARD_STARTED = 'FETCH_SCRUMBOARD_STARTED';
export const FETCH_SCRUMBOARD_SUCCESS = 'FETCH_SCRUMBOARD_SUCCESS';
export const FETCH_SCRUMBOARD_FAILURE = 'FETCH_SCRUMBOARD_FAILURE';

export function fetchScrumBoard(url, request, dispatch) {
    const success = (res) => dispatch(fetchScrumBoardSuccess(res));
    const failure = (err) => dispatch(fetchScrumBoardFailure(err));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchScrumBoardStarted(id) {
    return {
        type: FETCH_SCRUMBOARD_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function fetchScrumBoardSuccess(scrumBoard) {
    return {
        type: FETCH_SCRUMBOARD_SUCCESS,
        payload: {
            data:
                [...scrumBoard]
        }

    }
}

export function fetchScrumBoardFailure(message) {
    return {
        type: FETCH_SCRUMBOARD_FAILURE,
        payload: {
            error: message
        }
    }
}


//--------US018
export const FETCH_PRODUCTBACKLOG_STARTED = 'FETCH_PRODUCTBACKLOG_STARTED';
export const FETCH_PRODUCTBACKLOG_SUCCESS = 'FETCH_PRODUCTBACKLOG_SUCCESS';
export const FETCH_PRODUCTBACKLOG_FAILURE = 'FETCH_PRODUCTBACKLOG_FAILURE';

export function fetchProductBacklog(url, request, dispatch) {
    const success = (res) => dispatch(fetchProductBacklogSuccess(res));
    const failure = (err) => dispatch(fetchProductBacklogFailure(err));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchProductBacklogStarted(id) {
    return {
        type: FETCH_PRODUCTBACKLOG_STARTED,
        payload: {
            projectid: id
        }
    }
}

export function fetchProductBacklogSuccess(productBacklog) {
    return {
        type: FETCH_PRODUCTBACKLOG_SUCCESS,
        payload: {
            data:
                [...productBacklog]
        }

    }
}

export function fetchProductBacklogFailure(message) {
    return {
        type: FETCH_PRODUCTBACKLOG_FAILURE,
        payload: {
            error: message
        }
    }
}


//-----------------US:013--------------
export const POST_PROFILE_SUCCESS = 'POST_PROFILE_SUCCESS'
export const POST_PROFILE_FAILURE = 'POST_PROFILE_FAILURE'
export const POST_PROFILE_LOAD = 'POST_PROFILE_LOAD'

export function postProfileForm(url,request, dispatch){
    const success = (res) => dispatch(submitPostProfileFormSuccess(res));
    const failure = (err) => dispatch(submitPostProfileFormFailure(err));

   newProfileHTTPRequest(url, request, success, failure)
}


export function submitPostProfileFormSuccess(res){
    return{
        type: POST_PROFILE_SUCCESS,
        payload: {
            data: [...res],
            success: "New Profile created Successfully"
        }
    }
}

export function submitPostProfileFormFailure(error){
    return {
        type: POST_PROFILE_FAILURE,
        payload:{
            error: error
        }

    }
}
//----------------------------

export const FETCH_PROJECT_TYP_INFO_SUCCESS = 'FETCH_PROJECT_TYP_INFO_SUCCESS';
export const FETCH_PROJECT_TYP_INFO_FAILURE = 'FETCH_PROJECT_TYP_INFO_FAILURE';

export function fetchProjectTypInfo(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProjectTypInfoSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProjectTypInfoFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchProjectTypInfoSuccess(info) {
    return {
        type: FETCH_PROJECT_TYP_INFO_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchProjectTypInfoFailure(message) {
    return {
        type: FETCH_PROJECT_TYP_INFO_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_NEW_PROFILE_ID_SUCCESS = 'FETCH_NEW_PROFILE_ID_SUCCESS';
export const FETCH_NEW_PROFILE_ID_FAILURE = 'FETCH_NEW_PROFILE_ID_FAILURE';

export function fetchNewProfileID(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchNewProfileIDSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchNewProfileIDFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchNewProfileIDSuccess(info) {
    return {
        type: FETCH_NEW_PROFILE_ID_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchNewProfileIDFailure(message) {
    return {
        type: FETCH_NEW_PROFILE_ID_FAILURE,
        payload: {
            error: message
        }
    }
}



export const FETCH_CUSTOMER_INFO_SUCCESS = 'FETCH_CUSTOMER_INFO_SUCCESS';
export const FETCH_CUSTOMER_INFO_FAILURE = 'FETCH_CUSTOMER_INFO_FAILURE';

export function fetchCustomerInfo(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchCustomerInfoSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchCustomerInfoFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}


export function fetchCustomerInfoSuccess(info) {
    return {
        type: FETCH_CUSTOMER_INFO_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchCustomerInfoFailure(message) {
    return {
        type: FETCH_CUSTOMER_INFO_FAILURE,
        payload: {
            error: message
        }
    }
}



export const FETCH_BUSINESS_SECTOR_INFO_SUCCESS = 'FETCH_BUSINESS_SECTOR_INFO_SUCCESS';
export const FETCH_BUSINESS_SECTOR_INFO_FAILURE = 'FETCH_BUSINESS_SECTOR_INFO_FAILURE';

export function fetchBusinessSectorInfo(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchBusinessSectorInfoSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchBusinessSectorInfoFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchBusinessSectorInfoSuccess(info) {
    return {
        type: FETCH_BUSINESS_SECTOR_INFO_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchBusinessSectorInfoFailure(message) {
    return {
        type: FETCH_BUSINESS_SECTOR_INFO_FAILURE,
        payload: {
            error: message
        }
    }
}


export class SUBMIT_ACCOUNT_FORM_SUCCESS {
}

export class SUBMIT_ACCOUNT_FORM_STARTED {
}

//-------------------------US016------------------------

export const FETCH_USERSTORY_STATUS_STARTED = 'FETCH_USERSTORY_STARTED'
export const FETCH_USERSTORY_STATUS_SUCCESS = 'FETCH_USERSTORY_STATUS_SUCCESS'
export const FETCH_USERSTORY_STATUS_FAILURE = 'FETCH_USERSTORY_STATUS_FAILURE'

export function fetchUSStatus(url, request, dispatch){
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchUSStatusSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchUSStatusFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchUSStatusStarted() {
    return {
        type: FETCH_USERSTORY_STATUS_STARTED,

    }
}


export function fetchUSStatusSuccess(info){
    return{
        type: FETCH_USERSTORY_STATUS_SUCCESS,
        payload:{
            data: info
        }
    }

}

export function fetchUSStatusFailure(message){
    return{
        type: FETCH_USERSTORY_STATUS_FAILURE,
        payload:{
            error: message
        }
    }
}

//-------------------US016b-------------------------
export const FETCH_TASK_STATUS_STARTED = 'FETCH_TASK_STATUS_STARTED'
export const FETCH_TASK_STATUS_SUCCESS = 'FETCH_TASK_STATUS_SUCCESS'
export const FETCH_TASK_STATUS_FAILURE = 'FETCH_TASK_STATUS_FAILURE'

export function fetchTaskStatus(url, request, dispatch){
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchTaskStatusSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchTaskStatusFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchTaskStatusStarted() {
    return {
        type: FETCH_TASK_STATUS_STARTED,

    }
}

export function fetchTaskStatusSuccess(info){
    return{
        type: FETCH_TASK_STATUS_SUCCESS,
        payload:{
            data: info
        }
    }

}

export function fetchTaskStatusFailure(message){
    return{
        type: FETCH_TASK_STATUS_FAILURE,
        payload:{
            error: message
        }
    }
}






export  const SUBMIT_PRODUCT_OWNER_FORM_STARTED = "SUBMIT_PRODUCT_OWNER_FORM_STARTED";
export  const SUBMIT_PRODUCT_OWNER_FORM_SUCCESS = "SUBMIT_PRODUCT_OWNER_FORM_SUCCESS";
export  const SUBMIT_PRODUCT_OWNER_FORM_FAILURE = "SUBMIT_PRODUCT_OWNER_FORM_FAILURE";
export const SUBMIT_RESOURCE_FORM_RESET = "SUBMIT_RESOURCE_FORM_RESET";

export function submitProductOwnerForm(url, request, dispatch){
    const success = (res) => dispatch(submitProductOwnerFormSuccess(res));

    const failure = (err) => dispatch(submitProductOwnerFormFailure(err));

    makeHTTPRequest(url, request, success, failure);

}

export function submitProductOwnerFormSuccess(info){
    return{
        type: SUBMIT_PRODUCT_OWNER_FORM_SUCCESS,
        payload:{
            data: info
        }
    }


}

export function submitProductOwnerFormFailure(message) {

    return {
        type: SUBMIT_PRODUCT_OWNER_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}
    export function submitProductOwnerFormStarted() {
        return {
            type: SUBMIT_PRODUCT_OWNER_FORM_STARTED

        }

}
export function resetResourceCreation() {
    return {
        type: SUBMIT_RESOURCE_FORM_RESET
    }
}
//----------US014-------------
export const SUBMIT_SCRUM_MASTER_FORM_STARTED = "SUBMIT_SCRUM_MASTER_FORM_STARTED";
export const SUBMIT_SCRUM_MASTER_FORM_SUCCESS = "SUBMIT_SCRUM_MASTER_FORM_SUCCESS";
export const SUBMIT_SCRUM_MASTER_FORM_FAILURE = "SUBMIT_SCRUM_MASTER_FORM_FAILURE";

export function submitScrumMasterForm(url, request, dispatch) {
    const success = (res) => dispatch(submitScrumMasterFormSuccess(res));

    const failure = (err) => dispatch(submitScrumMasterFormFailure(err));

    makeHTTPRequest(url, request, success, failure);
}

export function submitScrumMasterFormStarted() {
    return {
        type: SUBMIT_SCRUM_MASTER_FORM_STARTED
    }
}

export function submitScrumMasterFormSuccess(info) {
    return {
        type: SUBMIT_SCRUM_MASTER_FORM_SUCCESS,
        payload: {
            data: info
        }
    }
}
export function submitScrumMasterFormFailure(message) {

    return {
        type: SUBMIT_SCRUM_MASTER_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}

//---------------------------------------
export const FETCH_RUNNING_SPRINT_SUCCESS = 'FETCH_RUNNING_SPRINT_SUCCESS';
export const FETCH_RUNNING_SPRINT_FAILURE = 'FETCH_RUNNING_SPRINT_FAILURE';

export function fetchRunningSprintSuccess(info) {
    return {
        type: FETCH_RUNNING_SPRINT_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchRunningSprintFailure(message) {
    return {
        type: FETCH_RUNNING_SPRINT_FAILURE,
        payload: {
            error: message
        }
    }
}


export function fetchRunningSprint(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchRunningSprintSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchRunningSprintFailure(err));

    makeHTTPRequest(url, request, success, failure);

}

//---------------------------------------------------
export const FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_SUCCESS = 'FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_SUCCESS';
export const FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_FAILURE = 'FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_FAILURE';



export function fetchNotDoneUSInRunningSprintSuccess(info) {
    return {
        type: FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function fetchNotDoneUSInRunningSprintFailure(message) {
    return {
        type: FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_FAILURE,
        payload: {
            error: message
        }
    }
}


export function fetchNotDoneUSInRunningSprint(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchNotDoneUSInRunningSprintSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchNotDoneUSInRunningSprintFailure(err));

    makeHTTPRequest(url, request, success, failure);

}

//--------------------------
export const SUBMIT_TASK_CREATION_FORM_SUCCESS = 'SUBMIT_TASK_CREATION_FORM_SUCCESS';
export const SUBMIT_TASK_CREATION_FORM_FAILURE = 'SUBMIT_TASK_CREATION_FORM_FAILURE';
export const SUBMIT_TASK_CREATION_FORM_RESET = 'SUBMIT_TASK_CREATION_FORM_RESET';


export function submitTaskCreateSuccess(info) {
    return {
        type: SUBMIT_TASK_CREATION_FORM_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitTaskCreateFailure(message) {
    return {
        type: SUBMIT_TASK_CREATION_FORM_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitTaskCreate(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitTaskCreateSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitTaskCreateFailure(err));

    makeHTTPRequest(url, request, success, failure);
}

export function resetTaskCreation() {
    return {
        type: SUBMIT_TASK_CREATION_FORM_RESET
    }
}

export const FETCH_ACCOUNTS_STARTED = 'FETCH_ACCOUNTS_STARTED';
export const FETCH_ACCOUNTS_SUCCESS = 'FETCH_ACCOUNTS_SUCCESS';
export const FETCH_ACCOUNTS_FAILURE = 'FETCH_ACCOUNTS_FAILURE';

export function fetchAccounts(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchAccountsSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchAccountsFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function fetchAccountsStarted() {
    return {
        type: FETCH_ACCOUNTS_STARTED,

    }
}

export function fetchAccountsSuccess(accounts) {
    return {
        type: FETCH_ACCOUNTS_SUCCESS,
        payload: {
            data:
                [...accounts]
        }

    }
}

export function fetchAccountsFailure(message) {
    return {
        type: FETCH_ACCOUNTS_FAILURE,
        payload: {
            error: message
        }
    }
}

//--------------------------
export const SUBMIT_CHANGE_PRIORITY_SUCCESS = 'SUBMIT_CHANGE_PRIORITY_SUCCESS';
export const SUBMIT_CHANGE_PRIORITY_FAILURE = 'SUBMIT_CHANGE_PRIORITY_FAILURE';
export const SUBMIT_CHANGE_PRIORITY_RESET = 'SUBMIT_CHANGE_PRIORITY_RESET';


export function submitChangePrioritySuccess(info) {
    return {
        type: SUBMIT_CHANGE_PRIORITY_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitChangePriorityFailure(message) {
    return {
        type: SUBMIT_CHANGE_PRIORITY_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitChangePriority(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitChangePrioritySuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitChangePriorityFailure(err));

    makeHTTPRequest(url, request, success, failure);
}

/*export function resetChangePriority() {
    return {
        type: SUBMIT_TASK_CREATION_FORM_RESET
    }
}*/
//--------------------------
export const SUBMIT_DECOMPOSE_US_SUCCESS = 'SUBMIT_DECOMPOSE_US_SUCCESS';
export const SUBMIT_DECOMPOSE_US_FAILURE = 'SUBMIT_DECOMPOSE_US_FAILURE';


export function submitDecomposeUSSuccess(info) {
    return {
        type: SUBMIT_DECOMPOSE_US_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitDecomposeUSFailure(message) {
    return {
        type: SUBMIT_DECOMPOSE_US_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitDecomposeUS(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitDecomposeUSSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitDecomposeUSFailure(err));

    makeHTTPRequest(url, request, success, failure);
}

//--------------------------------------

export const SUBMIT_CHANGE_STARTDATE_SUCCESS = 'SUBMIT_CHANGE_STARTDATE_SUCCESS';
export const SUBMIT_CHANGE_STARTDATE_FAILURE = 'SUBMIT_CHANGE_STARTDATE_FAILURE';

export function submitChangeStartDateSuccess(info) {
    return {
        type: SUBMIT_CHANGE_STARTDATE_SUCCESS,
        payload: {
            data: info
        }

    }
}

export function submitChangeStartDateFailure(message) {
    return {
        type: SUBMIT_CHANGE_STARTDATE_FAILURE,
        payload: {
            error: message
        }
    }
}


export function submitChangeStartDate(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(submitChangeStartDateSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(submitChangeStartDateFailure(err));

    makeHTTPRequest(url, request, success, failure);
}
