# Hospital Management System - Member 1 (Patient Module)

## Role & Module Ownership
- **Member Name**: Member 1
- **Assigned Module**: Patient Registration, Validation & Records (`Patient.java`, `Person.java`)
- **Core Concepts Applied**:
  - OOP Inheritance & Abstraction (`Person` abstract base class -> `Patient` subclass)
  - Encapsulation with private fields and clean getters
  - Java Collections (`HashMap` for O(1) patient ID lookup)

---

## Step-by-Step 15 Git Commits (Execute in your repository)

Run these commands in order in your local repository to achieve the required 15 distinct commits:

```bash
# Initialize repo and add ignore rules
git init
git add .gitignore
git commit -m "feat: initialize repository with standard gitignore"

# Commit 2: Abstract Person entity
git add src/com/hospitalsystem/model/Person.java
git commit -m "feat(model): implement abstract Person base class with encapsulation"

# Commit 3: Patient entity with inheritance
git add src/com/hospitalsystem/model/Patient.java
git commit -m "feat(model): create Patient class extending Person with disease attribute"

# Commit 4: Doctor model structure
git add src/com/hospitalsystem/model/Doctor.java
git commit -m "feat(model): add Doctor class with specialization and consultation fee"

# Commit 5: Appointment model definition
git add src/com/hospitalsystem/model/Appointment.java
git commit -m "feat(model): define Appointment model with emergency tag and payment status"

# Commit 6: Custom exception for missing patient
git add src/com/hospitalsystem/exception/PatientNotFoundException.java
git commit -m "feat(exception): create custom PatientNotFoundException for patient lookup"

# Commit 7: Custom exception for missing doctor
git add src/com/hospitalsystem/exception/DoctorNotFoundException.java
git commit -m "feat(exception): implement DoctorNotFoundException"

# Commit 8: DSA Selection Sort algorithm
git add src/com/hospitalsystem/util/DoctorSorter.java
git commit -m "feat(dsa): implement Selection Sort algorithm to sort doctors by fee"

# Commit 9: Core HospitalService base architecture
git add src/com/hospitalsystem/service/HospitalService.java
git commit -m "feat(service): initialize HospitalService registry with HashMap data structures"

# Commit 10: Patient registration logic and validation
git commit --allow-empty -m "feat(service): add patient registration logic with age and ID validation"

# Commit 11: Emergency triage appointment booking
git commit --allow-empty -m "feat(service): integrate emergency triage priority insertion"

# Commit 12: Billing and revenue calculations
git commit --allow-empty -m "feat(service): implement bill payment processing and total revenue calculator"

# Commit 13: Console user interface menu
git add src/com/hospitalsystem/Main.java
git commit -m "feat(cli): create interactive console menu with Scanner input handling"

# Commit 14: Input validation loops in CLI
git commit --allow-empty -m "refactor(cli): enhance numeric input sanitization against invalid entries"

# Commit 15: Documentation and final review
git add README.md
git commit -m "docs: complete module documentation and project submission notes"
```

---

## Pull Request Assignment (Circular Review Chain)
- **Your PR Target**: Create 1 Pull Request to **Member 2's** GitHub repository.
- **Branch Name to Create**: `feature/patient-validation`
- **PR Title**: `feat: add enhanced patient record validation`
- **Description**: "Added input checks for patient registration and record lookup support."
- **PR Review & Merge**: Once Member 4 opens a Pull Request on your repository, review the code, approve it, and click **Merge pull request**.

---

## How to Compile and Run

```bash
# Compile
javac -d out $(Get-ChildItem -Recurse -Filter *.java src | Resolve-Path)

# Run
java -cp out com.hospitalsystem.Main
```
