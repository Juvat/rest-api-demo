package pl.juvat.data_provider.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lominskk on 2019-06-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlText
    private String value;
}
