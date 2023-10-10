package in.influjtech.springbootmongodb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.influjtech.springbootmongodb.exception.TodoCollectionException;
import in.influjtech.springbootmongodb.model.TodoDTO;
import in.influjtech.springbootmongodb.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepo;
	
	@Override
	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException{
		Optional<TodoDTO> todoOptional = todoRepo.findById(todo.getMobileNumber());
		if (todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists(0));
		}else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
		}
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		// TODO Auto-generated method stub
		List<TodoDTO> todos = todoRepo.findAll();
		if (todos.size() > 0) {
			return todos;
		}else {
			return new ArrayList<TodoDTO>();
		}
	}

	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
		// TODO Auto-generated method stub
	Optional<TodoDTO> optionalTodo = todoRepo.findById(id);
	if (!optionalTodo.isPresent()) {
		throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
	} else {
		return optionalTodo.get();


	}
	}
	
}











