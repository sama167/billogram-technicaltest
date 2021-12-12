package st.projects.assignments.billogram.service.discount.generator;

import org.springframework.stereotype.Service;
import st.projects.assignments.billogram.repository.model.Campaign;
@Service
public class CodeGeneratorFactory {

    final SimpleCodeGenerator simpleCodeGenerator;

    public CodeGeneratorFactory(SimpleCodeGenerator simpleCodeGenerator) {
        this.simpleCodeGenerator = simpleCodeGenerator;
    }

    /**
     * A campaign can have some settings that specifies how
     * discount codes should be generated.
     * Now for simplicity we always return default generator!
     * */
    public CodeGeneratorStrategy getCodeGenerator(Campaign campaign){
        return simpleCodeGenerator;
    }

}
