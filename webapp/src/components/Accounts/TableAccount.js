import React from "react";
import TableAccountHeader from './TableAccountHeader';
import TableAccountBody from './TableAccountBody';

function TableAccount(props) {
    const {headers, data} = props;

    return (
        <div>

            <table align="center">
                <TableAccountHeader headers={headers}/>
                <TableAccountBody data = {data}/>
            </table>
        </div>
    );
}

export default TableAccount;

