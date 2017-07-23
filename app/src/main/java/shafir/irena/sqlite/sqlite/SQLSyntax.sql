--- Relational database
--- SQL IS NOT CASE SENSITIVE

SELECT col1Name, col2Name, col3Name,

--- *= All Columns
SELECT * FROM Customers;




-- LIKE
--- % - gives all the info with "ch" no mater where since % is from both sides
SELECT * FROM PRODUCTS
WHERE productName LIKE "%ch%"



SELECT * FROM PRODUCTS
WHERE productName LIKE "%ch___%";


-- ORDER BY
-- ASC = from low to high
--- DESC = from high to low
SELECT * FROM PRODUCTS
WHERE price > 30
ORDER BY price ASC, ProductName DESC



SELECT *
FROM products
JOIN categories
ON products.CategoryID = categories.CategoryID
ORDER BY CategoryName


SELECT CustomerName
FROM customers JOIN orders
ON customers.customerID = orders.customerID
JOIN orderDetails
ON orders.orderID = orderDetails.orderID
JOIN products
ON products.productName = orderDetails.orderID
WHERE 'Chais'



SELECT *
FROM Customers
LEFT JOIN orders
ON customers.customerID = orders.customerID
WHERE orders.customerID IS NULL


SELECT *
FROM CUSTOMERS
left JOIN ORDERS
ON customers.customerID = orders.customerID
WHERE orders.customerID IS NULL



--- many to many
CREATE TABLE Students(Student ID INTEGER,
						firstName TEXT,
						lastName TEXT);

CREATE TABLE phones(phoneID INTEGER,
					studentID INTEGER,
                    phone TEXT);

CREATE TABLE courses(courseID INTEGER,
                    courseName TEXT);

--- join chart: (combines between students & courses)
CREATE TABLE studentCourses(
               studentID INTEGER;
               courseID INTEGER);

-- adding to table
INSERT INTO Students(studentID, firstName, lastName)
VALUES(1, 'moshe', 'doe')

-- delete table
DROP TABLE Students

-- to get automatic ID we need to add AUTO_INCREMENT to the relevant key
CREATE TABLE studentCourses(
               studentID INTEGER AUTO_INCREMENT
               courseID INTEGER);

-- in that case we'll need to insert only the other columns

-- this will delete an entire table
DELETE FROM customers --- dangerous- WILL DELETE ALL THE COSTUMERS

-- this will delete only the specific product
DELETE FROM product
WHERE productName = 'chais'

-- change
UPDATE products
SET productName = 'chocho'
WHERE 'Chang'

-- delete line
DELETE FROM products
WHERE productID = 2