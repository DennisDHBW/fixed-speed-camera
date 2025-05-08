# fixed-speed-camera

# 📖 Project Overview

As part of this examination assignment, an **object-oriented implementation and simulation of a stationary speed camera system** is to be developed in **Java 21 (LTS)** using **Lombok**. The solution must fulfill **realistic and domain-consistent requirements**.

## 📌 Project Requirements

### 📚 Object-Oriented Principles
The entire design and implementation must strictly follow these object-oriented principles:
- **Abstraction**
- **Encapsulation**
- **Polymorphism**
- **Inheritance**

### 🧩 Main Components
- Domain-specific, well-structured classes containing attributes of the following data types:
  - `char`, `String`, `byte`, `short`, `int`, `long`, `LocalDate`, `LocalDateTime`, `boolean`, `enum`, `double`, `float`

### 🛠️ Methods
- At least **seven non-trivial methods** with carefully designed workflows.
- **Two of these methods must be overloaded**.
- All methods should interact meaningfully within a realistic, coordinated process.

### 🔗 Associations
Conceptualize and implement the following associations **twice each** with realistic use cases:
- `1:1`
- `1:n`
- `n:m`
- **Aggregation**
- **Composition**

### 🧬 Inheritance / Taxonomies
- Integrate **two domain-relevant taxonomic (hierarchical) relationships** using **inheritance**.
- Implement these using **Lombok’s `@SuperBuilder` annotation**.

### 📊 Data Structures
Design and implement realistic application scenarios for each of the following:
- `Array`
- `ArrayList`
- `Stack` (custom implementation based on `Array`)
- `Queue`
- `PriorityQueue`

### 🗄️ In-Memory Database
- Create a **hash-based in-memory data store** containing **10 records of license plates and vehicle owners**.
- On a traffic violation:
  - The system determines the vehicle owner using the license plate.
  - The fine is **automatically deducted from the driver's smartphone wallet** by the speed camera (instant payment system).
  - **Driving bans and appeals are not considered** in this implementation.

### 🚀 Main Application
- Deliver a fully integrated main application featuring a **complete and coherent use case scenario**.
- The application must demonstrate the use of:
  - All defined attributes
  - All implemented methods
  - All specified data structures
  - All designed associations
- Use **Lombok’s `@Builder` annotation efficiently and effectively** to reduce boilerplate code.

### 🤖 AI Assistance
- The use of AI-assisted tools for concept development and implementation is permitted, provided their use is **reflected upon and applied responsibly**.

### ⚙️ Mandatory Lombok Usage
- **Lombok must be used** for reducing boilerplate code (getters, setters, builders, etc.).
