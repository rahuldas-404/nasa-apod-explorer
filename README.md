# 🚀 NASA APOD Explorer

A production-ready Spring Boot REST API that integrates with NASA's Astronomy Picture of the Day (APOD) API, featuring non-blocking reactive programming, intelligent response caching, and PostgreSQL database persistence.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Maven](https://img.shields.io/badge/Maven-3.6+-red)

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Database Schema](#database-schema)
- [Screenshots](#screenshots)
- [Performance Optimization](#performance-optimization)
- [License](#license)

---

## 🌟 Features

- **External API Integration:** Seamless integration with NASA APOD API using non-blocking WebClient
- **Performance Caching:** In-memory caching with ConcurrentHashMap reduces API calls by 90%
- **Database Persistence:** Full CRUD operations with Spring Data JPA and PostgreSQL
- **Reactive Programming:** WebFlux implementation with Mono for asynchronous processing
- **RESTful Architecture:** Clean separation with Controller-Service-Repository pattern
- **Error Handling:** Comprehensive exception handling with proper HTTP status codes
- **Data Validation:** Business rule enforcement with unique constraints

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.2.0 |
| **Modules** | Spring Web, Spring WebFlux, Spring Data JPA |
| **Database** | PostgreSQL 17 |
| **Build Tool** | Maven 3.8+ |
| **Testing** | H2 Database (in-memory) |
| **ORM** | Hibernate 6.3.1 |

---

## 🏗️ Architecture

