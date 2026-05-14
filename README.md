# Delivery Courier Service

Courier Service – delivery sisteminin kuryer idarəetmə komponenti. Kuryerlərin yaradılması, statusunun izlənilməsi (FREE/BUSY) və Order Service-dən gələn hadisələrə reaksiya verməkdən məsuldur.

## 📋 Tələblər (Specification)

- Kuryer yarat (`POST /couriers`)
  - Yalnız name tələb olunur
  - Default status: FREE
- Bütün kuryerləri listələ (`GET /couriers`)
- Mövcud (FREE) kuryerləri tap (`GET /couriers/available`)
- Kuryer statusunu yenilə (`PATCH /couriers/{id}/status?status=BUSY/FREE`)
- Kuryer məlumatlarını gör (`GET /couriers/{id}`)
- Event-driven (gələcəkdə RabbitMQ):
  - ORDER_ASSIGNED → status BUSY
  - ORDER_DELIVERED → status FREE

## 🚀 Implementasiya olunanlar (Current PR)

### ✅ Entity & Database
- `Courier` entity (JPA + Lombok)
- Liquibase changelog ilə `couriers` cədvəli
- PostgreSQL əlaqəsi

### ✅ DTO və Mapper
- `CourierCreateRequest` (name)
- `CourierResponse` (id, name, status)
- `CourierMapper` (toEntity, toResponse)

### ✅ Repository & Service & Controller
- CRUD əməliyyatları
- `GET /couriers/available` – yalnız FREE statuslu kuryerlər
- `PATCH /couriers/{id}/status` – status dəyişmə
- `GET /couriers/{id}` – kuryer məlumatları

### ✅ Exception Handling
- `CourierNotFoundException` → 404
- `InvalidCourierStatusException` → 400
- Global exception handler (`@RestControllerAdvice`)

## 📬 API Testləri

```bash
# Kuryer yarat
curl -X POST http://localhost:8082/couriers \
  -H "Content-Type: application/json" \
  -d '{"name":"Elşən Qasımov"}'

# Bütün kuryerləri gör
curl http://localhost:8082/couriers

# Mövcud (FREE) kuryerləri gör
curl http://localhost:8082/couriers/available

# Kuryer statusunu BUSY et
curl -X PATCH http://localhost:8082/couriers/1/status?status=BUSY

# Kuryer məlumatlarını gör
curl http://localhost:8082/couriers/1
