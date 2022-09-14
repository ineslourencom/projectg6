import React, {useContext, useEffect} from 'react';
import {Link, useMatch} from "react-router-dom";
import {fetchProductBacklogStarted, fetchUSStatus, fetchUSStatusStarted} from "../context/Actions";
import AppContext from "../context/AppContext";
import USStatusTable from "../components/Projects/ProductBacklog/UserStoriesStatus/USStatusTable";
import {useNavigate} from "react-router-dom";


function DisplayUserStoriesStatusPage() {
    const {state, dispatch} = useContext(AppContext);
    const {usInfo, logIn, projects} = state;
    const {loading, error, data} = usInfo;
    const {loggedIn, email, profile} = logIn;
    const match = useMatch('/projects/:id/userStoriesStatus');
    const navigate = useNavigate();

    const goToProduckBacklog = () =>{
        dispatch(fetchProductBacklogStarted(match.params.id));
        navigate("/projects/" + match.params.id + "/productBacklog");

    }


    const headers = {
        storyNumber: "User Story Number",
        statement: "Title",
        detail: "Description",
        priority: "Priority",
        usStatus: "Status"
    };


    useEffect(() => {
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

        dispatch(fetchUSStatusStarted());
        const request = {};
        fetchUSStatus(url, request, dispatch)
    },[]);


    if (loading === true) {
        return (
            <div>

            <nav className={"second"}>
                <ul>
                    <li><a><Link to={"/projects/" + match.params.id}>Project Details</Link></a></li>
                    <li><a><Link to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                    </li>
                    <li className="dropdown">
                        <a  href="javascript:void(0)"className="dropbtn">Resources</a>
                        <div className="dropdown-content">
                            <a> <Link to={"/projects/" + match.params.id + "/resources"}>View Resources</Link></a>
                            <a> <Link to={"/projects/" + match.params.id + "/resources/create"}>Allocate Resource</Link></a>
                            <a> <Link to={"/projects/" + match.params.id + "/resources/productOwner"}>Appoint Product
                                Owner</Link></a>
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
                    <li><a><Link to={match}>Product Backlog</Link></a></li>
                    <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                </ul>
            </nav>
            <h1>Loading ....</h1>
        </div>);
    } else {
        if (error !== null) {
            return (
                <div>

                    <nav className={"second"}>
                        <ul>
                            <li><a><Link to={"/projects/" + match.params.id}>Project Details</Link></a></li>
                            <li><a><Link
                                to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                            </li>
                            <li className="dropdown">
                                <a  href="javascript:void(0)"className="dropbtn">Resources</a>
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
                            <li><a><Link to={match}>Product Backlog</Link></a></li>
                            <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                        </ul>
                    </nav>
                    <h1>Error ....</h1>
                </div>
            );
        } else {
            if (data !== null) {
                return (
                    <div>

                        <nav className={"second"}>
                            <ul>
                                <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                                <li><a><Link
                                    to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                                </li>
                                <li className="dropdown">
                                    <a  href="javascript:void(0)"className="dropbtn">Resources</a>
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
                                <li><a><Link to={match}>Product Backlog</Link></a></li>
                                <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                            </ul>
                        </nav>
                        <div>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <USStatusTable headers={headers} data={data}/>
                            <br/>
                            <br/>
                            <button type="submit" value="Go Back" onClick={goToProduckBacklog}>Go Back</button>
                            <br/>
                        </div>
                    </div>
                );
            } else {
                return (
                    <div>

                        <nav className={"second"}>
                            <ul>
                                <li><a><Link to={"/projects/" + match.params.id}>Project Details ID nº { match.params.id}</Link></a></li>
                                <li><a><Link
                                    to={"/projects/" + match.params.id + "/projectTypologies"}>Typologies</Link></a>
                                </li>
                                <li className="dropdown">
                                    <a  href="javascript:void(0)"className="dropbtn">Resources</a>
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
                                <li><a><Link to={match}>Product Backlog</Link></a></li>
                                <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>

                            </ul>
                        </nav>
                        <h1>No data ....</h1>
                    </div>
                );
            }
        }
    }
}

export default DisplayUserStoriesStatusPage



