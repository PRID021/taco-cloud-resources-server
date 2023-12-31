package sia.tacocloud.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 5, message = "Name must at least 5 characters long ")
	private String name;

	private Date createdAt = new Date();


	@NotNull
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	@ManyToMany(targetEntity = Ingredient.class)
	private List<Ingredient> ingredients = new ArrayList<>();


	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

}
