import React, {useContext, useEffect} from 'react';
import AppContext from '../context/AppContext';
import {Link, useMatch} from "react-router-dom";
import {
    fetchScrumBoard,
    fetchScrumBoardStarted,submitUSCategoryForm
} from "../context/Actions";
import ScrumBoardTable from "../components/Projects/ScrumBoard/ScrumBoardTable";
import EditScrumBoardCategoryForm from "../components/Projects/ScrumBoard/EditScrumBoardCategoryForm";
import {URL_API} from '../services/Service'


function ScrumBoardPage() {
    const { state, dispatch } = useContext(AppContext);
    const { scrumBoard, projects,userStoryCategory } = state;
    const match = useMatch('/projects/:id/scrumBoard');

    const headers = {
        storyNumber: "User Story Number",
        statement: "Statement",
        usStatus: "Status",
    };

    const sprintRunning = scrumBoard.data.map((us, index) => {
        if (index === 0) {
            return (
                <p key={index}>
                    The Sprint currently running is {us.sprintID}
                </p>
            );
        }
    });

    useEffect(() => {
        dispatch(fetchScrumBoardStarted(match.params.id));

        let url = null;
        projects.data.map((row, index) => {
            if (row.code == (match.params.id)) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "scrumBoard") {
                        console.log(JSON.stringify(row.links[i].href));
                        url = row.links[i].href;
                    }
                }
            }
        });
        const request = {};
        fetchScrumBoard(url, request, dispatch);
    }, [userStoryCategory]);

    console.log(JSON.stringify(scrumBoard.data) + "This is the first test ____xxxxxx____xxxx")

    const submitForm = (formInfo) => {
        let url = null;
        scrumBoard.data.map((row, index) => {
            if (row.usID == formInfo.usID) {
                for (let i = 0; i < row.links.length; i++) {
                    if (row.links[i].rel == "editCategory") {
                        console.log(JSON.stringify(row.links[i].href));
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
            body: JSON.stringify(formInfo)
        }

        console.log(JSON.stringify(scrumBoard.data + "este é o meu data"))
        console.log(JSON.stringify(url + "este é o meu URL"))
        console.log(JSON.stringify(request.body + "este é o meu body"))
        submitUSCategoryForm(url, request, dispatch);
    }

    if(scrumBoard.data.length !== 0){
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
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>
                    </ul>
                </nav>
                <br/>
                <h1>Scrum Board</h1>
                {sprintRunning}

                <EditScrumBoardCategoryForm dispatch={submitForm} projectid={match.params.id}/>
                <br/>
                <br/>
                <br/>
                <br/>

                <ScrumBoardTable headers={headers}/>
                <br/>
            </div>
        )
    }
    else{
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
                        <li><a><Link to={"/projects/" + match.params.id + "/productBacklog"}>Product Backlog</Link></a></li>
                        <li><a><Link to={"/projects/" + match.params.id + "/tasks"}>Tasks</Link></a></li>
                    </ul>
                </nav>
                <br/>
                <h1> Scrum Board</h1>
                <h3>The ScrumBoard is empty. <Link to={"/projects/" + match.params.id+ "/sprints"}> Check if there is a sprint currently running</Link> or <Link to={"/projects/" + match.params.id + "/productBacklog"}>add user stories</Link> to the sprint backlog.</h3>
                <br/>
                </div>
                );
    }


}

export default ScrumBoardPage;
