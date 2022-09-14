import React, {useContext, useEffect} from 'react';
import AppContext from '../context/AppContext';
import {resetResourceCreation, submitScrumMasterForm, submitScrumMasterFormStarted} from "../context/Actions";

import {Link, useMatch} from "react-router-dom";
import DefineScrumMasterForm from "../components/Resources/DefineScrumMasterForm";

function DefineScrumMasterPage() {

    const {state, dispatch} = useContext(AppContext);
    const {resource, projects} = state;
    const {loading, error, data} = resource;
    const match = useMatch('/projects/:id/resources/scrumMaster');

    useEffect(() => {
        dispatch(resetResourceCreation());
    },[]);

    const submitForm = (formInfo) => {
        dispatch(submitScrumMasterFormStarted)
        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "scrumMaster") {
                        url = row.links[i].href;
                    }
                }
            }
        })

        const request = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo)
        }
        submitScrumMasterForm(url, request, dispatch);

    }
    if (loading) {
        return (
            <div>
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                        </li>
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
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </li>
                    </ul>
                </nav>
                <h1> Define Scrum Master</h1>
                <h1> Loading</h1>
            </div>
        );

    }

    else if (error == null && data == null ) {

        return (
            <div>
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                        </li>
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
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>

                        </li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                    </ul>
                </nav>
                <h1> Define Scrum Master</h1>
                <DefineScrumMasterForm projectid={match.params.id} submit={submitForm}/>
            </div>
        );

    }
    else  if (error !== null) {

        return (
            <div>
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                        </li>
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
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                        </li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                    </ul>
                </nav>
                <p> An error ocurred:</p>
                <p> {error} </p>
                <h1> Define Scrum Master</h1>
                <DefineScrumMasterForm projectid={match.params.id} submit={submitForm}/>

            </div>
        );

    }

   else if (data !== null) {

        return (
            <div>
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                        </li>
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
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                        </li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                    </ul>
                </nav>
                <p>Resource created</p>
                <h1> Define Scrum Master</h1>
                <DefineScrumMasterForm projectid={match.params.id} submit={submitForm}/>

            </div>
        );

    }else {
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
            <h1>No data ....</h1>
            </div>
        );

    }

}
export default DefineScrumMasterPage;