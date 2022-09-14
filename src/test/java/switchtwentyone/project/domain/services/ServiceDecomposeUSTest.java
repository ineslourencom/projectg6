package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryCreator;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.NewUserStoryDomainDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("ServiceDecomposeUS")
@ExtendWith(SpringExtension.class)
class ServiceDecomposeUSTest {
    @Autowired
    private ServiceDecomposeUS decomposeUSService;

    @Autowired
    private ServiceCreateUS servUS;

    @Test
    void generateChildren() {
        List<UserStory> backlog = new ArrayList<>();
        UserStory parent = mock(UserStory.class);
        backlog.add(parent);
        List<NewUserStoryDomainDTO> newStories = new ArrayList<>();

        NewUserStoryDomainDTO dto1 = new NewUserStoryDomainDTO();
        NewUserStoryDomainDTO dto2 = new NewUserStoryDomainDTO();
        NewUserStoryDomainDTO dto3 = new NewUserStoryDomainDTO();

        Text stm1 = mock(Text.class);
        Text stm2 = mock(Text.class);
        Text stm3 = mock(Text.class);

        Text det1 = mock(Text.class);
        Text det2 = mock(Text.class);
        Text det3 = mock(Text.class);

        dto1.statement = stm1;
        dto2.statement = stm2;
        dto3.statement = stm3;
        dto1.detail = det1;
        dto2.detail = det2;
        dto3.detail = det3;

        newStories.add(dto1);
        newStories.add(dto2);
        newStories.add(dto3);


        UserStory us = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);

        PositiveNumber n1 = mock(PositiveNumber.class);
        PositiveNumber n2 = mock(PositiveNumber.class);
        PositiveNumber n3 = mock(PositiveNumber.class);

        UserStoryID idus = mock(UserStoryID.class);
        UserStoryID idus2 = mock(UserStoryID.class);
        UserStoryID idus3 = mock(UserStoryID.class);

        ProjectID pid = mock(ProjectID.class);

        when(servUS.generateUSNumber(backlog)).thenReturn(n1, n2, n3);
        when(servUS.determineUSPriority(backlog)).thenReturn(2, 3, 4);
        when(servUS.generateUSID(n1, pid)).thenReturn(idus);
        when(servUS.generateUSID(n2, pid)).thenReturn(idus2);
        when(servUS.generateUSID(n3, pid)).thenReturn(idus3);
        MockedStatic<UserStoryCreator> mock = mockStatic(UserStoryCreator.class);
        when(UserStoryCreator.createUserStory(idus,n1, stm1,det1,2,pid)).thenReturn(us);
        when(UserStoryCreator.createUserStory(idus2,n2,stm2,det2,3,pid)).thenReturn(us2);
        when(UserStoryCreator.createUserStory(idus3,n3,stm3,det3,4,pid)).thenReturn(us3);


        List<UserStory> expected = new ArrayList<>();
        expected.add(us);
        expected.add(us2);
        expected.add(us3);
        List<UserStory> result = decomposeUSService.generateChildren(backlog, newStories, pid, parent);
        mock.close();

        List<UserStory> expectedBacklog = new ArrayList<>();
        expectedBacklog.add(parent);
        expectedBacklog.add(us);
        expectedBacklog.add(us2);
        expectedBacklog.add(us3);


        assertEquals(expectedBacklog, backlog);
        assertEquals(expected, result);
    }



    @Test
    void ensureParenthoodIsDefined() {
        List<UserStory> backlog = new ArrayList<>();
        UserStory parent = mock(UserStory.class);
        backlog.add(parent);
        List<NewUserStoryDomainDTO> newStories = new ArrayList<>();

        NewUserStoryDomainDTO dto1 = new NewUserStoryDomainDTO();
        NewUserStoryDomainDTO dto2 = new NewUserStoryDomainDTO();
        NewUserStoryDomainDTO dto3 = new NewUserStoryDomainDTO();

        Text stm1 = mock(Text.class);
        Text stm2 = mock(Text.class);
        Text stm3 = mock(Text.class);

        Text det1 = mock(Text.class);
        Text det2 = mock(Text.class);
        Text det3 = mock(Text.class);

        dto1.statement = stm1;
        dto2.statement = stm2;
        dto3.statement = stm3;
        dto1.detail = det1;
        dto2.detail = det2;
        dto3.detail = det3;

        newStories.add(dto1);
        newStories.add(dto2);
        newStories.add(dto3);


        UserStory us = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);

        PositiveNumber n1 = mock(PositiveNumber.class);
        PositiveNumber n2 = mock(PositiveNumber.class);
        PositiveNumber n3 = mock(PositiveNumber.class);

        UserStoryID idus = mock(UserStoryID.class);
        UserStoryID idus2 = mock(UserStoryID.class);
        UserStoryID idus3 = mock(UserStoryID.class);

        ProjectID pid = mock(ProjectID.class);

        when(servUS.generateUSNumber(backlog)).thenReturn(n1, n2, n3);
        when(servUS.determineUSPriority(backlog)).thenReturn(2, 3, 4);
        when(servUS.generateUSID(n1, pid)).thenReturn(idus);
        when(servUS.generateUSID(n2, pid)).thenReturn(idus2);
        when(servUS.generateUSID(n3, pid)).thenReturn(idus3);
        MockedStatic<UserStoryCreator> mock = mockStatic(UserStoryCreator.class);
        when(UserStoryCreator.createUserStory(idus,n1, stm1,det1,2,pid)).thenReturn(us);
        when(UserStoryCreator.createUserStory(idus2,n2,stm2,det2,3,pid)).thenReturn(us2);
        when(UserStoryCreator.createUserStory(idus3,n3,stm3,det3,4,pid)).thenReturn(us3);


        List<UserStory> expected = new ArrayList<>();
        expected.add(us);
        expected.add(us2);
        expected.add(us3);
        decomposeUSService.generateChildren(backlog, newStories, pid, parent);
        mock.close();

        verify(us, times(1)).defineParenthood(parent);
        verify(us2, times(1)).defineParenthood(parent);
        verify(us3, times(1)).defineParenthood(parent);

    }

    @Test

    void ensureMinimumNumberOfNewUSIsResquired(){
        ProjectID pid = mock(ProjectID.class);
        List<UserStory> backlog = new ArrayList<>();
        UserStory parent = mock(UserStory.class);
        backlog.add(parent);
        List<NewUserStoryDomainDTO> newStories = new ArrayList<>();

        NewUserStoryDomainDTO dto1 = new NewUserStoryDomainDTO();

        assertThrows(IllegalArgumentException.class, () ->{decomposeUSService.generateChildren(backlog, newStories, pid, parent);});


    }

}