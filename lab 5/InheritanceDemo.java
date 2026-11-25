class Employee
  {
    String name;
    int salary;
    
    // Parent class constructor
    Employee(String name, int salary)
    {
      this.name = name;
      this.salary = salary;
    }
    
    // Parent class method 
    void displayEmployee()
    {
      System.out.println("Employee Name: " + name);
      System.out.println("Salary: " + salary);
    }
  }

class Manager extends Employee
  {
    String post;
    // Child class constructor
    Manager(String name, int salary)
    {
      super(name, salary); // Callinng parent class constructor
      this.post = "Manager";
    }

    // Child class method
    void display()
    {
      super.displayEmployee(); // Calling prent class method
      System.out.println("Post: " + post);
    }
  }

class Accountant extends Employee
  {
    String post;
    Accountant(String name, int salary)
    {
      super(name, salary);  // Calling prent class constructor
      this.post = "Accountant";
    }

    void display()
    {
      super.displayEmployee();
      System.out.println("Post: " + post);
    }
  }

class Developer extends Employee
  {
    String post;
    Developer(String name, int salary)
    {
      super(name, salary); // Calling parent class constructor
      this.post = "Developer";
    }

    void display()
    {
      super.displayEmployee();  // Calling prent class method
      System.out.println("Post: " + post);
    }
  }


class InheritanceDemo
  {
    public static void main(String[] args)
    {

      Manager m = new Manager("Sarthak", 50000);
      
      Accountant a = new Accountant("Rahat", 40000);
      

      Developer d =new Developer("Umesh", 45000);

      System.out.println("Manager Details:");
      m.display();

      System.out.println("------------------------------------");

      System.out.println("Accountant Details:");
      a.display();

      System.out.println("-------------------------------------");

      System.out.println("Developer Details: ");
      d.display();
      
    }
  }