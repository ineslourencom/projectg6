import React, {useState} from 'react';




function CreateSprintForm(props){
    const { dispatch, projectid, sprintDuration } = props;
    const[formInfo, setFormInfo] = useState("");
    const { startDate } = formInfo;
    console.log(JSON.stringify(sprintDuration));

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value, "projID": projectid}
        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch({formInfo})
    }

    return (
        <form>
            <label> ProjectID </label>
            <input type="text" name="projectid" value={projectid} readOnly="readonly"/>
            <label> Sprint Duration </label>
            <input type="text" name="sprintDuration" value={sprintDuration} readOnly="readonly" />
            <label> Start Date </label>
            <input type='date' name="startDate" value={ startDate } onChange={handleChange} />

            <input type='button' value={" Create New Sprint "} onClick={submitForm}/>

        </form>
    );


}

export default  CreateSprintForm;