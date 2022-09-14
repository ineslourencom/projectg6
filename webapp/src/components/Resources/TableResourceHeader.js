import React from "react";


function TableResourceHeader (props){
    const {headers}= props

    return(
        <thead>
        <tr>
            <th style={{width:"100px"}}>{headers.associatedAccount}</th>
            <th style={{width:"100px"}}>{headers.resourceID}</th>
            <th style={{width:"100px"}}>{headers.associatedRole}</th>
            <th style={{width:"100px"}}>{headers.startDate}</th>
            <th style={{width:"100px"}}>{headers.endDate}</th>
            <th style={{width:"100px"}}>{headers.percentageOfAllocation}</th>
            <th style={{width:"100px"}}>{headers.projectID}</th>
            <th style={{width:"100px"}}>{headers.costPerHourValue}</th>
            <th style={{width:"100px"}}>{headers.currency}</th>
        </tr>
        </thead>
    );
}
export default TableResourceHeader;