package switchtwentyone.project.domain.aggregates.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import switchtwentyone.project.domain.aggregates.resource.ResourceCreatable;

import static org.mockito.Mockito.mock;

@Profile("ProjectTest")
@Configuration
public class ProjectTestConfig {
    @Bean
    @Primary
    ResourceCreatable resourceCreatable(){return  mock(ResourceCreatable.class);}
}
