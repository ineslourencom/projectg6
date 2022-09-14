import React, {useContext, useEffect, useState} from 'react';
import AppContext from '../context/AppContext';
import {fetchResources, fetchResourcesStarted} from "../context/Actions";
import {Link, useMatch, useNavigate} from "react-router-dom";
import TableResource from "../components/Resources/TableResource";



function ResourcesPage() {
    const {state, dispatch} = useContext(AppContext);
    const {projects, resources} = state;
    const {loading, error, data} = resources;
    const {logIn} = state;
    const {email, profile} = logIn;
    const navigate = useNavigate();
    const match = useMatch('/projects/:id/resources');
    const [project, setProject]=useState(null);




    useEffect(()=>{
        if (project){
            FetchActive();
        }
    },[project])

    useEffect(() => {
        dispatch(fetchResourcesStarted());
        console.log("use effect")

        projects.data.map((row, index) => {
            if(row.code == (match.params.id)) {
                setProject(row);

            }
        });

           }, []);

    function FetchAll() {
        let urlAll = null;
        for (let i = 0; i < project.links.length; i++) {
            if (project.links[i].rel == "allResources") {
                urlAll = project.links[i].href;
            }
        }
        const request = {};
        fetchResources(urlAll, request, dispatch);

    }

    function FetchActive() {
        let url = null;
        for (let i = 0; i < project.links.length; i++) {
            if (project.links[i].rel == "activeResources") {
                url = project.links[i].href;
            }
        }
        const request = {};
        fetchResources(url, request, dispatch);


    }

    const headers = { associatedAccount:"AssociatedAccount",
        resourceID:"ResourceID",
        associatedRole:"AssociatedRole",
        startDate :"Start Date",
        endDate: "End Date",
        percentageOfAllocation:"Percentage of Allocation",
        projectID:"ProjectID",
        costPerHourValue:"Cost Per Hour Value",
        currency: "Currency"} ;


    if (loading === true) {
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
                <h1> Resources</h1>
            <h1>Loading ....</h1>
            </div>
        );
    } else {
        if (error !== null) {
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
                    <h1> Resources</h1>
                <h1>Error ....</h1>
                </div>
            );
        } else {
            if (data.length !== 0) {
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
                <h1> Resources</h1>
                            <input type='button' id = "active"  onClick= {FetchActive} value="active resources"/>
                            <input type='button' id = "all"  onClick={FetchAll}  value="all resources" />

                            <TableResource headers={headers} data={resources.data}/>
                        </div>
                    );

            } else {
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
                        <h1> Resources</h1>
                        <h3>No resources are active in this project at the moment.</h3>
                    </div>);
            }
        }
    }
}
export default ResourcesPage;