package edu.usach.apicommons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class NoSQLCollectionDTO implements DTO {
    private String collectionName;
}
