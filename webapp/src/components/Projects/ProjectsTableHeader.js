import React from 'react';


function ProjectsTableHeader(props) {
    const {headers} = props;

    return (
        <thead>
        <tr>
            <th style={{width: "100px"}}>{headers.id}</th>
            <th style={{width: "100px"}}>{headers.name}</th>
            <th style={{width: "100px"}}>{headers.description}</th>
        </tr>
        </thead>
    );
}

export default ProjectsTableHeader;