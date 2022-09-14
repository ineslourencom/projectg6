package switchtwentyone.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.datamodel.UserStoryJPA;

@Service
public class UserStoryDomainDataAssembler {
    public UserStoryJPA toData(UserStory us) {
        USDTO dto = UserStoryMapper.toDTO(us);
        return new UserStoryJPA(dto);
    }

    public UserStory toDomain( UserStoryJPA usJPA ) {
        PositiveNumber storyNumber = PositiveNumber.of(usJPA.getStoryNumber());
        UserStoryID usID = UserStoryID.ofProjIDAndUsNumber(new ProjectID(usJPA.getProjectID()), storyNumber );
        Text statement = Text.write(usJPA.getStatement());
        Text detail = Text.write(usJPA.getDetail());
        int priority = usJPA.getPriority();
        ProjectID projID = new ProjectID(usJPA.getProjectID());
        UserStory newUS = new UserStory(usID, storyNumber, statement, detail, priority, projID);
        newUS.setParent(UserStoryID.ofDouble(usJPA.getUsIDParent()));
        return newUS;
    }
}

