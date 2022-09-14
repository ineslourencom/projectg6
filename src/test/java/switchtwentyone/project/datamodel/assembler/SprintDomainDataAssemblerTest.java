package switchtwentyone.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.datamodel.SprintJPA;
import switchtwentyone.project.datamodel.SprintbacklogItemJPA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintDomainDataAssemblerTest {

    @Test
    void toData() {
        //Arrange
        ProjectID projectID = new ProjectID(2);
        PositiveNumber sprintNumber = PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projectID,sprintNumber);
        LocalDate startDate = LocalDate.of(2022,2,20);
        Sprint test = mock(Sprint.class);
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        List<SprintBacklogItem> list = new ArrayList<>();
        SprintBacklogItem item1 = mock(SprintBacklogItem.class);
        SprintBacklogItem item2 = mock(SprintBacklogItem.class);
        list.add(item1);
        list.add(item2);

        when(test.getSprintID()).thenReturn(sprintID);
        when(test.getSprintNumber()).thenReturn(2);
        when(test.getProjectID()).thenReturn(2);
        when(test.getStartDate()).thenReturn(startDate);
        when(test.getSprintDuration()).thenReturn(sprintDuration);
        when(test.getSprintBacklogItems()).thenReturn(list);

        SprintBacklogItemID id = SprintBacklogItemID.createSprintBacklogItemID("7aa0217e-da29-4f37-9ecf-d406d385729f");
        UserStoryID usID = UserStoryID.ofDouble(1.001);
        SprintBacklogItemID id2 = SprintBacklogItemID.createSprintBacklogItemID("e74b8af1-2794-49b3-9e1c-e20650dad721");
        UserStoryID usID2 = UserStoryID.ofDouble(1.002);
        ScrumBoardCategory category = ScrumBoardCategory.toDo();
        FibonacciNumber effort = FibonacciNumber.of(1);
        when(item1.getItemID()).thenReturn(id);
        when(item1.getUsID()).thenReturn(usID);
        when(item1.getCategory()).thenReturn(category);
        when(item1.getEffortEstimate()).thenReturn(effort);

        when(item2.getItemID()).thenReturn(id2);
        when(item2.getUsID()).thenReturn(usID2);
        when(item2.getCategory()).thenReturn(category);
        when(item2.getEffortEstimate()).thenReturn(effort);


        SprintDomainDataAssembler assembler = new SprintDomainDataAssembler();

        //Act
        SprintJPA result= assembler.toData(test);
        SprintJPA result1= assembler.toData(test);

        //Assert
        assertEquals(result,result1);

    }
    @Test
    void toDataNotEqual() {
        //Arrange
        ProjectID projectID = new ProjectID(1);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        SprintID sprintID = new SprintID(projectID,sprintNumber);
        LocalDate startDate = LocalDate.of(2022,02,1);
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        Sprint test = mock(Sprint.class);

        when(test.getSprintID()).thenReturn(sprintID);
        when(test.getSprintNumber()).thenReturn(1);
        when(test.getProjectID()).thenReturn(1);
        when(test.getStartDate()).thenReturn(startDate);
        when(test.getSprintDuration()).thenReturn(sprintDuration);

        PositiveNumber sprintNumber2 = PositiveNumber.of(2);
        SprintID sprintID2 = new SprintID(projectID,sprintNumber2);
        LocalDate startDate2 = LocalDate.of(2022,02,20);
        Sprint test2 = mock(Sprint.class);

        when(test2.getSprintID()).thenReturn(sprintID2);
        when(test2.getSprintNumber()).thenReturn(2);
        when(test2.getProjectID()).thenReturn(1);
        when(test2.getStartDate()).thenReturn(startDate2);
        when(test2.getSprintDuration()).thenReturn(sprintDuration);

        SprintDomainDataAssembler assembler = new SprintDomainDataAssembler();

        //Act
        SprintJPA result= assembler.toData(test);
        SprintJPA result1= assembler.toData(test2);

        //Assert
        assertNotEquals(result,result1);

    }
    @Test
    void toDomain() {
        //Arrange
        SprintJPA test = mock(SprintJPA.class);
        SprintbacklogItemJPA testItem1 = mock(SprintbacklogItemJPA.class);
        SprintbacklogItemJPA testItem2 = mock(SprintbacklogItemJPA.class);
        String category = "To do";

        List<SprintbacklogItemJPA> list = new ArrayList<>();
        list.add(testItem1);
        list.add(testItem2);

        when(test.getSprintID()).thenReturn(SprintID.ofDouble(3.001));
        when(test.getSprintNumber()).thenReturn(1);
        when(test.getProjectID()).thenReturn(3);
        when(test.getStartDate()).thenReturn(LocalDate.of(2022,03,15));
        when(test.getSprintDuration()).thenReturn(1);
        when(test.getSprintbacklogItemJPAList()).thenReturn(list);

        SprintBacklogItemID id1 = SprintBacklogItemID.createSprintBacklogItemID("13549b5d-8c38-4234-a00f-d55639b83061");
        SprintBacklogItemID id2 = SprintBacklogItemID.createSprintBacklogItemID("d3c94206-b534-48b4-ba14-b394f4aa8518");
        double usID1 = UserStoryID.ofDouble(1.001).getID();
        double usID2 = UserStoryID.ofDouble(1.002).getID();

        when(testItem1.getItemID()).thenReturn(id1);
        when(testItem1.getUsID()).thenReturn(usID1);
        when(testItem1.getEffortEstimate()).thenReturn(1);
        when(testItem1.getCategory()).thenReturn(category);

        when(testItem2.getItemID()).thenReturn(id2);
        when(testItem2.getUsID()).thenReturn(usID2);
        when(testItem2.getEffortEstimate()).thenReturn(1);
        when(testItem2.getCategory()).thenReturn(category);

        SprintDomainDataAssembler assembler = new SprintDomainDataAssembler();

        //Act
        Sprint result = assembler.toDomain(test);
        Sprint result1 = assembler.toDomain(test);

        //Assert
        assertEquals(result,result1);
    }

    @Test
    void toDomainNotEqual() {
        //Arrange
        SprintJPA test = mock(SprintJPA.class);

        when(test.getSprintID()).thenReturn(SprintID.ofDouble(3.001));
        when(test.getSprintNumber()).thenReturn(1);
        when(test.getProjectID()).thenReturn(3);
        when(test.getStartDate()).thenReturn(LocalDate.of(2022,3,15));
        when(test.getSprintDuration()).thenReturn(1);

        SprintJPA test2 = mock(SprintJPA.class);

        when(test2.getSprintID()).thenReturn(SprintID.ofDouble(3.002));
        when(test2.getSprintNumber()).thenReturn(2);
        when(test2.getProjectID()).thenReturn(3);
        when(test2.getStartDate()).thenReturn(LocalDate.of(2022,4,2));
        when(test2.getSprintDuration()).thenReturn(1);

        SprintDomainDataAssembler assembler = new SprintDomainDataAssembler();

        //Act
        Sprint result = assembler.toDomain(test);
        Sprint result1 = assembler.toDomain(test2);

        //Assert
        assertNotEquals(result,result1);
    }


}