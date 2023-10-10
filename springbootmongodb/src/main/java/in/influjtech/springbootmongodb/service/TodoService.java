package in.influjtech.springbootmongodb.service;


import java.util.List;

import in.influjtech.springbootmongodb.exception.TodoCollectionException;
import in.influjtech.springbootmongodb.model.TodoDTO;
import jakarta.validation.ConstraintViolationException;

public interface TodoService {

	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException;



} 
