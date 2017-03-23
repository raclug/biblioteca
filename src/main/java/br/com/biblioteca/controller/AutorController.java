package br.com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.biblioteca.entity.AutorEntity;
import br.com.biblioteca.enums.NacionalidadeEnum;
import br.com.biblioteca.enums.NacionalidadeEnum.NacionalidadeSerializer;
import br.com.biblioteca.repository.AutorRepository;

@Controller
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@RequestMapping("/biblioteca/cadastroAutor")
	public String init() {
		
		return "autor/cadastroAutor";
	}
	
	
	@RequestMapping(value = "/biblioteca/incluirAutor", method = RequestMethod.POST)
	@ResponseBody
	public void incluirAutor(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "autor") String autorP) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AutorEntity autor = mapper.readValue(autorP, AutorEntity.class);
		
		autor.setFoto(file.getBytes());
		autorRepository.save(autor);
	}
	
	@RequestMapping(value = "/biblioteca/listarAutor", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String listarAutor() throws Exception {
		Gson gson = new Gson();
		
		return gson.toJson(autorRepository.findAll().iterator().next());
	}
	
	
	
	@RequestMapping(value = "/biblioteca/nacionalidades", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getNacionalidades() throws Exception {
		
		Gson gson = new GsonBuilder().registerTypeAdapter(NacionalidadeEnum.class, new NacionalidadeSerializer()).create();
		
		return gson.toJson(NacionalidadeEnum.values());
	}
	

}
