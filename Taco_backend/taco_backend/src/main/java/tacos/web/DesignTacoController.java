package tacos.web;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
	this.ingredientRepo = ingredientRepo;

	}
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(ingredients::add);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors) {
	  if (errors.hasErrors()) {
	    return "design";
	  }
	  // Save the taco design...
	  // We'll do this in later
	  log.info("Processing design: " + taco);
	  return "redirect:/orders/current";
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("taco", new Taco()); 
		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		List<Ingredient> ingrList = new ArrayList<Ingredient>();
		for (Ingredient ingredient: ingredients) {
			if (ingredient.getType().equals(type)) ingrList.add(ingredient);
		}
		return ingrList;
	}

}
