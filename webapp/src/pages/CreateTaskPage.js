import React, {useContext, useEffect, useState} from "react";
import {Link, useMatch, useNavigate} from "react-router-dom";
import CreateTaskForm from "../components/Tasks/CreateTaskForm";
import {
    fetchRunningSprint, resetTaskCreation,
    submitTaskCreate
} from "../context/Actions";
import AppContext from "../context/AppContext";

function CreateTaskPage() {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;
    const navigate = useNavigate();
    const match = useMatch('/projects/:id/tasks/create');
    const [info, setInfo] = useState("");

    const goToDisplayTasks = () => {
        navigate("/projects/" + match.params.id + "/tasks");
    }


    const handleChange = (event) => {
        event.preventDefault();
        const {name, value} = event.target
        const newState = {...info, [name]: value}

        setInfo(newState);
    }

    useEffect(() => {
        console.log("use effect")

        dispatch(resetTaskCreation())

        let urlOne = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel === "runningSprint") {
                        console.log(JSON.stringify(row.links[i].href));
                        urlOne = row.links[i].href;
                    }
                }
            }
        });
        const requestOne = {};
        console.log(urlOne + "___ahahah____");
        fetchRunningSprint(urlOne, requestOne, dispatch);
    }, []);


    const submitForm = (formInfo) => {
        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "createTask") {
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
            body: JSON.stringify(formInfo)
        }
        console.log(projects.data);
        console.log(url + "___ahahah____");
        console.log(request.body + "___ihihihih____");
        console.log(formInfo)
        submitTaskCreate(url, request, dispatch);
        console.log("estouAqui")
    }

    let {
        option,
        containerID
    } = info;


    if (state.runningSprint.error !== null) {
        return (
            <div>
                <h3>In this project, there is no running sprint.</h3>
                <h3>You can only create tasks for the running sprint or it's
                    user stories.</h3>
            </div>)
    }
    if (state.taskCreationInfo.error === null && state.taskCreationInfo.data.length !== 0) {
        goToDisplayTasks();
    } else if (state.taskCreationInfo.error !== null) {
        return (
            <div>
                <nav className={"second"}>
                    <ul>
                        <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                            nº {match.params.id}</Link></a>
                        </li>
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
                                    Scrum
                                    Master</Link></a>
                            </div>
                        </li>
                        <li className="dropdown">
                            <a href="javascript:void(0)" className="dropbtn">Sprints</a>
                            <div className="dropdown-content">
                                <a> <Link to={"/projects/" + match.params.id + "/sprints"}>All Sprints</Link></a>
                                <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                            </div>
                        </li>
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                        </li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                    </ul>
                </nav>
                <h4> In this project, the running sprint is sprint number {state.runningSprint.data.sprintNumber},
                    that
                    started in {state.runningSprint.data.startDate}.</h4>
                <br/>
                <h3>Your was task was not created due to an error in you inputs. Please try again.</h3>
                <br/>
                <CreateTaskForm option={option} dispatch={submitForm} containerID={containerID}/>

            </div>
        )

    } else {
        if (info === "") {
            return (
                <div>
                    <nav className={"second"}>
                        <ul>
                            <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                                nº {match.params.id}</Link></a>
                            </li>
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
                                        Scrum
                                        Master</Link></a>
                                </div>
                            </li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Sprints</a>
                                <div className="dropdown-content">
                                    <a> <Link to={"/projects/" + match.params.id + "/sprints"}>All Sprints</Link></a>
                                    <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                                </div>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </ul>
                    </nav>
                    <h4>You can only create a task in the context of the current sprint of the selected project.</h4>
                    <h4>
                        In this project, the running sprint is sprint number {state.runningSprint.data.sprintNumber},
                        that started in {state.runningSprint.data.startDate}.</h4>
                    <br/>
                    <h3>
                        Do you want to create a Task related to:
                    </h3>


                    <select name={"option"} value={option} onChange={handleChange}>
                        <option value=""/>
                        <option value={"Sprint"}>Sprint</option>
                        <option value={"User Story"}> User Story</option>
                    </select>


                </div>
            )
        } else if (option === "Sprint") {

            containerID = state.runningSprint.data.sprintID;

            return (<div>
                    <nav className={"second"}>
                        <ul>
                            <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                                nº {match.params.id}</Link></a>
                            </li>
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
                                        Scrum
                                        Master</Link></a>
                                </div>
                            </li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Sprints</a>
                                <div className="dropdown-content">
                                    <a> <Link to={"/projects/" + match.params.id + "/sprints"}>All Sprints</Link></a>
                                    <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                                </div>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </ul>
                    </nav>

                    <CreateTaskForm option={option} dispatch={submitForm} containerID={containerID}/>

                </div>
            )

        } else if (option === "User Story") {
            containerID = null;
            return (<div>
                    <nav className={"second"}>
                        <ul>
                            <li><a><Link to={"/projects/" + match.params.id}>Project Details ID
                                nº {match.params.id}</Link></a>
                            </li>
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
                                        Scrum
                                        Master</Link></a>

                                </div>
                            </li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Sprints</a>
                                <div className="dropdown-content">
                                    <a> <Link to={"/projects/" + match.params.id + "/sprints"}>All Sprints</Link></a>
                                    <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                                </div>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a>
                            </li>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </ul>
                    </nav>

                    <CreateTaskForm option={option} dispatch={submitForm} containerID={containerID}/>
                </div>
            )
        }
    }
}

export default CreateTaskPage;