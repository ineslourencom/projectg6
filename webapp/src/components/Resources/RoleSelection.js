import React, {useState} from "react";

function RoleSelection(props) {
    const {dispatch, role} = props;
    const [formInfo, setFormInfo] = useState("");

    const handleChange = (event) => {
        const {name, value} = event.target
        dispatch(value);
        const newFormInfo = {[name]: value}
        setFormInfo(newFormInfo)
        console.log("selected role option:" + value)
    }
    const {displayRole} = formInfo

    return (
        <label>
        <br/>
            <br/>
            <label> SelectRole : </label>
            <select name="displayRole" value={displayRole} onChange={handleChange}>
                <option value=""></option>
                <option value="PRODUCT_OWNER">Product Owner</option>
                <option value="PROJECT_MANAGER">Project Manager</option>
                <option value="SCRUM_MASTER">Scrum Master</option>
                <option value="DEVELOPER">Developer</option>
            </select>
        </label>

    );


}

export default RoleSelection;