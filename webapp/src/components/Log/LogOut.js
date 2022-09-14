import React from 'react';

function LogOut(props) {
    const {dispatch} = props;

    const submit = () => {
        dispatch()
    }

    return (

        <input type="submit" value="Log Out" onClick={submit}/>
    )


}

export default LogOut;