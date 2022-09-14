import React from 'react';

function TableAccountBody(props) {

    const {data} = props;
    console.log(JSON.stringify(data))

    return (

        <tr>
            <td>{data.accountID}</td>
            <td>{data.email}</td>
            <td>{data.name}</td>
            <td>{data.function}</td>
            <td>{data.photo}</td>
            <td>{data.profileID}</td>
        </tr>

    )
}

export default TableAccountBody;