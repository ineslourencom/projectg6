import React, {useContext, useEffect, useState} from 'react';
import {fetchProjectsStarted, fetchProjects} from '../context/Actions';
import AppContext from '../context/AppContext';
import Table from "../components/Projects/Table";
import RoleSelection from "../components/Resources/RoleSelection";
import {useNavigate} from "react-router-dom";


function DisplayProjectsPage() {
    const {state, dispatch} = useContext(AppContext);
    const {projects, account} = state;
    const {loading, error, data} = projects;
    const {logIn} = state;
    const {email, profile} = logIn;
    const [role, setRole] = useState({role: "PROJECT_MANAGER"});
    const navigate = useNavigate();
    const goToCreateProject = () => {
        navigate("/projects/create");

    }

    const changeRole = (newRole) => {
        setRole({role: newRole})
        console.log("role was set: " + role)
    }

    const headers = {
        id: "ID",
        name: "Name",
        description: "Description"
    };

    let url = null;

    if (profile === "Director") {
        url = account.data._links.projects.href;
    } else {
        url = `${account.data._links.projects.href}&role=${role.role}`;
    }

    useEffect(() => {
        dispatch(fetchProjectsStarted());

        const request = {};

        fetchProjects(url, request, dispatch);
            console.log(JSON.stringify(projects.data));
        }, [role]);


    if (loading === true) {
        return (<h1>Loading ....</h1>);
    } else {
        if (error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            if (data.length !== 0) {
                if (profile === "Director") {
                    return (<div>
                        <br/>
                        <Table headers={headers} data={data}/>
                        <br/>
                        <button type="submit" onClick={goToCreateProject}>Create New Project</button>
                    </div>);

                } else {
                    return (<div>
                        <RoleSelection role={role} dispatch={changeRole}/>
                        <br/>
                        <Table headers={headers} data={data}/>
                    </div>);

                }
            } else {
                return (
                    <div>
                        <RoleSelection role={role} dispatch={changeRole}/>
                        <h1>No data ....</h1>
                    </div>);
            }
        }
    }
}

export default DisplayProjectsPage;