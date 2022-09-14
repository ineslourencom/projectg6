import React, {useContext, useState} from 'react';
import AppContext from "../../context/AppContext";


function AccountEditForm(props) {
    const { dispatch} = props;
    const {state} = useContext(AppContext);
    const {account} = state;
    const {data} = account;

    const [formInfo, setFormInfo] = useState(data);

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value }

        setFormInfo(newFormInfo);
    }


    const submitForm = () => {
        console.log(JSON.stringify(formInfo) + "This si the first form")
        dispatch(formInfo)
    }


    return (
        <form>
            <h1>User</h1>
            <br/>
            <label><strong>Email: </strong></label>
            <input type='text' name={"email"} value={formInfo.email}/>
            <br/>
            <br/>
            <label><strong>Name: </strong></label>
            <input type='text' name={"name"} value={formInfo.name}/>
            <br/>
            <br/>
            <br/>
            <label><strong>Job Title: </strong></label>
            <input type='text' name={"jobTitle"} value={formInfo.jobTitle} onChange={handleChange}/>
            <br/>
            <br/>
            <label><strong>Photo:</strong></label>
            <input type='text' name={"photo"} value={formInfo.photo} onChange={handleChange}/>
            <br/>
            <br/>
            <input type='button' value={"Submit"} onClick={submitForm} />
            <br/>
            <br/>
            <br/>

        </form>

    );

}

export default AccountEditForm;