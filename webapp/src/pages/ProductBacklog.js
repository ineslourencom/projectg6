import React, {useContext, useEffect, useState} from 'react';
import AppContext from '../context/AppContext';
import {
    fetchProductBacklog,
    fetchProductBacklogStarted,
    fetchProjectInfoStarted,
    fetchUSStatusStarted, submitChangePriority, submitSPRINTBACKLOGITEMCreationForm, submitUSCreationForm
} from "../context/Actions";
import {Link, useMatch, useNavigate} from "react-router-dom";
import ProductBacklogTable from "../components/Projects/ProductBacklog/ProductBacklogTable";
import AddUSToSprintBacklog from "../components/Projects/ProductBacklog/AddUSToSprintBacklog";

function ProductBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {productBacklog, logIn, projects, sprintBacklogItem, changePriority} = state;
    const {loading, error, data} = productBacklog;
    const {loggedIn, email, profile} = logIn;
    const match = useMatch('/projects/:id/productBacklog');
    const navigate = useNavigate();
    let message = "";

    const goToCreateUS = () => {
        dispatch(fetchProjectInfoStarted(match.params.id));
        navigate("/projects/" + match.params.id + "/userStories");
    }

    const goToShowUSStatus = () => {
        dispatch(fetchUSStatusStarted(match.params.id));
        navigate("/projects/" + match.params.id + "/userStoriesStatus");
    }

    const goToDecomposeUS = () => {
        navigate("/projects/" + match.params.id + "/userStories/decompose");
    }


    const headers = {
        storyNumber: "User Story Number",
        statement: "Statement",
        detail: "Detail",
        priority: "Priority",
        changePriority : "Change Priority",
        decomposeUS : "Decompose User Story"
    };

    useEffect(() => {
        dispatch(fetchProductBacklogStarted(match.params.id));
        console.log(email);

        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "productBacklog") {
                        console.log(JSON.stringify(row.links[i].href));
                        url = row.links[i].href;
                    }
                }
            }
        });
        const request = {};
        fetchProductBacklog(url, request, dispatch);
        // eslint-disable-next-line

        message = "";

    }, [sprintBacklogItem, changePriority]);


    const submitChangeOfPriority = (formInfo, usID) =>{
        let url = null;
        productBacklog.data.map((row, index) => {
            if (row.usID === (usID)){
                console.log("estou aqui")
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "changePriority") {
                        url = row.links[i].href;
                    }
                }            }
        })
        const request = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo)
        }
        console.log(productBacklog.data);
        console.log(url + "___ahahah____");
        console.log(request.body + "___ihihihih____");
        console.log(formInfo)
        submitChangePriority(url, request, dispatch)
    }


    const submitForm = (formInfo) => {
        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "sprintBacklogItems") {
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
        console.log(JSON.stringify(formInfo).slice(12, JSON.stringify(formInfo).length-1) + "____check here ____")
        submitSPRINTBACKLOGITEMCreationForm(url, request, dispatch);
    }


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
                <button type="submit" value="Show All Activities" onClick={goToShowUSStatus}> Show All UserStory Status</button>
                <button type="submit" value="Create User Story" onClick={goToCreateUS} >Create New User Story</button>
                <br/>
                <br/>
                <h1>Product Backlog</h1>
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
                    <button type="submit" value="Show All Activities" onClick={goToShowUSStatus}> Show All UserStory Status</button>
                    <button type="submit" value="Create User Story" onClick={goToCreateUS} >Create New User Story</button>
                    <br/>
                    <br/>
                    <h1>Product Backlog</h1>
                    <h2>Ooops! The product backlog is empty. Try and create a new User Story.  </h2>
                </div>
                );
        } else {
            if (data.length !==0) {
                if(sprintBacklogItem.error != null
                    && sprintBacklogItem.error !== "The string did not match the expected pattern."
                    && sprintBacklogItem.error !== "JSON.parse: unexpected character at line 1 column 1 of the JSON data"
                    && sprintBacklogItem.error !== "Unexpected token U in JSON at position 0"){message = JSON.stringify(sprintBacklogItem.error);}

                console.log(JSON.stringify(sprintBacklogItem.error) + "__ ____ ___ __ ");
                console.log(JSON.stringify(sprintBacklogItem.data) + "__444___444_444");

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
                        <button type="submit" value="Show All Activities" onClick={goToShowUSStatus}> Show All UserStory Status</button>
                        <button type="submit" value="Create User Story" onClick={goToCreateUS} >Create New User Story</button>
                        <button type="submit" value="Decompose User Story" onClick={goToDecomposeUS}>Decompose User Story</button>
                        <br/>
                        <br/>

                        <h1>Product Backlog</h1>
                        <ProductBacklogTable dispatch={submitChangeOfPriority} headers ={headers} data ={data}/>
                        <br/>
                        <br/>
                        <br/>
                        <h2>Add User Story to running Sprint</h2>
                        <AddUSToSprintBacklog dispatch={submitForm} projectid={match.params.id} productbacklog={productBacklog}/>
                        <br/>
                        <h4>{message}</h4>
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
                        <button type="submit" value="Show All Activities" onClick={goToShowUSStatus}> Show All UserStory Status</button>
                        <button type="submit" value="Create User Story" onClick={goToCreateUS} >Create New User Story</button>
                        <br/>
                        <br/>
                        <h1>Product Backlog</h1>
                        <h2>Ooops! The product backlog is empty. Try and create a new User Story.  </h2>
                    </div>
                );
            }
        }
    }
}

export default ProductBacklog;
