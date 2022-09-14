package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRequestRepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.ProfileRequestRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Transactional
public class ProfileRequestRepositoryTest {

    @Test
    void saveProfileRequestUnsuccessful() {
        ProfileRequest proReq = mock(ProfileRequest.class);
        ProfileRequest proReq1 = mock(ProfileRequest.class);
        ProfileRequest proReq2 = proReq1;
        ProfileRequestRepositoryImpl profileRequestRepo = new ProfileRequestRepositoryImpl();
        profileRequestRepo.saveProfileRequest(proReq);
        profileRequestRepo.saveProfileRequest(proReq1);

    }

    @Test
    void saveProfileRequestSuccessfull() {
        ProfileRequest proReq = mock(ProfileRequest.class);
        ProfileRequest proReq1 = mock(ProfileRequest.class);
        ProfileRequestRepositoryImpl profileRequestRepo = new ProfileRequestRepositoryImpl();

        profileRequestRepo.saveProfileRequest(proReq);

        //act
        boolean result = profileRequestRepo.saveProfileRequest(proReq1);
        ;

        //Assert
        assertTrue(result);
    }

    @Test
    void findByIdFalse() {
        ProfileRequestID id = mock(ProfileRequestID.class);
        ProfileRequest proReq = mock(ProfileRequest.class);
        ProfileRequest proReq1 = mock(ProfileRequest.class);
        ProfileRequestRepositoryImpl profileRequestRepo = new ProfileRequestRepositoryImpl();

        profileRequestRepo.saveProfileRequest(proReq);
        profileRequestRepo.saveProfileRequest(proReq1);

        Optional<ProfileRequest> found = profileRequestRepo.findProfileRequestByID(id);

        assertFalse(found.isPresent());
    }


}
