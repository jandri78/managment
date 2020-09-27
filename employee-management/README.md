##### MYSQL ########
CREATE SCHEMA `managment` ;


##### Request ######

$$$$$ CRUD Employee $$$$$

curl --location --request GET 'localhost:8080/employee/'


curl --location --request GET 'localhost:8080/employee/1'


curl --location --request GET 'localhost:8080/employee/byname?nameORlastname=andres'


curl --location --request POST 'localhost:8080/employee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nombres": "Andres",
    "apellidos": "suarez",
    "salarioBase": 6000000
}'


curl --location --request PUT 'localhost:8080/employee' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "id": 1,
        "nombres": "Andres",
        "apellidos": "Perez",
        "salarioBase": 3000000.0,
        "fechaIngreso": "2020-09-26",
        "fechaRetiro": "2020-09-28"
    }'


curl --location --request DELETE 'localhost:8080/employee/1'

$$$$$ $$$$$$$$$$$$$ $$$$$


$$$$$ CalcularPago $$$$$

curl --location --request POST 'localhost:8080/employee/calcular?id=1&month=09&year=2020'

$$$$$ $$$$$$$$$$$$$ $$$$$
