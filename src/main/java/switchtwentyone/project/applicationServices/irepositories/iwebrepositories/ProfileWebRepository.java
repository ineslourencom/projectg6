package switchtwentyone.project.applicationServices.irepositories.iwebrepositories;

import switchtwentyone.project.externaldto.ExternalProfileDTO;

import java.util.List;

public interface ProfileWebRepository {
    List<ExternalProfileDTO> getProfiles() ;
}
