package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

public class CourseManagementSystem {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    // List to store courses
    private List<Course> courses;
    // List to store students
    private List<Student> students;

    public CourseManagementSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        initializeCourses();
        initializeStudents();
    }

    // Course class
    static class Course {
        private String code;
        private String title;
        private String description;
        private int capacity;
        private int enrolled;
        private String schedule;

        public Course(String code, String title, String description, int capacity, String schedule) {
            this.code = code;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.enrolled = 0; // Initially no students enrolled
            this.schedule = schedule;
        }

        public String getCode() {
            return code;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getEnrolled() {
            return enrolled;
        }

        public String getSchedule() {
            return schedule;
        }

        public boolean registerStudent() {
            if (enrolled < capacity) {
                enrolled++;
                return true;
            }
            return false;
        }

        public boolean dropStudent() {
            if (enrolled > 0) {
                enrolled--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("%s - %s\n%s\nCapacity: %d, Enrolled: %d\nSchedule: %s",
                    code, title, description, capacity, enrolled, schedule);
        }
    }

    // Student class
    static class Student {
        private String id;
        private String name;
        private Set<Course> registeredCourses;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
            this.registeredCourses = new HashSet<>();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Set<Course> getRegisteredCourses() {
            return registeredCourses;
        }

        public boolean registerCourse(Course course) {
            if (course.registerStudent()) {
                return registeredCourses.add(course);
            }
            return false;
        }

        public boolean dropCourse(Course course) {
            if (registeredCourses.remove(course)) {
                return course.dropStudent();
            }
            return false;
        }
    }

    // Initialize courses
    private void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 30, "Mon/Wed/Fri 9:00AM - 10:00AM"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to differential and integral calculus.", 25, "Tue/Thu 11:00AM - 12:30PM"));
        courses.add(new Course("PHYS101", "Physics I", "Basic principles of physics.", 20, "Mon/Wed 2:00PM - 3:30PM"));
    }

    // Initialize students
    private void initializeStudents() {
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
    }

    // Display available courses
    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println();
        }
    }

    // Register a student for a course
    public void registerStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.registerCourse(course)) {
            System.out.println("Registered successfully.");
        } else {
            System.out.println("Course is full or already registered.");
        }
    }

    // Drop a course for a student
    public void dropStudentCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.dropCourse(course)) {
            System.out.println("Dropped successfully.");
        } else {
            System.out.println("Course not registered or already dropped.");
        }
    }

    // Find a student by ID
    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Find a course by code
    private Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    // Start the course management system
    public void start() {
        int choice;
        do {
            System.out.println("Course Management System");
            System.out.println("1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    dropStudentCourse();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    public static void main(String[] args) {
        CourseManagementSystem system = new CourseManagementSystem();
        system.start();
    }
}