#DDD refactoring logic

1) Although both ProductBacklog and SprintBacklog are detailed in the project requirements we will not consider them a domain entity as they can be implemented as attributes. They do not have actual attributes nor logic associated. They are just lists of user stories directly tied to the specific project they are created in. (P7.L17 - *the* product backlog not *a* product backlog, P7.L26/27, )
2) Every time a sprint or user story is mentioned in the project requirements they have a defined project as their creation/editing context. Thus, they are placed in the same aggregate (with project as the root). (P7.L17 - Product Owner is only defined in a project, P7.L32 - Project Manager is only defined in a project).
3) Task is an independent entity since they can be created outside the context of User Stories. (P4.L29/30 - general/independent).
4) 