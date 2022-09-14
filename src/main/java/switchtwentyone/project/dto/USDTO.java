package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class USDTO extends RepresentationModel<USDTO> {

    public double usID;
    public int storyNumber;
    public String statement;
    public String detail;
    public boolean isDecomposed;
    public double parent;
    public int priority;
    public int projID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof USDTO)) return false;
        USDTO usdto = (USDTO) o;
        return storyNumber == usdto.storyNumber && (isDecomposed == usdto.isDecomposed)
                && priority == usdto.priority
                && projID == usdto.projID && usID==(usdto.usID) && statement.equalsIgnoreCase(usdto.statement)
                && detail.equalsIgnoreCase(usdto.detail) && parent==(usdto.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usID, storyNumber, statement, detail, isDecomposed, parent, priority, projID);
    }
}
