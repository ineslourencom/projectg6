import React from 'react';

function USStatusTableHeader(props){
    const {headers} = props;

    return(
        <thead>
        <tr>
            <th style={{width:"50px"}}>{headers.storyNumber}</th>
            <th style={{width:"200px"}}>{headers.statement}</th>
            <th style={{width:"200px"}}>{headers.detail}</th>
            <th style={{width:"50px"}}>{headers.priority}</th>
            <th style={{width:"200px"}}>{headers.usStatus}</th>
        </tr>
        </thead>
    );

}
export default USStatusTableHeader;