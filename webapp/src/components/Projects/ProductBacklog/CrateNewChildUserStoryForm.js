import React, { useState} from 'react';
import USSearch from "./USSearch";
import {submitDecomposeUS, submitProjCreationForm} from "../../../context/Actions";


function CreateNewChildUserStoryForm(props){
    const { dispatch, projectid, productBacklog } = props;
    const[formInfo, setFormInfo] = useState("");
    const {usID, statement, detail } = formInfo;

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}
        setFormInfo(newFormInfo);
    }
    var childs = [];

    const addUS = (formInfo) => {

    }

    const submitForm = () => {
        dispatch(childs)
    }

    return (
        <div>
            <form>
                <label>User Story To be Decomposed</label>
                <USSearch usID={usID} productbacklog={productBacklog} handleChange={handleChange}/>
                <br/>
                <br/>
                <br/>
                <label>Statement </label>
                <input type='textarea' name={"statement"} value={ statement } onChange={handleChange} />
                <br/>
                <br/>
                <br/>
                <label>Detail </label>
                <input type='textarea' name={"detail"} value={ detail } onChange={handleChange} />
                <br/>
                <br/>
                <br/>
            </form>
            <br/>
            <br/>

            <button type="submit" value={"Submit"} onClick={submitForm}> Decompose User Story </button>
        </div>
    );
}

export default  CreateNewChildUserStoryForm;