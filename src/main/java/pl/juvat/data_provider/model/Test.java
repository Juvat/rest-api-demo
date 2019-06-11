package pl.juvat.data_provider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lominskk on 2019-06-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    private String name;
    private List<Parameter> parameters;
}
