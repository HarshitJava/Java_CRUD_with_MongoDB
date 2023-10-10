package in.influjtech.springbootmongodb.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.influjtech.springbootmongodb.model.TodoDTO;
import in.influjtech.springbootmongodb.repository.TodoRepository;

@CrossOrigin
@RestController
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
	List<TodoDTO> todos = todoRepo.findAll();
	if (todos.size() > 0) {
		return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
	}else {
		return new ResponseEntity<>("No Records available", HttpStatus.NOT_FOUND);
	}
  }
	
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id")String id) {
		Optional<TodoDTO> todoOptional = todoRepo.findById(id);
		if (todoOptional.isPresent()) {
			return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Record not found with id "+id, HttpStatus.NOT_FOUND);
		}
	}
	
	      
	/*@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateTodoById(@PathVariable("id") String id, @RequestBody TodoDTO updatedTodo) {
	    try {
	        // Check if the todo with the given ID exists
	        if (!todoRepo.existsById(id)) {
	            return new ResponseEntity<>("Todo not found with id " + id, HttpStatus.NOT_FOUND);
	        }

	        // Set the ID of the updatedTodo to match the path variable
	        updatedTodo.setId(id);

	        // Save the updatedTodo to the database
	        todoRepo.save(updatedTodo);

	        return new ResponseEntity<>("Successfully updated todo with id " + id, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	} */
	
	@PatchMapping("/todos/{id}")
	public ResponseEntity<?> partialUpdateTodoById(@PathVariable("id") String id, @RequestBody Map<String, Object> updates) {
	    try {
	        Optional<TodoDTO> todoOptional = todoRepo.findById(id);

	        if (!todoOptional.isPresent()) {
	            return new ResponseEntity<>("Todo not found with id " + id, HttpStatus.NOT_FOUND);
	        }

	        TodoDTO existingTodo = todoOptional.get();

	        for (Map.Entry<String, Object> entry : updates.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();

	            // Handle specific fields you want to update
	            if ("title".equals(key)) {
	                existingTodo.setTitle((String) value);
	            } else if ("description".equals(key)) {
	                existingTodo.setDescription((String) value);
	            } else if ("yourName".equals(key)) {
	            	existingTodo.setYourName((String) value);
	            }  else if ("email".equals(key)) {
	            	existingTodo.setEmail((String) value);
	            }  else if ("mobileNumber".equals(key)) {
	            	existingTodo.setMobileNumber((String) value);
	            }  else if ("password".equals(key)) {
	            	existingTodo.setPassword((String) value);
	            }   else if ("repeatPassword".equals(key)) {
	            	existingTodo.setRepeatPassword((String) value);
	            } 
	            // Add more fields as needed

	            // You can add additional checks or validation logic here if required.
	        }

	        // Save the updatedTodo back to the database
	        todoRepo.save(existingTodo);

	        return new ResponseEntity<>("Successfully updated todo with id " + id, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}




	
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			todoRepo.deleteById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/todos")
    public ResponseEntity<?> deleteAll() {
        try {
            todoRepo.deleteAll(); // Use the appropriate method to delete all records
            return new ResponseEntity<>("Successfully deleted all todos", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
