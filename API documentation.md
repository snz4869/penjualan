# API Documentation

## Authentication Endpoints

### Generate Token
**URL:** `/auth/token`

**Method:** `POST`

**Request Body:**
```json
{
  "username": "string",
  "password": "string"
}
```

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": "string"
  }
  ```
- **Error:**
  ```json
  {
    "responseCode": "401",
    "descErrorCode": "Invalid username or password"
  }
  ```

---

### Login
**URL:** `/auth/login`

**Method:** `POST`

**Request Body:**
```json
{
  "username": "string",
  "password": "string"
}
```

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": "Login successful"
  }
  ```
- **Error:**
  ```json
  {
    "responseCode": "401",
    "descErrorCode": "Invalid username or password"
  }
  ```

---

## Product Endpoints

### Add Product
**URL:** `/product/add`

**Method:** `POST`

**Request Body:**
```json
{
  "name": "string",
  "price": "number",
  "description": "string"
}
```

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": {
      "id": "string",
      "name": "string",
      "price": "number",
      "description": "string"
    }
  }
  ```
- **Error:** Refer to the specific error details in the response.

---

### Edit Product
**URL:** `/product/edit`

**Method:** `POST`

**Request Body:**
```json
{
  "id": "string",
  "name": "string",
  "price": "number",
  "description": "string"
}
```

**Response:**
- **Success:** Similar to Add Product.
- **Error:** Refer to the specific error details in the response.

---

### Delete Product
**URL:** `/product/delete`

**Method:** `POST`

**Request Body:**
```json
{
  "id": "string"
}
```

**Response:**
- **Success:** Similar to Add Product.
- **Error:** Refer to the specific error details in the response.

---

### Get All Products
**URL:** `/product/getAll`

**Method:** `GET`

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": [
      {
        "id": "string",
        "name": "string",
        "price": "number",
        "description": "string"
      }
    ]
  }
  ```
- **Error:** Refer to the specific error details in the response.

---

## Transaction Endpoints

### Add Transaction
**URL:** `/transactions/add`

**Method:** `POST`

**Request Body:**
```json
{
  "productId": "string",
  "quantity": "number",
  "date": "string"
}
```

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": {
      "id": "string",
      "productId": "string",
      "quantity": "number",
      "date": "string"
    }
  }
  ```
- **Error:** Refer to the specific error details in the response.

---

### Get Transaction by ID
**URL:** `/transactions/id/{id}`

**Method:** `GET`

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": {
      "id": "string",
      "productId": "string",
      "quantity": "number",
      "date": "string"
    }
  }
  ```
- **Error:** Refer to the specific error details in the response.

---

### Get Transactions by Date Range
**URL:** `/transactions/report`

**Method:** `GET`

**Request Parameters:**
- `startDate`: `yyyy-MM-dd`
- `endDate`: `yyyy-MM-dd`

**Response:**
- **Success:**
  ```json
  {
    "responseCode": "200",
    "responseSucceed": [
      {
        "id": "string",
        "productId": "string",
        "quantity": "number",
        "date": "string"
      }
    ]
  }
  ```
- **Error:** Refer to the specific error details in the response.
