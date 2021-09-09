package com.disributors.API.controllers;

import java.util.Optional;

import com.disributors.API.Response.GeneralResult;
import com.disributors.API.Response.NotFoundResponse;
import com.disributors.API.Response.SuccessResponse;
import com.disributors.API.models.Student;
import com.disributors.API.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Student")
@Validated
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<GeneralResult<Iterable<Student>>> getAll(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer take
    ) {
        Pageable paging = PageRequest.of(page != null ? page - 1 :  0, take != null ? take : 10);
        Iterable<Student> students = this.studentRepository.findAll(paging);
        SuccessResponse<Iterable<Student>> resp = new SuccessResponse<Iterable<Student>>(students);
        return new ResponseEntity<GeneralResult<Iterable<Student>>>(resp.getResponse(""), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<GeneralResult<Optional<Student>>> get(@PathVariable Integer id) {
        Optional<Student> stu = this.studentRepository.findById(id);
        System.out.println(stu);
        if (stu.isEmpty()) {
            NotFoundResponse<Optional<Student>> resp = new NotFoundResponse<Optional<Student>>(null);
            return new ResponseEntity<GeneralResult<Optional<Student>>>(
                resp.getResponse("دانش آموز یافت نشد"),
                HttpStatus.NOT_FOUND
            );
        } else {
            SuccessResponse<Optional<Student>> resp = new SuccessResponse<Optional<Student>>(stu);
            return new ResponseEntity<GeneralResult<Optional<Student>>>(resp.getResponse(""), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<GeneralResult<Boolean>> delete(@PathVariable(required = true) Integer id ) {
        Optional<Student> stu = this.studentRepository.findById(id);
        if(stu.isEmpty()) {
            NotFoundResponse<Boolean> resp = new NotFoundResponse<Boolean>(false);
            return new ResponseEntity<GeneralResult<Boolean>>(
                resp.getResponse("دانش آموز یافت نشد"),
                HttpStatus.NOT_FOUND
            );
        } else {
            this.studentRepository.deleteById(id);
            SuccessResponse<Boolean> resp = new SuccessResponse<Boolean>(true);
            return new ResponseEntity<GeneralResult<Boolean>>(
                resp.getResponse("دانش آموز با موفقیت حذف شد"),
                HttpStatus.OK
            );
        }
    }


    @PostMapping()
    @ResponseBody
    public ResponseEntity<GeneralResult<Student>> create(@RequestBody Student _student) {
        Student student = new Student(_student.getFirstName(), _student.getLastName(), _student.getNationalCode());
        Student stu = this.studentRepository.save(student);
        SuccessResponse<Student> resp = new SuccessResponse<Student>(stu);
        return new ResponseEntity<GeneralResult<Student>>(
            resp.getResponse("دانش آموز با موفقیت ایجاد شد"),
            HttpStatus.CREATED
        );
    }
}
