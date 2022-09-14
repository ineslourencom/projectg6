import React, {useContext} from 'react';
import AppContext from "../../../context/AppContext";


function ScrumBoardTable(props) {
    const { state } = useContext(AppContext);
    const { scrumBoard } = state;
    const { data } = scrumBoard;
    const {headers} = props;
    const sprintRunning = data.map((us, index) => {
        if (index === 0) {
            return (
                <p key={index}>
                    The Sprint currently running is {us.sprintID}
                </p>
            );
        }
    });

    console.log(JSON.stringify(data) + "This is the second test ____xxxxxx____xxxx")


    const rowsToDo = data.map((rowToDo, index) => {
        if(rowToDo.usStatus === "To do"){
        return (
            <tr key={index}>
                <td>{rowToDo.storyNumber}</td>
                <td>{rowToDo.statement}</td>
            </tr>
        );}
    });

    const rowsInProgress = data.map((rowInProgress, index) => {
        if(rowInProgress.usStatus === "In progress"){
            return (
                <tr key={index}>
                    <td>{rowInProgress.storyNumber}</td>
                    <td>{rowInProgress.statement}</td>
                </tr>
            );}
    });

    const rowsCodeReview = data.map((rowCodeReview, index) => {
        if(rowCodeReview.usStatus === "Code review"){
            return (
                <tr key={index}>
                    <td>{rowCodeReview.storyNumber}</td>
                    <td>{rowCodeReview.statement}</td>
                </tr>
            );}
    });

    const rowsRejected = data.map((rowDone, index) => {
        if(rowDone.usStatus === "Done"){
            return (
                <tr key={index}>
                    <td>{rowDone.storyNumber}</td>
                    <td>{rowDone.statement}</td>
                </tr>
            );}
    });

    const rowsDone = data.map((rowRejected, index) => {
        if(rowRejected.usStatus === "Rejected"){
            return (
                <tr key={index}>
                    <td>{rowRejected.storyNumber}</td>
                    <td>{rowRejected.statement}</td>
                </tr>
            );}
    });

    return (
        <div>
            <h2> To Do </h2>
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>{headers.storyNumber}</th>
                    <th>{headers.statement}</th>
                </tr>
                </thead>
                <tbody>{rowsToDo}</tbody>
            </table>
            <br/>
            <br/>
            <h2> In Progress </h2>
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>{headers.storyNumber}</th>
                    <th>{headers.statement}</th>
                </tr>
                </thead>
                <tbody>{rowsInProgress}</tbody>
            </table>
            <br/>
            <br/>
            <h2> Code Review </h2>
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>{headers.storyNumber}</th>
                    <th>{headers.statement}</th>
                </tr>
                </thead>
                <tbody>{rowsCodeReview}</tbody>
            </table>
            <br/>
            <br/>
            <h2> Done </h2>
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>{headers.storyNumber}</th>
                    <th>{headers.statement}</th>
                </tr>
                </thead>
                <tbody>{rowsDone}</tbody>
            </table>
            <br/>
            <br/>
            <h2> Rejected </h2>
            <table border="1" align="center">
                <thead>
                <tr>
                    <th>{headers.storyNumber}</th>
                    <th>{headers.statement}</th>
                </tr>
                </thead>
                <tbody>{rowsRejected}</tbody>
            </table>
        </div>
    );
}

export default ScrumBoardTable;