package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.dto.NewProfileRequestInfoDTO;
import switchtwentyone.project.dto.ProfileRequestDTO;
import switchtwentyone.project.dto.mapper.ProfileRequestMapper;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRequestRepository;

import java.util.Optional;


@Service
public class ApplicationServiceCreateProfileRequest {

    @Autowired
    ProfileRequestRepository profileRequestRepo;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    IAccountJPARepository accountJPARepo;

    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;


    public ApplicationServiceCreateProfileRequest() {
        //empty constructor
    }

    public ProfileRequestDTO createProfileRequest(NewProfileRequestInfoDTO newProfileRequestInfoDTO) {
        ProfileRequestDTO outDTO = null;
        Email fullemail = Email.of(newProfileRequestInfoDTO.getEmail());
        ProfileType profTyp = ProfileType.of(newProfileRequestInfoDTO.getProfileType());

        AccountID accountID = AccountID.of(fullemail);
        Optional<Account> account = accountRepo.findByAccountID(accountID);
        if(account.isPresent()){
            ProfileRequest newProfileRequest = account.get().createRequest(fullemail, profTyp);
            profileRequestRepo.saveProfileRequest(newProfileRequest);
            outDTO = ProfileRequestMapper.toDTO(newProfileRequest);
        } else {
            throw new IllegalAccessError("Account does not exist");
        }

        return outDTO;
    }




    public Optional<ProfileRequest> findProfileRequestByID(ProfileRequestID profileRequestID) {
        return profileRequestRepo.findProfileRequestByID(profileRequestID);
    }

}




