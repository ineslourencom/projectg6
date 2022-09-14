import {
    FETCH_PROJECTS_STARTED,
    FETCH_PROJECTS_SUCCESS,
    FETCH_PROJECTS_FAILURE,
    FETCH_PROFILES_STARTED,
    FETCH_PROFILES_SUCCESS,
    FETCH_PROFILES_FAILURE,
    FETCH_PROJECT_INFO_STARTED,
    FETCH_PROJECT_INFO_SUCCESS,
    FETCH_PROJECT_INFO_FAILURE,
    DELETE_PROJECT_INFO,
    SUBMIT_PROJECT_FORM_SUCCESS,
    SUBMIT_US_FORM_SUCCESS,
    SUBMIT_US_FORM_FAILURE,
    FETCH_ACCOUNT_INFO_STARTED,
    FETCH_ACCOUNT_INFO_SUCCESS,
    FETCH_ACCOUNT_INFO_FAILURE,
    SUBMIT_UPDATE_ACCOUNT_FORM_STARTED,
    SUBMIT_UPDATE_ACCOUNT_FORM_SUCCESS,
    SUBMIT_UPDATE_ACCOUNT_FORM_FAILURE,
    LOGIN,
    LOGOUT,
    FETCH_PROJECT_TYP_INFO_SUCCESS,
    FETCH_PRODUCTBACKLOG_STARTED,
    FETCH_PRODUCTBACKLOG_SUCCESS,
    FETCH_PRODUCTBACKLOG_FAILURE,
    POST_PROFILE_FAILURE,
    POST_PROFILE_SUCCESS,
    FETCH_NEW_PROFILE_ID_SUCCESS,
    POST_PROFILE_LOAD,
    FETCH_CUSTOMER_INFO_SUCCESS,
    FETCH_BUSINESS_SECTOR_INFO_SUCCESS,
    SUBMIT_PROJ_FORM_SUCCESS,
    SUBMIT_PROJ_FORM_FAILURE,
    SUBMIT_US_FORM_RESET,
    SUBMIT_PROJ_FORM_RESET,
    SUBMIT_CREATE_RESOURCE_FORM_SUCCESS,
    SUBMIT_CREATE_RESOURCE_FORM_FAILURE,
    SUBMIT_CREATE_SPRINT_FORM_SUCCESS,
    SUBMIT_CREATE_SPRINT_FORM_FAILURE,
    FETCH_USERSTORY_STATUS_SUCCESS,
    FETCH_USERSTORY_STATUS_FAILURE,
    FETCH_USERSTORY_STATUS_STARTED,
    FETCH_TASK_STATUS_STARTED,
    FETCH_TASK_STATUS_SUCCESS,
    FETCH_TASK_STATUS_FAILURE,
    SUBMIT_CREATE_RESOURCE_FORM_STARTED,
    SUBMIT_PRODUCT_OWNER_FORM_STARTED,
    SUBMIT_PRODUCT_OWNER_FORM_SUCCESS,
    SUBMIT_PRODUCT_OWNER_FORM_FAILURE,
    SUBMIT_RESOURCE_FORM_RESET,
    FETCH_SPRINTS_STARTED,
    FETCH_SPRINTS_SUCCESS,
    FETCH_SPRINTS_FAILURE,
    FETCH_RESOURCES_SUCCESS,
    FETCH_RESOURCES_FAILURE,
    SUBMIT_SCRUM_MASTER_FORM_STARTED,
    SUBMIT_SCRUM_MASTER_FORM_SUCCESS,
    SUBMIT_SCRUM_MASTER_FORM_FAILURE,
    SUBMIT_SPRINTBACKLOGITEM_FORM_SUCCESS,
    SUBMIT_SPRINTBACKLOGITEM_FORM_FAILURE,
    SUBMIT_SPRINTBACKLOGITEM_FORM_RESET,
    FETCH_SCRUMBOARD_STARTED,
    FETCH_SCRUMBOARD_SUCCESS,
    FETCH_SCRUMBOARD_FAILURE,
    SEARCH_ACCOUNT_STARTED,
    SEARCH_ACCOUNT_FAILURE,
    SEARCH_ACCOUNT_SUCCESS,
    FETCH_RUNNING_SPRINT_SUCCESS,
    FETCH_RUNNING_SPRINT_FAILURE,
    FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_SUCCESS,
    FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_FAILURE,
    SUBMIT_TASK_CREATION_FORM_FAILURE,
    SUBMIT_TASK_CREATION_FORM_SUCCESS,
    SUBMIT_TASK_CREATION_FORM_RESET,
    FETCH_ACCOUNTS_STARTED,
    FETCH_ACCOUNTS_SUCCESS,
    FETCH_ACCOUNTS_FAILURE,
    SUBMIT_CHANGE_PRIORITY_FAILURE,
    SUBMIT_CHANGE_PRIORITY_SUCCESS,
    SUBMIT_USCATEGORY_FORM_SUCCESS,
    SUBMIT_USCATEGORY_FORM_FAILURE,
    SUBMIT_CHANGE_STARTDATE_SUCCESS,
    SUBMIT_CHANGE_STARTDATE_FAILURE,
    SUBMIT_DECOMPOSE_US_SUCCESS,
    SUBMIT_DECOMPOSE_US_FAILURE

} from './Actions.js'


function reducer(state, action) {
    switch (action.type) {
        case FETCH_PROJECTS_STARTED:
            return {
                ...state,
                projects: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PROJECTS_SUCCESS:
            return {
                ...state,
                projects: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_PROJECTS_FAILURE:
            return {
                ...state,
                projects: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_PROFILES_STARTED:
            return {
                ...state,
                profiles: {
                    loading: true,
                    error: null,
                    data: [],
                }
            }
        case FETCH_PROFILES_SUCCESS:
            return {
                ...state,
                profiles: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }
        case FETCH_PROFILES_FAILURE:
            return {
                ...state,
                profiles: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }


        case FETCH_PROJECT_INFO_STARTED:
            return {
                ...state,
                projectDetails: {
                    loading: true,
                    error: null,
                    data: null,
                    projectid: action.payload.projectid,
                }
            }
        case FETCH_PROJECT_INFO_SUCCESS:
            return {
                ...state,
                projectDetails: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                    //  projectid: state.projectDetails.projectid,
                }
            }
        case FETCH_PROJECT_INFO_FAILURE:
            return {
                ...state,
                projectDetails: {
                    loading: false,
                    error: action.payload.error,
                    data: null,
                    projectid: 0,
                }
            }
        case DELETE_PROJECT_INFO:
            return {
                ...state,
                projectDetails: {
                    loading: false,
                    error: null,
                    data: null,
                    projectid: 0,
                }
            }

        case SUBMIT_PROJECT_FORM_SUCCESS:
            return {
                ...state,
                projectDetails: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                    projectid: state.projectDetails.projectid,

                }

            }

        case FETCH_ACCOUNT_INFO_STARTED:
            return {
                ...state,
                account: {
                    loading: true,
                    error: null,
                    data: [],
                }
            }

        case FETCH_ACCOUNT_INFO_SUCCESS:
            return {
                ...state,
                account: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_ACCOUNT_INFO_FAILURE:
            return {
            ...state,
            account: {
                loading: false,
                error: action.payload.error,
                data: [],
            }
        }

        case SEARCH_ACCOUNT_STARTED:
            return {
                ...state,
                searchedAccount: {
                    loading: true,
                    error: null,
                    data: [],
                }
            }

        case SEARCH_ACCOUNT_SUCCESS:
            return {
                ...state,
                searchedAccount: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case SEARCH_ACCOUNT_FAILURE:
            return {
                ...state,
                searchedAccount: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }



        case SUBMIT_UPDATE_ACCOUNT_FORM_STARTED:
            return {
                ...state,
                account: {
                    loading: true,
                    error: null,
                    data: null,
                    accountID: action.payload.email,
                }
            }

        case SUBMIT_UPDATE_ACCOUNT_FORM_SUCCESS:
            return {
                ...state,
                account: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                    accountID: action.payload.data.email,
                }
            }

        case SUBMIT_UPDATE_ACCOUNT_FORM_FAILURE:
            return {
                ...state,
                account: {
                    loading: false,
                    error: action.payload.error,
                    data: null,
                    accountID: null
                }
            }


        case LOGIN:
            return {
                ...state,
                logIn: {
                    loggedIn: true,
                    email: action.payload.data.email,
                    profile: action.payload.data.profile
                }

            }
        case LOGOUT:
            return {
                ...state,
                logIn: {
                    loggedIn: false,
                    email: "",
                    profile : ""
                }
            }

        case SUBMIT_US_FORM_SUCCESS:
            return {
                ...state,
                userStoryDetails: {
                    error: null,
                    message: action.payload.data
                }
            }

        case SUBMIT_US_FORM_FAILURE:
            return {
                ...state,
                userStoryDetails: {
                    error: action.payload.error,
                    message: null
                }
            }
        case SUBMIT_US_FORM_RESET:
            return {
                ...state,
                userStoryDetails: {
                    error: null,
                    message: null
                }
            }

        case SUBMIT_SPRINTBACKLOGITEM_FORM_SUCCESS:
            return {
                ...state,
                sprintBacklogItem: {
                    error: null,
                    data: action.payload.data
                }
            }

        case SUBMIT_SPRINTBACKLOGITEM_FORM_FAILURE:
            return {
                ...state,
                sprintBacklogItem: {
                    error: action.payload.error,
                    data: []
                }
            }
        case SUBMIT_SPRINTBACKLOGITEM_FORM_RESET:
            return {
                ...state,
                sprintBacklogItem: {
                    error: null,
                    data: null
                }
            }

        case FETCH_PRODUCTBACKLOG_STARTED:
            return {
                ...state,
                productBacklog: {
                    loading: true,
                    error: null,
                    data: null,
                    projectid: action.payload.projectid,
                }
            }

        case FETCH_PRODUCTBACKLOG_SUCCESS:
            return {
                ...state,
                productBacklog: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_PRODUCTBACKLOG_FAILURE:
            return {
                ...state,
                productBacklog: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_SCRUMBOARD_STARTED:
            return {
                ...state,
                scrumBoard: {
                    loading: true,
                    error: null,
                    data: [],
                    projectid: action.payload.projectid,
                }
            }

        case FETCH_SCRUMBOARD_SUCCESS:
            return {
                ...state,
                scrumBoard: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_SCRUMBOARD_FAILURE:
            return {
                ...state,
                scrumBoard: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_SPRINTS_STARTED:
            return {
                ...state,
                sprints: {
                    loading: true,
                    error: null,
                    data: [],
                    projectid: action.payload.projectid,
                }
            }

        case FETCH_SPRINTS_SUCCESS:
            return {
                ...state,
                sprints: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_SPRINTS_FAILURE:
            return {
                ...state,
                sprints: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }
        case POST_PROFILE_FAILURE:
            return {
                ...state, profileInfo: {
                    loading: false, error: action.payload.error, data: null, profileid: null
                }

            }
        case POST_PROFILE_SUCCESS:
            return {
                ...state, profileInfo: {
                    loading: false, error: action.payload.data, profileid: state.profileInfo.profileid
                }
            }
        case POST_PROFILE_LOAD:
            return {
                ...state, profileInfo: {
                    loading: false, error: null, data: null, profileid: state.payload.profileid
                }
            }
        default:
            return state
                ;

        case FETCH_PROJECT_TYP_INFO_SUCCESS:
            return {
                ...state,
                projectTypologies: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_NEW_PROFILE_ID_SUCCESS:
            return {
                ...state,
                profileInfo: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_CUSTOMER_INFO_SUCCESS:
            return {
                ...state,
                customer: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case FETCH_BUSINESS_SECTOR_INFO_SUCCESS:
            return {
                ...state,
                businessSector: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }

        case SUBMIT_PROJ_FORM_SUCCESS:
            return {
                ...state,
                createProject: {
                    error: null,
                    message: action.payload.data
                }
            }

        case SUBMIT_PROJ_FORM_FAILURE:
            return {
                ...state,
                createProject: {
                    error: action.payload.error,
                    message: null
                }
            }

        case SUBMIT_PROJ_FORM_RESET:
            return {
                ...state,
                createProject: {
                    error: null,
                    message: null
                }
            }
        case SUBMIT_CREATE_RESOURCE_FORM_STARTED:
            return{
                ...state,
                resource: {
                    loading: true,
                    data: null,
                    error: null
                }

            }

        case SUBMIT_CREATE_RESOURCE_FORM_SUCCESS:
            return {
                ...state,
                resource: {
                    loading: false,
                    data: action.payload.data,
                    error: null
                }
            }

        case SUBMIT_CREATE_RESOURCE_FORM_FAILURE:
            return {
                ...state,
                resource: {
                    loading: false,
                    data: null,
                    error: action.payload.error
                }
            }

        case SUBMIT_CREATE_SPRINT_FORM_SUCCESS:
            return {
                ...state,
                sprint: {
                    data: action.payload.data,
                    error: null
                }
            }
        case SUBMIT_CREATE_SPRINT_FORM_FAILURE:
            return {
                ...state,
                sprint: {
                    data: null,
                    error: action.payload.error
                }
            }



        case FETCH_USERSTORY_STATUS_SUCCESS:
            return {
                ...state,
                usInfo:{
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_USERSTORY_STATUS_FAILURE:
            return {
                ...state,
                usInfo:{
                    loading: false,
                    error: action.payload.error,

                }
            }

        case FETCH_USERSTORY_STATUS_STARTED:
            return {
                ...state,
                usInfo:{
                    loading: true,
                    error: null,
                    data: null

                }
            }

        case FETCH_TASK_STATUS_STARTED:
            return {
                ...state,
                taskInfo:{
                    loading: true,
                    error: null,
                    data: null

                }
            }

        case FETCH_TASK_STATUS_SUCCESS:
            return {
                ...state,
                taskInfo:{
                    loading: false,
                    error: null,
                    data: action.payload.data

                }
            }

        case FETCH_TASK_STATUS_FAILURE:
            return {
                ...state,
                taskInfo:{
                    loading: false,
                    error: action.payload.error,

                }
            }



        case SUBMIT_PRODUCT_OWNER_FORM_STARTED:
            return{
                ...state,
                resource: {
                    loading: true,
                    data: null,
                    error: null
                }

            }
        case SUBMIT_PRODUCT_OWNER_FORM_SUCCESS:
            return {
                ...state,
                resource: {
                    loading: false,
                    error: null,
                    data: action.payload.data

                }
            }
        case SUBMIT_PRODUCT_OWNER_FORM_FAILURE:
            return {
                ...state,
                resource: {
                    loading: false,
                    error: action.payload.error,
                    data: null

                }
            }
        case SUBMIT_RESOURCE_FORM_RESET:
            console.log("reset ....")
            return{
                ...state,
                resource: {
                    loading: false,
                    error: null,
                    data:null
                }
            }

        case SUBMIT_SCRUM_MASTER_FORM_STARTED:
            return{
                ...state,
                resource: {
                    loading: true,
                    data: null,
                    error: null
                }

            }
        case SUBMIT_SCRUM_MASTER_FORM_SUCCESS:
            return {
                ...state,
                resource: {
                    loading: false,
                    error: null,
                    data: action.payload.data

                }
            }
        case SUBMIT_SCRUM_MASTER_FORM_FAILURE:
            return {
                ...state,
                resource: {
                    loading: false,
                    error: action.payload.error,
                    data: null

                }
            }
        case FETCH_RESOURCES_SUCCESS:
            console.log("reducer" + JSON.stringify())
            return{
                ...state,
                resources: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_RESOURCES_FAILURE:
            return{
                ...state,
                resources: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case FETCH_RUNNING_SPRINT_SUCCESS:
            return{
                ...state,
                runningSprint: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_RUNNING_SPRINT_FAILURE:
            return{
                ...state,
                runningSprint: {
                    loading: false,
                    error: action.payload.error,
                    data: null
                }
            }

        case FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_SUCCESS:
            return{
                ...state,
                usNotDoneInRunningSprint: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case FETCH_NOT_DONE_US_IN_RUNNING_SPRINT_FAILURE:
            return{
                ...state,
                usNotDoneInRunningSprint: {
                    loading: false,
                    error: action.payload.error,
                    data: null
                }
            }

        case SUBMIT_TASK_CREATION_FORM_SUCCESS:
            return{
                ...state,
                taskCreationInfo: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case SUBMIT_TASK_CREATION_FORM_FAILURE:
            return{
                ...state,
                taskCreationInfo: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        case SUBMIT_TASK_CREATION_FORM_RESET:
            return {
                ...state,
                taskCreationInfo: {
                    loading: false,
                    error: null,
                    data: []
                }
            }

        case FETCH_ACCOUNTS_STARTED:
            return {
                ...state,
                accounts: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_ACCOUNTS_SUCCESS:
            return {
                ...state,
                accounts: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_ACCOUNTS_FAILURE:
            return {
                ...state,
                accounts: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case SUBMIT_CHANGE_PRIORITY_SUCCESS:
            return{
                ...state,
                changePriority: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case SUBMIT_CHANGE_PRIORITY_FAILURE:
            return{
                ...state,
                changePriority: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case SUBMIT_DECOMPOSE_US_SUCCESS:
            return{
                ...state,
                decomposeUS: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case SUBMIT_DECOMPOSE_US_FAILURE:
            return{
                ...state,
                decomposeUS: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        case SUBMIT_USCATEGORY_FORM_SUCCESS:
            return {
                ...state,
                userStoryCategory: {
                    error: null,
                    message: action.payload.data
                }
            }

        case SUBMIT_USCATEGORY_FORM_FAILURE:
            return {
                ...state,
                userStoryCategory: {
                    error: action.payload.error,
                    message: null
                }
            }

        case SUBMIT_CHANGE_STARTDATE_SUCCESS:
            return{
                ...state,
                setSprintStartDate: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }
        case SUBMIT_CHANGE_STARTDATE_FAILURE:
            return{
                ...state,
                setSprintStartDate: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }


    }
}

export default reducer;