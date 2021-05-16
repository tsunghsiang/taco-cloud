/* Delete tables */
DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Ingredient;
DELETE FROM Taco;
DELETE FROM Taco_Order;

/* Insert ingredient values */
INSERT INTO Ingredient(id, name, type) VALUES ('FLTO', 'Flour Tortilla', 0);
INSERT INTO Ingredient(id, name, type) VALUES ('COTO', 'Corn Tortilla', 0);
INSERT INTO Ingredient(id, name, type) VALUES ('GRBP', 'Ground Beef', 1);
INSERT INTO Ingredient(id, name, type) VALUES ('CARN', 'Carnitas', 1);
INSERT INTO Ingredient(id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 2);
INSERT INTO Ingredient(id, name, type) VALUES ('LETC', 'Lettuce', 3);
INSERT INTO Ingredient(id, name, type) VALUES ('CHED', 'Cheddar', 3);
INSERT INTO Ingredient(id, name, type) VALUES ('JACK', 'Monterrey Jack', 3);
INSERT INTO Ingredient(id, name, type) VALUES ('SLSA', 'Salsa', 4);
INSERT INTO Ingredient(id, name, type) VALUES ('SRCR', 'Sour Cream', 4);