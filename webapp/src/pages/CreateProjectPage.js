import React, {useContext, useEffect} from 'react';
import AppContext from "../context/AppContext";
import {resetProjectCreation, submitProjCreationForm} from "../context/Actions";
import CreateProjectForm from "../components/Projects/CreateProjectForm";
import {useNavigate} from "react-router-dom";


function CreateProjectPage() {

    const {state, dispatch} = useContext(AppContext);
    const {createProject, projects} = state;
    const {error, message} = createProject;
    const navigate = useNavigate();

    const goToDisplayProject = () =>{
        navigate("/projects");
    }

    useEffect(() => {
        dispatch(resetProjectCreation())
    }, []);


    const submitForm = (formInfo) => {
        let url = null;
        for (let i = 0; i < projects.data[0].links.length; i++) {
            if (projects.data[0].links[i].rel == "createProject") {
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
        console.log(projects.data);
        console.log(url + "___ahahah____");
        console.log(request.body + "___ihihihih____");
        console.log(formInfo)
        submitProjCreationForm(url, request, dispatch);
    }


    if (error == null && message == null) {
        return (
            <div>
                <h1> Create New Project</h1>
                <CreateProjectForm dispatch={submitForm}/>
            </div>
        );
    } else if (error == null && message != null) {
       goToDisplayProject();
    } else if (error != null && message == null) {
        return (
            <div>
                <h1> Create New Project</h1>
                <p>Project not created! Please try again!</p>
                <CreateProjectForm dispatch={submitForm} />

                {/*<p> {error} </p>*/}
            </div>
        );
    }

}

export default CreateProjectPage;