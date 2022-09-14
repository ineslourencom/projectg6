import React from 'react';


function TaskStatusTableBody(props){
    const {data} = props;

    const rows = data.map((row, index) => {
        return(
            <tr key={index}>
                <td>{row.taskName}</td>
                <td>{row.belongsTo}</td>
                <td>{row.containerId}</td>
                <td>{row.status}</td>
                <td>{row.percOfExec} % </td>
            </tr>
        )

    });
    return (
        <tbody>{rows}</tbody>
    );

}
export default TaskStatusTableBody;