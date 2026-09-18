// -----Universal Courses System-----
import java.util.*;
public class Courses {
// global variable declartion

    public static Scanner input = new Scanner(System.in);
    public static final String CORRECT_NAME = "employee";
    public static final String  CORRECT_PASSWRD = "1234";
    public static String[] courseNames=new String[1000];
    public static double[] courseFees=new double[1000];
    public static int[] maxStudents=new int[1000];
    public static int[] registeredStudents=new int[1000];
    public static String[] studentIDs=new String[1000];
    public static boolean[][] studentCourses=new boolean[1000][1000];
    public static int courseCount=0;
    public static int studentCount=0;
    public static int courseIndex;
    public static int studentIndex;

public static void main(String[] args){

// login loop - keeps asking until username&&password are correct
       boolean check =true;
        while(check){
                System.out.println("Enter The Username :");
                String userName=input.nextLine();
                System.out.println("Enter The Password :");
                String Password=input.nextLine();
                check=checkUserPassword(userName,Password);
                if(check){
                    System.out.println("In Correct Password Try Again!.");
                }
                }

// main loop, runs until user chooses to exit (option 5)
        while(true){
                System.out.println("Enter your Choice :");
                int option=input.nextInt();
                input.nextLine();
// choose The option
                switch (option) {

                case 1:
// ---- register a student in a course ----
                      studentCount++;
//  Enter The ID and  check it if it repeat  
                      System.out.println("Enter Your ID :");
                      String id=input.nextLine();
//   return The index of student id
                      studentIndex=findStudent(id);
//   display the menu of courses
                      displayCourses();
//   Enter TheN Number Of Course
                      System.out.println("Enter Your Number Of Course  :");
                     int courseChoice=input.nextInt();
// make sure the entered course number is valid
                     if(courseChoice<=0||courseChoice>courseCount){
                       System.out.println("Enter The Number Of Course AS You Shown !");
                       break;
                      }
// Take The index Of Course
                 courseIndex=(courseChoice - 1);
// check if the course is already full
                    if((maxStudents[courseIndex]-registeredStudents[courseIndex])==0){
                        System.out.println("Registration denied.\nThis course is full");
                        break;
                    }
// check if student is already registered in this course
                    if(studentCourses[studentIndex][courseIndex]){
                        System.out.println("you already registered in the selected course.");
                        break;
                    }
 // check the 5-course limit per student
                    int count=countStudentCourses();
                    if(count>=5){
                        System.out.println("Registration denied.\n A student cannot register for more than 5 courses.");
                        break;
                    }
// everything is fine -> register the student
                    registeredStudents[courseIndex]=registeredStudents[courseIndex] + 1;
                    studentCourses[studentIndex][courseIndex]=true;
// print confirmation message
                    registration();
                break;

                case 2:
// ---- add a new course ----
                    addCourse();
                break;

                case 3:
// ---- delete an existing course ----

                    System.out.println("Enter The Number Of Course to delet it:");
//  Enter The Number Of Course That u wana to delet
                    int delet=input.nextInt();
// make sure the entered course number is valid
                    if(delet<=0||delet>courseCount){
                        System.out.println("Enter The Number Of Course to delet it As it Shown:");
                        break;
                    }
// Call The Methode to delet 
                    deletCourse(delet);
                break;

                case 4:
// ---- display all courses ----
                    displayCourses();
                break;

                case 5:
// ---- exit the program ----
                    System.out.println("Thank you for using the Universal Courses.");
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Please Enter The Number Of  Option Between 1 and 5 Only!");
                 
            }
        }
}
// checks if entered username&&password match the correct ones
    public static boolean checkUserPassword(String user,String password){

        if((user.equals(CORRECT_NAME))&&(password.equals(CORRECT_PASSWRD))) {
            System.out.println("Login successful. \n\n");

 // IF THE CONDTION CORRECT Display The menu
            displayMenu();
// returns false when login is correct (TO STOP THE LOOP)
            return false;
        }else
// true = wrong credentials, keep asking
            return true;
    }
    public static void displayMenu(){
            System.out.println("=====================================");
            System.out.println("UNIVERSITY MENU");
            System.out.println("=====================================");
            System.out.println("1. Register Course");
            System.out.println("2. Add New Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Display Courses");
            System.out.println("5. Exit Program");
            System.out.println("=====================================");

    }


 // looks for a student by ID in the studentIDs array
 // if found, returns their index; if not found, adds them as a new student
    public static int findStudent(String userId){
        for(int i=0;i<studentCount;i++){
            if(userId.equals(studentIDs[i])){
                studentCount--;
                return i;
            }
        }
// student not found -> register as a new one
            studentIDs[studentCount-1]=userId;
            return (studentCount-1);
    }

 // prints out all the currently available courses with their details
    public static void displayCourses(){
        System.out.println("----------- COURSE LIST -----------");
        for(int i=0;i<courseCount;i++){
             System.out.println((i+1)+" . "+courseNames[i]+"|"+courseFees[i]+"|Capacity:"+maxStudents[i]+"|Registered:"+registeredStudents[i]+"|Available:"+(maxStudents[i]-registeredStudents[i]));
        }
    }

 // counts how many courses a specific student is currently registered in
    public static int  countStudentCourses(){
        int count=0;
        for(int i=0;i<courseCount;i++){
            if(studentCourses[studentIndex][i]==true)
                count++;

        }
        return count;
    }
 // prints a confirmation message after a successful registration
    public static void registration(){
        System.out.println("Registration successful!");
        System.out.println("Student ID:"+studentIDs[studentIndex]);
        System.out.println("Course:"+courseNames[courseIndex]);
        System.out.println("Registered Courses:"+registeredStudents[courseIndex]);
        System.out.println("Remaining Seats:"+(maxStudents[courseIndex] - registeredStudents[courseIndex]) );
    }
// asks the user for course details and adds a new course to the arrays
    public static void addCourse(){
        courseCount++;
        System.out.println("Enter The Name Of Your Course :");
            courseNames[courseCount - 1]=input.nextLine();
        System.out.println("Enter The Price Of Your Course :");
            courseFees[courseCount - 1]=input.nextDouble();
        System.out.println("Enter The Max Student Of Your Course :");
            maxStudents[courseCount - 1]=input.nextInt();
    }
// removes a course by shifting all the following courses one position back
    public static void deletCourse(int delet){
        for(int i=(delet - 1);i<courseCount-1;i++){
             courseNames[i]=courseNames[i+1];
            courseFees[i]=courseFees[i+1];
            maxStudents[i]=maxStudents[i+1];
            registeredStudents[i]=registeredStudents[i+1];
// also shift the registration data for every student for this course
          for(int s=0; s<studentCount; s++){
            studentCourses[s][i] = studentCourses[s][i+1];
          }
        }
// one less course now
        courseCount--;
    }
}