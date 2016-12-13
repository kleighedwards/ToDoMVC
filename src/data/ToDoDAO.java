package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.ToDo;

@Transactional
public class ToDoDAO {

	@PersistenceContext
	private EntityManager em;

	// Get All ToDos
	public List<ToDo> index() {
		String query = "Select t from ToDo t";
		List<ToDo> todos = em.createQuery(query, ToDo.class).getResultList();

		return todos;
	}

	// Get ToDo By ID
	public ToDo show(int id) {
		return em.find(ToDo.class, id);
	}

	// Add New ToDo
	public void create(ToDo todo) {
		em.persist(todo);
		em.flush();
	}

	// Delete ToDo
	public void destroy(int id) {
		ToDo deleteToDo = em.find(ToDo.class, id);

		em.remove(deleteToDo);
	}

	// Edit ToDo
	public void update(int id, ToDo todo) {
		ToDo editToDo = em.find(ToDo.class, id);
		
		editToDo.setTask(todo.getTask());
		editToDo.setCompleted(todo.getCompleted());
		
		em.persist(editToDo);
	}
}
