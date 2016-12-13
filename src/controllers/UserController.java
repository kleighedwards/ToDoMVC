package controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.UserDAO;
import entities.ToDo;
import entities.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	// Marco Polo To Test Connection Successful
	@RequestMapping(path = "/marco", method = RequestMethod.GET)
	public String ping() {
		return "polo";
	}

	// Returns All Users
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public List<User> index() {
		return userDAO.index();
	}

	// Returns A Single User With The Provided ID
	@RequestMapping(path = "user/{id}", method = RequestMethod.GET)
	public User show(@PathVariable int id) {
		return userDAO.show(id);
	}

	// Returns ToDo's For A Single User
	@RequestMapping(path = "user/{id}/todos", method = RequestMethod.GET)
	public Set<ToDo> showTodos(@PathVariable int id) {
		return userDAO.show(id).getTodos();
	}

	// Create A ToDo For A User
	@RequestMapping(path = "user/{id}/todos", method = RequestMethod.POST)
	public void createTodo(@PathVariable int id, @RequestBody String jsonToDo, HttpServletResponse response) {
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
			userDAO.createToDo(id, newToDo);
		}
	}

	// Delete ToDo From A User
	@RequestMapping(path = "user/{id}/todos/{t_id}", method = RequestMethod.DELETE)
	public void deleteToDo(@PathVariable int id, @PathVariable int t_id) {

		try {
			userDAO.destroyToDo(id, t_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update A ToDo
	@RequestMapping(path = "user/{id}/todos/{t_id}", method = RequestMethod.PUT)
	public void updateToDo(@PathVariable int id, @PathVariable int t_id, @RequestBody String jsonToDo) {
		ObjectMapper mapper = new ObjectMapper();
		ToDo editToDo = null;

		try {
			editToDo = mapper.readValue(jsonToDo, ToDo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		userDAO.updateToDo(id, t_id, editToDo);
	}

	// Adds A New User
	@RequestMapping(path = "user", method = RequestMethod.POST)
	public void create(@RequestBody String jsonUser, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		User newUser = null;

		try {
			newUser = mapper.readValue(jsonUser, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newUser == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			userDAO.create(newUser);
		}
	}

	// Delete A User
	@RequestMapping(path = "user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {

		try {
			userDAO.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete A ToDo From User
	@RequestMapping(path = "user/{id}/todos/{t_id}", method = RequestMethod.GET)
	public void deleteTodo(@PathVariable int id, @PathVariable int t_id) {

	}

	// Edit A User
	@RequestMapping(path = "user/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody String jsonUser) {
		ObjectMapper mapper = new ObjectMapper();
		User editUser = null;

		try {
			editUser = mapper.readValue(jsonUser, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		userDAO.update(id, editUser);
	}
}
