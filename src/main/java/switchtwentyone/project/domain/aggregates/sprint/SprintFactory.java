package switchtwentyone.project.domain.aggregates.sprint;


public class SprintFactory implements SprintCreatable {

        private static SprintFactory instance;

        private SprintFactory(){
            //Private construtor, after Singleton Pattern.
        }

        public static SprintFactory getInstance(){
            if(instance == null){
                instance =new SprintFactory();
            }
            return  instance;
        }
    }

