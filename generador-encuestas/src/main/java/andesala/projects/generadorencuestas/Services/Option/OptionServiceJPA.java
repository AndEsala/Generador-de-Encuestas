package andesala.projects.generadorencuestas.Services.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andesala.projects.generadorencuestas.Models.Option;
import andesala.projects.generadorencuestas.Repositorys.OptionRepository;

@Service
public class OptionServiceJPA implements OptionService {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public void saveOption(Option option) {
        optionRepository.save(option);
    }
    
}
