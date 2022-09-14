function USNotDoneInSprint(props) {


    const handleChange = props.handleChange
    const containerID = props.containerID

    let userStories = props.state.usNotDoneInRunningSprint.data;
    let optionItems = userStories.map((us) =>
        <option value={us.usID}>US nº{us.storyNumber} - {us.statement}  </option>
    );
    return (
            <select name="containerID" value={containerID} onChange={handleChange}>
                {optionItems}
            </select>
    )
}

export default USNotDoneInSprint;