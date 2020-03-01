CREATE TABLE mortgagecalculator
(id INTEGER PRIMARY KEY AUTO_INCREMENT,
 calculation_name VARCHAR(30),
 purchase_price FLOAT,
 deposit_paid FLOAT,
 bond_term INT,
 fixed_interest_rate FLOAT,
 monthly_payment FLOAT,
 timestamp TIMESTAMP
 );
