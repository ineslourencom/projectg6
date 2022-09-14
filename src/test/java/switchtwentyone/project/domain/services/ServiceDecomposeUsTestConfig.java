package switchtwentyone.project.domain.services;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("ServiceDecomposeUS")
@Configuration
public class ServiceDecomposeUsTestConfig {
    @Bean
    @Primary
    public ServiceCreateUS servUS(){return Mockito.mock(ServiceCreateUS.class);}


}
