class Employee
{
    String name;
    int salary;
    static int count;
    
    // class constructor
    Employee(String name, int salary)
    {
      this.name = name;
      this.salary = salary;
    count ++;
    }
    
    // Parent class method
    void displayEmployee()
    {
      System.out.println("Employee Name: " + name );
      System.out.println("Salary: " + salary);
    }

    static void getcount()
    {
      System.out.println("count: " +count);
    }
}

class EmployeeDemo
{
  public static void main(String[] args)
  {
    System.out.println(".......................");
    Employee e = new Employee("Rahat", 30000);
    e.displayEmployee();
    e.getcount();
  }
  
}