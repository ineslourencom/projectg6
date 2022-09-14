import React, {useContext, useEffect} from "react";
import AppContext from "../../../context/AppContext";
import {fetchBusinessSectorInfo} from "../../../context/Actions";
import BusinessSector from "./BusinessSector";


function BusinessSectorSearch(props) {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;

    useEffect(() => {

        let url = null;
        for (let i = 0; i < projects.data[0].links.length; i++) {
            if (projects.data[0].links[i].rel == "allBusinessSectors") {
                url = projects.data[0].links[i].href;
            }
        }

        const request = {};

        fetchBusinessSectorInfo(url, request, dispatch);

    }, []);


     return <BusinessSector state={state}  handleChange={props.handleChange}  businessSector={props.businessSector}/>
}

export default BusinessSectorSearch;