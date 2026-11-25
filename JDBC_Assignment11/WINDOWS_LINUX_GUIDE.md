
# JDBC Assignment 11 – Windows & Linux Setup Guide

This guide explains how to compile and run a Java JDBC program with MySQL on Windows and Linux (GitHub Codespaces).

## 1. Project Structure

```text
JDBC_Assignment11/
│
├── JDBCDemo.java
├── JDBCDemo.class
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

### Step 2: Start MySQL Server

Open PowerShell as Administrator:

```powershell
net start MySQL80
```

Alternatively, start MySQL from Windows Services.

### Step 3: Compile Java Program

```powershell
javac -cp ".;lib/*" JDBCDemo.java
```

### Step 4: Run Java Program

```powershell
java -cp ".;lib/*" JDBCDemo
```

## 3. Linux / GitHub Codespaces Setup

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

Check MySQL status:

```bash
sudo service mysql status
```

### Step 4: Create Database

```bash
sudo mysql
```

Execute SQL:

```sql
CREATE DATABASE studentdb;
EXIT;
```

### Step 5: Compile Java Program

```bash
javac -cp ".:lib/*" JDBCDemo.java
```

### Step 6: Run Java Program

```bash
java -cp ".:lib/*" JDBCDemo
```

## 4. JDBC Connection Code

```java
String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "YOUR_MYSQL_PASSWORD";

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
    url, user, password
);
```

Replace `YOUR_MYSQL_PASSWORD` with your configured MySQL password.

## 5. Windows vs Linux Commands

| Operation | Windows PowerShell | Linux / Codespaces |
|---|---|---|
| Compile | `javac -cp ".;lib/*" JDBCDemo.java` | `javac -cp ".:lib/*" JDBCDemo.java` |
| Run | `java -cp ".;lib/*" JDBCDemo` | `java -cp ".:lib/*" JDBCDemo` |
| Start MySQL | `net start MySQL80` | `sudo service mysql start` |
| Check Java | `java -version` | `java -version` |
| Check MySQL | `mysql --version` | `mysql --version` |

### Important Difference

- Windows uses semicolon (`;`) as the classpath separator.
- Linux uses colon (`:`) as the classpath separator.
- Java source code remains the same on both operating systems.

## 6. Common JDBC Errors and Solutions

### Error 1: MySQL JDBC Driver Not Found

```text
java.lang.ClassNotFoundException:
com.mysql.cj.jdbc.Driver
```

**Solution:**

Include MySQL Connector JAR in the classpath.

Windows:

```powershell
java -cp ".;lib/*" JDBCDemo
```

Linux:

```bash
java -cp ".:lib/*" JDBCDemo
```

### Error 2: Communications Link Failure

```text
Communications link failure
SQL State: 08S01
```

**Possible Causes:**

- MySQL Server is not running.
- Incorrect database host.
- Incorrect MySQL port.
- Incorrect connection URL.

**Solution:**

Start MySQL Server and verify the connection URL.

### Error 3: Duplicate Primary Key

```text
Duplicate entry '106' for key 'students.PRIMARY'
SQL State: 23000
Error Code: 1062
```

**Reason:**

The student ID already exists in the database.

**Solution:**

Use a new unique ID or check whether the record already exists before inserting it.

```sql
SELECT * FROM students WHERE id = 106;
```

## 7. Expected Output

```text
MySQL JDBC Driver loaded successfully!

Database connected successfully!

CREATE: Record inserted successfully!

READ: Student Records

101 - Rahat - 20
102 - Soham - 26
103 - Snehal - 21
```

*Note: Actual output depends on the records present in your database.*

## 8. Important Notes

1. Install Java JDK on your system.
2. Keep MySQL Server running before executing JDBC code.
3. Keep MySQL Connector JAR inside the `lib` folder.
4. Use the correct classpath separator for your operating system.
5. Ensure database name, username and password are correct.
6. Windows and GitHub Codespaces have separate MySQL environments.
7. Their databases and records are not automatically shared.

---

**JDBC Assignment 11**

Java + MySQL + JDBC

Supported Environments:

- Windows 10/11 – PowerShell
- Linux – Ubuntu
- GitHub Codespaces – Linux Terminal
