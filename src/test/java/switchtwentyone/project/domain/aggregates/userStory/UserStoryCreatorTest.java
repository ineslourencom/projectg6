package switchtwentyone.project.domain.aggregates.userStory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryCreatorTest {

    @Test
    void createUserStoryTest() {
        ProjectID projID1 = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber1);
        Text statement1 = Text.write("Thisisstatement1");
        Text detail = Text.write("Thisisadetail");
        int usPriority1 = 1;
        UserStory newUserStory1 = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projID1);

        UserStory actual = UserStoryCreator.createUserStory(usID1, usNumber1, statement1, detail, usPriority1, projID1);

        assertEquals(newUserStory1, actual);

    }


}