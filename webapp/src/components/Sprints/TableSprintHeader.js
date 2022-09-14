import React from "react";


function TableSprintHeader (props){
    const {headers}= props

    return(
        <thead>
        <tr>
            <th style={{width:"100px"}}>{headers.sprintNumber}</th>
            <th style={{width:"100px"}}>{headers.startDate}</th>
            <th style={{width:"100px"}}>{headers.endDate}</th>
            <th style={{width:"100px"}}>{headers.setNewStartDate}</th>
        </tr>
        </thead>
    );
}
export default TableSprintHeader;