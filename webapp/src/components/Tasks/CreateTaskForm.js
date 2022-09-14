import React, {useEffect, useState} from "react";
import ActiveAccountsSearch from "./ActiveAccounts/ActiveAccountsSearch";
import USNotDoneInSprintSearch from "./USNotDoneInSprint/USNotDoneInSprintSearch";
import {fetchRunningSprint} from "../../context/Actions";

function CreateTaskForm(props) {
    const [formInfo, setFormInfo] = useState("");
    const {dispatch} = props;
    const handleChange = (event) => {
        event.preventDefault();
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch(formInfo)
    }


    let {
        taskName,
        taskDescription,
        taskStartDate,
        taskEndDate,
        taskEffortEstimate,
        taskType,
        taskResourceID,
        containerID,
        option
    } = formInfo


    useEffect(() => {
        const newFormInfo = {...formInfo, option: props.option, containerID: props.containerID}
        setFormInfo(newFormInfo);
    }, []);


    if (option === "Sprint") {
        return (
            <form>
                <h2>Create task form:</h2>
                <h3>This task will be related to the current running sprint</h3>
                <br/>
                <br/>
                <label>Name: </label>
                <br/>
                <input type='text' name={"name"} placeholder="Insert task name.." value={taskName} required
                       title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>Description: </label>
                <br/>
                <input type='text' name={"description"} placeholder="Insert task description.."
                       value={taskDescription}
                       required title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>StartDate :</label>
                <br/>
                <input type='date' name={"startDate"} value={taskStartDate} required max={taskEndDate}
                       title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>End Date: </label>
                <br/>
                <input type='date' name={"endDate"} value={taskEndDate} required min={taskStartDate}
                       title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>Effort Estimate (Fibonacci Sequence): </label>
                <br/>
                <select defaultValue={1} name={"effortEstimate"} value={taskEffortEstimate} onChange={handleChange}>
                    <option value/>
                    <option value={1}>1</option>
                    <option value={2}> 2</option>
                    <option value={3}> 3</option>
                    <option value={5}> 5</option>
                    <option value={8}> 8</option>
                    <option value={13}> 13</option>
                    <option value={21}> 21</option>
                </select>
                <br/>
                <label>Type: </label>
                <br/>
                <select name={"type"} value={taskType} onChange={handleChange}>
                    <option value={"Meeting"}>Meeting</option>
                    <option value={"Documentation"}> Documentation</option>
                    <option value={"Design"}> Design</option>
                    <option value={"Implementation"}> Implementation</option>
                    <option value={"Testing"}> Testing</option>
                    <option value={"Deployment"}> Deployment</option>
                </select>
                <br/>
                <label>Responsible*: </label>
                <br/>
                <ActiveAccountsSearch handleChange={handleChange} activeAccount={taskResourceID}/>
                <br/>
                <input type="button" value={"Submit"} onClick={submitForm}/>
                <br/>
                <br/>
                <h6>*(Note that you can only choose an active resource from the selected project)</h6>

            </form>)

    } else if(option === "User Story") {
        return (
            <form>
                <h2>Create task form:</h2>
                <br/>
                <label>Your task will be  relates to an user story, please choose from the options bellow*: </label>
                <br/>
                <USNotDoneInSprintSearch handleChange={handleChange} containerID={containerID}/>
                <br/>
                <label>Name: </label>
                <br/>
                <input type='text' name={"name"} placeholder="Insert task name.." value={taskName} required
                       title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>Description: </label>
                <br/>
                <input type='text' name={"description"} placeholder="Insert task description.."
                       value={taskDescription}
                       required title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>StartDate :</label>
                <br/>
                <input type='date' name={"taskStartDate"} value={taskStartDate}  max={taskEndDate}
                       title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>End Date: </label>
                <br/>
                <input type='date' name={"taskEndDate"} value={taskEndDate}  min={taskStartDate}
                       title="Required field"
                       onChange={handleChange}/>
                <br/>
                <label>Effort Estimate (Fibonacci Sequence): </label>
                <br/>
                <select name={"effortEstimate"} value={taskEffortEstimate} onChange={handleChange}>
                    <option/>
                    <option value={1}>1</option>
                    <option value={2}> 2</option>
                    <option value={3}> 3</option>
                    <option value={5}> 5</option>
                    <option value={8}> 8</option>
                    <option value={13}> 13</option>
                    <option value={21}> 21</option>
                </select>
                <br/>
                <label>Type: </label>
                <br/>
                <select name={"type"} value={taskType} onChange={handleChange}>
                    <option value={"Meeting"}>Meeting</option>
                    <option value={"Documentation"}> Documentation</option>
                    <option value={"Design"}> Design</option>
                    <option value={"Implementation"}> Implementation</option>
                    <option value={"Testing"}> Testing</option>
                    <option value={"Deployment"}> Deployment</option>
                </select>
                <br/>
                <label>Responsible**: </label>
                <br/>
                <ActiveAccountsSearch handleChange={handleChange} activeAccount={taskResourceID}/>
                <br/>
                <input type="button" value={"Submit"} onClick={submitForm}/>
                <br/>
                <br/>
                <h6>*(Note that you can only choose from  User Stories that are in the current Sprint, and are not yet done)</h6>
                <h6>**(Note that you can only choose an active resource from the selected project)</h6>

            </form>
        )
    }
}

export default CreateTaskForm;