package switchtwentyone.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileCreatable;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.Text;

@SpringBootApplication
/**
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * @Configuration: Tags the class as a source of bean definitions for the application context.
 * @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * @EnableWebMvc: Flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet. Spring Boot adds it automatically when it sees spring-webmvc on the classpath.
 * @ComponentScan: Tells Spring to look for other components, configurations, and services in the com.example.testingweb package, letting it find the HelloController class.
 */
public class ProjectApplication implements ApplicationRunner {

    @Autowired
    ProfileRepository profileRepository;

    private void bootstrapping() {
        Text userDescription =  Text.write("Account type with the standard permissions level.");
        Profile userProfile = ProfileCreatable.createProfile(ProfileID.ofProfileType("User"), userDescription);
        profileRepository.save(userProfile);

        Text adminDescription = Text.write("It's responsible for managing user accounts and association to available profiles. " +
                "Manages resources and general configurations. " +
                "Account type must be created when the system is installed. ");
        Profile adminProfile = ProfileCreatable.createProfile(ProfileID.ofProfileType("Administrator"), adminDescription);
        profileRepository.save(adminProfile);

        Text directorDescription = Text.write("It's an employee with special management functions and consequently special permissions " +
                "in the project administration sphere.");
        Profile directorProfile = ProfileCreatable.createProfile(ProfileID.ofProfileType("Director"), directorDescription);
        profileRepository.save(directorProfile);

        Text visitorDescription = Text.write("Profile that is automaticaly created when you create an account." +
                "The only permission is to ask for other profile.");
        Profile visitorProfile = ProfileCreatable.createProfile(ProfileID.ofProfileType("Visitor"), visitorDescription);
        profileRepository.save(visitorProfile);

    }

    public void run(ApplicationArguments args) throws Exception{
        this.bootstrapping();
    }



    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}


