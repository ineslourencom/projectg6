import React, {useContext, useEffect} from "react";
import AppContext from "../../../context/AppContext";
import {useMatch} from "react-router-dom";
import {fetchNotDoneUSInRunningSprint} from "../../../context/Actions";
import ActiveAccounts from "../ActiveAccounts/ActiveAccounts";
import USNotDoneInSprint from "./USNotDoneInSprint";

function USNotDoneInSprintSearch(props) {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;
    const match = useMatch('/projects/:id/tasks/create');


    useEffect(() => {
        console.log("use effect")

        var url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "notDoneUS") {
                        url = row.links[i].href;
                    }
                }
            }
        });
        const request = {};

        fetchNotDoneUSInRunningSprint(url, request, dispatch);
    }, []);

    return (
        <USNotDoneInSprint state={state} handleChange={props.handleChange} containerID={props.containerID}/>)

}

export default USNotDoneInSprintSearch;