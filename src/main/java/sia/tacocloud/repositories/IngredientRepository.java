package sia.tacocloud.repositories;

import sia.tacocloud.models.Ingredient;

import org.springframework.data.repository.CrudRepository;
public interface IngredientRepository  extends CrudRepository<Ingredient, String>{
}
