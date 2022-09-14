import React from "react";
import {useState} from "react";

function SetNewStartDateForm (props){
    const [formInfo, setFormInfo] = useState("");
    const {dispatch} = props;

    const handleChange = (event) => {
        event.preventDefault();
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch(formInfo, props.sprintNumber)
        console.log({formInfo} + "bjjja")
    }

let {
    startDate
}=formInfo

    return (<div>

        {  <form>
            <input type='date' name={"startDate"} value={startDate} onChange={handleChange}/>
            <input type="button" value={"Set New Start Date"} onClick={submitForm} />
        </form>}
    </div>)
}
export default SetNewStartDateForm;