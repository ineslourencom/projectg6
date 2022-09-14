import React, {useContext} from "react";
import AppContext from "../context/AppContext";
import {submitProjTypologyCreationForm} from "../context/Actions";
import CreateProjectTypologyForm from "../components/Typologies/CreateProjectTypologyForm";
import {Link, useMatch} from "react-router-dom";

function CreateProjectTypologyPage() {

    const { state, dispatch } = useContext(AppContext);
    const { data, projects } = state;
    const match = useMatch('/projects/:id/projectTypologies');


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
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                        <li><a><Link to={match}>Typologies</Link></a></li>
                        <li className="dropdown">
                            <a  href="javascript:void(0)"className="dropbtn">Resources</a>
                            <div className="dropdown-content">
                                <a> <Link to={"/projects/" + match.params.id + "/resources"} >View Resources</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/resources/create"}>Allocate Resource</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/resources/productOwner"}>Appoint Product Owner</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/resources/scrumMaster"}>Appoint Scrum Master</Link></a>

                            </div>
                        </li>
                        <li className="dropdown">
                            <a  href="javascript:void(0)"className="dropbtn">Sprints</a>
                            <div className="dropdown-content">
                                <a> <Link to={"/projects/" + match.params.id+ "/sprints"}>All Sprints</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                            </div>
                        </li>
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>
                    </ul>
                </nav>
                <h1> Create New Project Typology </h1>
                <CreateProjectTypologyForm dispatch={submitForm}/>
            </div>
        );
    } else {
        return (<h1>No data ....</h1>);
    }

}

export default CreateProjectTypologyPage;