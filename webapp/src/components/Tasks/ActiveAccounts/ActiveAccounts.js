function ActiveAccounts(props) {


    const handleChange = props.handleChange
    const activeAccount = props.activeAccount

    let activeAccounts = props.state.resources.data;
    let optionItems = activeAccounts.map((actAccount) =>
        <option value={actAccount.resourceID}>{actAccount.associatedAccount}</option>
    );
    return (
        <select name="activeAccount" value={activeAccount} onChange={handleChange}>
            <option value=""/>
            {optionItems}
        </select>
    )
}

export default ActiveAccounts;