package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Service {

    private static Map<String, Student> studentList = new HashMap<>();
    private static Map<String, Teacher> teacherList = new HashMap<>();

    private static Map<String,List<String>> relations = new HashMap<>();


    static {
        studentList.put("Gaurav",new Student("Gaurav" ,19 , 30));
        studentList.put("Sonali",new Student("Sonali" ,20 , 70));
        studentList.put("Mummy",new Student("Mummy" ,21 , 300));
    }
    static {
        teacherList.put("chandra",new Teacher("chandra" ,19 ,30));
        teacherList.put("sunil",new Teacher("Sunil Kumar" ,20 , 37));
        teacherList.put("physics",new Teacher("physics  Wallah" ,21 , 18));
    }
    static {
        List<String> chandras = new ArrayList<>();
        chandras.add("Gaurav");
        chandras.add("Sonali");

        relations.put("chandra",chandras);
        teacherList.put("sunil",new Teacher("Sunil Kumar" ,20 , 37));
        teacherList.put("physics",new Teacher("physics  Wallah" ,21 , 18));
    }


    public void addStudent(Student student) {
        studentList.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {

        teacherList.put(teacher.getName(),teacher);
    }

    public List<String> getAllStudents() {
        List<String> ans = new ArrayList<>();

      for(var key : studentList.keySet()){
          ans.add(key);
      }
        return ans;
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(relations.containsKey(teacher)){
            List<String> students =relations.get(teacher);
            students.add(student);
        }else{
            List<String> students = new ArrayList<>();
            students.add(student);
            relations.put(teacher,students);
        }
    }


    public Student getStudentsByName(String name) {
        return studentList.getOrDefault(name,null);
    }

    public void deleteTeacherByName(String teacher) {
        System.out.println(relations);
        if(relations.containsKey(teacher)){
            List<String> students = relations.get(teacher);
            System.out.println(students);
            for (var name : students) {
                students.remove(students);
            }
            relations.remove(teacher);
        }
        if(teacherList.containsKey(teacher)){
            teacherList.remove(teacher);
        }
        System.out.println(relations);


    }

    public void deleteAllTeachers(){
        for (var key : relations.keySet()) {
            List<String> students = relations.get(key);
            for (var s : students) {
                students.remove(s);
            }
        }
    }

    public List<String> getStudentsByTeacherName(String teacher) {

        List<String> list = relations.getOrDefault(teacher,new ArrayList<>());
        return list;
    }
}
