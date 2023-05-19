package promi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class online_payment_simulation {

    static class Course{

        String courseName;

        double courseFee,transtionFee,Salary,libraryFee,reTakeFee;

        boolean retake;

        public Course(String courseName, double courseFee, double transtionFee, double Salary, double libraryFee, double reTakeFee, boolean retake) {
            this.courseName = courseName;
            this.courseFee = courseFee;
            this.transtionFee = transtionFee;
            this.Salary = Salary;
            this.libraryFee = libraryFee;
            this.reTakeFee = reTakeFee;
            this.retake = retake;
        }

        public Course(String courseName, double courseFee) {
            this.courseName = courseName;
            this.courseFee = courseFee;
        }

        public Course() {
        }

        public double getTranstionFee() {
            return transtionFee;
        }

        public void setTranstionFee(double transtionFee) {
            this.transtionFee = transtionFee;
        }

        public double getSalary() {
            return Salary;
        }

        public void setSalary(double Salary) {
            this.Salary = Salary;
        }

        public double getLibraryFee() {
            return libraryFee;
        }

        public void setLibraryFee(double libraryFee) {
            this.libraryFee = libraryFee;
        }

        public double getReTakeFee() {
            return reTakeFee;
        }

        public void setReTakeFee(double reTakeFee) {
            this.reTakeFee = reTakeFee;
        }

        public boolean isRetake() {
            return retake;
        }

        public void setRetake(boolean retake) {
            this.retake = retake;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public double getCourseFee() {
            return courseFee;
        }

        public void setCourseFee(double courseFee) {
            this.courseFee = courseFee;
        }

        @Override
        public String toString() {
            return  "courseName=" + courseName + "\ncourseFee=" + courseFee + "\ntranstionFee=" + transtionFee + "\nSalary=" + Salary + "\nlibraryFee=" + libraryFee + "\nreTakeFee=" + reTakeFee + "\nretake=" + retake ;
        }

    }

    static class Account{

        String firstName,lastName;

        int accountId;

        double price;

        public Account(String firstName, String lastName, int accountId, double price) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountId = accountId;
            this.price = price;
        }

        public Account() {

        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return  "firstName=" + firstName + "\nlastName=" + lastName + "\naccountId=" + accountId + "\nprice=" + price+"\nlibrary fee: "+this ;
        }

    }

    static class Admin extends Account {

        String adminCode;

        List<Student> clients;

        public Admin() {


        }

        public Admin(String adminCode, List<Student> clients, String firstName, String lastName, int accountId, double price) throws IOException {
            super(firstName, lastName, accountId, price);
            this.adminCode = adminCode;
            this.clients = clients;

            addAdminCodeToFile( adminCode );

        }

        public Admin(String adminCode, List<Student> clients) {
            this.adminCode = adminCode;
            this.clients = clients;
        }

        public void addClient(Student clientAccount) throws FileNotFoundException, IOException{

            clients.add(clientAccount);

            File file=new File("Client_Approval.txt");

            FileWriter out=new FileWriter(file,true);

            try (BufferedWriter bw = new BufferedWriter(out); PrintWriter pw = new PrintWriter(bw)) {

                pw.println( clients.get(clients.size()-1).toString() );

                pw.flush();
                pw.close();
                bw.close();
                out.close();

            }

            System.out.println("The data is added successfully");

        }

        public String getAdminCode() {
            return adminCode;
        }

        public void setAdminCode(String adminCode) {
            this.adminCode = adminCode;
        }

        public List<Student> getClients() {
            return clients;
        }

        public void setClients(List<Student> clients) {
            this.clients = clients;
        }

        @Override
        public String getFirstName() {
            return firstName;
        }

        @Override
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public String getLastName() {
            return lastName;
        }

        @Override
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public int getAccountId() {
            return accountId;
        }

        @Override
        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {

            StringBuilder sb=new StringBuilder();

            clients.forEach((i) -> {
                sb.append(i.toString()).append("\n");
            });

            return  super.toString()+"\nadminCode=" + adminCode + ", clients=" + sb ;
        }

        private void addAdminCodeToFile(String adminCode) throws IOException {
            //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            File file=new File("Basic_Admin_Surity.txt");

            FileWriter out=new FileWriter(file,true);

            try (BufferedWriter bw = new BufferedWriter(out); PrintWriter pw = new PrintWriter(bw)) {

                pw.println(adminCode+"\n");

                // bw.append(adminCode);

                pw.close();
                bw.close();
                out.close();
            }

        }

    }

    static class Student extends Account {

        String adminCode;

        public Student() {



        }

        List<Course> courses=new ArrayList<>();

        public Student(String adminCode, String firstName, String lastName, int accountId, double price) {
            super(firstName, lastName, accountId, price);
            this.adminCode = adminCode;
        }

        public Student(String adminCode) {
            this.adminCode = adminCode;
        }

        public boolean isValid() throws FileNotFoundException{

            File file=new File("Basic_Admin_Surity.txt");

            Scanner in=new Scanner(file);

            while( in.hasNextLine() ){

                String s=in.nextLine().trim();

                if( this.getAdminCode().equalsIgnoreCase(s) ){

                    return true;

                }

            }

            return false;

        }

        public void addCourse(Course course) throws IOException{

            courses.add(course);

            File file=new File("Courses.txt");

            FileWriter fw=new FileWriter(file,true);

            try (BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {

                pw.println(courses.get(courses.size()-1).toString()+"\n");

                pw.flush();

                pw.close();
                bw.close();
                fw.close();
            }

        }

        public void seeCourses() throws FileNotFoundException{

            courses.forEach((i) -> {
                System.out.println(i.toString());
            });

            File file=new File("Courses.txt");

            Scanner in=new Scanner(file);

            while(in.hasNextLine()){

                System.out.println(in.nextLine());

            }

        }

        public List<Course> getCourses() {
            return courses;
        }

        public void setCourses(List<Course> courses) {
            this.courses = courses;
        }

        public void deposit(double price){

            this.setPrice(this.getPrice()+price);

        }

        public void withDraw(double withdraw){

            if( this.getPrice()-withdraw<0 ){

                throw new ArrayIndexOutOfBoundsException();

            }

            this.setPrice(this.getPrice()-withdraw);

        }

        public String getAdminCode() {
            return adminCode;
        }

        public void setAdminCode(String adminCode) {
            this.adminCode = adminCode;
        }

        @Override
        public String getFirstName() {
            return firstName;
        }

        @Override
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public String getLastName() {
            return lastName;
        }

        @Override
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public int getAccountId() {
            return accountId;
        }

        @Override
        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString(){

            StringBuilder sb=new StringBuilder();

            courses.forEach((i) -> {
                sb.append(i.toString()).append("\n");
            });

            StringBuilder another=new StringBuilder();

            another.append(this.getFirstName()).append("\n");
            another.append(this.getLastName()).append("\n");
            another.append(this.getAccountId()).append("\n");
            another.append(this.getPrice());

            return another+"\n"+this.getAdminCode()+"\ncourses: "+sb.toString()+"\n\n";

        }

    }

    static Scanner in=new Scanner(System.in);

    static boolean logIn(String firstName,String lastName) throws FileNotFoundException{

        File file=new File("Client_Approval.txt");

        Scanner scanner=new Scanner(file);

        while(scanner.hasNextLine()){

            String first=scanner.nextLine();
            String last=scanner.nextLine();
            String third=scanner.nextLine();
            String fourth=scanner.nextLine();
            String fifth=scanner.nextLine();
            String sixth=scanner.nextLine();
            String seventh=scanner.nextLine();
            String eightth=scanner.nextLine();

            if( first.equalsIgnoreCase(firstName) && last.equalsIgnoreCase(lastName) ){

                return true;

            }

            /*String h="";

            while(!"\n".equals(h)){

                h=in.nextLine();

            }*/

        }

        return false;

    }

    static int choice(){

        System.out.println("1: add Admin");

        System.out.println("2: add client");

        System.out.println("3: deposit");

        System.out.println("4: withdraw");

        System.out.println("5: log out");

        System.out.println("6: see All data");

        System.out.println("7: add courses");

        System.out.println("8: see Courses");

        System.out.println("9: update courses");

        System.out.println("10: exit");

        int choice=in.nextInt();

        return choice;

    }

    public static void main(String[] args) throws IOException {

        List<Student> list=new ArrayList<>();

        Admin admin=new Admin();

        Student student = null;

        do{

            int choice=choice();

            switch(choice){

                case 1:

                    admin=new Admin(in.next(),list,in.next(),in.next(),in.nextInt(),in.nextDouble());

                    break;

                case 2:

                    student=new Student(in.next(),in.next(),in.next(),in.nextInt(),in.nextDouble());

                    if( student.isValid() ){

                        admin.addClient(student);

                        list.add( student );

                        admin.setClients(list);

                        System.out.println("data added");

                    }

                    break;

                case 3:

                    try{

                        int index=in.nextInt();

                        if(logIn(in.next(),in.next())){

                            list.get(index).deposit(in.nextDouble());

                            admin.addClient(student);

                            admin.setClients(list);

                        }

                    }catch(ArrayIndexOutOfBoundsException e){

                        System.out.println("serial in correct");

                    }

                    break;

                case 4:

                    try{

                        int index=in.nextInt();

                        if( logIn(in.next(),in.next()) ){

                            //try{

                            list.get(index).withDraw(in.nextDouble());

                            admin.setClients(list);

                        }

                    }catch(ArrayIndexOutOfBoundsException e){

                        System.out.println("serial in correct");

                    }

                    break;

                case 5:

                    int index=in.nextInt();
                    if( logIn(in.next(),in.next()) ){

                        list.remove(index);

                    }

                    break;

                case 6:

                    for(int i=0;i<list.size();i++){

                        System.out.println(i+": "+list.get(i).toString());

                    }

                    break;

                case 7:

                    index=in.nextInt();

                    try{

                        if( logIn(in.next(),in.next()) ){

                            list.get(index).addCourse( new Course( in.next(),in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextBoolean() ) );

                        }

                    }catch(Exception e){

                        System.out.println("out of range");

                    }

                    break;

                case 8:

                    try{

                        index=in.nextInt();

                        if( logIn(in.next(),in.next()) ){

                            list.get(index).seeCourses();

                        }

                    }catch(Exception e){

                        System.out.println("limit over");

                    }

                    break;

                case 9:

                    try{

                        index=in.nextInt();

                        if( logIn(in.next(),in.next()) ){

                            list.get(index).courses.set(index,new Course( in.next(),in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextBoolean() ) );

                            list.get(index).addCourse( list.get(index).courses.get(index) );

                        }

                    }catch(ArrayIndexOutOfBoundsException e){

                        System.out.println("limit over");

                    }

                    //      }

                    break;

                case 10:

                    return;

//                    break;

            }

        }while(true);


    }

}
//123 Promi 123 123 456
//123 123 123 123 123
//1 2 3 4 5 6 false