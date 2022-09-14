import React, { useState} from 'react';
import USSearch from "./USSearch";

function AddUSToSprintBacklog(props){
    const { dispatch, projectid, productbacklog } = props;
    const[formInfo, setFormInfo] = useState("");
    const { effort, usID } = formInfo;

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}
        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch({formInfo})
    }

    return (
        <div>
        <form>
            <label>User Story Number (choose US from the product backlog)</label>
            <USSearch usID={usID} productbacklog={productbacklog} handleChange={handleChange}/>
            <label>Effort (choose a fibonacci number)</label>
            <br/>
            <select name={"effort"} value={ effort } onChange={handleChange}>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option value="8">8</option>
                <option value="13">13</option>
                <option value="21">21</option>
                <option value="34">34</option>
            </select>
        </form>
            <button type="submit" value={"Submit"} onClick={submitForm}> Add to Running Sprint Backlog </button>
        </div>
    );

}

export default  AddUSToSprintBacklog;