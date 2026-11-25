
# JDBC Assignment 11 – Windows & Linux Setup Guide

This guide explains how to compile and run Java JDBC programs with MySQL on Windows, Ubuntu Linux, and GitHub Codespaces. It also includes common errors and their solutions.

## 1. Project Structure

```text
JDBC_Assignment11/
│
├── JDBCDemo.java
├── README.md
│
└── lib/
    └── mysql-connector-j-26.10.0.jar
```

## 2. Windows Setup (PowerShell)

### Step 1: Check Java Installation

```powershell
java -version
javac -version
```

### Step 2: Check MySQL Installation

```powershell
mysql --version
```

### Step 3: Start MySQL Server

Open PowerShell as Administrator:

```powershell
net start MySQL80
```

Alternatively, start MySQL from Windows Services.

### Step 4: Login to MySQL

```powershell
mysql -u root -p
```

### Step 5: Create Database

```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;
```

### Step 6: Create Students Table

```sql
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
```

Verify:

```sql
SHOW DATABASES;
USE studentdb;
SHOW TABLES;
DESC students;
```

### Step 7: Create JDBC User

```sql
CREATE USER IF NOT EXISTS 'jdbcuser'@'localhost'
IDENTIFIED BY 'jdbc123';

GRANT ALL PRIVILEGES ON studentdb.*
TO 'jdbcuser'@'localhost';

FLUSH PRIVILEGES;
EXIT;
```

### Step 8: Compile Java Program

```powershell
javac -cp ".;lib/*" JDBCDemo.java
```

### Step 9: Run Java Program

```powershell
java -cp ".;lib/*" JDBCDemo
```

---

## 3. Linux / Ubuntu / GitHub Codespaces Setup

### Step 1: Check Java Installation

```bash
java -version
javac -version
```

### Step 2: Install MySQL Server

```bash
sudo apt update
sudo apt install mysql-server -y
```

### Step 3: Start MySQL Server

```bash
sudo service mysql start
```

Check status:

```bash
sudo service mysql status
```

### Step 4: Open MySQL

```bash
sudo mysql
```

### Step 5: Create Database

```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;
```

### Step 6: Create Students Table

```sql
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
```

### Step 7: Create JDBC User

Ubuntu commonly uses socket authentication for the MySQL root account. Therefore, Java JDBC login using root may fail.

Create a separate user:

```sql
CREATE USER IF NOT EXISTS 'jdbcuser'@'localhost'
IDENTIFIED BY 'jdbc123';

GRANT ALL PRIVILEGES ON studentdb.*
TO 'jdbcuser'@'localhost';

FLUSH PRIVILEGES;
EXIT;
```

### Step 8: Test JDBC User

```bash
mysql -u jdbcuser -p studentdb
```

Enter the password:

```text
jdbc123
```

Exit:

```sql
EXIT;
```

### Step 9: Compile Java Program

```bash
javac -cp ".:lib/*" JDBCDemo.java
```

### Step 10: Run Java Program

```bash
java -cp ".:lib/*" JDBCDemo
```

---

## 4. JDBC Connection Code

Use the following connection code in `JDBCDemo.java`.

```java
String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "jdbcuser";
String password = "jdbc123";

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
    url, user, password
);

System.out.println("Database connected successfully!");
```

Required imports:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
```

**Note:** Use your configured username and password. Do not commit real database credentials to GitHub.

---

## 5. Windows vs Linux Commands

| Operation | Windows PowerShell | Linux / Codespaces |
|---|---|---|
| Check Java | `java -version` | `java -version` |
| Check Compiler | `javac -version` | `javac -version` |
| Check MySQL | `mysql --version` | `mysql --version` |
| Start MySQL | `net start MySQL80` | `sudo service mysql start` |
| Login MySQL | `mysql -u root -p` | `sudo mysql` |
| Compile | `javac -cp ".;lib/*" JDBCDemo.java` | `javac -cp ".:lib/*" JDBCDemo.java` |
| Run | `java -cp ".;lib/*" JDBCDemo` | `java -cp ".:lib/*" JDBCDemo` |

### Important Difference

- Windows uses semicolon (`;`) as the classpath separator.
- Linux uses colon (`:`) as the classpath separator.
- Java source code remains the same on both operating systems.
- Windows and GitHub Codespaces have separate MySQL environments.

---

## 6. Common JDBC Errors and Solutions

### Error 1: MySQL JDBC Driver Not Found

```text
java.lang.ClassNotFoundException:
com.mysql.cj.jdbc.Driver
```

**Reason:** MySQL Connector JAR is missing from the classpath.

**Solution:**

Verify that the JAR exists:

```text
lib/mysql-connector-j-26.10.0.jar
```

Windows:

```powershell
java -cp ".;lib/*" JDBCDemo
```

Linux:

```bash
java -cp ".:lib/*" JDBCDemo
```

### Error 2: Access Denied for Root User

```text
Access denied for user 'root'@'localhost'

SQL State: 28000
Error Code: 1698
```

**Reason:** Ubuntu MySQL commonly uses socket authentication for root.

**Solution:**

```bash
sudo mysql
```

Execute:

```sql
CREATE USER IF NOT EXISTS 'jdbcuser'@'localhost'
IDENTIFIED BY 'jdbc123';

GRANT ALL PRIVILEGES ON studentdb.*
TO 'jdbcuser'@'localhost';

FLUSH PRIVILEGES;
EXIT;
```

Update Java:

```java
String user = "jdbcuser";
String password = "jdbc123";
```

### Error 3: Database Does Not Exist

```text
Unknown database 'studentdb'

SQL State: 42000
Error Code: 1049
```

**Solution:**

```sql
CREATE DATABASE IF NOT EXISTS studentdb;
SHOW DATABASES;
```

### Error 4: Students Table Does Not Exist

```text
Table 'studentdb.students' doesn't exist

SQL State: 42S02
Error Code: 1146
```

**Solution:**

```sql
USE studentdb;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);

SHOW TABLES;
```

### Error 5: Communications Link Failure

```text
Communications link failure

SQL State: 08S01
```

**Possible Causes:**

- MySQL Server is not running.
- Incorrect database host.
- Incorrect port.
- Incorrect JDBC URL.

Linux solution:

```bash
sudo service mysql start
sudo service mysql status
```

Check port:

```bash
sudo ss -ltnp | grep 3306
```

Verify JDBC URL:

```java
String url = "jdbc:mysql://localhost:3306/studentdb";
```

### Error 6: Duplicate Primary Key

```text
Duplicate entry '106' for key 'students.PRIMARY'

SQL State: 23000
Error Code: 1062
```

**Reason:** Student ID already exists.

**Solution:**

```sql
SELECT * FROM students WHERE id = 106;
```

Use a new unique ID or update the existing record.

### Error 7: MySQL Command Not Found

```text
mysql: command not found
```

**Solution for Ubuntu:**

```bash
sudo apt update
sudo apt install mysql-client -y
```

Verify:

```bash
mysql --version
```

### Error 8: MySQL Service Not Running

```text
Can't connect to local MySQL server
through socket
```

**Linux solution:**

```bash
sudo service mysql start
sudo service mysql status
```

Check logs if the service fails:

```bash
sudo journalctl -u mysql -n 50 --no-pager
```

### Error 9: Table Already Exists

```text
Table 'students' already exists
Error Code: 1050
```

**Solution:**

Use:

```sql
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
```

### Error 10: Incorrect JDBC Password

```text
Access denied for user 'jdbcuser'@'localhost'

SQL State: 28000
```

**Solution:**

Open MySQL:

```bash
sudo mysql
```

Reset password:

```sql
ALTER USER 'jdbcuser'@'localhost'
IDENTIFIED BY 'jdbc123';

FLUSH PRIVILEGES;
EXIT;
```

Update the Java password accordingly.

### Error 11: Database Access Denied

```text
SELECT command denied to user
```

**Solution:**

```bash
sudo mysql
```

Execute:

```sql
GRANT ALL PRIVILEGES ON studentdb.*
TO 'jdbcuser'@'localhost';

FLUSH PRIVILEGES;
EXIT;
```

### Error 12: Java Compiler Not Found

```text
javac: command not found
```

**Linux solution:**

```bash
sudo apt update
sudo apt install default-jdk -y
```

Verify:

```bash
java -version
javac -version
```

On Windows, install a Java JDK and configure the PATH environment variable.

### Error 13: Incorrect Classpath Separator

**Windows:**

```powershell
javac -cp ".;lib/*" JDBCDemo.java
java -cp ".;lib/*" JDBCDemo
```

**Linux:**

```bash
javac -cp ".:lib/*" JDBCDemo.java
java -cp ".:lib/*" JDBCDemo
```

---

## 7. Database Verification Commands

Login to MySQL.

Linux:

```bash
sudo mysql
```

Windows:

```powershell
mysql -u root -p
```

Execute:

```sql
SHOW DATABASES;

USE studentdb;

SHOW TABLES;

DESC students;

SELECT * FROM students;

SELECT COUNT(*) FROM students;
```

Exit:

```sql
EXIT;
```

---

## 8. Expected Output

```text
MySQL JDBC Driver loaded successfully!

Database connected successfully!

CREATE: Record inserted successfully!

READ: Student Records

101 - Rahat - 20
102 - Soham - 26
103 - Snehal - 21
```

**Note:** Actual output depends on the records present in the database and the operations performed by your Java program.

---

## 9. Complete Setup Checklist

Before running your JDBC program, verify:

- [ ] Java JDK is installed.
- [ ] `java -version` works.
- [ ] `javac -version` works.
- [ ] MySQL Server is installed.
- [ ] MySQL Server is running.
- [ ] Database `studentdb` exists.
- [ ] Table `students` exists.
- [ ] JDBC user is created.
- [ ] JDBC user has database permissions.
- [ ] MySQL Connector JAR exists inside `lib/`.
- [ ] JDBC URL is correct.
- [ ] Username and password are correct.
- [ ] Correct classpath separator is used.
- [ ] Student IDs are unique before inserting records.

---

## 10. Useful Quick Commands

### Windows PowerShell

```powershell
java -version
javac -version
mysql --version
Get-Service MySQL80
net start MySQL80
javac -cp ".;lib/*" JDBCDemo.java
java -cp ".;lib/*" JDBCDemo
```

### Linux / GitHub Codespaces

```bash
java -version
javac -version
mysql --version
sudo service mysql status
sudo service mysql start
sudo mysql
javac -cp ".:lib/*" JDBCDemo.java
java -cp ".:lib/*" JDBCDemo
```

---

## 11. Important Notes

1. Install Java JDK on your system.
2. Keep MySQL Server running before executing JDBC code.
3. Keep MySQL Connector JAR inside the `lib` folder.
4. Use the correct classpath separator for your operating system.
5. Ensure database name, username, and password are correct.
6. On Ubuntu, prefer a separate JDBC user instead of using the root account.
7. Create the database and table before executing INSERT operations.
8. Use unique primary keys to avoid duplicate entry errors.
9. Windows and GitHub Codespaces have separate MySQL environments.
10. Database records are not automatically shared between Windows and Codespaces.
11. Do not commit real database passwords to GitHub.
12. Use `CREATE TABLE IF NOT EXISTS` to avoid errors when repeating table setup.

---

## 12. Assignment Information

**Assignment:** JDBC Assignment 11

**Technologies:**
- Java
- MySQL
- JDBC
- MySQL Connector/J

**Supported Environments:**
- Windows 10/11 – PowerShell
- Linux – Ubuntu
- GitHub Codespaces – Linux Terminal

**Main Operations:**
- CREATE – Insert student records
- READ – Display student records
- UPDATE – Modify student records
- DELETE – Remove student records

**Purpose:** To compile, execute, connect, and perform CRUD operations using Java JDBC and MySQL across Windows and Linux environments.

---

**End of Guide**
