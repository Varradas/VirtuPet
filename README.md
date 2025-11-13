# 🐾 VirtuPet
## The Intelligent Virtual Pet Care System

CS-211 Final Project | 3NIGMA | CS-2102 

## 🧠 Project Overview
**VirtuPet** is a **Java console-based simulation game** where users adopt and care for a digital pet with unique moods, needs, and evolving personalities.  
The system goes beyond simple feeding or playing — it simulates realistic pet behavior and emotional intelligence, changing responses depending on how the user treats it.

This project applies the core **Object-Oriented Programming (OOP) principles** — **Encapsulation, Inheritance, Polymorphism, and Abstraction** — along with **arrays, exception handling**, and **user interaction** to create a dynamic, life-like virtual companion.

---

## 🎯 Objectives
- To simulate real-world pet care behavior using **Java and OOP**  
- To demonstrate all **OOP concepts** in a single interactive program  
- To apply **arrays**, **collections**, **methods**, **control structures**, and **exception handling** effectively  
- To create a pet system that **adapts to user interaction** and evolves over time  

---

## ⚙️ Key Features

### 🐶 1. Multiple Pet Species
Users can adopt different types of pets such as **Dog**, **Cat**, or **Dragon**.  
Each subclass has its own:
- Unique behavior patterns  
- Favorite foods  
- Reaction styles  
- Personality traits  

**Example:**  
A **dog** may love fetch and become excited after playing,  
while a **cat** may prefer napping and ignore frequent play attempts.

---

### 💬 2. Emotion System
Each pet has **emotional states** such as:
- 😺 Happy  
- 😿 Sad  
- 🍖 Hungry  
- 💤 Bored  
- 🤒 Sick  

These moods change dynamically depending on how the user interacts with their pet.

---

### 🍖 3. Pet Care Activities
The player can perform actions such as:
- **Feed** the pet (choose food from an inventory)  
- **Play** with the pet (different activities depending on type)  
- **Rest or Heal** the pet  
- **Check** its health and happiness levels  

---

### ⏳ 4. Time Simulation
Each player action **advances in-game time**, affecting the pet’s:
- Hunger  
- Energy  
- Happiness  

Neglect over time can make the pet **sad**, **sick**, or **lose trust** in the player — encouraging consistent interaction.

---

### 🧬 5. Adaptive Behavior (Polymorphism)
Different pets respond uniquely to the same command.  
- Example:  
  - `Dog.play()` → increases happiness greatly  
  - `Cat.play()` → increases happiness slightly  
  - `Dragon.play()` → might reduce energy faster  

This demonstrates **polymorphism**, where the same method behaves differently across subclasses.

---

### 🧯 6. Exception Handling
The program uses **custom exceptions** to manage invalid inputs:
- Feeding a non-existent pet  
- Selecting an invalid menu option  
- Trying to play when the pet is asleep  

This ensures smooth gameplay and robust input validation.

---

### 💾 7. Simple Save System (Optional)
An optional **save and load system** stores the pet’s data (name, hunger, happiness, etc.) in a text file.  
Players can **resume their game** later without losing progress.

---

## 🔮 Future Enhancements
- GUI interface with images and animations  
- More pet types and personality customization  
- Mini-games to increase happiness  
- Health and illness tracking system  
- Database support for storing pet data  

---

## 👥 Contributors
- **De Castro, Vinz Gabriel S.**  
- **Mendoza, John Laurence M.**  
- **Berana, Jon Paul S.**

**Subject:** Object-Oriented Programming (OOP)

---

## 📝 License
This project is created for **educational purposes** under the **Object-Oriented Programming** course.  
© 2025 VirtuPet Team. All rights reserved.

---

## Update Log
11/13/2025 - De Castro, Vinz Gabriel
- Redid file structure, classes are now under the virtupetClasses folder, no changes on the classes themselves.
- Serialization/Deserialization of both the Pet and Food classes have been figured out, save files are located in "files/data" folder.
- New "test.java" file, will be used to test the methods that will be used to manipulate the data of each object, so far it demonstrates how object data is saved/loaded. I could    have also demonstrated resting and eating though I got lazy. Plus the eat action is better tested when the multipliers and food data is finalized. This file will also likely      become a dev file used to easily change the data found in foodData.ser considering the player won't have access to it.
# **Stuff to do before going to the next stage:**
- Still need to figure out if we'll need an Activity class or if it will be implemented in the Pet class for simplicity.
- Pet class still incomplete, will need to figure out how to implement the activity_multiplier and food_multiplier to vary each pet's favorite activities/food.
- Need to figure out how to implement ENUMs that corresponds to the current emotional value level, maybe also for the Species that we'll determine to be available.

