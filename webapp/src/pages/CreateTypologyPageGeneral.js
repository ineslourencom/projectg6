import React, {useContext} from "react";
import AppContext from "../context/AppContext";
import {submitProjTypologyCreationForm} from "../context/Actions";
import CreateProjectTypologyForm from "../components/Typologies/CreateProjectTypologyForm";
import {Link, useMatch} from "react-router-dom";

function CreateTypologyPageGeneral() {

    const { state, dispatch } = useContext(AppContext);
    const { data, projects } = state;
    const match = useMatch('/projects/projectTypologies');


    const submitForm = (formInfo) => {
        let url = null;
        for (let i = 0; i < projects.data[0].links.length; i++) {
            if (projects.data[0].links[i].rel == "createTypology") {
                url = projects.data[0].links[i].href;
            }
        }

        const request = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo)
        }
        console.log(url);
        console.log(request.body);
        console.log(formInfo)
        submitProjTypologyCreationForm(url, request, dispatch); //actions
    }


    if (data !== null) {
        return (
            <div>
                <h1> Create New Project Typology </h1>
                <CreateProjectTypologyForm dispatch={submitForm}/>
            </div>
        );
    } else {
        return (<h1>No data ....</h1>);
    }

}

export default CreateTypologyPageGeneral;