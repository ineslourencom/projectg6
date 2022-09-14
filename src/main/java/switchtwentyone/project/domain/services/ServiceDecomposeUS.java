package switchtwentyone.project.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryCreator;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.NewUserStoryDomainDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDecomposeUS {
    @Autowired
    ServiceCreateUS createUSService;


    public List<UserStory> generateChildren(List<UserStory> backlog,
                                            List<NewUserStoryDomainDTO> newUserStories,
                                            ProjectID projectID,
                                            UserStory parent) {

        List<UserStory> children = new ArrayList<>();
if (newUserStories.size() <2 ){
    throw new IllegalArgumentException("At least two new user stories are necessary to decompose");
}
        for (NewUserStoryDomainDTO dto : newUserStories) {
            PositiveNumber usNumber = createUSService.generateUSNumber(backlog);
            int priority = createUSService.determineUSPriority(backlog);
            UserStoryID usID = createUSService.generateUSID(usNumber, projectID);
            UserStory child = UserStoryCreator.createUserStory(usID, usNumber, dto.statement, dto.detail,  priority,  projectID);
            child.defineParenthood(parent);
            backlog.add(child);
            children.add(child);
        }


        return children;
    }

}
