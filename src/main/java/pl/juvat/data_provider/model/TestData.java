package pl.juvat.data_provider.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JacksonXmlRootElement
public class TestData {

    private String title;
    private String description;
    private List<Test> tests;
}
