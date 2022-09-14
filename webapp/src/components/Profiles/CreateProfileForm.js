import React, {useContext, useState} from "react";
import {postProfileForm} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import '../../style/geral.css';


function CreateProfileForm(props) {

    const {state, dispatch} = useContext(AppContext);
    const {account}= state;
    const {error, success} = state.profileInfo


    const [compState, setState] = useState({
        profileType: "",
        description: ""
    });


    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...compState, [name]: value}
        setState(newFormInfo);
    }

    const onFormSubmit = (event) => {
        event.preventDefault();
        const url = `${account.data._links.createProfile.href}`;
        console.log(url);
        console.log(JSON.stringify(account.data) + "dataaaaaaaaaaaaaaaCreateprof");

        const request = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(compState)
        }
        postProfileForm(url, request, dispatch)

    }


    return (
        <div>
            <form onSubmit={onFormSubmit}>
                <p>
                    <h1>Create Profile</h1>
                </p>

                <p><label htmlFor="description">Profile Name </label></p>

                <p>
                    <input
                        type="text"
                        name="profileType"
                        placeholder="Insert a profile name.."
                        id="profileType"
                        value={compState.profileType}
                        required
                        maxLength={25}
                        title="Only accepts Alphabetic elements"
                        onChange={handleChange}/>
                    <p>
                    </p>

                    <p>
                        <label for="description">Description</label>
                    </p>

                    <p>
                        <input
                            type="text"
                            name="description"
                            placeholder="Insert a profile description.."
                            id="description"
                            value={compState.description}
                            max={300}
                            required
                            onChange={handleChange}/>

                    </p>

                </p>

                <input type="submit" value="Submit"/>

            </form>
            <p>
                {error}
                {success}
            </p>
        </div>
    )
}

export default CreateProfileForm;