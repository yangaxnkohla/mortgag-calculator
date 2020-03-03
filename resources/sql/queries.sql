-- :name save-calculation! :! :n
-- :doc creates a new calculation record
INSERT INTO mortgagecalculator
(calculation_name, purchase_price, deposit_paid, bond_term,fixed_interest_rate,monthly_payment,timestamp)
VALUES (:calculation_name, :purchase_price, :deposit_paid, :bond_term, :fixed_interest_rate, :monthly_payment, :timestamp)
--INSERT INTO mortgagecalculator
--(id, calculation_name, purchase_price, deposit_paid, bond_term,fixed_interest_rate,monthly_payment,timestamp)
--VALUES (:id, :calculation_name, :purchase_price, :deposit_paid, :bond_term, :fixed_interest_rate, :monthly_payment, :timestamp)

-- :name get-calculations :? :*
-- :doc retrieves all calculation records
SELECT * FROM mortgagecalculator

-- <--- IGONE DEFAULT CODE --->
-- :name update-user! :! :n
-- :doc updates an existing user record
-- UPDATE users
-- SET first_name = :first_name, last_name = :last_name, email = :email
-- WHERE id = :id

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
-- DELETE FROM users
-- WHERE id = :id
-- <--- IGONE DEFAULT CODE ---/>
