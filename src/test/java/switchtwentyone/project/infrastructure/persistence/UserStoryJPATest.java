package switchtwentyone.project.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.datamodel.UserStoryJPA;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryJPATest {

    @Test
    void getId() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        UserStoryID usJPAID = usJPA.getUsID();

        //Assert
        assertEquals(usJPAID, usID1);
    }

    @Test
    void setId() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        PositiveNumber usNumber2 = PositiveNumber.of(2);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        UserStoryID usID2 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setUsID(usID2);

        //Assert
        assertEquals(usJPA.getUsID(), usID2);
    }


    @Test
    void notEqualToNull(){
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        PositiveNumber usNumber2 = PositiveNumber.of(2);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        UserStoryID usID2 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);
        UserStoryJPA usJPA1 = new UserStoryJPA(dto);

        //Act
        boolean result = usJPA.equals(null);

        assertFalse(result);
    }

    @Test
    void equalToItSelf(){
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        PositiveNumber usNumber2 = PositiveNumber.of(2);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        UserStoryID usID2 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        boolean result = usJPA.equals(usJPA);

        assertTrue(result);
    }


    @Test
    void getStoryNumber() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        int usNumber = usJPA.getStoryNumber();

        //Assert
        assertEquals(1, usNumber);
    }

    @Test
    void setStoryNumber() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setStoryNumber(2);

        //Assert
        assertEquals(2, usJPA.getStoryNumber());
    }

    @Test
    void getStatement() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        String statement = usJPA.getStatement();

        //Assert
        assertEquals(statement1.getValue(), statement);
    }

    @Test
    void setStatement() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setStatement("Statement");

        //Assert
        assertEquals("Statement", usJPA.getStatement());
    }

    @Test
    void testGetDetail() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        String newDetail = usJPA.getDetail();
        boolean result = newDetail.equalsIgnoreCase("This is a detail");

        //Assert
        assertTrue(result);
    }

    @Test
    void testSetDetail() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setDetail("newDetail");

        //Assert
        assertEquals("newDetail", usJPA.getDetail());
    }

    @Test
    void isDecomposed() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        boolean decomposed = usJPA.isDecomposed();

        //Assert
        assertEquals(false, decomposed);
    }

    @Test
    void setDecomposed() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setDecomposed(true);

        //Assert
        assertEquals(true, usJPA.isDecomposed());
    }

    @Test
    void getPriority() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        int priority = usJPA.getPriority();

        //Assert
        assertEquals(1, priority);
    }

    @Test
    void setPriority() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setPriority(2);

        //Assert
        assertEquals(2, usJPA.getPriority());
    }

    @Test
    void testGetProjectID() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        int projectID1 = usJPA.getProjectID();

        //Assert
        assertEquals(1, projectID1);
    }

    @Test
    void setProjectID(){
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        usJPA.setProjectID(2);

        //Assert
        assertEquals(2, usJPA.getProjectID());

    }

    @Test
    void getUSIDparent() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getIsDecomposed()).thenReturn(false);
        USDTO dto = UserStoryMapper.toDTO(us);
        UserStoryJPA usJPA = new UserStoryJPA(dto);

        //Act
        double usIDParent = usJPA.getUsIDParent();

        //Assert
        assertEquals(usID1.getID(), usIDParent);
    }
}
