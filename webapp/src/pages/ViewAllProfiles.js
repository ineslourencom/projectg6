import React, {useContext, useEffect, useState} from 'react';
import {fetchProfilesStarted, fetchProfiles} from '../context/Actions';
import AppContext from '../context/AppContext';
import ProfilesTable from "../components/Profiles/ProfilesTable";


function ViewAllProfilesPage() {
    const {state, dispatch} = useContext(AppContext);
    const {profiles, account} = state;
    const {loading, error, data} = profiles;


    const headers = {
        profileID: "Profile",
        description: "Description"
    };


    useEffect(() => {
        dispatch(fetchProfilesStarted());

        const request = {};

        let url = account.data._links.viewAllProfiles.href;

        fetchProfiles(url, request, dispatch);
        console.log(JSON.stringify(data));
    }, []);


    if (loading === true) {
        return (<h1>Loading ....</h1>);
    } else {
        if (error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            if (data.length !== 0) {
                    return (<div>
                        <br/>
                        <h1> Profiles </h1>
                        <h4>This page displays all available profiles, internal and external (provided by group 1).</h4>
                        <ProfilesTable headers={headers} data={data}/>
                        <br/>
                    </div>);
            } else {
                return (
                    <div>
                        <h1> Profiles </h1>
                        <h1>No data ....</h1>
                    </div>);
            }
        }
    }
}

export default ViewAllProfilesPage;