package com.yoshikawa.contabancaria.infra.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DataBaseConfig {

    public void connectionMongoDB(){

        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://localhost:27017/contaBancaria?authSource=admin?connectTimeoutMS=2000"))
                        .applyToSocketSettings(builder ->
                                builder.connectTimeout(5, SECONDS))
                        .build());

    }
}
