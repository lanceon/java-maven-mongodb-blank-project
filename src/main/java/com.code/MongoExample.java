package com.code;

import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.List;

public class MongoExample {
    public static void main(String[] args) throws UnknownHostException {

        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        DBCollection items = db.getCollection("items");

        items.remove(new BasicDBObject());

        for (int i=0; i<100000; i++) {
            DBObject doc = new BasicDBObject("num", i).append("value", "value-" + i);
            items.insert(doc);
        }

        DBObject query = new BasicDBObject("num", new BasicDBObject().append("$gte", 10000).append("$lte", 10010));
        DBObject sort = new BasicDBObject("num", -1);
        DBObject retkeys = new BasicDBObject("_id", 0);

        List<DBObject> list = items.find(query, retkeys).sort(sort).toArray();

        for (DBObject i : list)
            System.out.println(i);


    }
}
