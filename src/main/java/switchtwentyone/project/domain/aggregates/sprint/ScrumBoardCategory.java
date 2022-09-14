package switchtwentyone.project.domain.aggregates.sprint;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class ScrumBoardCategory implements ValueObject<ScrumBoardCategory> {

    private Category category;

    private ScrumBoardCategory(Category cat){
        this.category = cat;
    }

    public static ScrumBoardCategory toDo(){
        return new ScrumBoardCategory(Category.To_do);
    }

    public static ScrumBoardCategory inProgress(){
        return new ScrumBoardCategory(Category.In_progress);
    }

    public static ScrumBoardCategory codeReview(){
        return new ScrumBoardCategory(Category.Code_review);
    }

    public static ScrumBoardCategory done(){
        return new ScrumBoardCategory(Category.Done);
    }

    public static ScrumBoardCategory rejected(){
        return new ScrumBoardCategory(Category.Rejected);
    }

    public static ScrumBoardCategory dbConverter(String dbName){
        return new ScrumBoardCategory(Category.fromDbName(dbName));
    }

    public Category getCategory(){
        return this.category;
    }


    @Override
    public boolean sameValueAs(ScrumBoardCategory other) {
        return other != null
                && this.category == other.category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrumBoardCategory other = (ScrumBoardCategory) o;
        return this.sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

    @Override
    public String toString(){
        return category.toString();
    }
}
