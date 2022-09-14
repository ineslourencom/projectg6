import React, {useContext} from 'react';
import AppContext from '../context/AppContext';
import {Link, useMatch} from "react-router-dom";



function ProjectManagementPage() {
    const {state, dispatch} = useContext(AppContext);
    const match = useMatch('/projectManagement/:id');


    return (

        <div>
            <nav>
                <ul>
                    <li><a><Link to={ '/projects/' + match.params.id }>Project Details</Link></a></li>
                    <li><a><Link to="/projectTypologies">Typologies</Link></a></li>
                    <li><a><Link to="/resources">Resources</Link></a></li>
                    <li><a><Link to="/sprints">Sprints</Link></a></li>
                </ul>
            </nav>
        </div>


    );
}

export default ProjectManagementPage;