import TaskStatusTableHeader from "./TaskStatusTableHeader";
import TaskStatusTableBody from "./TaskStatusTableBody";


function TaskStatusTable(props) {
    const {headers, data} = props;

    return (

        <table align="center">
            <TaskStatusTableHeader headers={headers}/>
            <TaskStatusTableBody data={data}/>
        </table>

    );
}
export default TaskStatusTable;