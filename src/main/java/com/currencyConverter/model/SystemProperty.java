package com.currencyConverter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("system_property")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SystemProperty {

    @Id
    private String name;

    private String value;

}
