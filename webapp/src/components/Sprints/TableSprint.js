import React from "react";
import TableSprintHeader from "../Sprints/TableSprintHeader";
import TableSprintBody from "../Sprints/TableSprintBody";

function TableSprint (props) {
    const {headers, data}= props;

    return (
        <div>
            <table align="center">
                <TableSprintHeader headers= {headers}/>
                <TableSprintBody data = {data} dispatch={props.dispatch}/>
            </table>
        </div>
    );
}
export default TableSprint;