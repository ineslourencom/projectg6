package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProfileJPARepository;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.datamodel.assembler.ProfileDomainDataAssembler;
import switchtwentyone.project.datamodel.ProfileJPA;


import java.util.*;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    @Autowired
    IProfileJPARepository profileJPARepository;
    @Autowired
    ProfileDomainDataAssembler profileAssembler;


    public ProfileRepositoryImpl() {

    }


    @Override
    public List<Profile> findAll(){

        Iterable<ProfileJPA> setProfileJpa = profileJPARepository.findAll();

        List<Profile> setProfiles =  new ArrayList<>();

        for(ProfileJPA profileJPA: setProfileJpa){
            Profile profile = profileAssembler.toDomain(profileJPA);
            setProfiles.add(profile);
        }
        return setProfiles;
    }


    @Override
    public Profile save(Profile profile){
        ProfileJPA profileJPA = profileAssembler.toData(profile);
        profileJPA = profileJPARepository.save(profileJPA);
        return profileAssembler.toDomain(profileJPA);
    }


    @Override
    public Optional<Profile> findProfileById(ProfileID profileID){
        Optional <Profile> profile = Optional.empty();
        Optional<ProfileJPA> optionalProfileJPA = profileJPARepository.findById(profileID);

        if(optionalProfileJPA.isPresent()){
            ProfileJPA profileJPA = optionalProfileJPA.get();
            profile = Optional.of(profileAssembler.toDomain(profileJPA));
        }

        return profile;
    }


    @Override
    public void deleteAll(){
        profileJPARepository.deleteAll();
    }

}
