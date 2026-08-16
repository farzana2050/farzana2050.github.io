# Databases Enhancement

## Artifact Overview

The artifact selected for the Databases category is the Animal Shelter database application originally developed as part of my previous computer science coursework. The application uses Python and MongoDB to manage animal records through Create, Read, Update, and Delete (CRUD) operations.

I selected this artifact for my CS 499 ePortfolio because it demonstrates the connection between application development and persistent data management. The project provided an opportunity to demonstrate database programming, CRUD operations, validation, exception handling, testing, and security while improving an existing database-driven application.

## Enhancement

For the CS 499 enhancement, I improved the original Python CRUD module to make the database implementation more secure, reliable, and maintainable.

One of the most important improvements was removing the hard-coded database password from the source code. The enhanced implementation allows the password to be passed when the `AnimalShelter` object is created or retrieved from the `AAC_DB_PASSWORD` environment variable. This reduces the risk associated with storing credentials directly in source code and demonstrates a stronger security mindset.

I also strengthened input validation throughout the CRUD methods. The `create` method verifies that incoming data is a non-empty dictionary and checks for required fields such as `animal_id`, `name`, and `animal_type`. The `read`, `update`, and `delete` methods also validate their inputs before performing database operations.

The database connection was improved through exception handling, a connection timeout, and a MongoDB `ping` operation that verifies that the database server can be reached. I also added a `close` method so that the MongoDB client connection can be properly closed when database operations are complete.

The update and delete operations were designed to require meaningful query information before modifying database records. This helps reduce the possibility of unintentionally updating or deleting a large number of records.

## Testing and Results

I tested the enhanced module by performing the complete CRUD lifecycle against the MongoDB database. The test created a new animal record, retrieved that record, updated the animal's name, retrieved the updated record, deleted the record, and finally verified that the deleted record was no longer present.

The testing produced successful results for each operation. The create operation returned `True`, the update operation reported one modified record, the delete operation reported one deleted record, and the final read returned an empty list. These results provided evidence that the enhanced CRUD implementation performed the intended database operations correctly.

Testing the complete lifecycle was important because database functionality should not be evaluated only by examining individual methods. The operations work together as part of a larger data-management process, so testing them in sequence helped verify that the enhanced module behaved correctly from creation through deletion.

## Skills Demonstrated

This enhancement demonstrates skills in:

- MongoDB database development
- Python database programming
- CRUD operations
- Data validation
- Exception and connection handling
- Secure credential management
- Database testing
- Code maintainability
- Resource management
- Security-focused software development

The artifact also demonstrates my ability to analyze an existing database implementation, identify weaknesses, implement improvements, and verify the resulting behavior through testing.

## Course Outcome Alignment

This enhancement strongly supports the Computer Science program outcome involving the use of well-founded techniques, skills, and tools to implement computing solutions that deliver value and accomplish specific goals. Python, PyMongo, and MongoDB were used together to create a maintainable interface between application code and persistent data.

The artifact also supports the outcome involving the design and evaluation of computing solutions. I evaluated the original CRUD implementation, identified opportunities to improve reliability and security, implemented those improvements, and tested the complete database lifecycle to verify the results.

The enhancement particularly supports the security-focused program outcome. Removing hard-coded credentials, validating input before database operations, handling connection failures, and restricting unsafe update and delete requests demonstrate a security mindset that considers potential weaknesses before they result in data or application problems.

The organization, documentation, and testing of the enhanced module also contribute to professional technical communication by making the database implementation easier for another developer to understand and maintain.

## Reflection

Enhancing this artifact strengthened my understanding of the relationship between application code, databases, reliability, and security. One of the most important lessons I learned was that database development involves more than successfully executing CRUD commands. A professional database application must also validate incoming information, handle failures appropriately, manage resources, and protect credentials.

One challenge during the enhancement was configuring and testing the application in the Codio environment while working with MongoDB authentication and database connectivity. Changes to credential handling required me to understand how the application received its password and how that information was passed to MongoDB without returning to the insecure approach of permanently storing the password in the source code.

Testing also reinforced the importance of verifying the effect of database operations rather than assuming they succeeded. Reading the record after creation and update, and checking for an empty result after deletion, provided evidence that each operation produced the expected change in the database.

Overall, this enhancement improved my confidence in developing database-backed applications and strengthened my understanding of secure credential management, defensive programming, validation, testing, and maintainable database code. These skills are directly applicable to my goal of working in software and full-stack development.

## Artifact Files

- [Original Artifact](original/)
- [Enhanced Artifact](enhanced/)
