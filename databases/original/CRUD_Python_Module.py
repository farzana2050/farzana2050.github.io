# Example Python Code to Insert a Document 

from pymongo import MongoClient 
from bson.objectid import ObjectId 

class AnimalShelter(object): 
    """ CRUD operations for Animal collection in MongoDB """ 

    def __init__(self): 
        # Initializing the MongoClient. This helps to access the MongoDB 
        # databases and collections. This is hard-wired to use the aac 
        # database, the animals collection, and the aac user. 
        # 
        # You must edit the password below for your environment. 
        # 
        # Connection Variables 
        # 
        USER = 'aacuser' 
        PASS = '@min@mitul366'
        HOST = 'localhost' 
        PORT = 27017 
        DB = 'aac' 
        COL = 'animals' 
        # 
        # Initialize Connection 
        # 
        self.client = MongoClient(
            host=HOST,
            port=PORT,
            username=USER,
            password=PASS,
            authSource='admin'
        )
        self.database = self.client['%s' % (DB)] 
        self.collection = self.database['%s' % (COL)] 

    # Create a method to return the next available record number for use in the create method
            
    # Complete this create method to implement the C in CRUD. 
    def create(self, data):
        if data is not None: 
            try:
                self.database.animals.insert_one(data)  # data should be dictionary       
                return True
            except Exception as e:
                print(e)
                return False
        else: 
            raise False

    # Create method to implement the R in CRUD.
    def read(self, criteria):
        if criteria is not None:
            try:
                data = self.database.animals.find(criteria)
                return list(data)
            except Exception as e:
                print(e)
                return []
            else:
                return []
    # Update method to implement the U in CRUD.
    def update(self, query, new_values):
        if query is not None and new_values is not None:
            try:
                result = self.collection.update_many(query, {"$set": new_values})
                return result.modified_count
            except Exception as e:
                print("Update error:", e)
                return 0
        return 0
    
    # Delete method to implement the D in CRUD.
    def delete(self, query):
        if query is not None:
            try:
                result = self.collection.delete_many(query)
                return result.deleted_count
            except Exception as e:
                print("Delete error:", e)
                return 0
        return 0