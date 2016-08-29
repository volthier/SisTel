/*package br.gov.cultura.DitelAdm.service;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;

@Service
public class LimiteAtestoFormatter implements Formatter<LimiteAtesto> {

	@Autowired
	LimiteAtestoService limiteAtestoService;
	
	public LimiteAtestoFormatter(){
		super();
	}
	
    @Override
    public String print(LimiteAtesto object, Locale locale) {
        return (object != null ? object.getIdLimiteAtesto().toString() :"");
        }

    @Override
    public LimiteAtesto parse(final String text, Locale locale) throws ParseException {
        final Integer idLimiteAtesto = Integer.valueOf(text);
        return this.limiteAtestoService.getLimitesAtesto().get(idLimiteAtesto);
    }
}*/