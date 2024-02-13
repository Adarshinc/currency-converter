package com.currencyConverter.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("system_property")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SystemProperty {

    @Id
    private String id;

    private String name;

    private String value;

}
