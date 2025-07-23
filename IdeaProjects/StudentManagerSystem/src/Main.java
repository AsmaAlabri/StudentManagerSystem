import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: updateGrade(); break;
                case 4: deleteStudent(); break;
                case 5: searchById(); break;
                case 6: searchByName(); break;
                case 7: calculateAverage(); break;
                case 8: showHighestGrade(); break;
                case 9: showLowestGrade(); break;
                case 10: countTotalStudents(); break;
                case 11: sortByGrade(); break;
                case 12: sortByName(); break;
                case 13: clearAll(); break;
                case 14: exit = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\n=== Student Manager ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student Grade");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Search Student by Name");
        System.out.println("7. Calculate Average Grade");
        System.out.println("8. Show Highest Grade");
        System.out.println("9. Show Lowest Grade");
        System.out.println("10. Count Total Students");
        System.out.println("11. Sort Students by Grade");
        System.out.println("12. Sort Students by Name");
        System.out.println("13. Clear All Students");
        System.out.println("14. Exit");
        System.out.print("Choose: ");
    }

    public static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        students.add(new Student(name, id, grade));
        System.out.println(" Student added.");
    }

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) s.displayInfo();
        }
    }

    public static void updateGrade() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new grade: ");
                double newGrade = scanner.nextDouble();
                s.setGrade(newGrade);
                System.out.println(" Grade updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void deleteStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                System.out.println(" Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void searchById() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        for (Student s : students) {
            if (s.getId() == id) {
                s.displayInfo();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void searchByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("Student not found.");
    }

    public static void calculateAverage() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        double total = 0;
        for (Student s : students) total += s.getGrade();
        System.out.println(" Average grade: " + (total / students.size()));
    }

    public static void showHighestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        Student top = Collections.max(students, Comparator.comparingDouble(Student::getGrade));
        System.out.print(" Highest grade: ");
        top.displayInfo();
    }

    public static void showLowestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        Student low = Collections.min(students, Comparator.comparingDouble(Student::getGrade));
        System.out.print(" Lowest grade: ");
        low.displayInfo();
    }

    public static void countTotalStudents() {
        System.out.println(" Total students: " + students.size());
    }

    public static void sortByGrade() {
        students.sort(Comparator.comparingDouble(Student::getGrade));
        System.out.println(" Students sorted by grade:");
        viewAllStudents();
    }

    public static void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println(" Students sorted by name:");
        viewAllStudents();
    }

    public static void clearAll() {
        students.clear();
        System.out.println(" All students cleared.");
    }
}
