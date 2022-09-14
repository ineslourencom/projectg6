import React from 'react';
import ProjectsTableHeader from './ProjectsTableHeader';
import ProjectsTableBody from './ProjectsTableBody';

function Table(props) {
    const {headers, data} = props;

    return (

            <table align="center">
                <ProjectsTableHeader headers={headers}/>
                <ProjectsTableBody data = {data}/>
            </table>

    );
}

export default Table;