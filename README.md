# 📚 Library Management System (Java AWT + JDBC)

A simple **Library Management System** built using **Java AWT** for GUI and **MySQL** with **JDBC** for database operations. This mini-project supports full **CRUD functionality**, including search and update features.

---

## 🚀 Features

- ➕ Add Book
- 🔍 Search Book by ID
- 🔄 Update Book Details
- 📋 View All Books
- ❌ Delete Book
- 🧼 Clear Fields (UI reset)

---

## 🛠️ Tech Stack

- **Java** (AWT for GUI)
- **MySQL** (JDBC for DB connectivity)
- **JDBC** (PreparedStatement for secure queries)

---

## 💾 Database Setup

1. Open MySQL and run the following query:
```sql
CREATE DATABASE book;

USE book;

CREATE TABLE boo (
    book_id INT PRIMARY KEY,
    book_name VARCHAR(25),
    cost INT
);
