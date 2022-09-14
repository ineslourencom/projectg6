function ProfileDropDown(props) {

    const handleChange=props.handleChange
    const profiles =props.profile
    let profile = props.state.profileInfo.data;
    let optionItems = profile.map((profs) =>
        <option value={profs.profileID}>{profs.profileID}</option>
    );
    return (
        <div>
            <label> Profile  </label>
            <select name="ProfileID" value={profiles} onChange={handleChange}>
                <option value=""/>
                {optionItems}
            </select>
        </div>
    )
}

export default ProfileDropDown;