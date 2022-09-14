package switchtwentyone.project.domain.services;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.AccountController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProfilesController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ServiceAccountLinks {

    public void appendDirectorLinkCollection(Account account, AccountShortDTO dto){
        Link linkProjects = linkTo(methodOn(ProjectController.class).getListOfAllProjects(account.getEmail().getEmailData())).withRel("projects");
        Link linkUpdateProfile = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("updateProfile");
        Link linkProfiles = linkTo(methodOn(ProfilesController.class).getLocalProfilesDTOS()).withRel("profiles");
        Link linkAccounts = linkTo(methodOn(AccountController.class).findAccountInfo(account.getEmail().getEmailData(), "short")).withRel("accounts");
        Link linkSearchAccount = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("searchAccount");
        Link linkCreateProfile = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("createProfile");
        Link linkViewAllProfiles = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("viewAllProfiles");
        dto.add(linkProjects);
        dto.add(linkUpdateProfile);
        dto.add(linkProfiles);
        dto.add(linkAccounts);
        dto.add(linkSearchAccount);
        dto.add(linkCreateProfile);
        dto.add(linkViewAllProfiles);
    }

    public void appendNotDirectorLinkCollection(Account account, AccountShortDTO dto){
        Link linkProjects = linkTo(methodOn(ProjectController.class).getListOfProjectsByUser(account.getEmail().getEmailData())).withRel("projects");
        dto.add(linkProjects);
    }

    public void appendGlobalLinkCollection(Account account, AccountShortDTO dto){
        Link linkProjects = linkTo(methodOn(ProjectController.class).getListOfAllProjects(account.getEmail().getEmailData())).withRel("projects");
        Link linkUpdateProfile = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("updateProfile");
        Link linkProfiles = linkTo(methodOn(ProfilesController.class).getLocalProfilesDTOS()).withRel("profiles");
        Link linkAccounts = linkTo(methodOn(AccountController.class).findAccountInfo(account.getEmail().getEmailData(), "short")).withRel("accounts");
        Link linkSearchAccount = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("searchAccount");
        Link linkCreateProfile = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("createProfile");
        Link linkViewAllProfiles = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("viewAllProfiles");
        Link linkViewAllAccounts = linkTo(methodOn(AccountController.class).getListOfAllAccountsAndStatus()).withRel("viewAllAccounts");

        dto.add(linkProjects);
        dto.add(linkUpdateProfile);
        dto.add(linkProfiles);
        dto.add(linkAccounts);
        dto.add(linkSearchAccount);
        dto.add(linkCreateProfile);
        dto.add(linkViewAllProfiles);
        dto.add(linkViewAllAccounts);
    }
}
