import React from 'react';
import {Link} from "react-router-dom";



function ProjectsTableBody(props) {
    const {data} = props;

    const rows = data.map((row, index) => {
        if(row.links.length!==0){
            return (
                <tr>
                    <td>{row.code}</td>
                    <td><Link to={'/projects/' + row.code}>{row.name}</Link></td>
                    <td>{row.description}</td>
                </tr>
            );
        }
        else{
            return (
                <tr>
                    <td>{row.code}</td>
                    <td>{row.name}</td>
                    <td>{row.description}</td>
                </tr>
            );
        }
    });
    return (
        <tbody>{rows}</tbody>
    );
}

export default ProjectsTableBody;