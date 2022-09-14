import React, {useEffect, useState} from "react";

function ChangePriorityForm(props) {
    const [formInfo, setFormInfo] = useState("");
    const {dispatch} = props;

    const handleChange = (event) => {
        event.preventDefault();
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }



    const submitForm = () => {
        dispatch(formInfo, props.usID)
    }

    let {
        newPriority
    } = formInfo


    return (<div>

      {  <form>
            <input type='number' min={1} name={"newPriority"} value={newPriority} onChange={handleChange}/>
            <input type="button" value={"Change Priority"} onClick={submitForm}/>
        </form>}
    </div>)

}

export default ChangePriorityForm;