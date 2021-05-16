package tacos.repository.jdbc;

import tacos.data.Taco;

public interface TacoRepository {
	Taco save(Taco taco);
}
