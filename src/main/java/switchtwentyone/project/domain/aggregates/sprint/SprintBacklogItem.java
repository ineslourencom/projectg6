package switchtwentyone.project.domain.aggregates.sprint;

import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Entity;

import java.util.Objects;

public class SprintBacklogItem implements Entity<SprintBacklogItem> {

    SprintBacklogItemID itemID;
    UserStoryID usID;
    ScrumBoardCategory category;
    FibonacciNumber effortEstimate;

    public SprintBacklogItem(SprintBacklogItemID itemID, UserStoryID usID, FibonacciNumber effortEstimate){
        this.itemID = itemID;
        this.usID = usID;
        this.category = ScrumBoardCategory.toDo();
        this.effortEstimate = effortEstimate;
    }

    public SprintBacklogItemID getItemID() {
        return this.itemID;
    }

    public void setItemID(SprintBacklogItemID itemID) {
        this.itemID = itemID;
    }

    public UserStoryID getUsID() {
        return UserStoryID.ofDouble(usID.getID());
    }

    public void setUsID(UserStoryID usID) {
        this.usID = usID;
    }

    public ScrumBoardCategory getCategory() {
        return category;
    }

    public void setCategory(ScrumBoardCategory category) {
        this.category = category;
    }

    public FibonacciNumber getEffortEstimate() {
        return FibonacciNumber.of(effortEstimate.getNumber());
    }

    public void setEffortEstimate(FibonacciNumber effortEstimate) {
        this.effortEstimate = effortEstimate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, usID, category, effortEstimate);
    }

    @Override
    public boolean sameIdentityAs(SprintBacklogItem other) {
        return other!= null && this.itemID.equals(other.itemID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintBacklogItem)) return false;
        SprintBacklogItem that = (SprintBacklogItem) o;
        return sameIdentityAs(that);
    }

}
