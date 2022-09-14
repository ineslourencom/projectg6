import React from 'react';
import {Link} from "react-router-dom";


function USStatusTableBody(props){
    const {data} = props;

    const rows = data.map((row, index) => {
        return(
            <tr key={index}>
                <td><Link to={ '/userStory/'+ row.storyNumber}></Link>{row.storyNumber}</td>
                <td><Link to={ '/userStory/'+ row.storyNumber}>{row.statement}</Link></td>
                <td>{row.detail}</td>
                <td>{row.priority}</td>
                <td>{row.usStatus}</td>
            </tr>
        )

    });
    return (
        <tbody>{rows}</tbody>
    );

}
export default USStatusTableBody;