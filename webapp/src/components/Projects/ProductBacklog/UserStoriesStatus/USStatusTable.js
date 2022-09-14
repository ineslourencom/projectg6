import USStatusTableHeader from "./USStatusTableHeader";
import USStatusTableBody from "./USStatusTableBody";


function USStatusTable(props) {
    const {headers, data} = props;

    return (

        <table align="center">
            <USStatusTableHeader headers={headers}/>
            <USStatusTableBody data={data}/>
        </table>

    );
}
export default USStatusTable;