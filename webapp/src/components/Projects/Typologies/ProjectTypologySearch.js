import {useContext, useEffect} from "react";
import AppContext from "../../../context/AppContext";
import {fetchProjectTypInfo} from "../../../context/Actions";
import ProjectTypology from "./ProjectTypology";

function ProjectTypologySearch(props) {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;


    useEffect(() => {

        let url = null;
        for (let i = 0; i < projects.data[0].links.length; i++) {
            if (projects.data[0].links[i].rel == "allTypologies") {
                url = projects.data[0].links[i].href;
            }
        }

        const request = {};

        fetchProjectTypInfo(url, request, dispatch);

    }, []);

    return <ProjectTypology state={state}  handleChange={props.handleChange}  projectTypology={props.projectTypology}/>
}

export default ProjectTypologySearch;
