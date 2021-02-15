package tacos;

import java.util.List;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class Taco {
	  // end::allButValidation[]
	  @NonNull
	  //@Size(min=5, message="Name must be at least 5 characters long")
	  // tag::allButValidation[]
	  private String name;
	  // end::allButValidation[]
	  //@Size(min=1, message="You must choose at least 1 ingredient")
	  // tag::allButValidation[]
	  private List<String> ingredients;
}
