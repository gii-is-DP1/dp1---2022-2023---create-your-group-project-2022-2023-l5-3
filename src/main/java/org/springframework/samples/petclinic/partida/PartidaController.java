package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/partidas")
public class PartidaController {

	@Autowired
	private PartidaService partidaService;
	
	private static final String VIEW_CREATE_PARTIDA = "partidas/createOrUpdatePartidaForm";
	private static final String VIEW_LIST = "partidas/partidaList";

    
	

	@GetMapping(value = { "/partidas" })
	public String showPartidaList(Map<String, Object> model) {
		
		List<Partida> partidas = new ArrayList<>();
        partidas = (List<Partida>) partidaService.findPartidas();
		model.put("partidas", partidas);
		return "partidas/partidaList";
	}
	
	@GetMapping(path="/create")
	public String initCreationForm(Map<String,Object> model){
		Partida partida = new Partida();
		
		model.put("partida", partida);

		return VIEW_CREATE_PARTIDA;
		
	}
	
	@PostMapping(path="/create")
	public String processCreationForm(@Valid Partida p, BindingResult result, Map<String, Object> model) {
		
		if (result.hasErrors()) {
			List<ObjectError> errores = result.getAllErrors();
			String errores2 = "";
			
			for(ObjectError i:errores) {
				String error = i.toString();
				errores2+=error;
				}
			model.put("message", errores2);
			return VIEW_CREATE_PARTIDA;
		}
		else {
			
			this.partidaService.save(p);
			model.put("message", "Partida OK");
			
			return "welcome";
		}
	
		
	}
	

	
	
	
}
