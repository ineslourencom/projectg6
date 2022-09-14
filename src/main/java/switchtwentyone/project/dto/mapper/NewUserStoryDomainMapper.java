package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ChildUserStoryDTO;
import switchtwentyone.project.dto.NewUserStoryDomainDTO;

public class NewUserStoryDomainMapper {
    private NewUserStoryDomainMapper(){}

    public static NewUserStoryDomainDTO toDomain(ChildUserStoryDTO dto ){

        NewUserStoryDomainDTO domainDTO = new NewUserStoryDomainDTO();
        domainDTO.statement = Text.write(dto.statement);
        domainDTO.detail = Text.write(dto.detail);

        return domainDTO;
    }




}
