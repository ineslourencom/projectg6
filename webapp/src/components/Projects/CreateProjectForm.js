import React, {useState} from 'react';
import ProjectTypologySearch from "./Typologies/ProjectTypologySearch";
import CustomerSearch from "./Customers/CustomerSearch";
import BusinessSectorSearch from "./BusinessSector/BusinessSectorSearch";



const CreateProjectForm = (props) => {
    const {dispatch} = props;
    const [formInfo, setFormInfo] = useState("");


    const submitForm = () => {
        dispatch(formInfo)
    }


    const handleChange = (event) => {
        event.preventDefault();
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }

    const {
        projectName,
        description,
        startDate,
        endDate,
        sprintDuration,
        budget,
        customer,
        businessSector,
        projectTypology
    } = formInfo;


    return (
        <div>
            <form>
                <label>Name: </label>
                <br/>
                <input type='text' name={"name"} placeholder="Insert project name.."value={projectName} required title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>Description: </label>
                <br/>
                <input type='text' name={"description"}placeholder="Insert project description.." value={description} required title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>StartDate :</label>
                <br/>
                <input type='date' name={"startDate"} value={startDate} required max={endDate} title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>End Date: </label>
                <br/>
                <input type='date' name={"endDate"} value={endDate} required min={startDate} title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>Sprint Duration (in weeks): </label>
                <br/>
                <input type='number' name={"sprintDuration"} value={sprintDuration} required min="1"
                       title="Required field" onChange={handleChange}/>
                <br/>
                <label>Budget: </label>
                <br/>
                <input type='number' name={"budget"} value={budget} min="1" title="Required field"
                       onChange={handleChange} required/>
            </form>
            <label>Project Typology: </label>
            <ProjectTypologySearch handleChange={handleChange} projectTypology={projectTypology}/>
            <label>Customer: </label>
            <CustomerSearch handleChange={handleChange} customer={customer}/>
            <label>Business Sector: </label>
            <BusinessSectorSearch handleChange={handleChange} businessSector={businessSector}/>
            <br/>
            <button  disabled={formInfo === ""} type="submit" value={"Submit"} onClick={submitForm}>
                Submit
            </button>
        </div>
    );

}

export default CreateProjectForm;