import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import {Provider} from './AppContext';
import reducer from './Reducer';

const initialState = {


    selectedProject: {
        projectid: 0
    },

    projects: {
        loading: true,
        error: null,
        data: null,
    },

    profiles: {
        loading: true,
        error: null,
        data: null,
    },

    projectDetails: {
        projectid: 0,
        loading: false,
        error: null,
        data: null,
    },

    projectTypologies: {
        loading: false,
        error: null,
        data: [],
    },

    profileInfo: {
        loading: false,
        error: "",
        success: "",
        data: [],
    },

    userStoryDetails: {
        error: null,
        message: null
    },

    editProject: {
        loaded: false
    },

    resource: {
        loading: false,
        error: null,
        data: []
    },

    resources: {
        loading: false,
        error: null,
        data: []
    },

    sprints: {
        loading: false,
        error: null,
        data: []
    },

    sprint: {
        error: null,
        data: []
    },

    sprintBacklogItem: {
        error: null,
        data: []
    },

    selectedAccount: {
        accountid: 0
    },

    accounts: {
        loading: false,
        error: null,
        data: [],
    },

    account: {
        accountID: "",
        loading: false,
        error: null,
        data: [],
    },

    searchedAccount: {
        accountID: "",
        loading: false,
        error: null,
        data: [],
    },

    logIn: {
        loggedIn: false,
        email: "",
        profile: ""
    },

    productBacklog: {
        loading: false,
        error: null,
        data: [],
    },

    scrumBoard: {
        loading: false,
        error: null,
        data: [],
    },

    customer: {
        loading: false,
        error: null,
        data: [],
    },

    businessSector: {
        loading: false,
        error: null,
        data: [],
    },

    createProject: {
        error: null,
        message: null
    },

    profilePage: {
        create: false,
        update: false
    },

    accountPage: {
        search: false,
        viewOwn: false
    },

    usInfo: {
        loading: true,
        error: null,
        data: []
    },

    runningSprint: {
        loading: false,
        error: null,
        data: []
    },

    taskInfo: {
        loading: true,
        error: null,
        data: []
    },

    usNotDoneInRunningSprint: {
        loading: false,
        error: null,
        data: []
    },

    taskCreationInfo: {
        loading: false,
        error: null,
        data: []
    },

    changePriority: {
        error: null,
        data: []
    },
    decomposeUS: {
        error: null,
        data: []
    },
    userStoryCategory: {
        error: null,
        message: null
    },

    setSprintStartDate: {
        error: null,
        data: []
    }


};
const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            state,
            dispatch
        }}>
            {props.children}
        </Provider>
    );
};
AppProvider.propTypes = {
    children: PropTypes.node,
};


export default AppProvider;