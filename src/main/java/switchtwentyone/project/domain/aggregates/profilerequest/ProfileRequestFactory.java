package switchtwentyone.project.domain.aggregates.profilerequest;

import org.springframework.stereotype.Component;

@Component
public class ProfileRequestFactory implements ProfileRequestCreatable {

    private static ProfileRequestFactory instance;

    private ProfileRequestFactory(){
        //Private construtor, after Singleton Pattern.
    }

    public static ProfileRequestFactory getInstance(){
        if(instance == null){
            instance =new ProfileRequestFactory();
        }
        return  instance;
    }
}