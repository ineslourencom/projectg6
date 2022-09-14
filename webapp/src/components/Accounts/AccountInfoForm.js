import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";

function AccountInfoForm() {
        const { state } = useContext(AppContext);
        const {account, logIn} = state;
        const {data} = account;
        const {profile} = logIn;
        console.log(JSON.stringify(data));


        return (
            <form>
                    <br/>
                    <br/>
                    <h1>User</h1>
                    <label>- - - - - - - - - - - - - - - - - - - -</label>
                    <br/>
                    <br/>
                    <label><strong>Email: </strong>{data.email}</label>
                    <br/>
                    <br/>
                    <label><strong>Name: </strong>{data.name}</label>
                    <br/>
                    <br/>
                    <label><strong>Job Title: </strong>{data.jobTitle}</label>
                    <br/>
                    <br/>
                    <label><strong>Photo: </strong>{data.photo}</label>
                    <br/>
                    <br/>
                    <label><strong>Profile: </strong>{profile}</label>
                    <br/>
                    <label>- - - - - - - - - - - - - - - - - - - -</label>
            </form>
        );
}
export default AccountInfoForm;