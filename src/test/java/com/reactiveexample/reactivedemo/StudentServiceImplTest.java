package com.reactiveexample.reactivedemo;

import com.reactiveexample.reactivedemo.service.StudentServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import(StudentServiceImpl.class)
public class StudentServiceImplTest {

  @Autowired
  private StudentServiceImpl service;

  @Test
  public void createStudentTest() {

  }
  @Test
  public void updateStudentTest() {
  }
  @Test
  public void getByIdStudentTest() {

  }
  @Test
  public void getAllStudentsTest() {

  }
  @Test
  public void deleteStudentTest() {

  }
}
