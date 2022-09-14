package switchtwentyone.project.dto;

import java.util.Objects;

public class AddUSToSprintInfoDTO {

    private double usID;
    private int effort;

    public AddUSToSprintInfoDTO(double usID, int effort){
        this.usID = usID;
        this.effort = effort;
    }


    public double getUsID() {
        return usID;
    }

    public void setUsID(double usID) {
        this.usID = usID;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddUSToSprintInfoDTO)) return false;
        AddUSToSprintInfoDTO usdto = (AddUSToSprintInfoDTO) o;
        return usID == (usdto.usID)
                && effort == usdto.effort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usID, effort);
    }

}
