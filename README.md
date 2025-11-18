# Welcome

This project is a web-based content management application aimed to provide basic functionalities such as post creation, comment handling, and URL optimization. The application will be developed using Java Spring Boot, following industry best practices to ensure clean architecture, maintainability, and scalability. Given that the expected traffic is moderate (up to 5 visitors per minute), the system is designed to handle up to 10 RPS (Requests Per Second) efficiently while maintaining minimal operational costs.

The project is also intended as a learning exercise to enhance my proficiency with Spring Boot frameworks, RESTful API design, and scalable system deployment, as well as to build a showcase project for professional growth.

## Table of Contents

- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)

## Technologies

- **Java**: 21
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: MySQL 8
- **Containerization**: Docker
- **IDE**: IntelliJ IDEA

## Installation

1. **Clone the Repository**:

   ```bash
   git clone git@github.com:ilham-yori/personal-blog.git
   cd blog
   ```

2. **Run MySQL**:

   - Go to `docker-compose.yml` and run all containers.

3. **Configure MySQL and secret**:

   - Pergi ke http://localhost:3307/ and login with `root` and `example`
   - Di PHPMyAdmin, buat database: `blog` atau sesuaikan dengan value di `application-dev.properties`
   - Tambahkan file `secret.properties` isinya sesuaikan dengan `SecretProperties.java`

4. **Configure Flyway & Migrate DB**:
   - Buka Run Configurations di Intellij (run -> edit configurations)
   - Tambah New Configuration (alt + insert)
   - Pilih maven, dan isi `validate flyway:migrate -Dflyway.configFiles=src/main/resources/application-dev.properties`
   - Run
     
5. **Configure Run Configs**:
   - Buka Run Configurations di Intellij (run -> edit configurations)
   - Set environment variables dengan `SPRING_PROFILES_ACTIVE=dev`

## Usage

- Jika sudah di configure di IDE, cukup jalankan dengan klik run di menu bar atas. Atau SHIFT+F10 untuk shortcutnya.
- Bisa diakses di `http://localhost:8080`.

## Documentation
Link: https://docs.google.com/document/d/1MMUJ2nSnJY1yCNKqpDH0_NtJsaFoGXmndsWHwggyp2g/edit?usp=drive_link
