package com.api.data.userspojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // default constructor
@AllArgsConstructor // parameterize constructor
@Data // getter and setter
@Builder // to build the method
public class UsersLambok {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
