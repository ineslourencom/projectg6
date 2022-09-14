import React, {useContext} from "react";
import CreateProfileForm from '../components/Profiles/CreateProfileForm';
import {Outlet} from 'react-router-dom'
import UpdateProfileForm from "../components/Profiles/UpdateProfileForm";
import AppContext from "../context/AppContext";


function ProfilesCreateAndUpdate() {

    const { state, dispatch } = useContext(AppContext);
    const { profilePage } = state;


    return (
        <div>
            <div>
                <h1>Profiles</h1>
                <div>
                    {
                        profilePage.create && !profilePage.update && <CreateProfileForm/>
                    }
                    {
                        !profilePage.create && profilePage.update && <UpdateProfileForm/>
                    }
                </div>
                <Outlet/>
            </div>


        </div>
    )

}


export default ProfilesCreateAndUpdate;

