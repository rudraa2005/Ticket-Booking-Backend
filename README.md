# 🚆 Ticket Booking Backend

A simple **Java console-based train ticket booking system**.  
This project demonstrates how to manage user authentication, train search, booking, and cancellation using a menu-driven CLI app.

---

## 📌 Features
- **User Management**
  - Sign up with username and password
  - Login with existing credentials
- **Train Booking**
  - Search trains by source and destination
  - View available trains with timings
  - Select and book available seats
  - Cancel existing bookings
- **Booking History**
  - Fetch all your bookings anytime

---

## 🛠️ Tech Stack
- **Java** (Core Java, OOP principles)
- **Gradle** (build tool)
- **Collections Framework** (List, Map, etc.)
- **File I/O** (for user persistence)

---

## 📂 Project Structure
```plaintext
Ticket-Booking-Backend/
│── src/main/java/org/TicketBooking/
│   ├── App.java                # Entry point
│   ├── entities/               # Entities (Train, Ticket, User)
│   ├── services/               # User booking service
│   ├── util/                   # Utility functions
│── build.gradle                # Gradle build file
│── settings.gradle
```
⸻

▶️ How to Run

1. Clone the repo
   ```plaintext
   git clone https://github.com/rudraa2005/Ticket-Booking-Backend.git
   cd Ticket-Booking-Backend
   ```
2. Build with Gradle
   ```plaintext
   ./gradlew build
   ```
3. Run the application
   ```plaintext
   ./gradlew run
   ```
⸻

📖 Usage

When you run the app, you’ll see a menu:
```plaintext
Choose an option:
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
```
Example Flow
	1.	Sign up → Create an account
	2.	Login → Authenticate yourself
	3.	Search trains → Select a train
	4.	Book seat → Enter row & column
	5.	Fetch bookings → View your tickets
	6.	Cancel → Cancel with train ID

⸻

🔮 Future Improvements
	•	Add database support (MySQL/PostgreSQL)
	•	REST API version with Spring Boot
	•	More advanced seat management (berth preference, classes)
	•	Payment simulation
