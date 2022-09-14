
import React, {useContext, useState} from "react";
import InsertEmail from "../Geral/InsertEmail";
import GetProfileList from "./GetProfileList";
import {
    submitUpdateProfileEditionForm
} from "../../context/Actions";
import AppContext from "../../context/AppContext";

function UpdateProfileForm(props) {

    const { dispatch, state } = useContext(AppContext);
    const {account} = state;
    const [pageState, setPageState] = useState({
        email :"",
        profile:""
    });

    const handleChange = (event) => {
        const {value} = event.target
        const newPageState = {...pageState, email: value}
        setPageState(newPageState);
    }
    const handleProfileChange = (event) => {
        const {value} = event.target
        const newPageState = {...pageState, profile: value}
        setPageState(newPageState);

    }

    const submitForm = (event) => {
        event.preventDefault();
        const url = `${account.data._links.updateProfile.href}/${pageState.email}/profiles`;
        console.log(JSON.stringify(url));
        console.log(JSON.stringify(account.data) + "dataaaaaaaaaaaaaaaupdateprof");

        const request = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({profileID:pageState.profile})
        }


        submitUpdateProfileEditionForm(url, request, dispatch);
    }



    return (
        <form onSubmit={submitForm}>
            <div>
                <h1>Update Profile</h1>
                <InsertEmail email={pageState.email} handleChange={handleChange}/>
                <GetProfileList profile={pageState.profile} handleChange={handleProfileChange}/>
            </div>
            <input type="submit" value="Save"/>
        </form>
    );

}

export default UpdateProfileForm;