import React, {useContext, useState} from 'react';
import {
    fetchAccountInfoStarted,
    fetchAccountInfo, searchAccountStarted, searchAccount
} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import TableAccount from "./TableAccount";


function SearchAccountForm() {

    const {state, dispatch} = useContext(AppContext);
    const {searchedAccount, account} = state;
    const {loading, error, data} = searchedAccount;

    const [formInfo, setFormInfo] = useState('');

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}
        setFormInfo(newFormInfo);
    }

    const submitForm = (formInfo) => {
        dispatch(searchAccountStarted(accountID));

        const url = `${account.data._links.searchAccount.href}/${accountID}?type=long`;
        console.log(JSON.stringify(account.data) + "dataaaaaaaaaaaaaaasearch");

        console.log(JSON.stringify(url));
        const request = {};
        searchAccount(url, request, dispatch);

    }

    const headers = {
        accountID: "AccountID",
        email: "Email",
        name: "Name",
        function: "Function",
        photo: "Photo",
        profileID: "Profile ID",
    };


    const {accountID} = formInfo;


    if (loading === true) {
        return (
            <h1>
                Loading ....
            </h1>
        );
    } else {
        if (error !== null) {
            return (
                <div>
                    <form onSubmit={submitForm}>
                    <label>Email : </label>
                    <input type='email' name={"accountID"} placeholder="Insert email account.." value={accountID} onChange={handleChange}/>
                    <input type='submit' value={"Search"} />
                    </form>
                        <h1>
                        Email does not exist! ....
                    </h1>
                </div>

            );
        } else {
            return (
                <div>
                <form onSubmit={submitForm}>
                    <label>Email : </label>
                    <input type='email' name={"accountID"}placeholder="Insert email account.." value={accountID} onChange={handleChange}/>
                    <input type='submit' value={"Search"} />
                </form>

                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <TableAccount headers={headers} data={data}/>
                </div>
                );
        }

    }

}


export default SearchAccountForm;



