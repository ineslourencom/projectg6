import React, {useState} from 'react';
const CreateProjectTypologyForm = (props) => {
    const { dispatch } = props;
    const [formInfo, setFormInfo] = useState("");

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }

    const {
        name,
        description,
    } = formInfo;

    const submitForm = (e) => {
        e.preventDefault()
        dispatch(formInfo)}

    return (
        <form>
            <label>Name</label>
            <br/>
            <input type='text' name={"name"} placeholder="Insert project typology name.."value={name} onChange={handleChange}/>
            <br/>
            <br/>
            <label>Description</label>
            <br/>
            <input type='text' name={"description"} placeholder="Insert project typology description.."value={description} onChange={handleChange}/>
            <br/>
            <br/>
            <input type='submit' value={"Submit"} onClick={submitForm}/>
        </form>
    );

}

export default CreateProjectTypologyForm;