import React, {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext";
import {fetchNewProfileID} from "../../context/Actions";
import ProfileDropDown from "./ProfileDropDown";

function GetProfileList(props) {

    const {state,dispatch}=useContext(AppContext)
    const {account} = state;


    useEffect(() => {

        let url = account.data._links.profiles.href;

        const request = {};

        fetchNewProfileID(url, request, dispatch);

    }, []);

    return (

        <ProfileDropDown state={state} handleChange={props.handleChange} profile={props.profile}/>

            );
}

export default GetProfileList;