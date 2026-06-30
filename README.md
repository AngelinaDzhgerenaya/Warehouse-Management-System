# Inventory & Order Management System

Backend приложение для управления товарами, складами и заказами.

---

## Описание проекта

Система позволяет:
- управлять товарами
- хранить остатки на складах
- резервировать товар
- оформлять заказы
- подтверждать и отменять заказы
- фиксировать все операции со складом

---

## Основной функционал

### Товары
- создание и управление товарами
- привязка к категориям
- хранение цены

### Склады
- хранение товаров
- учёт остатков
- учёт зарезервированного товара

### Stock (остатки)
- увеличение/уменьшение количества
- резервирование товара
- возврат товара на склад
- фиксация операций

### Заказы
- создание заказа
- автоматическое резервирование товара
- подтверждение заказа (CONFIRMED)
- отмена заказа (CANCELED)
- расчёт общей суммы заказа

---

## Жизненный цикл заказа
При изменении статуса:
- обновляется Stock
- создаётся StockOperation
- изменяется резерв

---

## Технологии

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

---

## Основные сущности

- Product
- Category
- Warehouse
- Stock
- StockOperation
- Order
- OrderItem

---

## API основные endpoints

### Categories
- GET `/categories` — получить категории

### Products
- POST `/products` — создать продукт
- GET `/products/{id}` — получить продукт

### Warehouses
- POST `/warehouses` — создать склад
- GET`/warehouses/{id}` — получить склад

### Stock
- GET `/stocks` - получить кол-во продукта на складе
- POST `/stocks/increase` - добавить кол-во продукта на склад
- POST `/stock/decrease` - убрать кол-во продукта на склад

### Stock Operations
- GET `/stocks-operations/product/{id}` - список операций с продуктом
- GET `/stocks-operations/warehouse/{id}` - список операций со складом

### Orders
- POST `/orders` — создать заказ
- GET `/orders` — получить заказ со статусом
- GET `/orders/{id}` — получить заказ
- POST `/orders/confirm/{id}` — подтвердить заказ
- POST `/orders/cancel/{id}` — отменить заказ

---

## Как запустить

- Требования (Java, Docker, PostgreSQL)
- Запуск БД: docker-compose up -d
- Запуск приложения: mvn spring-boot:run
- Адрес: http://localhost:8080/api