import {useContext} from "react";
import AppContext from "../../../context/AppContext";


function USSearch(props) {
    const {state, dispatch} = useContext(AppContext);
    const {productbacklog, handleChange, usID} = props;
    const { data } = productbacklog;

    let optionItems = data.map((us, index) =>
        <option value={us.usID}>{us.storyNumber}</option>
    );
    return (
        <div>
            <select name="usID" value={usID} onChange={handleChange}>
                <option value=""/>
                {optionItems}
            </select>
        </div>
    )
}

export default USSearch;