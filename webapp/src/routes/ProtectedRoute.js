import React, {useContext} from 'react';
import {
    Navigate,
    useLocation,
} from 'react-router-dom';
import AppContext from '../context/AppContext';

const ProtectedRoute = (props) => {
    const { state } =useContext(AppContext);
    const { loggedIn } = state.logIn;
    const location = useLocation();

    if (!loggedIn) {
        return <Navigate to="/login" replace state={{ from: location }} />;
    }

    return props.children;
};

export default ProtectedRoute;