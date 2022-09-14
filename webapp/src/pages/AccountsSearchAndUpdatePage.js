import React, {useContext} from "react";
import SearchAccountForm from "../components/Accounts/SearchAccountForm";
import {Outlet} from "react-router-dom";


function AccountsSearchAndUpdatePage() {

    return (
        <div>
            <div>
                <h1>Accounts</h1>
                <div>
                   <SearchAccountForm/>
                </div>

                <Outlet/>
            </div>

        </div>
    )
}

export default AccountsSearchAndUpdatePage;