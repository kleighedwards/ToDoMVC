package data;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import entities.ToDo;
import entities.User;

@Transactional
public class UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// Get All Users
	public List<User> index() {
		String query = "Select u from User u";
		List<User> users = em.createQuery(query, User.class).getResultList();

		return users;
	}

	// Get User By ID
	public User show(int id) {
		return em.find(User.class, id);
	}

	// Add New User
	public User create(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);

		em.persist(user);
		em.flush();

		return user;
	}

	// Authenticate User
	public User authenticateUser(User user) {
		User managedUser = null;

		List<User> users = index();

		for (User u : users) {
			if (u.getUsername().equals(user.getUsername())) {
				managedUser = em.find(User.class, u.getId());
			}
		}

		if (managedUser != null) {
			String rawPassword = user.getPassword();
			String encodedPassword = managedUser.getPassword();

			if (passwordEncoder.matches(rawPassword, encodedPassword)) {
				return managedUser;
			}
		}

		return managedUser;
	}

	// Delete User
	public void destroy(int id) {
		User deleteUser = em.find(User.class, id);

		em.remove(deleteUser);
	}

	// Edit User
	public void update(int id, User user) {
		User editUser = em.find(User.class, id);

		editUser.setEmail(user.getEmail());
		editUser.setPassword(user.getPassword());

		em.persist(editUser);
	}

	// Create A New ToDo For A User
	public void createToDo(int id, ToDo newToDo) {
		User user = em.find(User.class, id);

		newToDo.setUser(user);

		em.persist(newToDo);
		em.flush();
	}

	// Delete ToDo From User
	public void destroyToDo(int id, int tId) {
		User user = em.find(User.class, id);
		ToDo todo = em.find(ToDo.class, tId);

		Collection<ToDo> todos = user.getTodos();

		for (ToDo t : todos) {
			if (t == todo) {
				todos.remove(t);

				em.remove(todo);
			}
		}
	}

	public void updateToDo(int id, int tId, ToDo todo) {
		ToDo editToDo = em.find(ToDo.class, tId);		
		
		editToDo.setTask(todo.getTask());
		editToDo.setCompleted(todo.getCompleted());

		em.persist(editToDo);
		em.flush();
	}

}
