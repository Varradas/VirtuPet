<h1 align = "center">🐾 VirtuPet 🐾</h1>
<h2 align = "center">The Intelligent Virtual Pet Care System</h2>
<p align = "center">CS-211 Final Project | 3NIGMA | CS-2102</p>

## 🧠 Overview
**VirtuPet** is a **console-based Java application** that allows users to adopt, feed, and interact with virtual pets in a simulated environment. The program demonstrates the practical application of Object-Oriented Programming (OOP) concepts—encapsulation, inheritance, polymorphism, and abstraction—while implementing modular design, proper file handling, and randomization for pet stats and behaviors.

Users can adopt a new pet (Dog, Cat, Bird, Rabbit), feed pets with different types of food, perform activities to boost pet stats, monitor emotional state and overall well-being, save and load progress, and handle pet deaths if neglected.

All pet data, including activity multipliers and food effectiveness, are stored using serialization (.ser files) to maintain a persistent game state.


---

## Project Structure
```
📂 file/
├── 📂 audio/
│   ├── 🎵 back.wav
│   ├── 🎵 bgMusic.wav
│   ├── 🎵 error.wav
│   ├── 🎵 select.wav
│   ├── 🎵 statDown.wav
│   └── 🎵 statUp.wav
├── 📂 sprites/
│   ├── 📂 BIRD/
│   ├── 📂 CAT/
│   ├── 📂 DOG/
│   ├── 📂 RABBIT/
│   └── 📄 dog1.txt
├── 📂 virtupetClasses/
│   ├── ☕ Action.java
│   ├── ☕ Activity.java
│   ├── ☕ ActivitySelection.java
│   ├── ☕ EmotionalState.java
│   ├── ☕ Food.java
│   ├── ☕ Pet.java
│   ├── ☕ Species.java
│   ├── ☕ SpeciesActivities.java
│   └── 📂 PetSubclasses/
│       ├── ☕ Bird.java
│       ├── ☕ Cat.java
│       ├── ☕ Dog.java
│       └── ☕ Rabbit.java
└── ☕ virtuPet.java
```

---

## How to Run the Program
Open your terminal in the `files/` folder and run:
```
javac *.java
```
Run the program using:
```
java 
```

---

## ⚙️ Features
- 🐶 **Adopt Pet:** Select and create a new pet of a chosen species.
- 🍎 **Feed Pet:** Provide food to restore energy, mood, and emotional value; effectiveness varies per species.
- ⚡ **Perform Activity:** Engage pets in activities to increase stats; activity effects vary per species and include randomized multipliers.
- 💖 **View Stats:** Monitor hunger, mood, energy, and emotional state in real time.
- ⌛ **Time Simulation:** Pet stats decrease over time; neglect may lead to death.
- ✖️ **Custom Multipliers:** Each species has unique activity and food multipliers, adding variability to gameplay.
- 💾 **Save & Load:** Persist pet progress using serialization.

---

## ‧₊˚ Object-Oriented Principles
### 💊 Encapsulation
 Encapsulation in VirtuPet is implemented through careful use of access modifiers in class design. In the Pet class, fields such as hunger_level, mood_level, and energy are marked as protected, allowing subclasses to access and modify them safely, while name and species are private and can only be accessed or modified through public getter and setter methods like getPetName(), setName(), getSpecies(), and setSpecies(). Additionally, activity_multiplier and food_multiplier are protected, giving subclasses the flexibility to customize behavior while keeping the underlying structure hidden from external classes. By restricting direct access, all updates to pet stats and attributes are controlled through defined methods such as updateStatsWhenTimePass() and updateEmotionalState(), ensuring changes remain consistent and validated.

### 💡 Abstraction
 Abstraction is demonstrated through classes such as Action, ActivitySelection, and Food. These classes encapsulate the internal mechanics of performing activities, feeding pets, and calculating species-specific multipliers, so users do not need to interact with the low-level logic directly. For example, calling performActivity() abstracts away activity multipliers and energy calculations, and feedPet() abstracts the species-specific effectiveness of each type of food. This separation of concerns hides complex implementation details and allows the main program (virtuPet.java) to interact with pets through simple, high-level commands.

### 🧬 Inheritance
 Inheritance is applied through the abstract Pet class (public abstract), which serves as the base class for all pet types. Subclasses such as Dog, Cat, Bird, and Rabbit (public final) inherit common fields and methods from Pet while overriding methods like updateEmotionalState() and updateStatsWhenTimePass() to implement species-specific behavior. This design promotes code reuse, maintains organized structure, and allows for easy extension, enabling new pet species to be added without modifying the base class.

### 🎭 Polymorphism
 Polymorphism is illustrated through method overriding in the pet subclasses. Methods like updateEmotionalState() and updateStatsWhenTimePass() behave differently depending on the specific subclass, even when invoked through a Pet reference. For instance, calling Pet myPet = new Dog("Max", Species.DOG); myPet.updateStatsWhenTimePass(mypets, myPet); dynamically invokes the Dog-specific implementation of the method at runtime. This enables the program to manage all pets uniformly in the main loop while respecting each species’ unique logic and behavior.

---

## Example Output
```

```

---

## 👥 Contributors
- **Berana, Jon Paul S.**
- **De Castro, Vinz Gabriel S.**  
- **Mendoza, John Laurence M.**  

---

## ‧₊˚ Acknowledgment
We sincerely thank our Object-Oriented Programming **instructor** for guidance throughout the course. Special thanks to our **team** for their cooperation, hard work, and contributions to completing this project.

---

### DISCLAIMER
This project is created for **educational purposes** under the **Object-Oriented Programming** course.  
© 2025 VirtuPet Team. All rights reserved.
