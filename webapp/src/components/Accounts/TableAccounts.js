import React from 'react';
import AccountsTableHeader from "./AccountsTableHeader";
import AccountsTableBody from "./AccountsTableBody";

function TableAccounts(props) {
    const {headers, data} = props;

    return (

        <table align="center">
            <AccountsTableHeader headers={headers}/>
            <AccountsTableBody data = {data}/>
        </table>

    );
}

export default TableAccounts;