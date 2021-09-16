package com.reactiveexample.reactivedemo.service;

import com.reactiveexample.reactivedemo.model.Student;
import com.reactiveexample.reactivedemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import rx.Completable;
import rx.Observable;
import rx.Single;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentRepository repository;

  public Single<Student> save(String name) {
    Student student = new Student(name);
    return Single.create(singleSubscriber -> {
      Student savedStudent = repository.save(student);
      singleSubscriber.onSuccess(savedStudent);
    });
  }

  public Completable update(Long id, String name) {
    return Completable.create(singleSubscriber -> {
      Optional<Student> studentOp = repository.findById(id);
      if(studentOp.isPresent()) {
        Student student = studentOp.get();
        student.setName(name);
        repository.save(student);
        singleSubscriber.onCompleted();
      } else {
        singleSubscriber.onError(new EntityNotFoundException("Not found: " + id));
      }
    });
  }

  public Single<Student> getById(Long id) {
    return Single.create(singleSubscriber -> {
      Optional<Student> studentOp = repository.findById(id);
      if(studentOp.isPresent()) {
        Student student = studentOp.get();
        singleSubscriber.onSuccess(student);
      } else {
        singleSubscriber.onError(new EntityNotFoundException("Not found: " + id));
      }
    });
  }

  public Observable<Student> getAllStudents() {
    return Observable.from(repository.findAll());
  }

  public Completable delete(Long id) {
    return Completable.create(singleSubscriber -> {
      Optional<Student> studentOp = repository.findById(id);
      if(studentOp.isPresent()) {
        Student student = studentOp.get();
        repository.delete(student);
        singleSubscriber.onCompleted();
      } else {
        singleSubscriber.onError(new EntityNotFoundException("Not found: " + id));
      }
    });
  }
}
