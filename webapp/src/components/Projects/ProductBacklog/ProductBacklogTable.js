import React from 'react';
import CreateTaskForm from "../../Tasks/CreateTaskForm";
import ChangePriorityForm from "../../UserStories/ChangePriorityForm";
import DecomposeUSPage from "../../UserStories/DecomposeUSPage";
import {useNavigate} from "react-router-dom";


function ProductBacklogTable(props) {
    const {headers, data} = props;
    const navigate = useNavigate();

    const rows = data.map((row, index) => {
        return (
            <tr key={index}>
                <td>{row.storyNumber}</td>
                <td>{row.statement}</td>
                <td>{row.detail}</td>
                <td>{row.priority}</td>
                <td><ChangePriorityForm dispatch={props.dispatch} usID={row.usID}/></td>
            </tr>
        )
    });

    return (
        <div>
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>{headers.storyNumber}</th>
                    <th>{headers.statement}</th>
                    <th>{headers.detail}</th>
                    <th>{headers.priority}</th>
                    <th>{headers.changePriority}</th>

                </tr>
                </thead>
                <tbody>{rows}</tbody>
            </table>
        </div>
    );
}

export default ProductBacklogTable;