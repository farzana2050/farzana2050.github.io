# Databases Enhancement

## Artifact Overview

The artifact selected for the Databases category is the Animal Shelter database application originally developed as part of my coursework. The application uses Python and MongoDB to manage animal records through Create, Read, Update, and Delete (CRUD) operations. I selected this artifact because it demonstrates my ability to connect application code to a database and manage persistent data programmatically.

## Enhancement

For the CS 499 enhancement, I improved the original Python CRUD module to make the database implementation more secure, reliable, and maintainable. One important improvement was removing the hard-coded database password from the source code. The enhanced application allows the password to be provided when the AnimalShelter object is created or retrieved from the AAC_DB_PASSWORD environment variable.

I also strengthened input validation throughout the CRUD methods. The create method validates that incoming data is a non-empty dictionary and checks for required fields such as animal_id, name, and animal_type. The read, update, and delete methods also validate their inputs before performing database operations.

The database connection was improved by adding connection error handling and a MongoDB ping operation to verify that the database server is reachable. I also added a close method so the MongoDB client connection can be closed properly when database operations are complete.

## Testing and Results

I created a test script to verify the complete CRUD lifecycle. The test inserted a new animal record, retrieved the record from MongoDB, updated the animal's name, retrieved the updated record, deleted the record, and confirmed that the deleted record was no longer present.

The test produced successful results for each operation. The create operation returned True, the update operation reported one modified record, the delete operation reported one deleted record, and the final read returned an empty list. These results confirmed that the enhanced CRUD functionality operated correctly.

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

## Course Outcomes

This enhancement demonstrates progress toward the Computer Science program outcome of using well-founded techniques, skills, and computing tools to implement solutions that provide value and accomplish specific goals. It also supports the security outcome by removing hard-coded credentials, validating application input, and improving the handling of database resources.

The enhancement also demonstrates my ability to evaluate an existing computing solution, identify weaknesses, implement improvements, and verify those improvements through testing.

## Artifact Files

The original and enhanced versions of the database artifact are available below:

- [Original Artifact](original/)
- [Enhanced Artifact](enhanced/)
