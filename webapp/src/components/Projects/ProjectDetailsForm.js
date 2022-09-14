import React from 'react';


function ProjectDetailsForm(props){
    const { data } = props;


    console.log(JSON.stringify(data) + "__123455__");
    const {code, description, startDate, endDate, sprintDuration, plannedSprints, status } = data;

    return(
        <form>

            <br/>
            <label><strong>Start Date</strong></label>
            <br/>
            <p> {startDate} </p>
            <br/>
            <label><strong>End Date</strong></label>
            <br/>
            <p> {endDate} </p>
            <br/>
            <label><strong>Sprint Duration (weeks)</strong></label>
            <br/>
            <p> {sprintDuration} </p>
            <br/>
            <label><strong>Planned Sprints</strong></label>
            <br/>
            <p> {plannedSprints} </p>
            <br/>
            <label><strong>Status</strong></label>
            <br/>
            <p> {status} </p>
            <br/>
            <label><strong>Description</strong></label>
            <br/>
            <p> {description} </p>
        </form>

    )

}

export default ProjectDetailsForm;