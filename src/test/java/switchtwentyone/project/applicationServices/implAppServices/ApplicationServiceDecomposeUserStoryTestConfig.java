package switchtwentyone.project.applicationServices.implAppServices;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import switchtwentyone.project.domain.services.ServiceCreateUS;
import switchtwentyone.project.domain.services.ServiceDecomposeUS;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.ProjectRepositoryImpl;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.UserStoryRepositoryImpl;

@Profile("ApplicationServiceDecomposeUserStory")
@Configuration
public class ApplicationServiceDecomposeUserStoryTestConfig {
    @Bean
    @Primary
    public ProjectRepository projRepo(){return Mockito.mock(ProjectRepositoryImpl.class);}

    @Bean
    @Primary
    public UserStoryRepository usRepo(){return Mockito.mock(UserStoryRepositoryImpl.class);}
    @Bean
    @Primary
    public ServiceCreateUS createUSService(){return Mockito.mock(ServiceCreateUS.class);}

    @Bean
    @Primary
    public ServiceDecomposeUS decomposeUSService(){return Mockito.mock(ServiceDecomposeUS.class);}



}
