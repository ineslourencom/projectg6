import React, {useState} from 'react';


function ProjectEditForm(props) {
    const {data, dispatch} = props;

    const [formInfo, setFormInfo] = useState(data);

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }

    const {code, description, startDate, endDate, sprintDuration, status, plannedSprints} = formInfo;

    const submitForm = () => {
        dispatch(formInfo)
    }


    return (
        <form onSubmit={submitForm}>
            <br/>
            <label>Start Date</label>
            <br/>
            <br/>
            <input type="date" name="startDate" value={startDate} max={endDate} onChange={handleChange}/>
            <br/>
            <br/>
            <label>End Date</label>
            <br/>
            <br/>
            <input type="date" name="endDate" value={endDate} min={startDate} onChange={handleChange}/>
            <br/>
            <br/>
            <label>Sprint Duration (weeks)</label>
            <br/>
            <br/>
            <input type="number" name="sprintDuration" min="1" value={sprintDuration} onChange={handleChange}/>
            <br/>
            <br/>
            <label>Planned Sprints</label>
            <br/>
            <br/>
            <input type="number" name="plannedSprints" min="1" value={plannedSprints} onChange={handleChange}/>
            <br/>
            <br/>
            <label htmlFor="status">Status:</label>
            <br/>
            <select name="status" id="status" value={status} onChange={handleChange}>
                <option value="PLANNED">Planned</option>
                <option value="INCEPTION"> Inception</option>
                <option value="ELABORATION"> Elaboration</option>
                <option value="CONSTRUCTION">Construction</option>
                <option value="TRANSITION"> Transition</option>
                <option value="WARRANTY">Warranty</option>
                <option value="CLOSED">Closed</option>
            </select>
            <br/>
            <label>Description</label>
            <br/>
            <textarea rows="5" cols="50" name="description" value={description} required onChange={handleChange}/>

            <br/>
            <input type="submit" value="Submit"/>
            <br/>
            <br/>
            <br/>

        </form>

    )

}

export default ProjectEditForm;