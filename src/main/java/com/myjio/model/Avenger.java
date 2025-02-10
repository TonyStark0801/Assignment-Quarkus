package com.myjio.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "avengers")
public class Avenger extends PanacheMongoEntity {


    private String name;

    @NotBlank(message = "Email Cannot be empty")
    @Email
    private String email;
}
