package com.reactiveexample.reactivedemo.controller;

import com.reactiveexample.reactivedemo.model.Student;
import com.reactiveexample.reactivedemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Completable;
import rx.Observable;
import rx.Single;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {
  @Autowired
  private StudentService service;

  @PostMapping
  public Single<ResponseEntity<Student>> createStudent(@Valid @RequestParam String name) {
    return service.save(name)
            .map(saved -> ResponseEntity.ok().body(saved));
  }

  @GetMapping("/{id}")
  public Single<ResponseEntity<Student>> getById(@PathVariable Long id) {
    return service.getById(id)
            .map(student -> ResponseEntity.ok().body(student));
  }

  @GetMapping
  public Observable<ResponseEntity<Student>> getAll() {
    return service.getAllStudents()
            .map(student -> ResponseEntity.ok().body(student));
  }

  @PutMapping("/{id}")
  public Completable updateStudent(@PathVariable Long id, String name) {
    return service.update(id, name);
  }

  @DeleteMapping("/{id}")
  public Completable deleteStudent(@PathVariable Long id) {
    return service.delete(id);
  }
}
