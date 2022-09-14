import React from 'react';

function TaskStatusTableHeader(props){
    const {headers} = props;

    return(
        <thead>
        <tr>
            <th style={{width:"200px"}}>{headers.taskName}</th>
            <th style={{width:"200px"}}>{headers.belongsTo}</th>
            <th style={{width:"150px"}}>{headers.containerId}</th>
            <th style={{width:"200px"}}>{headers.status}</th>
            <th style={{width:"50px"}}>{headers.percOfExec}</th>
        </tr>
        </thead>
    );

}
export default TaskStatusTableHeader;