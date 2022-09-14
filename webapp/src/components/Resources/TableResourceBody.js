import React from 'react';


function TableResourceBody(props) {
    const {data} = props;

    const rows = data.map((row, index) => {
    return(
        <tr>
            <td>{row.associatedAccount}</td>
            <td>{row.resourceID}</td>
            <td>{row.associatedRole}</td>
            <td>{row.startDate}</td>
            <td>{row.endDate}</td>
            <td>{row.percentageOfAllocation}</td>
            <td>{row.projectID}</td>
            <td>{row.costPerHourValue}</td>
            <td>{row.currency}</td>
        </tr>
    )}
    );  return (
            <tbody>{rows}</tbody>
        );

}

export default TableResourceBody;