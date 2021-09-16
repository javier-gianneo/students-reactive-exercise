package com.reactiveexample.reactivedemo.service;

import com.reactiveexample.reactivedemo.model.Student;
import rx.Completable;
import rx.Observable;
import rx.Single;

public interface StudentService {

  Single<Student> save(String name);

  Completable update(Long id, String name);

  Single<Student> getById(Long id);

  Observable<Student> getAllStudents();

  Completable delete(Long id);
}
