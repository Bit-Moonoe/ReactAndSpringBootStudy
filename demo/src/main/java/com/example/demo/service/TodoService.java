package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		repository.save(entity);
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
		
	}
	
	
	public List<TodoEntity> create(final TodoEntity entity){
		validation(entity);
		repository.save(entity);
		log.info("Entity id: {} is saved", entity.getId());
		return repository.findByUserId(entity.getUserId());
		
		
	}
	
	private void validation(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
		throw new RuntimeException("Entity cannot be null.");
		}
		
		if(entity.getUserId() ==null) {
			log.warn("Unknown user.");
		throw new RuntimeException("Unknown user.");
		}
	}
	
	public List<TodoEntity> retrieve(final String userId){
//		log.warn("userId: ", userId.toString());
		System.out.println(userId);
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity){
		validation(entity);
		
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(todo->{
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			repository.save(todo);
		  }
		);
		return retrieve(entity.getUserId());
		
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
		validation(entity);
		
		try {
			repository.delete(entity);
		}catch(Exception e) {
			log.error("¿¡·¯ ³µ½´", entity.getId(), e);
			throw new RuntimeException("¿¡·¯³´¾î¿µ"+ entity.getId());
		}
		
		return retrieve(entity.getUserId());
	}
}
