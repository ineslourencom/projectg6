package switchtwentyone.project.domain.services;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;

import java.util.*;

@Service
public class ServiceFindUS{




    public TreeMap<UserStory, String> getAllUserStoriesInfo(List<UserStory> usListFromAppService, Sprint runningSprint, List<Sprint> previousSprints){

        TreeMap<UserStory, String> listUSStatus = new TreeMap<>();

        listUSStatus.putAll(getUSFromRunningSprint(usListFromAppService,runningSprint));
        listUSStatus.putAll(getAllUSFromPreviousSprints(usListFromAppService, previousSprints ));
        listUSStatus.putAll(getDecomposedUS(usListFromAppService));
        listUSStatus.putAll(getNonAllocatedUSInAnySprint(usListFromAppService,previousSprints,runningSprint));


        return listUSStatus;
    }

    public TreeMap<UserStory, String> getAllUserStoriesInfoWithNoRunningSprint(List<UserStory> usListFromAppService,List<Sprint> previousSprints){
        TreeMap<UserStory, String> listUSStatus = new TreeMap<>();
        TreeMap<UserStory, String> usToBeAllocated = new TreeMap<>();

        for (UserStory us : usListFromAppService) {
            if (! getAllUSFromPreviousSprints(usListFromAppService, previousSprints).containsKey(us)){

                usToBeAllocated.put(us, "Not Yet Added to Any Sprint");
            }
        }
        listUSStatus.putAll(getAllUSFromPreviousSprints(usListFromAppService, previousSprints));
        listUSStatus.putAll(getDecomposedUS(usListFromAppService));
        listUSStatus.putAll(usToBeAllocated);

        return listUSStatus;
    }






    public TreeMap<UserStory, String> getUSFromRunningSprint(List<UserStory> usListFromAppService, Sprint runningSprint){

        List<SprintBacklogItem> sprintBacklogInActive = runningSprint.getSprintBacklogItems();

        TreeMap<UserStory, String> usFromRunningSprint = new TreeMap<>();

        if (!sprintBacklogInActive.isEmpty()) {
            for (SprintBacklogItem sBacklogItem : sprintBacklogInActive) {

                UserStoryID usID = sBacklogItem.getUsID();
                UserStory us1 = getUserStoryByID(usListFromAppService, usID);
                String usStatus = sBacklogItem.getCategory().getCategory().getDbName();
                usFromRunningSprint.put(us1, usStatus);
            }
        }

        return usFromRunningSprint;
    }




    protected TreeMap<UserStory, String> getAllUSFromPreviousSprints(List<UserStory> usListFromAppService, List<Sprint> previousSprints){

        List<SprintBacklogItem> sprintBacklogFromPreviousSprints = new ArrayList<>();
        TreeMap<UserStory, String> usFromPreviousSprints = new TreeMap<>();

        for (Sprint sprint: previousSprints) {
            List <SprintBacklogItem> springBacklog = sprint.getSprintBacklogItems();
            sprintBacklogFromPreviousSprints.addAll(springBacklog);
        }

        for (SprintBacklogItem sBacklogItem : sprintBacklogFromPreviousSprints) {
            UserStoryID usID = sBacklogItem.getUsID();
            UserStory us1 = getUserStoryByID(usListFromAppService, usID);
            String usStatus = sBacklogItem.getCategory().getCategory().getDbName();
            usFromPreviousSprints.put(us1,usStatus);

        }

        return usFromPreviousSprints;
    }

    protected TreeMap<UserStory, String> getDecomposedUS(List<UserStory> usListFromAppService){
        TreeMap<UserStory, String> usDecomposed = new TreeMap<>();

        for (UserStory us: usListFromAppService) {
            boolean isDecomposed =  us.getIsDecomposed();

            if(isDecomposed){
                usDecomposed.put(us, "Decomposed");
            }

        }
        return usDecomposed;
    }

    protected TreeMap<UserStory, String> getNonAllocatedUSInAnySprint(List<UserStory> usListFromAppService,List<Sprint> previousSprints, Sprint runningSprint) {
        TreeMap<UserStory, String> usToBeAllocated = new TreeMap<>();
        for (UserStory us: usListFromAppService) {
            if(! getAllUSFromPreviousSprints(usListFromAppService, previousSprints).containsKey(us)
                    && !getUSFromRunningSprint(usListFromAppService, runningSprint).containsKey(us)){

                usToBeAllocated.put(us, "Not Yet Added to Any Sprint");
            }
        }
        return usToBeAllocated;
    }





    protected UserStory getUserStoryByID(@NotNull List<UserStory> usListFromAppService, UserStoryID usID) {


        return usListFromAppService.stream()
                .filter(userStory -> userStory.hasID(usID))
                .findAny().get();
    }


    /**
     * This method finds all UserStoryIDs in a given Sprint.
     *
     * @param sprint Optional of the sprint instance
     * @return a list of all UserStoryIDs in a Sprint, an empty
     * list if none exist
     */
    public List<UserStoryID> findUSIDinSprint(Optional<Sprint> sprint){
        List<UserStoryID> usIDList = new ArrayList<>();
        List<SprintBacklogItem> sprintBacklogItems;
        if (sprint.isPresent()) {
            sprintBacklogItems = sprint.get().getSprintBacklogItems();
            for (SprintBacklogItem sbli : sprintBacklogItems) {
                usIDList.add(sbli.getUsID());
            }
        }
        return usIDList;
    }


    /**
     * This method finds all UserStoryIDs of Done User Stories in a given list
     * of Sprints.
     *
     * @param sprintList Optional of the sprint instance
     * @return a list of all UserStoryIDs of Done USs in a Sprint list, an empty
     * list if none exist
     */
    public List<UserStoryID> findDoneUSIDinSprint(List<Sprint> sprintList){
        List<UserStoryID> usIDList = new ArrayList<>();
        List<SprintBacklogItem> sprintBacklogItems;
        for (Sprint s : sprintList) {
            sprintBacklogItems = s.getSprintBacklogItems();
            for (SprintBacklogItem sbli : sprintBacklogItems) {
                if (sbli.getCategory().sameValueAs(ScrumBoardCategory.done())) {
                    usIDList.add(sbli.getUsID());
                }
            }
        }
        return usIDList;
    }

    /**
     * This method generates / compiles the Product Backlog from three lists.
     *
     * @param usListA all Non-Decomposed UserStories in a given project
     * @param usListB all UserStories in the running sprint of a given project
     * @param usListC all DONE UserStories in a project
     * @return the Product Backlog of a project i.e. a UserStory pool from which to pick USs for Sprints
     */
    public List<UserStory> compileProductBacklog(List<UserStory> usListA, List<UserStory> usListB, List<UserStory> usListC) {
        List<UserStory> productBacklog = new ArrayList<>();
        for (UserStory usA : usListA) {
            boolean eligible = true;
            for (UserStory usB : usListB) {
                if ((usB.equals(usA))) {
                    eligible = false;
                }
            }
            for (UserStory usC : usListC) {
                if ((usC.equals(usA))) {
                    eligible = false;
                }
            }
            if (eligible) {
                productBacklog.add(usA);
            }
        }
        return productBacklog;
    }

    /**
     * This code orders a list of User Stories by their priority.
     */
    public void orderListOfUSByPriority(List<UserStory> userStoryList) {
        userStoryList.sort(new UserStoryComparator());
    }

    /**
     * Static class that creates the comparator for user stories.
     * This class needs to be moved to an Utils package.
     */
    static class UserStoryComparator implements Comparator<UserStory> {
        // override the compare() method
        @Override
        public int compare(final UserStory us1,
                           final UserStory us2) {
            int answer;
            if (us1.getPriority() > us2.getPriority()) {
                answer = 1;
            } else if (us1.getPriority() == us2.getPriority()) {
                answer = 0;
            } else {
                answer = -1;
            }
            return answer;
        }
    }

}
