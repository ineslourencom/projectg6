import React, {useContext, useEffect, useState} from 'react';
import {Link, useMatch, useNavigate} from 'react-router-dom';
import {
    fetchProjectInfo,
    fetchProjectInfoStarted,
    submitProjectEditionFormStarted,
    submitProjectEditionForm,
} from '../context/Actions';
import AppContext from '../context/AppContext';
import ProjectDetailsForm from '../components/Projects/ProjectDetailsForm'
import ProjectEditForm from "../components/Projects/ProjectEditForm";
import '../style/navigation.css';


function ProjectDetailsPage() {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;
    const {projectDetails} = state;
    const {data} = projects;
    const match = useMatch('/projects/:id');
    const navigate = useNavigate();


    const [mode, setMode] = useState({
        pageMode: "read"
    })

    useEffect(() => {
        dispatch(fetchProjectInfoStarted(match.params.id));
        var url = null;
        data.map((row, index) => {
            console.log(JSON.stringify(row.links) + "__________EER_______")
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "self") {
                        url = row.links[i].href;
                    }
                }
            }
        });

        const request = {};
        fetchProjectInfo(url, request, dispatch);
    }, [mode]);


    const submitForm = (formInfo) => {
        dispatch(submitProjectEditionFormStarted(formInfo.code));
        var url = null;
        data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "self") {
                        url = row.links[i].href;
                    }
                }
            }
        });

        const request = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo),
        }
        submitProjectEditionForm(url, request, dispatch);
        setMode({
            pageMode: "read"
        });
        console.log(JSON.stringify(mode.pageMode));
    }

    if (projectDetails.loading === true) {
        return (<h1>Loading ....</h1>);
    } else {
        if (projectDetails.error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            if (projectDetails.data !== null) {
                console.log(JSON.stringify(projectDetails.data));
                const {data} = projectDetails;
                if (mode.pageMode === "read") {
                    return (
                        <div>
                            <nav className={"second"}>
                                <ul>
                                    <li><a><Link to={match}>Project Details ID nº { match.params.id}</Link></a></li>
                                    <li><a><Link
                                        to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
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
                                    <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product
                                        Backlog</Link></a></li>
                                    <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>
                                </ul>
                            </nav>
                            <ProjectDetailsForm data={data}/>
                            <button type="submit" value="Edit details" onClick={() => setMode({
                                pageMode: "edit"
                            })}> Edit Details
                            </button>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                    );
                } else {
                    return (
                        <div>
                            <nav className={"second"}>
                                <ul>
                                    <li><a><Link to={match}>Project Details ID nº { match.params.id}  </Link></a></li>
                                    <li><a><Link
                                        to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                                    </li>
                                    <li className="dropdown">
                                        <a> <Link to={"/projects/" + match.params.id + "/resources"}
                                                  href="javascript:void(0)" className="dropbtn">Resources</Link></a>
                                        <div className="dropdown-content">
                                            <a> <Link to={"/projects/" + match.params.id + "/resources"} >View Resources</Link></a>
                                            <a> <Link to={"/projects/" + match.params.id + "/resources/create"}>Allocate
                                                Resource</Link></a>
                                            <a> <Link to={"/projects/" + match.params.id + "/resources/productOwner"}>Appoint
                                                Product Owner</Link></a>
                                            <a> <Link to={"/projects/" + match.params.id + "/resources/scrumMaster"}>Appoint
                                                Scrum Master</Link></a>

                                        </div>
                                    </li>
                                    <li className="dropdown">
                                        <a  href="javascript:void(0)"className="dropbtn">Sprints</a>
                                        <div className="dropdown-content">
                                            <a> <Link to={"/projects/" + match.params.id+ "/sprints"}>All Sprints</Link></a>
                                            <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                                        </div>
                                    </li>
                                    <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product
                                        Backlog</Link></a></li>
                                    <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                                </ul>
                            </nav>
                            <ProjectEditForm data={projectDetails.data} dispatch={submitForm}/>
                        </div>
                    );
                }

            } else {
                return (<h1>No data ....</h1>);
            }
        }
    }
}

export default ProjectDetailsPage;