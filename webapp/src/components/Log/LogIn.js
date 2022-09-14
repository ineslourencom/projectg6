import React, {useState} from 'react';
import '../../style/login.css';


function LogIn(props) {
    const {data, dispatch} = props;

    const [formInfo, setFormInfo] = useState(data);

    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}

        setFormInfo(newFormInfo);
    }
    const {email, password, profile} = formInfo;
    const submitForm = () => {
        dispatch(formInfo)
    }

    return (

        <div>
                <form onSubmit={submitForm}>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>

                    <label>Email </label>
                    <input type="email" name="email" value={email} required onChange={handleChange}
                           placeholder="Insert your email.."/>
                    <br/>
                    <br/>
                    <label>Password </label>
                    <input type="password" name="password" value={password} required onChange={handleChange}
                           placeholder="Insert your password.."/>
                    <br/>
                    <br/>
                    <label>Profile </label>
                    <select name="profile" value={profile} onChange={handleChange}>
                        <option value="Visitor">Visitor</option>
                        <option value="Administrator">Administrator</option>
                        <option value="Director">Director</option>
                        <option value="User" selected="selected">User</option>

                    </select>
                    <br/>
                    <br/>
                    <input type="submit" value="Log In"/>
                </form>

        </div>
    )


}

export default LogIn;