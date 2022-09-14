package switchtwentyone.project.domain.aggregates.userStory;

import org.jetbrains.annotations.NotNull;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;

public class UserStory implements Entity<UserStory>, Comparable<UserStory> {

    /**
     * The attribute "usID" is the unique id of the user story within the whole application.
     */
    private UserStoryID usID;
    /**
     * The attribute "storyNumber" is the unique id of the user story within
     * its own project.
     */
    private PositiveNumber storyNumber;
    /**
     * The attribute "statement" is the user story as it is presented to devs.
     */
    private Text statement;
    /**
     * The attribute "detail" is a more detailed explanation of the user story.
     */
    private Text detail;
    /**
     * The attribute "iDecomposed" reveals if the US has originated child USs, this means, if it is a parent US.
     */
    private boolean decomposed;


    /**
     * The attribute "parent" reveals if this US was generated by the decomposition of other US. In case it was, the ID
     * of the parentUS is recorded here.
     */
    private UserStoryID parent;
    /**
     * The attribute "priority" shows the priority of the US in the product backlog.
     */
    private int priority;

    /**
     * The user story knows the project it is associated to and therefore the projectID is stored as an attribute.
     */
    private ProjectID projID;


    public UserStory(UserStoryID usID, PositiveNumber storyNumber, Text statement, Text detail, int priority, ProjectID projID) {
        this.usID = usID;
        this.storyNumber = storyNumber;
        this.statement = statement;
        this.detail = detail;
        this.priority = priority;
        this.decomposed = false;
        this.projID = projID;
    }

    public boolean hasID(UserStoryID id) {
        return this.usID.sameValueAs(id);

    }


    /**
     * Method to check if the code passed is higher that the US's.
     */
    public boolean isCodeHigher(int code) {
        boolean result = false;
        if (this.storyNumber.getNumber() < code) {
            result = true;
        }
        return result;
    }


    /**
     * Marks the status of a user story as decomposed
     *
     * @return newly defined status
     */

    public void markAsDecomposed() {
        this.decomposed = true;
    }

    /**
     * Determines if the number of a user story is equal to the one passed in as a parameter.
     *
     * @param number number to be compared.
     * @return true if user story number is the same as the one passed in as a parameter.
     */

    public boolean hasNumber(final int number) {
        return this.storyNumber.getNumber() == number;
    }

    /**
     * Defines the parent of a user story.
     *
     * @param parent user story that originated calling object
     * @return true if parenthood definition was successful.
     */
    public boolean defineParenthood(UserStory parent) {
        boolean done = false;
        if (this.parent == null && (!this.equals(parent))) {
            this.parent = parent.usID;
            done = true;
        }
        return done;
    }

    public boolean hasProjectID(ProjectID projectID) {
        boolean result = false;
        if (this.projID.equals(projectID)) {
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "UserStory: " + storyNumber.getNumber() +
                " Statement: " + statement.toString() + " Priority: " + priority;
    }

    @Override
    public boolean sameIdentityAs(final UserStory other) {
        return other != null && this.usID.equals(other.usID);
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final UserStory other = (UserStory) object;
        return sameIdentityAs(other);
    }

    /**
     * @return Hash code of account id.
     */
    @Override
    public int hashCode() {
        return usID.hashCode();
    }


    public UserStoryID getUsID() {
        return UserStoryID.ofProjIDAndUsNumber(this.projID, this.storyNumber);
    }

    public PositiveNumber getStoryNumber() {
        return PositiveNumber.of(this.storyNumber.getNumber());
    }

    public Text getStatement() {
        return Text.write(this.statement.getValue());
    }

    public Text getDetail() {
        return Text.write(this.detail.getValue());
    }

    public boolean getIsDecomposed(){return this.decomposed;}

    public UserStoryID getParent() {
        UserStoryID result = null;
        if (this.parent != null) {
            result = this.parent;
        }
        return result;
    }

    public void setParent(UserStoryID usID) {
        this.parent = usID;
    }

    public int getPriority() {
        return this.priority;
    }

    /**
     * This method is called, for example, when adding a US to the sprint Backlog (priority turns 0).
     *
     * @param priority the number to define priority of user story
     * @return
     */
    public void setPriority(final int priority) {
        this.priority = priority;
    }

    public ProjectID getProjectID() {
        return new ProjectID(this.projID.getProjectID());
    }

    @Override
    public int compareTo(@NotNull UserStory o) {
        if (usID.sameValueAs(o.usID)) {
            return 0;
        } else {
            return -1;
        }
    }
}
