import React from 'react';
import {Link} from "react-router-dom";



function ProfilesTableBody(props) {
    const {data} = props;

    const rows = data.map((row, index) => {
        return (
            <tr>
                <td>{row.profileID}</td>
                <td>{row.description}</td>
            </tr>
        )
    });
    return (
        <tbody>{rows}</tbody>
    );
}

export default ProfilesTableBody;