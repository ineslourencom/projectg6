import React, {useEffect, useContext} from "react";
import {Link, useMatch, useNavigate} from "react-router-dom";
import {fetchTaskStatus, fetchTaskStatusStarted} from "../context/Actions";
import AppContext from "../context/AppContext";
import TaskStatusTable from "../components/Tasks/TasksStatus/TaskStatusTable";

function DisplayTasksPage() {
    const {state, dispatch} = useContext(AppContext);
    const {taskInfo, projects} = state;
    const {loading, data, error} = taskInfo;
    const match = useMatch('/projects/:id/tasks');
    const navigate = useNavigate();
    const goToCreateTask = () => {
        navigate("/projects/" + match.params.id + "/tasks/create");

    }

    console.log(taskInfo);

    const headers = {
        taskName: "Name",
        belongsTo: "Belongs to",
        containerId: "Code",
        status: "Status",
        percOfExec: "% of Execution"
    }

    let url = null;

    useEffect( ()=>{
        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "tasks") {
                        console.log(JSON.stringify(row.links[i].href));
                        url = row.links[i].href;
                    }
                }
            }
        });
        dispatch(fetchTaskStatusStarted());

        const request = {};

        fetchTaskStatus(url, request, dispatch);
    }, []);




    if (loading === true) {
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
                        <li><a><Link to={match}>Product Backlog</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                    </ul>
                </nav>
                <br/>
                <h1>Tasks</h1>
                <br/>
                <button type="submit" onClick={goToCreateTask}>Create New Task</button>
                <br/>
                <br/>
                <h2>Task Status</h2>
                <h1>Loading...</h1>
            </div>
                );
    } else {
        if (error !== null) {
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
                            <li><a><Link to={match}>Product Backlog</Link></a></li>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </ul>
                    </nav>
                    <br/>
                    <h1>Tasks</h1>
                    <br/>
                    <button type="submit" onClick={goToCreateTask}>Create New Task</button>
                    <br/>
                    <br/>
                    <h1>There are no tasks yet. Create One!</h1>
                </div>);
        } else {
            if (data.length !== 0) {
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
                                    <a href="javascript:void(0)" className="dropbtn">Sprints</a>
                                    <div className="dropdown-content">
                                        <a> <Link to={"/projects/" + match.params.id + "/sprints"}>All
                                            Sprints</Link></a>
                                        <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                                    </div>
                                </li>
                                <li><a><Link to={match}>Product Backlog</Link></a></li>
                                <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                            </ul>
                        </nav>
                        <br/>
                        <h1>Tasks</h1>
                        <br/>
                        <button type="submit" onClick={goToCreateTask}>Create New Task</button>
                        <br/>
                        <br/>
                        <h2>Task Status</h2>
                        <TaskStatusTable headers={headers} data={data}/>
                    </div>
                );

            }else {
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
                                        <a> <Link to={"/projects/" + match.params.id + "/sprints"}>All
                                            Sprints</Link></a>
                                        <a> <Link to={"/projects/" + match.params.id + "/scrumBoard"}>Scrum Board</Link></a>
                                    </div>
                                </li>
                                <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product
                                    Backlog</Link></a>
                                </li>
                                <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                            </ul>
                        </nav>

                        <br/>
                        <h1>Tasks</h1>
                        <br/>
                        <button type="submit" onClick={goToCreateTask}>Create New Task</button>
                        <br/>
                        <br/>
                        <h2>Task Status</h2>
                        <h2> There are no Tasks! Create One! </h2>
                    </div>);
            }
        }
    }

}

export default DisplayTasksPage;