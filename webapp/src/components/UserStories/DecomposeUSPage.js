import React, {useContext} from "react";
import {Link, useMatch} from "react-router-dom";

import AppContext from "../../context/AppContext";
import CreateNewChildUserStoryForm from "../Projects/ProductBacklog/CrateNewChildUserStoryForm";
import {submitDecomposeUS} from "../../context/Actions";


function DecomposeUSPage() {
    const match = useMatch('/projects/:id/userStories/decompose');
    const { state, dispatch } = useContext(AppContext);
    const {productBacklog, projects} = state;

    const submitForm = (childs) => {
        let url = null;
        for (let i = 0; i < projects.data[0].links.length; i++) {
            if (projects.data[0].links[i].rel == "createProject") {
                url = projects.data[0].links[i].href;
            }
        }
        const request = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(childs)
        }
        console.log(projects.data);
        console.log(url + "___ahahah____");
        console.log(request.body + "___ihihihih____");
        submitDecomposeUS(url, request, dispatch);

    }

    return (<div>
        <nav className={"second"}>
            <ul>
                <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a></li>
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
        <h1>Decompose User Story</h1>
        <CreateNewChildUserStoryForm dispatch={submitForm} projectid={match.params.id} productBacklog={productBacklog}/>


    </div>)

}

export default DecomposeUSPage;