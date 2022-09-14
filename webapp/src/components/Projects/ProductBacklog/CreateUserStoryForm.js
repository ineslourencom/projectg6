import React, { useState} from 'react';


function CreateUserStoryForm(props){
    const { dispatch, projectid } = props;
    const[formInfo, setFormInfo] = useState("");
    const { statement, detail } = formInfo;

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
                    <label>ProjectID</label>
                    <input type='text' name={"projID"} value={ projectid }  />
                    <br/>
                    <br/>
                    <br/>
                    <label>Statement</label>
                    <input type='text' name={"statement"} value={ statement } onChange={handleChange} />
                    <br/>
                    <br/>
                    <br/>
                    <label>Detail</label>
                    <input type='text' name={"detail"} value={ detail } onChange={handleChange} />
                    <br/>
                    <br/>
                    <br/>
                    <input type='button' value={"Submit"} onClick={submitForm} />
                </form>
        );

}

export default  CreateUserStoryForm;