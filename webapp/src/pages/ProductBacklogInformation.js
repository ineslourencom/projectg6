import React, {useContext} from 'react';
import AppContext from '../../../context/AppContext';
import { fetchUSStatusStarted
        } from "../context/Actions";
import {useMatch, useNavigate} from "react-router-dom";

import ProductBacklog from "./ProductBacklog";
import productBacklog from "./ProductBacklog";
import {useState} from "@types/react";

function ProductBacklogInformation() {
    const {state, dispatch} = useContext(AppContext);
    const match = useMatch('/projects/:id');
    const navigate = useNavigate();



    const goToSeeActivitiesStatus = () => {
        dispatch(fetchUSStatusStarted(match.params.id))
        navigate("project/{id}/userStories/showStatus")

    }

    const [mode, setMode] = useState({
        pageMode: "read"
    })


    if (productBacklog.loading === true) {
        return (<h1>Loading ....</h1>);
    } else {
        if (productBacklog.error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            if (productBacklog.data !== null) {
                if (mode.pageMode === "read") {
                    return (
                        <div>
                            <ProductBacklog data={productBacklog.data}/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <h3>Product Backlog</h3>
                            <ProductBacklog projectid={match.params.id}/>
                            <br/>
                            <br/>
                            <button onClick={goToSeeActivitiesStatus}>Show All Current Status</button>
                            <br/>
                            <button onClick={null}>Add US to Running Sprint</button>
                            <br/>
                            <br/>

                        </div>
                    );


                } else {
                    return (<h1>No data ....</h1>);
                }
            }
        }

    }
}