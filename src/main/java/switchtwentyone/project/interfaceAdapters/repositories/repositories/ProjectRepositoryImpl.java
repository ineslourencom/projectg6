package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProjectJPARepository;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.datamodel.ProjectJPA;
import switchtwentyone.project.datamodel.assembler.ProjectDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    IProjectJPARepository iProjectJPARepository;
    @Autowired
    ProjectDomainDataAssembler projectDomainDataAssembler;

    public ProjectRepositoryImpl() {

    }


    @Override
    public Optional<Project> findById(ProjectID id) {
        Optional<ProjectJPA> opProjectJPA = iProjectJPARepository.findById(id);
        Optional<Project> opProject = Optional.empty();
        if (opProjectJPA.isPresent()) {
            ProjectJPA projectJPA = opProjectJPA.get();
            Project project = projectDomainDataAssembler.toDomain(projectJPA);
            opProject = Optional.of(project);

        }
        return opProject;
    }


    @Override
    public int getHighestProjectID() {
        int highestProjectID = 0;
        List<ProjectJPA> allProjectsJPA = iProjectJPARepository.findAll();
        for (int i = 0; i < allProjectsJPA.size(); i++) {
            int id = allProjectsJPA.get(i).getId().getProjectID();
            if (id > highestProjectID) {
                highestProjectID = id;
            }
        }
        return highestProjectID;
    }


    @Override
    public Project save(Project proj) {
        ProjectJPA projJPA = projectDomainDataAssembler.toData(proj);
        ProjectJPA savedProjectJpa = iProjectJPARepository.save(projJPA);
        Project project = projectDomainDataAssembler.toDomain(savedProjectJpa);
        return project;
    }

    /**Returns all existing projects
     *
     * @return all existing projects
     */
    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        List<ProjectJPA> projectsJPA = iProjectJPARepository.findAll();
        for (ProjectJPA p : projectsJPA) {
            Project project = projectDomainDataAssembler.toDomain(p);
            projects.add(project);

        }
        return projects;
    }


    /**
     * Validate presence of project in database given a ProjectID.
     *
     * @param id project identifier
     * @return true if present, false if otherwise
     */
    @Override
    public boolean existsById(ProjectID id) {
        return iProjectJPARepository.existsById(id);
    }

}
