package tacos;

import lombok.Data;

import java.util.List;

@Data
public class Taco {

    public String name;
    private List<String> ingredients;
}
