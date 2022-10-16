package andesala.projects.generadorencuestas.Services.Option;

import java.util.List;

import andesala.projects.generadorencuestas.Models.Option;

public interface OptionService {
    void saveOption(Option option);
    void deleteOptions(List<Option> opciones);
}
