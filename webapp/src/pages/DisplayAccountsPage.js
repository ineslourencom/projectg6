import React, {useContext, useEffect} from 'react';
import {fetchAccounts, fetchAccountsStarted} from '../context/Actions';
import AppContext from '../context/AppContext';
import Table from "../components/Accounts/TableAccounts";
import {URL_API} from "../services/Service";


function DisplayAccountsPage() {
    const {state, dispatch} = useContext(AppContext);
    const {accounts, account} = state;
    const {loading, error, data} = accounts;

    const headers = {
        email: "Email",
        status: "Status",
    };


    useEffect(() => {
        dispatch(fetchAccountsStarted());

        const request = {};

        const url = account.data._links.viewAllAccounts.href;

        fetchAccounts(url, request, dispatch);
        // eslint-disable-next-line
    }, []);


    if (loading === true) {
        return (<h1>Loading ....</h1>);
    } else {
        if (error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            if (data.length !== 0) {
                return (<div>
                    <br/>
                    <h1>Accounts</h1>
                    <Table headers={headers} data={data}/>
                    <br/>
                </div>);

            } else {
                return (
                    <div>
                        <h1>No data ....</h1>
                    </div>);
            }
        }
    }
}

export default DisplayAccountsPage;