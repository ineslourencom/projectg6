import React, {useContext, useEffect} from "react";
import AppContext from "../context/AppContext";
import {Link, useMatch, useNavigate} from "react-router-dom";
import {
    fetchAllSprints,
    fetchAllSprintsStarted, fetchProjectInfo, fetchProjectInfoStarted, submitChangeStartDate,
    submitCreateSprintForm
} from "../context/Actions";
import TableSprint from "../components/Sprints/TableSprint";
import CreateSprintForm from "../components/Sprints/CreateSprintForm";



function CreateSprintPage() {
    const {state, dispatch} = useContext(AppContext);
    const {projects, sprints, sprint, projectDetails, setSprintStartDate} = state;
    const {logIn} = state;
    const { data, error } = sprint;
    const {email, profile} = logIn;
    const navigate = useNavigate();
    const match = useMatch('/projects/:id/sprints');
    let message = "";

    useEffect(() => {
        dispatch(fetchAllSprintsStarted(), fetchProjectInfoStarted(match.params.id));
        var url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "allSprints") {
                        url = row.links[i].href;
                    }
                }
            }
        });
        const request = {};

        fetchAllSprints(url, request, dispatch);

        var url2 = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "self") {
                        url2 = row.links[i].href;
                    }
                }
            }
        });

        const request2 = {};
        fetchProjectInfo(url2, request2, dispatch);


        console.log(JSON.stringify(sprint) + "%%%%666%%%%");
        console.log(JSON.stringify(data) + "%%%%555%%%%");
        console.log(JSON.stringify(error) + "%%%%444%%%%");
        console.log(JSON.stringify(message) + "%%%%333%%%%");

    }, [sprint, setSprintStartDate]);

    const submitForm = (formInfo) => {
        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "allSprints") {
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
            body: JSON.stringify(formInfo).slice(12, JSON.stringify(formInfo).length - 1)
        }

        submitCreateSprintForm(url, request, dispatch);

    }
    const submitnewStartDate = (formInfo, sprintNumber) =>{
        let url= "http://localhost:8080/projects/"+ match.params.id +"/sprints/"+ sprintNumber;

        const request = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo)
        }

            //console.log(url + "___ahahah____");
            //console.log(request.body + "___ihihihih____");
            //console.log(formInfo)

        submitChangeStartDate(url, request, dispatch);
    }






    const headers = {
        sprintNumber: "Sprint Number",
        startDate: "Start Date",
        endDate: "End Date",
        setNewStartDate: "Set New Start Date"
    };


    if (sprints.loading === true) {
        if (error != null){message = "Sprints must be sequential. Please choose a date after the end of the last sprint.";}
        return (
            <div>
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a></li>
                        <li className="dropdown">
                            <a href="javascript:void(0)" className="dropbtn">Resources</a>
                            <div className="dropdown-content">
                                <a> <Link to={"/projects/" + match.params.id + "/resources"}>View Resources</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/resources/create"}>Allocate
                                    Resource</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/resources/productOwner"}>Appoint
                                    Product Owner</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/resources/scrumMaster"}>Appoint Scrum
                                    Master</Link></a>

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
                <h1> Sprints </h1>
                <CreateSprintForm dispatch={submitForm} projectid={match.params.id} sprintDuration={projectDetails.data.sprintDuration}/>
                <br/>
                <h3> {message} </h3>
                <br/>
                <h2>Loading ....</h2>
            </div>
        );
    } else {
        if (sprints.error !== null) {
            if (error != null){message = "Sprints must be sequential. Please choose a date after the end of the last sprint.";}
            return (
                <div>
                    <nav className={"second"}>
                        <ul>
                            <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                                nº {match.params.id}</Link></a></li>
                            <li><a><Link
                                to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a></li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Resources</a>
                                <div className="dropdown-content">
                                    <a> <Link to={"/projects/" + match.params.id + "/resources"}>View Resources</Link></a>
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
                            <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </ul>
                    </nav>
                    <h1> Sprints </h1>
                    <CreateSprintForm dispatch={submitForm} projectid={match.params.id} sprintDuration={projectDetails.data.sprintDuration}/>
                    <br/>
                    <h3> {message} </h3>
                    <br/>
                    <h2>Error ....</h2>
                </div>
            );
        } else {
            if (sprints.data.length !== 0) {
                if (error != null){message = "Sprints must be sequential. Please choose a date after the end of the last sprint.";}
                return (
                    <div>
                        <nav className={"second"}>
                            <ul>
                                <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                                    nº {match.params.id}</Link></a></li>
                                <li><a><Link
                                    to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                                </li>
                                <li className="dropdown">
                                    <a href="javascript:void(0)" className="dropbtn">Resources</a>
                                    <div className="dropdown-content">
                                        <a> <Link to={"/projects/" + match.params.id + "/resources"}>View Resources</Link></a>
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
                                <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a></li>
                                <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                            </ul>
                        </nav>
                        <h1> Sprints </h1>
                        <CreateSprintForm dispatch={submitForm} projectid={match.params.id} sprintDuration={projectDetails.data.sprintDuration}/>
                        <br/>
                        <h3> {message} </h3>
                        <br/>
                        <TableSprint headers={headers} data={sprints.data} dispatch={submitnewStartDate}/>
                    </div>
                );
            } else if (sprints.data.length === 0) {
                if (error != null){message = "Sprints must be sequential. Please choose a date after the end of the last sprint.";}
                return (
                    <div>
                        <nav className={"second"}>
                            <ul>
                                <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                                    nº {match.params.id}</Link></a></li>
                                <li><a><Link
                                    to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                                </li>
                                <li className="dropdown">
                                    <a href="javascript:void(0)" className="dropbtn">Resources</a>
                                    <div className="dropdown-content">
                                        <a> <Link to={"/projects/" + match.params.id + "/resources"}>View Resources</Link></a>
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
                                <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a></li>
                                <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                            </ul>
                        </nav>
                        <h1> Sprints </h1>
                        <CreateSprintForm dispatch={submitForm} projectid={match.params.id} sprintDuration={projectDetails.data.sprintDuration}/>
                        <br/>
                        <h3> {message} </h3>
                        <br/>
                        <h2>There are no sprints in this project yet.</h2>
                    </div>
                );

            }
        }
    }}
export default CreateSprintPage;