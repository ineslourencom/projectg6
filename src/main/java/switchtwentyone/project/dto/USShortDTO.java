package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class USShortDTO extends RepresentationModel<USShortDTO> {

    public double usID;
    public int storyNumber;
    public String statement;
    public String detail;
    public int priority;
    public int projID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        USShortDTO that = (USShortDTO) o;
        return usID == that.usID
                && storyNumber == that.storyNumber
                && statement.equalsIgnoreCase(that.statement)
                && detail.equalsIgnoreCase(that.detail)
                && priority == that.priority
                && projID == that.projID;

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usID, storyNumber, statement, detail, priority, projID);
    }


}
