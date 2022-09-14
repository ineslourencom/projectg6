import React, {useContext, useEffect} from "react";
import AppContext from "../../../context/AppContext";
import {
    fetchResources,
    fetchResourcesStarted,
} from "../../../context/Actions";
import {useMatch} from "react-router-dom";
import ActiveAccounts from "./ActiveAccounts";

function ActiveAccountsSearch(props) {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;
    const match = useMatch('/projects/:id/tasks/create');


    useEffect(() => {
        dispatch(fetchResourcesStarted());
        console.log("use effect")

        var url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "activeResources") {
                        url = row.links[i].href;
                    }
                }
            }
        });
        const request = {};

        fetchResources(url, request, dispatch);
    }, []);

    return (
        <ActiveAccounts state={state} handleChange={props.handleChange} activeAccount={props.activeAccount}/>)

}

export default ActiveAccountsSearch;