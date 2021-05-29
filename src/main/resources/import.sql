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

/* Insert Users */
INSERT INTO Users(username, password, enabled) VALUES ('admin', 'sysadmin', true);
INSERT INTO Users(username, password, enabled) VALUES ('user', 'test', true);
/* Insert UserAuthorities */
INSERT INTO UserAuthorities(username, authority) VALUES ('admin', 'admin');
INSERT INTO UserAuthorities(username, authority) VALUES ('user', 'customer');