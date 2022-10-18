package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartidaController {

	
	private final PartidaService partidaService;

    @Autowired
	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}

	@GetMapping(value = { "/partidas" })
	public String showPartidaList(Map<String, Object> model) {
		List<Partida> partidas = new ArrayList<>();
        partidas = (List<Partida>) partidaService.findPartidas();
		model.put("partidas", partidas);
		return "partidas/partidaList";
	}
}
