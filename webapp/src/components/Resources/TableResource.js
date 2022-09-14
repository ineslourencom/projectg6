import React from "react";
import TableResourceHeader from "../Resources/TableResourceHeader";
import TableResourceBody from "../Resources/TableResourceBody";

function TableResource (props) {
    const {headers, data}= props;

    return (
        <div>
            <table align="center">
                <TableResourceHeader headers= {headers}/>
                <TableResourceBody data = {data}/>
            </table>
        </div>
    );
}
export default TableResource;