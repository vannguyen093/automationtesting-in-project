package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CustomerData {
        @JsonProperty("firstName")
        String firstName;
        @JsonProperty("lastName")
        String lastName;
        @JsonProperty("gender")
        String gender;
        @JsonProperty("email")
        String email;
        @JsonProperty("password")
        String password;
        @JsonProperty("date")
        String date;
        @JsonProperty("month")
        String month;
        @JsonProperty("year")
        String year;
}
