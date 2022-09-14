import React, {useContext, useState} from 'react';
import AppContext from "../../../context/AppContext";


function EditScrumBoardCategoryForm(props){
    const { dispatch, projectid } = props;
    const[formInfo, setFormInfo] = useState("");
    const { usID, category } = formInfo;
    const { state } = useContext(AppContext);
    const { scrumBoard } = state;
    const { data } = scrumBoard;

    const sprintRunning = data.map((us, index) => {
        if (index === 0) {
            return (
                <p key={index}>
                    The Sprint currently running is {us.sprintID}
                </p>
            );
        }
    });


    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value, "projID": projectid}
        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch(formInfo)
    }

    return (
        <div>
            <form>
                <label>ProjectID</label>
                <input type='text' name={"projID"} value={ projectid }  />
                <br/>
                <label>User Story Number</label>
                <input type='text' name={"usID"} value={ usID } onChange={handleChange} />
                <br/>
                <label>Category</label>
                <select name="category" value={category} onChange={handleChange}>
                    <option value="To do">To do</option>
                    <option value="In progress">In progress</option>
                    <option value="Code review">Code Review</option>
                    <option value="Rejected">Rejected</option>
                    <option value="Done">Done</option>
                </select>
                <br/>
                <input type='button' value={"Submit"} onClick={submitForm} />
            </form>
        </div>
    );

}

export default  EditScrumBoardCategoryForm;