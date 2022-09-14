package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IResourceJPARepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.datamodel.ResourceJPA;
import switchtwentyone.project.datamodel.assembler.ResourceDomainDataAssembler;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.ResourceRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceRepositoryTest {
    @InjectMocks
    ResourceRepositoryImpl resourceRepository;
    @Mock
    IResourceJPARepository jpaRepository;
    @Mock
    ResourceDomainDataAssembler assembler;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByProjectID() {
        ProjectID projectID = new ProjectID(5);
        ResourceID scrumMasterID = new ResourceID(1);
        Email pedro = Email.of("pedro@isep.ipp.pt");
        AccountID accountIDSM = AccountID.of(pedro);
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of("Scrum Master"));
        String scrum = "Scrum Master";
        Period periodSM = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));
        LimitedPercentage percentage = LimitedPercentage.decimal(0.75);
        Double percentDouble = 0.75;
        Currency currency = Currency.getInstance("EUR");
        Monetary cost = Monetary.of(15.00,currency);
        Double costHour = 15.00;

        ResourceID productOwnerID = new ResourceID(2);
        Email paula = Email.of("paula@isep.ipp.pt");
        AccountID accountIDPO = AccountID.of(paula);
        Role productOwner = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));
        String owner = "Product Owner";
        Period periodPO = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));

        ResourceID developerID = new ResourceID(2);
        Email lina = Email.of("lina@isep.ipp.pt");
        AccountID accountIDDev = AccountID.of(lina);
        Role developer = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        String dev = "Developer";
        Period periodDev = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));

        ResourceJPA scrumMasterJpa = mock(ResourceJPA.class);


        ResourceJPA productOwnerJpa = mock(ResourceJPA.class);


        ResourceJPA developerJpa = mock(ResourceJPA.class);


        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(scrumMasterJpa);
        resourceJPAList.add(productOwnerJpa);
        resourceJPAList.add(developerJpa);

        when(jpaRepository.findByProjectID(projectID)).thenReturn(resourceJPAList);


        Resource mockScrum = mock(Resource.class);


        Resource mockOwner = mock(Resource.class);


        Resource mockDev = mock(Resource.class);


        List<Resource> expected =new ArrayList<>();
        expected.add(mockScrum);
        expected.add(mockOwner);
        expected.add(mockDev);

        when(assembler.toDomain(scrumMasterJpa)).thenReturn(mockScrum);
        when(assembler.toDomain(productOwnerJpa)).thenReturn(mockOwner);
        when(assembler.toDomain(developerJpa)).thenReturn(mockDev);

        List<Resource> result = resourceRepository.findAllByProjectID(projectID);

    assertEquals(expected,result);

    }

    @Test
    void findAllActiveResourcesByEmailAndRole() {
        ProjectID projectID = new ProjectID(5);
        ResourceID scrumMasterID = new ResourceID(1);
        Email pedro = Email.of("pedro@isep.ipp.pt");
        AccountID accountIDSM = AccountID.of(pedro);
        String scrum = "SCRUM_MASTER";
        String scrumDescription ="Description";
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(scrumDescription));
        LocalDate periodStartDate = LocalDate.of(2022,5,1);
        Period periodSM = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));
        LimitedPercentage percentage = LimitedPercentage.decimal(0.75);
        Double percentDouble = 0.75;
        Currency currency = Currency.getInstance("EUR");
        Monetary cost = Monetary.of(15.00,currency);
        Double costHour = 15.00;

        ResourceJPA scrumMasterJpa = mock(ResourceJPA.class);



        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(scrumMasterJpa);

        AccountID associatedAccount = AccountID.of(pedro);
        String role = scrum;
        LocalDate today = LocalDate.of(2022,5,15);

        when(jpaRepository.findByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndRole(associatedAccount,today,today,role)).thenReturn(resourceJPAList);

        Resource mockScrum = mock(Resource.class);


        List<Resource> expected =new ArrayList<>();
        expected.add(mockScrum);

        when(assembler.toDomain(scrumMasterJpa)).thenReturn(mockScrum);

        List<Resource> result = resourceRepository.findAllActiveResourcesByEmailAndRole(pedro,scrumMaster,LocalDate.of(2022,5,15));


        assertEquals(expected,result);
    }


    @Test
    void findAllByAccontID() {
        ProjectID projectID = new ProjectID(5);
        ResourceID scrumMasterID = new ResourceID(1);
        Email pedro = Email.of("pedro@isep.ipp.pt");
        AccountID accountIDSM = AccountID.of(pedro);
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of("Scrum Master"));
        String scrum = "Scrum_Master";
        Period periodSM = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));
        LimitedPercentage percentage = LimitedPercentage.decimal(0.75);
        Double percentDouble = 0.75;
        Currency currency = Currency.getInstance("EUR");
        Monetary cost = Monetary.of(15.00,currency);
        Double costHour = 15.00;

        ResourceID productOwnerID = new ResourceID(2);
        Email paula = Email.of("paula@isep.ipp.pt");
        AccountID accountIDPO = AccountID.of(paula);
        Role productOwner = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));
        String owner = "Product Owner";
        Period periodPO = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));

        ResourceID developerID = new ResourceID(2);
        Email lina = Email.of("lina@isep.ipp.pt");
        AccountID accountIDDev = AccountID.of(lina);
        Role developer = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        String dev = "Developer";
        Period periodDev = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));

        ResourceJPA scrumMasterJpa = mock(ResourceJPA.class);

        ResourceJPA productOwnerJpa = mock(ResourceJPA.class);

        ResourceJPA developerJpa = mock(ResourceJPA.class);


        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(scrumMasterJpa);

        when(jpaRepository.findByAssociatedAccount_Id(pedro)).thenReturn(resourceJPAList);

        Resource mockScrum = mock(Resource.class);


        List<Resource> expected =new ArrayList<>();
        expected.add(mockScrum);

        when(assembler.toDomain(scrumMasterJpa)).thenReturn(mockScrum);

        List<Resource> result = resourceRepository.findAllByAccountID(pedro);


        assertEquals(expected,result);
    }

    @Test
    void save() {
        Resource resource = mock(Resource.class);
        ResourceJPA resourceJPA = mock(ResourceJPA.class);
        when(assembler.toData(resource)).thenReturn(resourceJPA);
        ResourceJPA jpaSaved = mock(ResourceJPA.class);
        when(jpaRepository.save(resourceJPA)).thenReturn(jpaSaved);
        Resource saved = mock(Resource.class);
        when(assembler.toDomain(jpaSaved)).thenReturn(saved);

        Resource result = resourceRepository.save(resource);

        assertEquals(saved,result);

    }

    @Test
    void findResourcethatDoesNotExistTest() {
        //Criar os dados para este teste

        Role role = mock(Role.class);
        when(role.getRoleAsString()).thenReturn("abc");
        LocalDate date = LocalDate.now();
        ProjectID projectID = mock(ProjectID.class);

        Optional<Resource> expected = Optional.empty();
        when(jpaRepository
                .findByRoleAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                        ("abc",projectID, date, date)).thenReturn(Optional.empty());

        Optional<Resource> result = resourceRepository.findActiveResource(role,projectID, date);

        assertEquals(expected, result);

    }
    @Test
    void findResourcethatExistsTest() {
        Resource resource = mock(Resource.class);
        ResourceJPA resourceJPA = mock(ResourceJPA.class);

        Optional<Resource> expected = Optional.of(resource);
        Role role = mock(Role.class);
        when(role.getRoleAsString()).thenReturn("abc");
        LocalDate date = LocalDate.now();
        ProjectID projectID = mock(ProjectID.class);

        when(jpaRepository
                .findByRoleAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                        ( "abc",projectID, date, date)).thenReturn(Optional.of(resourceJPA));

        when(assembler.toDomain(resourceJPA)).thenReturn(resource);
        Optional<Resource> result = resourceRepository.findActiveResource(role,projectID, date);

        assertEquals(expected, result);

    }

    @Test
    void deleteResourceThatExistsTest() {
        Role role = mock(Role.class);
        when(role.getRoleAsString()).thenReturn("abc");
        LocalDate startDate = LocalDate.of(1995,7, 13);
        LocalDate endDate = LocalDate.of(2022, 5, 31);
        ProjectID projectID = mock(ProjectID.class);
        Period period = Period.between(startDate, endDate);

        when(jpaRepository
                .existsByRoleAndProjectIDAndStartDateGreaterThanEqualAndStartDateLessThanEqual("abc", projectID,startDate, endDate))
                .thenReturn(true);

        boolean result = resourceRepository.deleteResources(role,projectID,period);

        assertTrue(result);

    }


    @Test
    void deleteResourceThatDoesNotExistTest() {
        Role role = mock(Role.class);
        when(role.getRoleAsString()).thenReturn("abc");
        LocalDate startDate = LocalDate.of(1995,7, 13);
        LocalDate endDate = LocalDate.of(2022, 5, 31);
        ProjectID projectID = mock(ProjectID.class);
        Period period = Period.between(startDate, endDate);

        when(jpaRepository
                .existsByRoleAndProjectIDAndStartDateGreaterThanEqualAndStartDateLessThanEqual("abc", projectID,startDate, endDate))
                .thenReturn(false);

        boolean result = resourceRepository.deleteResources(role,projectID,period);

        assertFalse(result);

    }

    @Test
    void findActiveResourceByAccountAndProjectID() {

        Resource resource = mock(Resource.class);
        ResourceJPA resourceJPA = mock(ResourceJPA.class);

        Optional<Resource> expected = Optional.of(resource);
        AccountID accountID = mock(AccountID.class);

        LocalDate date = LocalDate.now();
        ProjectID projectID = mock(ProjectID.class);

        when(jpaRepository
                .findByAssociatedAccountAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual(accountID,projectID,date,date)).thenReturn(Optional.of(resourceJPA));

        when(assembler.toDomain(resourceJPA)).thenReturn(resource);
        Optional<Resource> result = resourceRepository.findActiveResourceByAccountAndProjectID(accountID,projectID,date);

        assertEquals(expected, result);

    }

    @Test
    void findAllActiveResourcesByEmail() {
        ProjectID projectID = new ProjectID(5);
        ResourceID scrumMasterID = new ResourceID(1);
        Email pedro = Email.of("pedro@isep.ipp.pt");
        AccountID accountIDSM = AccountID.of(pedro);
        String scrum = "SCRUM_MASTER";
        String scrumDescription ="Description";
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(scrumDescription));
        LocalDate periodStartDate = LocalDate.of(2022,5,1);
        Period periodSM = Period.between(LocalDate.of(2022,05,01),LocalDate.of(2022,05,31));
        LimitedPercentage percentage = LimitedPercentage.decimal(0.75);
        Double percentDouble = 0.75;
        Currency currency = Currency.getInstance("EUR");
        Monetary cost = Monetary.of(15.00,currency);
        Double costHour = 15.00;

        ResourceJPA scrumMasterJpa = mock(ResourceJPA.class);



        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(scrumMasterJpa);

        AccountID associatedAccount = AccountID.of(pedro);

        LocalDate today = LocalDate.of(2022,5,15);

        when(jpaRepository.findByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqual(associatedAccount, today, today)).thenReturn(resourceJPAList);

        Resource mockScrum = mock(Resource.class);


        List<Resource> expected =new ArrayList<>();
        expected.add(mockScrum);

        when(assembler.toDomain(scrumMasterJpa)).thenReturn(mockScrum);

        List<Resource> result = resourceRepository.findAllActiveResourcesByEmail(pedro,today);

        assertEquals(expected,result);
    }
}