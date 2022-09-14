import React from "react";

function InsertEmail(props) {



    return (

        <label>
            <label> Email : </label>
            <input type="email" value={props.email} onChange={props.handleChange}placeholder="Insert email account.."/>
        </label>
    );
}

export default InsertEmail;