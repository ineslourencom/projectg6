import React, {useContext, useEffect, useState} from 'react';
import AppContext from '../context/AppContext';
import {URL_API} from "../services/Service";

import LogIn from "../components/Log/LogIn";
import LogOut from "../components/Log/LogOut";
import {
    fetchAccountInfo,
    fetchAccountInfoStarted,
    login,
    logout,
    submitUpdateAccountInfo,
    submitUpdateAccountInfoFormStarted
} from "../context/Actions";
import '../style/login.css';
import AccountInfoForm from "../components/Accounts/AccountInfoForm";
import AccountEditForm from "../components/Accounts/AccountEditForm";


function LogInPage() {
    const {state, dispatch} = useContext(AppContext);
    const {logIn, account} = state;
    const {loggedIn, email, profile} = logIn;
    const { loading, error, data} = account;

    const [mode, setMode] = useState({
        pageMode: "read"
    })

    const submitLogIn= (formInfo) => {
        dispatch(login(formInfo));
    }

    const submitLogOut= () => {
        dispatch(logout());
    }

    useEffect(() => {
        dispatch(fetchAccountInfoStarted(email));
        const url = `${URL_API}/accounts/${email}?type=short`

        const request = {};
        fetchAccountInfo(url, request, dispatch);
    }, [logIn]);

    const submitForm = (formInfo) => {
        dispatch(submitUpdateAccountInfoFormStarted(email));
        const url = account.data._links.accounts.href;

        console.log(JSON.stringify(formInfo)+ "12345")

        console.log(JSON.stringify( account.data._links )+ "ajkhkjhjkhkkjhjkhjkhkjhjkhjkhjkh")
        console.log(JSON.stringify( account.data._links.accounts.href )+ "jjjjjjjj")
        const request = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo),
        }
        console.log(JSON.stringify(request.body) + "ahahahahahahahahhahahahah");
        submitUpdateAccountInfo(url, request, dispatch);
        setMode({
            pageMode: "read"
        });
        console.log(JSON.stringify(mode.pageMode));
    }


    if (loggedIn) {
        if (loading === true) {
            return (
                <div>
                    <br/>
                    <h1>Loading ... </h1>
                    <br/>
                    <br/>
                    <h3>Password</h3>
                    <button type="submit" value="Edit password" onClick={() => setMode({
                        pageMode: "edit"
                    })}> Reset Password
                    </button>
                    <br/>
                    <br/>
                    <p> Logged in as {email}, {profile} </p>
                    <LogOut dispatch = {submitLogOut}/>
                </div>
            );
        } else if (error !== null) {
            return(
            <div>
                <br/>
                <h1>Error ... </h1>
                <br/>
                <br/>
                <h3>Password</h3>
                <button type="submit" value="Edit password" onClick={() => setMode({
                    pageMode: "edit"
                })}> Reset Password
                </button>
                <br/>
                <br/>
                <p> Logged in as {email}, {profile} </p>
                <LogOut dispatch={submitLogOut}/>
            </div>);
        }
        else if(data !== []){
            if (mode.pageMode === "read") {
                return (
                    <div>
                        <br/>
                        <AccountInfoForm />
                        <button type="submit" value="Edit account info" onClick={() => setMode({
                            pageMode: "edit"
                        })}> Edit Account Details
                        </button>
                        <br/>
                        <br/>
                        <h3>Password</h3>
                        <button type="submit" value="Edit password" onClick={() => setMode({
                            pageMode: "edit"
                        })}> Reset Password
                        </button>
                        <br/>
                        <br/>
                        <p> Logged in as {email}, {profile} </p>
                        <LogOut dispatch = {submitLogOut}/>
                    </div>
                );
            }
            else if (mode.pageMode === "edit") {
                return (
                    <div>
                        <br/>
                        <AccountEditForm dispatch={submitForm}/>
                    </div>
                );
            }
        }
        else if(data === []){
            return (
                <div>
                    <br/>
                    <h1>No data ... </h1>
                    <br/>
                    <br/>
                    <h3>Password</h3>
                    <button type="submit" value="Edit password" onClick={() => setMode({
                        pageMode: "edit"
                    })}> Reset Password
                    </button>
                    <br/>
                    <br/>
                    <p> Logged in as {email}, {profile} </p>
                    <LogOut dispatch = {submitLogOut}/>
                </div>
            );
        }

    } else {
        return (
            <LogIn data= {logIn} dispatch = {submitLogIn}/>
        )
    }


}

export default LogInPage;