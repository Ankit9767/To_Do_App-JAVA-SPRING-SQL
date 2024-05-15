package com.ankit.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
		
		private static List<Todo> todos = new ArrayList<>() ;
		
			private static int TodosCount = 0 ;
		
		static {
			todos.add(new Todo(++TodosCount , "Ankit" , 
					"Learn Java" , LocalDate.now().plusYears(1) , false ));
			todos.add(new Todo(++TodosCount , "Ankit" , 
					"Learn Python" , LocalDate.now().plusYears(1) ,false ));
			todos.add(new Todo(++TodosCount , "Ankit" , 
					"Learn React" , LocalDate.now().plusYears(1) , false ));
		}
		
		public List<Todo> findByUsername(String username){
			Predicate<? super Todo> predicate =
					todo -> todo.getUsername().equalsIgnoreCase(username);
			return todos.stream().filter(predicate).toList() ;
		}
		public void addTodo(String username , String description , LocalDate targetDate , boolean done) {
				Todo todo =new Todo(++TodosCount , username , description , targetDate , done) ;
				todos.add(todo) ;
		}
		
		public void DeleteById(int id) {
			Predicate<? super Todo> predicate = todo -> todo.getId() == id ;
			todos.removeIf(predicate) ;
			
		}
		
		public Todo FindByID(int id) {
			Predicate<? super Todo> predicate = todo -> todo.getId() == id ;
			Todo todo = todos.stream().filter(predicate).findFirst().get() ;
			return todo ;
			
		}
		public void updateTodo(@Valid Todo todo) {
			DeleteById(todo.getId()) ;
			todos.add(todo) ;
			
		}
}
