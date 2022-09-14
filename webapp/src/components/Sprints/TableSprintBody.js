import React from "react";
import SetNewStartDateForm from "./SetNewStartDateForm";

function TableSprintBody(props) {

    const {data} = props;
    console.log(JSON.stringify(data) + "___jkdhbae,jrhaelrhwer_________")

    const rows = data.map((row, index) => {
        return (
            <tr>
                <td>{row.sprintNumber}</td>
                <td>{row.startDate}</td>
                <td>{row.endDate}</td>
                <td><SetNewStartDateForm dispatch={props.dispatch} sprintNumber={row.sprintNumber}/></td>
            </tr>
        )}
    );
    return (
        <tbody>{rows}</tbody>
    );
}
export default TableSprintBody;