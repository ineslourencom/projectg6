package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.datamodel.ProfileJPA;

public interface IProfileJPARepository extends CrudRepository<ProfileJPA, ProfileID> {
}