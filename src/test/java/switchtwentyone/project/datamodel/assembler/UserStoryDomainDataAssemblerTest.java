package switchtwentyone.project.datamodel.assembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.UserStoryJPA;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserStoryDomainDataAssemblerTest {

    @Test
    void toData() {
        //Arrange
        ProjectID projID1 = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        UserStory newUserStory1 = mock(UserStory.class);
        UserStoryID parent = UserStoryID.ofProjIDAndUsNumber( new ProjectID(1), PositiveNumber.of(2));

        when(newUserStory1.getUsID()).thenReturn(usID1);
        when(newUserStory1.getStoryNumber()).thenReturn(usNumber1);
        when(newUserStory1.getProjectID()).thenReturn(projID1);
        when(newUserStory1.getStatement()).thenReturn(statement1);
        when(newUserStory1.getDetail()).thenReturn(detail);
        when(newUserStory1.getPriority()).thenReturn(1);
        when(newUserStory1.getParent()).thenReturn(parent);
        UserStoryDomainDataAssembler usAssembler = new UserStoryDomainDataAssembler();

        //Act
        UserStoryJPA convertedUserStoryJPA1 = usAssembler.toData(newUserStory1);
        UserStoryJPA convertedUserStoryJPA2 = usAssembler.toData(newUserStory1);

        //Assert
        assertEquals(convertedUserStoryJPA1, convertedUserStoryJPA2);

    }

    @Test
    void toDomain() {
        //Arrange
        UserStoryJPA usJPA = mock(UserStoryJPA.class);
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        when(usJPA.getStoryNumber()).thenReturn(1);
        when(usJPA.getUsID()).thenReturn(usID);
        when(usJPA.getStatement()).thenReturn("statement");
        when(usJPA.getDetail()).thenReturn("detail");
        when(usJPA.getPriority()).thenReturn(1);
        when(usJPA.getProjectID()).thenReturn(1);
        UserStoryDomainDataAssembler usAssembler = new UserStoryDomainDataAssembler();


        //Act
        UserStory convertedUserStory1 = usAssembler.toDomain(usJPA);
        UserStory convertedUserStory2 = usAssembler.toDomain(usJPA);

        //Assert
        assertEquals(convertedUserStory1, convertedUserStory2);

    }
}
