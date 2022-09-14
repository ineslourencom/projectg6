import React, { useContext, useEffect } from 'react';
import AppContext from '../context/AppContext';
import {
    resetUSCreation,
    submitUSCreationForm
} from "../context/Actions";
import CreateUSForm from "../components/Projects/ProductBacklog/CreateUserStoryForm";
import {Link, useMatch} from "react-router-dom";

function CreateUSPage() {

    const { state, dispatch } = useContext(AppContext);
    const { userStoryDetails, projectDetails } = state;
    const {projectid} = projectDetails
    const { projects } = state;
    const { error, message } = userStoryDetails;
    const match = useMatch('/projects/:id/userStories');

    useEffect(() => {
        dispatch(resetUSCreation());
    }, []);

    const submitForm = (formInfo) => {
        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "userStories") {
                        console.log(JSON.stringify(row.links[i].href));
                        url = row.links[i].href;
                    }
                }
            }
        });
        const request = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo).slice(12, JSON.stringify(formInfo).length-1)
        }

        submitUSCreationForm(url, request, dispatch);
    }

        if(error == null && message == null){
            return (
                <div>
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
                    <h1> Create New User Story</h1>
                    <CreateUSForm dispatch={submitForm} projectid={projectDetails.projectid}/>
                </div>
            );
        }
        else if (error == null && message != null) {
            console.log("test___" + JSON.stringify(error));
            console.log(JSON.stringify(message));
            return (
                <div>
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
                    <h1> Create New User Story</h1>
                    <CreateUSForm dispatch={submitForm} projectid={projectDetails.projectid}/>
                    <p> User Story was successfully created. Check the product backlog.</p>

                </div>
            );
        }
        else if (error != null && message == null){
            console.log("test___" + JSON.stringify(error));
            console.log(JSON.stringify(message));
            return (
                <div>
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
                    <h1> Create New User Story</h1>
                    <CreateUSForm dispatch={submitForm} projectid={projectDetails.projectid}/>
                    <p> User Story not created:</p>
                    <p> {error} </p>
                </div>
            );
        }

}

export default CreateUSPage;
