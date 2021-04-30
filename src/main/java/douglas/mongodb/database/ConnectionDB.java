package douglas.mongodb.database;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class ConnectionDB {


	public static void getConnection() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("douglasDB");

		for (String name : database.listCollectionNames()) {

			System.out.println(name);
		}
		//boolean auth = database.authenticate("username", "pwd".toCharArray());

		ArrayList<Document> docs = new ArrayList<Document>();
		MongoCollection<Document> collection = database.getCollection("customer");
		Document d1 = new Document();
		d1.append("name", "Douglas_Muriel");
		d1.append("age", 70);
		docs.add(d1);
		collection.insertMany(docs);

		System.out.println("Documento criado com sucesso!");

		MongoCursor<Document> cur = collection.find().iterator();
		while (cur.hasNext()) {

            Document doc = cur.next();
            ArrayList<Object> customers = new ArrayList<Object>(doc.values());

            System.out.printf("%s: %s%n", customers.get(1), customers.get(2));
        }

		mongoClient.close();
	}


}
