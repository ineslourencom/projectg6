package switchtwentyone.project.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectInfoDTO {
    public int code;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public  int plannedSprints;
    public  int sprintDuration;
    public String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectInfoDTO that = (ProjectInfoDTO) o;
        return code == that.code && plannedSprints == that.plannedSprints && sprintDuration == that.sprintDuration && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, startDate, endDate, plannedSprints, sprintDuration, status);
    }
}
