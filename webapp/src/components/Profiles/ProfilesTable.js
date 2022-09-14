import React from 'react';
import ProfilesTableHeader from './ProfilesTableHeader';
import ProfilesTableBody from './ProfilesTableBody';

function ProfilesTable(props) {
    const {headers, data} = props;

    return (

            <table align="center">
                <ProfilesTableHeader headers={headers}/>
                <ProfilesTableBody data = {data}/>
            </table>

    );
}

export default ProfilesTable;