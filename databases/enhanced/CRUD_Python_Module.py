import os

from pymongo import MongoClient


class AnimalShelter:
    """Provide CRUD operations for the AAC animal collection in MongoDB."""

    def __init__(self, username="aacuser", password=None):
        """
        Initialize the MongoDB connection.

        The password can be passed directly when creating the object or
        retrieved from the AAC_DB_PASSWORD environment variable.
        """

        if password is None:
            password = os.getenv("AAC_DB_PASSWORD")

        if not password:
            raise ValueError(
                "Database password is required. "
                "Provide a password or set AAC_DB_PASSWORD."
            )

        host = "localhost"
        port = 27017
        database_name = "aac"
        collection_name = "animals"

        try:
            self.client = MongoClient(
                host=host,
                port=port,
                username=username,
                password=password,
                authSource="admin",
                serverSelectionTimeoutMS=5000
            )

            self.database = self.client[database_name]
            self.collection = self.database[collection_name]

            # Confirm that MongoDB is reachable.
            self.client.admin.command("ping")

        except Exception as e:
            raise ConnectionError(
                f"Unable to connect to MongoDB: {e}"
            ) from e

    def create(self, data):
        """Insert a new animal record into the database."""

        if not isinstance(data, dict) or not data:
            raise ValueError(
                "Create data must be a non-empty dictionary."
            )

        required_fields = [
            "animal_id",
            "name",
            "animal_type"
        ]

        for field in required_fields:
            if field not in data or data[field] in (None, ""):
                raise ValueError(
                    f"Missing required field: {field}"
                )

        try:
            result = self.collection.insert_one(data)
            return result.inserted_id is not None

        except Exception as e:
            print("Create error:", e)
            return False

    def read(self, criteria):
        """Return records matching the supplied MongoDB query."""

        if criteria is None:
            criteria = {}

        if not isinstance(criteria, dict):
            raise ValueError(
                "Read criteria must be a dictionary."
            )

        try:
            return list(self.collection.find(criteria))

        except Exception as e:
            print("Read error:", e)
            return []

    def update(self, query, new_values):
        """Update records matching the supplied query."""

        if not isinstance(query, dict) or not query:
            raise ValueError(
                "Update query must be a non-empty dictionary."
            )

        if not isinstance(new_values, dict) or not new_values:
            raise ValueError(
                "Update values must be a non-empty dictionary."
            )

        try:
            result = self.collection.update_many(
                query,
                {"$set": new_values}
            )

            return result.modified_count

        except Exception as e:
            print("Update error:", e)
            return 0

    def delete(self, query):
        """Delete records matching the supplied query."""

        if not isinstance(query, dict) or not query:
            raise ValueError(
                "Delete query must be a non-empty dictionary."
            )

        try:
            result = self.collection.delete_many(query)
            return result.deleted_count

        except Exception as e:
            print("Delete error:", e)
            return 0

    def close(self):
        """Close the MongoDB client connection."""

        if self.client:
            self.client.close()