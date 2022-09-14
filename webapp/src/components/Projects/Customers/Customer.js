function Customer(props) {


    const handleChange= props.handleChange
    const customer=props.customer

    let customers = props.state.customer.data;
    let optionItems = customers.map((cust) =>
        <option value={cust.NIF}>{cust.name}</option>
    );
    return (
        <div>
            <select name="CustomerID" value={customer} onChange={handleChange}>
                <option value=""/>
                {optionItems}
            </select>
        </div>
    )
}

export default Customer;