package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProjectTypologyJPARepository;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.datamodel.ProjectTypologyJPA;
import switchtwentyone.project.datamodel.assembler.ProjectTypologyDomainDataAssembler;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectTypologyRepositoryImpl implements ProjectTypologyRepository {


    @Autowired
    IProjectTypologyJPARepository iProjectTypologyJPARepository;

    @Autowired
    ProjectTypologyDomainDataAssembler projectTypologyDomainDataAssembler;

    @Override
    public ProjectTypology saveProjectTypology(ProjectTypology projectTypology) {
        ProjectTypologyJPA projectTypologyJPA = projectTypologyDomainDataAssembler.toData(projectTypology);
        ProjectTypologyJPA savedProjectTypologyJPA = iProjectTypologyJPARepository.save(projectTypologyJPA);
        return projectTypologyDomainDataAssembler.toDomain(savedProjectTypologyJPA);
    }


    @Override
    public Optional<ProjectTypology> findProjectTypologyByID(ProjectTypologyID projectTypologyID) {
        Optional<ProjectTypologyJPA> projectTypologyJPAFound = iProjectTypologyJPARepository.findById(projectTypologyID);
        Optional<ProjectTypology> projectTypologyFound = Optional.empty();
        if (projectTypologyJPAFound.isPresent()) {
            projectTypologyFound = Optional.of(projectTypologyDomainDataAssembler.toDomain(projectTypologyJPAFound.get()));
        }
            return projectTypologyFound;
        }

    @Override
    public List<ProjectTypology> getAllProjectTypologies() {
        List<ProjectTypologyJPA> listProjectTypologyJPA = (List<ProjectTypologyJPA>) iProjectTypologyJPARepository.findAll();
        List<ProjectTypology> listProjectTypology = new ArrayList<>();

        for (int i = 0; i < listProjectTypologyJPA.size(); i++) {
            ProjectTypology projectTypology = projectTypologyDomainDataAssembler.toDomain(listProjectTypologyJPA.get(i));
            listProjectTypology.add(projectTypology);
        }
        return listProjectTypology;
    }



    }