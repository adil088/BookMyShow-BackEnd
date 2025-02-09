BookMyShow Backend Project

🚀 Overview

This is a backend system for a BookMyShow-style application, designed with a modular architecture. The project is structured into three independent services running on different ports, ensuring that failures in one service do not affect the others.

🏗️ Project Structure

✅ db/master – Handles core database operations, including managing users, movies, theaters, and bookings.

✅ exp/master – Manages API requests from users, handling ticket bookings, retrieving movie details, and other user interactions.

✅ mail/master – Manages email notifications, ensuring users receive booking confirmations and updates.

⚙️ Tech Stack

🔹 Backend: Java, Spring Boot🔹 Database: PostgreSQL🔹 Security: Spring Security, JWT Authentication🔹 Email: JavaMail API

📌 Getting Started

Prerequisites

Java 17+

PostgreSQL

Maven

Installation

Clone the repository:

git clone https://github.com/adil088/BookMyShow-BackEnd.git

Navigate to the project directory:

cd BookMyShow-BackEnd

Install dependencies:

mvn clean install

Set up the PostgreSQL database and update application.properties with your database credentials.

Run each module separately:

mvn spring-boot:run

📂 API Endpoints

Refer to the documentation for detailed API usage.

🚀 Future Enhancements

Implementing seat selection

Adding payment integration

Developing the frontend with ReactJS
