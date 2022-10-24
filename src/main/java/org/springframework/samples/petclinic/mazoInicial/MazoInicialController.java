package org.springframework.samples.petclinic.mazoInicial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MazoInicialController {
    private final MazoInicialService mazoinicialservice;

    @Autowired
	public MazoInicialController(MazoInicialService mazoinicialservice) {
		this.mazoinicialservice = mazoinicialservice;
	}

	@GetMapping(value = { "/mazos" })
	public String showMazosIniciales(Map<String, Object> model) {
		List<MazoInicial> mazosIniciales = new ArrayList<>();
        mazosIniciales = (List<MazoInicial>) mazoinicialservice.findAllMazosIniciales();
		model.put("mazosiniciales",  mazosIniciales);
		return "mazos/mazosiniciales";
	}





}
