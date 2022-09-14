--Default Values--

-- Customers --
INSERT INTO CUSTOMERS( NIF, NAME )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Customer.csv', null, 'fieldSeparator=;' );


-- Project Typologies --
INSERT INTO PROJECT_TYPOLOGY( VALUE_STRING, DESCRIPTION )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Typologies.csv', null, 'fieldSeparator=;' );

-- Business Sector --
INSERT INTO BUSINESS_SECTOR( CODE, NAME )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Business_Sector.csv', null, 'fieldSeparator=;' );

-- Project --
INSERT INTO PROJECT( PROJECTID, BUDGET, CODE, NIF, DESCRIPTION, END_DATE, NAME, PLANNED_SPRINTS, VALUE_STRING, SPRINT_DURATION, START_DATE, STATUS )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Project.csv', null, 'fieldSeparator=;' );

-- Sprints --
INSERT INTO SPRINTS( ID, PROJECTID, SPRINT_DURATION, SPRINT_NUMBER, START_DATE )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Sprint.csv', null, 'fieldSeparator=;' );

-- Profiles -- In reaity, it is not adding anything because, profiles with the same id are already being created in the bootstrap
INSERT INTO PROFILES( PROFILE_TYPE_DATA, DESCRIPTION_DATA )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Profiles.csv', null, 'fieldSeparator=;' );

-- User Stories --
INSERT INTO USER_STORIES( ID, DECOMPOSED, DETAIL, PRIORITY, PROJECTID, STATEMENT, STORY_NUMBER, USIDPARENT )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/UserStories.csv', null, 'fieldSeparator=;' );

-- Sprint Backlog Item --
INSERT INTO SPRINTBACKLOG_ITEMS( ID, CATEGORY, EFFORT_ESTIMATE, USID, SPRINT_ID )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Sprint_Backlog_Item.csv', null, 'fieldSeparator=;' );

-- Account --
INSERT INTO ACCOUNTS( EMAIL_DATA, ACTIVE, EMAIL, FUNCTION, NAME, PASSWORD, PHOTO )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Account.csv', null, 'fieldSeparator=;' );

-- Account_Profile --
INSERT INTO ACCOUNTJPA_PROFILEIDS( ACCOUNTJPA_EMAIL_DATA, PROFILEIDS )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Accounts_Profile.csv', null, 'fieldSeparator=;' );

-- Resources --
INSERT INTO RESOURCES( RESOURCEID, EMAIL_DATA, COST_PER_HOUR, CURRENCY, END_DATE, PERCENTAGE_OF_ALLOCATION, PROJECTID, ROLE, ROLE_DESCRIPTION, START_DATE )
SELECT *
FROM CSVREAD( 'src/main/resources/Data/Resources.csv', null, 'fieldSeparator=;' );
