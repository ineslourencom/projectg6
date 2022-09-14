function ProjectTypology(props) {


    const handleChange= props.handleChange
    const projectTypology=props.projectTypology
    let projectTypologies = props.state.projectTypologies.data;
    let optionItems = projectTypologies.map((projTyp) =>
        <option value={projTyp.name}>{projTyp.name}</option>
    );
    return (
        <div>
            <select name="ProjectTypologyID" value={projectTypology} onChange={handleChange}>
                <option value=""/>
                {optionItems}
            </select>
        </div>
    )
}

export default ProjectTypology;