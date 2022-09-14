import React, { useContext } from 'react';



function TableAccountHeader(props) {
    const {headers} = props


    return(

        <thead>
        <tr>
            <th style={{width:"100px"}}>{headers.accountID}</th>
            <th style={{width:"100px"}}>{headers.email}</th>
            <th style={{width:"100px"}}>{headers.name}</th>
            <th style={{width:"100px"}}>{headers.function}</th>
            <th style={{width:"100px"}}>{headers.photo}</th>
            <th style={{width:"100px"}}>{headers.profileID}</th>
        </tr>
        </thead>

    );
}
export default TableAccountHeader;