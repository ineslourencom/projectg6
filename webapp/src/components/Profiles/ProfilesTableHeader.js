import React from 'react';


function ProfilesTableHeader(props) {
    const {headers} = props;

    return (
        <thead>
        <tr>
            <th style={{width: "100px"}}>{headers.profileID}</th>
            <th style={{width: "100px"}}>{headers.description}</th>
        </tr>
        </thead>
    );
}

export default ProfilesTableHeader;