## Glossary

**v20220206

**Account**

Register in the system that corresponds to a person. Holds some data of the person, such as name, email, password and
 photo (optional). May be attributed one or several profiles to this user account.

 ###Attributes

    *name -  The user name.
    *email - used to acess the system, must be unique to a person when creating account.
    *password - used in combination with email to acess the system.
    *function - Equivalent to job position. Definition of an employee's function in the company.
    *It isn't related to the person use of the system.
    *photo - Image of the person.
    *workingCapacityHoursPerWeek - Maximum amount of time that an account is available to work in a week
     (example: 40 hours per week).
    *isActive - defines if a account is active or inactive. At the time of its creation, the status (isActive) is, by default, inactive (false). After, Administrator action it can change it to active (true).

**BusinessSector**

This is the business industry/sector where the project worked on by our clients is inserted.

 ###Attributes

    *CAE -  Economic activity classification. Code that identifies the Business Sector activities of the client.
    *description - the business sector identification.

**Customer**

This is the person or company for which our client is developing a project.

###Attributes

    *name - person or company name.
    *NIF -   a sequential number intended exclusively for the treatment of fiscal and customs index information. Unique to each customer.

**EffortUpdate**

The entity that registers work done in tasks associated to user stories and sprint (scrum) ceremonies. It is submitted by contributing developers (Resource) assigned to the task.


###Attributes

	*dateTime - in LocalDate format it is a timestamp for the submitted data.
	*hoursSpent - a number in double format it registers the hours spent (decimal number) on task.
	*comment - a String that registers an optional comment to the work made on task.

**Product Backlog**

The product backlog is the list of all users stories of a certain project. This means that whenever a User Story is
created it is added to the list of User Stories in the Product Backlog.

**Profile**

Entity responsible for define the parameters that a profile should contain. 

###Attributes

    *ProfileType - Define the name of a profile;
    *Description - Responsible for describe functions and permissions of a profile type.

**ProfileRequest**

The Request profile is linked to the profiles by which the guest can request a profile change to the administrator (i.e. administrator, visitor, director, user).

###Attributes

    *dateTime -  the date on which the request for a change of profile was made.
    *comment - brief comment with explanation of the request for profile change.

**Project Typology**

Information regarding how the budget of a project is calculated. Established in a contract.

### Attributes:
    *Description - Information regardig how project typology operates
    *Code -unique and sequencial number that identifies the project typology and is automaticaly generated 

**Project**

collaborative enterprise related to software development process that is carefully planned to achieve a particular aim.

    *Code - unique and sequencial number that identifies the project and is automaticaly generated;
    *Name - project identification;
    *Description - brief information related to project;
    *Start date - date wich defines the project begining;
    *End date - date wich defines the project ending;
    *Sprint duration - how long the sprint takes. described in ccomplete weeks;
    *Planned sprints - expected number of sprints in a project;
    *Stauts - situation in which the project finds itself. Must be one of the following options (Planned / Inception / Elaboration / Construction / Transition / Warranty 20 / Closed);
    *Budget - monetary amount available for resource spending;

**Resource**

It's an user/account that is allocated to a certain project with a specific profile type and with a known duration.
This profile type defines the relationship that the account has with project.

###Atributes

    *startDate - the date a resource starts working in a project.
    *endDate - the date a resource ends working in a specific project.
    *percentageOfAllocation - the assignement time that a resource is allocated to a project, directly related to the workingCapacityHoursPerWeek,
    represented by a percentage that can´t surplus 100%(100% for ull-time, or the percentage corresponding to part-time).
    *costPerHour - monetary value that the resource costs ffor each hour used in the project.

**ResourceRole**

Special profiles, since they are not fixed profiles assigned to particular users, but the result of the roles the users have in a
particular project over a certain period.

###Atributes

    *role - The Project Manager, Product Owner and Scrum Master and Developer are the possible roles to be assign to resources in a project.
    *description - Brief description of the role.

**Sprint**

Set period of time during which the user stories of the sprint backlog have to be completed,  made ready for review, deployed  and approved. It has a pre-defined duration.


###Attributes

    *number -  The number of a sprint.
    *startDate - date when sprint starts.
    *password - end date of sprint.

**Sprint Backlog**

Each Sprint must have its own Sprint Backlog.
According to the Scrum concept, a Sprint Backlog is a list of User Stories
that have been selected from the Product Backlog to be worked on during that Sprint.
Considering that these User Stories may belong to different
categories in the Sprint Scrum Board, the Sprint Backlog contains, in fact, one list of User Stories for each Scrum Board
category.

**Task**

Task is the actual set of developments that occurs during each sprint.

###Attributes:
    *number - task Number from which is identified
    *name - task name; up to 100 characters and cannot be empty
    *description - task description; up to 300 characters and cannot be empty
    *startDate - (optional) task start date
    *endDate - (optional) task end date
    *effortEstimate - (optional) result from a qualitative attribution of the estimated effort; the type of data is integer from the Fibonacci sequence;
    *taskType - describes the type of task; several options includes Meeting, Documentation, Design, Implementation, Testing or Deployment;
    *taskStatus - String that defines the status of the task; several options includes Planned, Running, Finished or Blocked; by default is set as “Planned"
    *hoursSpent - (optional) determines the number of hours spent during the execution of the task; this number is incremental; by default is set by 0
    *percentageOfExecution - (optional) determines the percentage of completion of the task; such as hours spent, this attribute is also incremental; by default is set by 0
    *userStory - (optional) this attribute relates the task to a specific user story; might be set as null when the connection is inexistent
    *effortUpdate - this attribute is a list of records; connects the task with effortUpdate class
    *taskPrecedence - (optional) designs the task as having a precedent task; might be set as null

**User Story (US)**

General explanation of a software feature, from the end user's perspective, to be implemented in the project of our client.
The user stories are created by the Product Owner and added to the product backlog.
The Product Owner may also decompose a user story that is too broad in scope into more detailed User Stories.

###Atributes

    *number - sequential number attributed to the user story in the product backlog in the moment of its creation.
    *statement - brief description of the User Story
	*detail - a more detailed description of the user story.
	*status -informs the current state of the User Story in the Product Backlog. The possible values, presently, are "Planned", "In Progress", "Rejected", "Finished" and "Decomposed"
	*priority -number that defines the order by which a user story should be included in sprints to be implemented. When the user story is finished, in progress, or decomposed, this value is set to zero.