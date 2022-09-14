package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.AccountController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProfilesController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ServiceAccountLinksTest {

    @Test
    void appendDirectorLinkCollection() {
        ServiceAccountLinks service = new ServiceAccountLinks();
        Account account = mock(Account.class);
        AccountShortDTO dto = new AccountShortDTO();
        dto.jobTitle = "job";
        dto.email = "job@email.com";
        dto.name = "name";
        dto.photo = "photo";

        Email email = Email.of("job@email.com");
        when(account.getEmail()).thenReturn(email);

        service.appendDirectorLinkCollection(account, dto);

        AccountShortDTO expectedDto = new AccountShortDTO();
        expectedDto.jobTitle = "job";
        expectedDto.email = "job@email.com";
        expectedDto.name = "name";
        expectedDto.photo = "photo";

        Link linkProjects = linkTo(methodOn(ProjectController.class).getListOfAllProjects(account.getEmail().getEmailData())).withRel("projects");
        Link linkUpdateProfile = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("updateProfile");
        Link linkProfiles = linkTo(methodOn(ProfilesController.class).getLocalProfilesDTOS()).withRel("profiles");
        Link linkAccounts = linkTo(methodOn(AccountController.class).findAccountInfo(account.getEmail().getEmailData(), "short")).withRel("accounts");
        Link linkSearchAccount = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("searchAccount");
        Link linkCreateProfile = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("createProfile");
        Link linkViewAllProfiles = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("viewAllProfiles");
        expectedDto.add(linkProjects);
        expectedDto.add(linkUpdateProfile);
        expectedDto.add(linkProfiles);
        expectedDto.add(linkAccounts);
        expectedDto.add(linkSearchAccount);
        expectedDto.add(linkCreateProfile);
        expectedDto.add(linkViewAllProfiles);

        assertEquals(expectedDto, dto);

    }

    @Test
    void appendNotDirectorLinkCollection() {
            ServiceAccountLinks service = new ServiceAccountLinks();
            Account account = mock(Account.class);
            AccountShortDTO dto = new AccountShortDTO();
            dto.jobTitle = "job";
            dto.email = "job@email.com";
            dto.name = "name";
            dto.photo = "photo";

            Email email = Email.of("job@email.com");
            when(account.getEmail()).thenReturn(email);

            service.appendNotDirectorLinkCollection(account, dto);

            AccountShortDTO expectedDto = new AccountShortDTO();
            expectedDto.jobTitle = "job";
            expectedDto.email = "job@email.com";
            expectedDto.name = "name";
            expectedDto.photo = "photo";

            Link linkProjects = linkTo(methodOn(ProjectController.class).getListOfProjectsByUser(account.getEmail().getEmailData())).withRel("projects");
            expectedDto.add(linkProjects);

            assertEquals(expectedDto, dto);
    }

    @Test
    void appendGlobalLinkCollection() {
        ServiceAccountLinks service = new ServiceAccountLinks();
        Account account = mock(Account.class);
        AccountShortDTO dto = new AccountShortDTO();
        dto.jobTitle = "job";
        dto.email = "job@email.com";
        dto.name = "name";
        dto.photo = "photo";

        Email email = Email.of("job@email.com");
        when(account.getEmail()).thenReturn(email);

        service.appendGlobalLinkCollection(account, dto);

        AccountShortDTO expectedDto = new AccountShortDTO();
        expectedDto.jobTitle = "job";
        expectedDto.email = "job@email.com";
        expectedDto.name = "name";
        expectedDto.photo = "photo";

        Link linkProjects = linkTo(methodOn(ProjectController.class).getListOfAllProjects(account.getEmail().getEmailData())).withRel("projects");
        Link linkUpdateProfile = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("updateProfile");
        Link linkProfiles = linkTo(methodOn(ProfilesController.class).getLocalProfilesDTOS()).withRel("profiles");
        Link linkAccounts = linkTo(methodOn(AccountController.class).findAccountInfo(account.getEmail().getEmailData(), "short")).withRel("accounts");
        Link linkSearchAccount = linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("searchAccount");
        Link linkCreateProfile = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("createProfile");
        Link linkViewAllProfiles = linkTo(methodOn(ProfilesController.class).getProfilesDTOS()).withRel("viewAllProfiles");
        Link linkViewAllAccounts = linkTo(methodOn(AccountController.class).getListOfAllAccountsAndStatus()).withRel("viewAllAccounts");

        expectedDto.add(linkProjects);
        expectedDto.add(linkUpdateProfile);
        expectedDto.add(linkProfiles);
        expectedDto.add(linkAccounts);
        expectedDto.add(linkSearchAccount);
        expectedDto.add(linkCreateProfile);
        expectedDto.add(linkViewAllProfiles);
        expectedDto.add(linkViewAllAccounts);

        assertEquals(expectedDto, dto);
    }
}