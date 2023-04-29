package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacosController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", Type.WRAP),
                new Ingredient("COTO", "kukurydziana", Type.WRAP),
                new Ingredient("GRBF", "mielona wołowina", Type.PROTEIN),
                new Ingredient("CARN", "kawałki mięsa", Type.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkę", Type.VEGGIES),
                new Ingredient("LETC", "sałata", Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterey Jack", Type.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE),
                new Ingredient("SRCR", "śmietana", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    ingredients.stream().filter(i -> i.getType() == type).collect(Collectors.toList()));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if(errors.hasErrors()) {
            return "design";
        }

        log.info("Przetwarzanie projektu taco: " + design.getIngredients());
        return "redirect:/orders/current";
    }
}
