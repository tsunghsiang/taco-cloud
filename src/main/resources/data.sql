/* Delete tables */
DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Ingredient;
DELETE FROM Taco;
DELETE FROM Taco_Order;
/* Insert ingredient values */
INSERT INTO Ingredient(id, name, type) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO Ingredient(id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO Ingredient(id, name, type) VALUES ('GRBP', 'Ground Beef', 'PROTEIN');
INSERT INTO Ingredient(id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO Ingredient(id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO Ingredient(id, name, type) VALUES ('LETC', 'Lettuce', 'CHEESE');
INSERT INTO Ingredient(id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Ingredient(id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO Ingredient(id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO Ingredient(id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');