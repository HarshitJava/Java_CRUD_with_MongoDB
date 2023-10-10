package in.influjtech.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.influjtech.springbootmongodb.model.TodoDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

	/*@Query("{'TODO': ?0}")
	Optional<TodoDTO> findByTodo(String TodoDTO);
	@Autowired
	void updateTodo(String id, TodoDTO TodoDTO); */
	
	
}
