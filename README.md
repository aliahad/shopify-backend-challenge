# Shopify Backend Challenge
Shopify Backend Developer Intern Challenge - Summer 2022 \
This service is build to solve the challenge outlined [here](https://docs.google.com/document/d/1z9LZ_kZBUbg-O2MhZVVSqTmvDko5IJWHtuFmIu_Xg1A/edit)

## Inventory Management APIs
This service contains some APIs for managing items in an inventory, built using Java Spring Boot framework.

## Get started
In order to run the APIs locally, you need to install:
* Java jdk-17
* Maven
* IntelliJ

## How to run?
### Step 1:
Clone [this](https://github.com/aliahad/shopify-backend-challenge.git) repository

### Step 2:
Open project in IntelliJ, and wait until all dependencies are resolved

### Step 3:
Go to `ChallengeApplication.java` file and run `main` method

Once service is up and running, you're ready to test APIs.

## API Routes
|Description|Request Method|URI|Path Variable|Request Body|
|-----------|--------------|---|-------------|------------|
|Get all items|GET|`/inventory/items`|-|-|
|Get item by id|GET|`/inventory/item/{id}`|`id` of item|-|
|Add item|POST|`/inventory/item`|-|```{"name":"name of item", "price": price of item}```|
|Update item by id|PUT|`/inventory/item/{id}`|`id` of item|```{"name":"name of item", "price": price of item}```|
|Delete item by id|DELETE|`/inventory/item/{id}`|`id` of item|-|
|Get all items as CSV|GET|`/inventory/items.csv`|-|-|

## Sample API Requests
### Get all items:
Request: 
```
curl -X GET http://localhost:8080/inventory/items
```
Response:
```
[
    {
        "id": 1,
        "name": "Item 1"
        "price": 10.0
    },
    {
        "id": 2,
        "name": "Item 2"
        "price": 18.5
    }
]
```

### Get item by id:
Request: 
```
curl -X GET http://localhost:8080/inventory/item/1
```
Response:
```
{
    "id": 1,
    "name": "Item 1"
    "price": 10.0
}
```

### Add item:
Request: 
```
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"name": "Item 1", "price": 10.5}'  \
    http://localhost:8080/inventory/item
```
Response:
```
{
    "id": 1,
    "name": "Item 1"
    "price": 10.5
}
```

### Update item:
Request:
```
curl -X PUT \
    -H "Content-Type: application/json" \
    -d '{"name": "Item 1", "price": 35.0}'  \
    http://localhost:8080/inventory/item/1
```
Response:
```
{
    "id": 1,
    "name": "Item 1"
    "price": 35.0
}
```

### Delete item by id:
Request: 
```
curl -X DELETE http://localhost:8080/inventory/item/1
```
Response:
```
No response, just a success status
```

### Get all items as CSV:
Request:
```
curl -X GET http://localhost:8080/inventory/items.csv
```
Response:
```
Download a CSV file named items.csv
```
