import React, {useContext, useState} from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import CreateUSPage from '../pages/CreateUSPage';
import DisplayProjectsPage from "../pages/DisplayProjectsPage";
import ProfilePage from '../pages/ProfilesCreateAndUpdate';
import CreateProjectPage from "../pages/CreateProjectPage";
import LogInPage from "../pages/LogInPage";
import AccountPage from "../pages/AccountsSearchAndUpdatePage";
import CreateProjectTypologyPage from "../pages/CreateProjectTypologyPage";
import ProjectDetailsPage from "../pages/ProjectDetailsPage";
import CreateResourcePage from "../pages/CreateResourcePage";
import CreateSprintPage from "../pages/CreateSprintPage";
import ProductBacklogPage from "../pages/ProductBacklog";
import DefineScrumMasterPage from "../pages/DefineScrumMasterPage";
import DisplayUserStoriesStatusPage from "../pages/DisplayUserStoriesStatusPage"
import ProtectedRoute from "./ProtectedRoute";
import '../style/navigation.css';
import AppContext from "../context/AppContext";
import DefineProductOwnerPage from "../pages/DefineProductOwnerPage";
import ResourcesPage from "../pages/ResourcesPage";
import ScrumBoardPage from "../pages/ScrumBoardPage";
import DisplayTasksPage from "../pages/DisplayTasksPage";
import CreateTaskPage from "../pages/CreateTaskPage";
import DisplayAccountsPage from "../pages/DisplayAccountsPage";
import DecomposeUSPage from "../components/UserStories/DecomposeUSPage";
import ViewAllProfilesPage from "../pages/ViewAllProfiles";
import CreateTypologyPageGeneral from "../pages/CreateTypologyPageGeneral";


function MainRoute() {
    const {state, dispatch} = useContext(AppContext);
    const {logIn} = state;
    const {loggedIn, email, profile} = logIn;
    const { profilePage } = state;
    const { accountPage } = state;
    const data = {"email": email, "profile": ""}

    if (loggedIn) {
        return (
            <Router>
                <div>
                    <nav className={"main"}>
                        <ul>
                            <li><a><Link to="/projects">Project Management</Link></a></li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Profiles</a>
                                <div className="dropdown-content">
                                    <a> <Link to='/profiles/createProfile' onClick={() => {profilePage.create = true; profilePage.update = false;}}>Create Profile</Link></a>
                                    <a> <Link to="/profiles/updateProfile" onClick={() => {profilePage.create = false; profilePage.update = true;}}>Update Profile</Link></a>
                                    <a> <Link to="/allProfiles">View All Profiles</Link></a>
                                </div>
                            </li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Accounts</a>
                                <div className="dropdown-content">
                                    <a> <Link to='/accounts/searchAccount' onClick={() => {accountPage.search = true; accountPage.viewOwn = false;}}>Search Account</Link></a>
                                    <a> <Link to='/accounts/listAccounts' onClick={() => {accountPage.search = true; accountPage.viewOwn = false;}}>All Accounts</Link></a>
                                </div>
                            </li>
                            <li><a><Link to={"/projects/projectTypologies"}>Typologies</Link></a></li>

                            <li><a className={"active"}><Link to="/login">User</Link></a></li>
                        </ul>
                    </nav>
                    <Routes>
                        <Route path="/" element={<LogInPage/>}/>
                        <Route path="/login" element={<LogInPage/>}/>
                        <Route path="/projects/:id"
                               element={<ProtectedRoute>
                                   <ProjectDetailsPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/userStories"
                               element={
                                   <ProtectedRoute>
                                       <CreateUSPage/>
                                   </ProtectedRoute>}/>
                        <Route path="/profiles"
                               element={<ProtectedRoute>
                                   <ProfilePage/>
                               </ProtectedRoute>}/>
                        <Route path="/profiles/createProfile"
                               element={<ProtectedRoute>
                                   <ProfilePage/>
                               </ProtectedRoute>}/>
                        <Route path="/profiles/updateProfile"
                               element={<ProtectedRoute>
                                   <ProfilePage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts"
                               element={<ProtectedRoute>
                                   <AccountPage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts/searchAccount"
                               element={<ProtectedRoute>
                                   <AccountPage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts/listAccounts"
                               element={<ProtectedRoute>
                                   <DisplayAccountsPage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts/viewOwnAccount"
                               element={<ProtectedRoute>
                                   <AccountPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects"
                               element={<ProtectedRoute>
                                   <DisplayProjectsPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/create"
                               element={<ProtectedRoute>
                                   <CreateProjectPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/projectTypologies"
                               element={<ProtectedRoute>
                                   <CreateProjectTypologyPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources"
                               element={<ProtectedRoute>
                                   <ResourcesPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources/create"
                               element={<ProtectedRoute>
                                   <CreateResourcePage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources/productOwner"
                               element={<ProtectedRoute>
                                   <DefineProductOwnerPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/sprints"
                               element={<ProtectedRoute>
                                   <CreateSprintPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/productBacklog"
                               element={<ProtectedRoute>
                                   <ProductBacklogPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/userStoriesStatus"
                               element={<ProtectedRoute>
                                   <DisplayUserStoriesStatusPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources/scrumMaster"
                               element={<ProtectedRoute>
                                   <DefineScrumMasterPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/scrumBoard"
                               element={<ProtectedRoute>
                                   <ScrumBoardPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/tasks"
                               element={<ProtectedRoute>
                                   <DisplayTasksPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/tasks/create"
                               element={<ProtectedRoute>
                                   <CreateTaskPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/userStories/decompose"
                               element={<ProtectedRoute>
                                   <DecomposeUSPage/>
                               </ProtectedRoute>}/>
                        <Route path="/allProfiles"
                               element={<ProtectedRoute>
                                   <ViewAllProfilesPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/projectTypologies"
                               element={<ProtectedRoute>
                                   <CreateTypologyPageGeneral/>
                               </ProtectedRoute>}/>

                    </Routes>

                </div>

            </Router>

        );
    } else {
        return (
            <Router>
                <div>
                    <nav className={"main"}>
                        <ul>
                            <li><a><Link to="/projects">Project Management</Link></a></li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Profiles</a>
                                <div className="dropdown-content">
                                    <a> <Link to='/profiles/createProfile' onClick={() => {profilePage.create = true; profilePage.update = false;}}>Create Profile</Link></a>
                                    <a> <Link to="/profiles/updateProfile" onClick={() => {profilePage.create = false; profilePage.update = true;}}>Update Profile</Link></a>
                                </div>
                            </li>
                            <li className="dropdown">
                                <a href="javascript:void(0)" className="dropbtn">Accounts</a>
                                <div className="dropdown-content">
                                    <a> <Link to='/accounts/searchAccount' onClick={() => {accountPage.search = true; accountPage.viewOwn = false;}}>Search Account</Link></a>
                                </div>
                            </li>
                            <li><a><Link to={"/projects/projectTypologies"}>Typologies</Link></a></li>
                            <li><a><Link to="/login">User</Link></a></li>
                        </ul>
                    </nav>
                    <Routes>
                        <Route path="/" element={<LogInPage/>}/>
                        <Route path="/login" element={<LogInPage/>}/>
                        <Route path="/projects/:id"
                               element={<ProtectedRoute>
                                   <ProjectDetailsPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/userStories"
                               element={
                                   <ProtectedRoute>
                                       <CreateUSPage/>
                                   </ProtectedRoute>}/>
                        <Route path="/profiles"
                               element={<ProtectedRoute>
                                   <ProfilePage/>
                               </ProtectedRoute>}/>
                        <Route path="/profiles/createProfile"
                               element={<ProtectedRoute>
                                   <ProfilePage/>
                               </ProtectedRoute>}/>
                        <Route path="/profiles/updateProfile"
                               element={<ProtectedRoute>
                                   <ProfilePage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts"
                               element={<ProtectedRoute>
                                   <AccountPage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts/searchAccount"
                               element={<ProtectedRoute>
                                   <AccountPage/>
                               </ProtectedRoute>}/>
                        <Route path="/accounts/viewOwnAccount"
                               element={<ProtectedRoute>
                                   <AccountPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects"
                               element={<ProtectedRoute>
                                   <DisplayProjectsPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/create"
                               element={<ProtectedRoute>
                                   <CreateProjectPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/projectTypologies"
                               element={<ProtectedRoute>
                                   <CreateProjectTypologyPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources"
                               element={<ProtectedRoute>
                                   <ResourcesPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources/create"
                               element={<ProtectedRoute>
                                   <CreateResourcePage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources/productOwner"
                               element={<ProtectedRoute>
                                   <DefineProductOwnerPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/sprints"
                               element={<ProtectedRoute>
                                   <CreateSprintPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/productBacklog"
                               element={<ProtectedRoute>
                                   <ProductBacklogPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/userStoriesStatus"
                               element={<ProtectedRoute>
                                   <DisplayUserStoriesStatusPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/resources/scrumMaster"
                               element={<ProtectedRoute>
                                   <DefineScrumMasterPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/scrumBoard"
                               element={<ProtectedRoute>
                                   <ScrumBoardPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/tasks"
                               element={<ProtectedRoute>
                                   <DisplayTasksPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/:id/tasks/create"
                               element={<ProtectedRoute>
                                   <CreateTaskPage/>
                               </ProtectedRoute>}/>

                        <Route path="/projects/:id/userStories/decompose"
                               element={<ProtectedRoute>
                                   <DecomposeUSPage/>
                               </ProtectedRoute>}/>
                        <Route path="/allProfiles"
                               element={<ProtectedRoute>
                                   <ViewAllProfilesPage/>
                               </ProtectedRoute>}/>
                        <Route path="/projects/projectTypologies"
                               element={<ProtectedRoute>
                                   <CreateTypologyPageGeneral/>
                               </ProtectedRoute>}/>

                    </Routes>

                </div>

            </Router>

        );
    }
}

export default MainRoute;