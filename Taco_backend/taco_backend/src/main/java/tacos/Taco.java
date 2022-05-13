package tacos;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
@Data
public class Taco {
  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<String> ingredients;
  private Long id;
  private Date createdAt;
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Taco other = (Taco) obj;
	return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
			&& Objects.equals(ingredients, other.ingredients) && Objects.equals(name, other.name);
}
@Override
public int hashCode() {
	return Objects.hash(createdAt, id, ingredients, name);
}
}
