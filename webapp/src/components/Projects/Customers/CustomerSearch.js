import React, {useContext, useEffect} from "react";
import AppContext from "../../../context/AppContext";
import {fetchCustomerInfo} from "../../../context/Actions";
import Customer from "./Customer";
function CustomerSearch(props) {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;


    useEffect(() => {

        let url = null;
        for (let i = 0; i < projects.data[0].links.length; i++) {
            if (projects.data[0].links[i].rel == "allCustomers") {
                url = projects.data[0].links[i].href;
            }
        }

        const request = {};

        fetchCustomerInfo(url, request, dispatch);

    }, []);

    return <Customer state={state}  handleChange={props.handleChange}  customer={props.customer}/>
}

export default CustomerSearch;