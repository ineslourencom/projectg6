package switchtwentyone.project.dto;

import java.time.LocalDate;
import java.util.Objects;

public class NewSprintInfoDTO {
    private LocalDate startDate;

    public NewSprintInfoDTO(LocalDate startDate) {
        this.startDate = startDate;
    }

    public NewSprintInfoDTO() {

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewSprintInfoDTO that = (NewSprintInfoDTO) o;
        return Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate);
    }
}

