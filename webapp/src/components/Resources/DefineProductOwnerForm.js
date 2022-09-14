import React, {useState} from "react";

function DefineProductOwnerForm(props){
    const { submit, projectid } = props;
    const[formInfo, setFormInfo] = useState("");

    const { associatedAccount, startDate, endDate, percentageOfAllocation, costPerHourValue, currency } = formInfo;

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value, "projectID": projectid }

        setFormInfo(newFormInfo);}

        const submitForm = (e) => {
        e.preventDefault()
        submit(formInfo)}

    return (
        <div>
            <form onSubmit={submitForm}>
                <br/>
                <label >Project ID</label>
                <br/>
                <input type="number" name="projectID" value={projectid} required title="Required field" />
                <br/>
                <label>Email</label>
                <br/>
                <input type="email" name="associatedAccount" placeholder="Insert email" value={associatedAccount} required title="Required field" onChange={handleChange}/>
                <br/>
                <br/>
                <label>Start Date</label>
                <br/>
                <input type="date" name="startDate" value={startDate} required max={endDate} onChange={handleChange}/>
                <br/>
                <br/>
                <label>End Date</label>
                <br/>
                <input type="date" name="endDate" value={endDate} required min={startDate} onChange={handleChange}/>
                <br/>
                <br/>
                <label>Allocation Percentage</label>
                <br/>
                <input type="number" name="percentageOfAllocation" required min="0.1" max="1.0" step=".01" value= {percentageOfAllocation} onChange={handleChange}/>
                <br/>
                <br/>
                <label >Cost per hour</label>
                <br/>
                <input type="number" name="costPerHourValue" value={costPerHourValue} required min= "0.1"  step=".01" onChange={handleChange}/>
                <br/>
                <br/>
                <label>Currency</label>
                <br/>
                <select name="currency" id="currency" value={currency} onChange={handleChange}>
                    <option value="Select_Currency">-</option>
                    <option value="EUR">EUR</option>
                    <option value="USD">USD</option>
                    <option value="YEN">YEN</option>
                    <option value="GBP">GBP</option>
                    <option value="AUD">AUD</option>
                    <option value="CAD">CAD</option>
                    <option value=""/>
                </select>
                <br/>
                <br/>
                <input type='submit' value={"Submit"} />
                <br/>
                <br/>
                <br/>

            </form>


        </div>
    );

    }
    export default DefineProductOwnerForm
