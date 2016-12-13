package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ToDoDAO;
import entities.ToDo;

@RestController
public class ToDoController {

	@Autowired
	private ToDoDAO todoDAO;

	// Ping Pong To Test Connection Successful
	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}

	// Returns All ToDos
	@RequestMapping(path = "todo", method = RequestMethod.GET)
	public List<ToDo> index() {
		return todoDAO.index();
	}

	// Returns A Single ToDo With The Provided ID
	@RequestMapping(path = "todo/{id}", method = RequestMethod.GET)
	public ToDo show(@PathVariable int id) {
		return todoDAO.show(id);
	}

	// Adds A New ToDo
	@RequestMapping(path = "todo", method = RequestMethod.POST)
	public void create(@RequestBody String jsonToDo, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		ToDo newToDo = null;

		try {
			newToDo = mapper.readValue(jsonToDo, ToDo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newToDo == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			todoDAO.create(newToDo);
		}
	}

	// Delete A ToDo
	@RequestMapping(path = "todo/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {

		try {
			todoDAO.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Edit A ToDo
	@RequestMapping(path = "todo/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody String jsonToDo) {
		ObjectMapper mapper = new ObjectMapper();
		ToDo editToDo = null;

		try {
			editToDo = mapper.readValue(jsonToDo, ToDo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		todoDAO.update(id, editToDo);
	}

}
