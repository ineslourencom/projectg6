import React from 'react';


function AccountsTableHeader(props) {
    const {headers} = props;

    return (
        <thead>
        <tr>
            <th style={{width: "100px"}}>{headers.email}</th>
            <th style={{width: "100px"}}>{headers.status}</th>
        </tr>
        </thead>
    );
}

export default AccountsTableHeader;