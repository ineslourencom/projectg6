import React from 'react';




function AccountsTableBody(props) {
    const {data} = props;

    const rows = data.map((row) => {
        return (
            <tr>
                <td>{row.email}</td>
                <td>{row.active.toString()}</td>
            </tr>
        )
    });
    return (
        <tbody>{rows}</tbody>
    );
}

export default AccountsTableBody;