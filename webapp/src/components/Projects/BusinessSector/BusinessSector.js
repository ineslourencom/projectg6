function BusinessSector(props) {


    const handleChange= props.handleChange
    const businessSector=props.businessSector

    let businessSectors = props.state.businessSector.data;
    let optionItems = businessSectors.map((busSect) =>
        <option value={busSect.code}>{busSect.description}</option>
    );
    return (
        <div>
            <select name="BusinessSectorID" value={businessSector} onChange={handleChange}>
                <option value=""/>
                {optionItems}
            </select>
        </div>
    )
}

export default BusinessSector;