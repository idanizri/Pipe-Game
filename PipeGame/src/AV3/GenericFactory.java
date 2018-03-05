package AV3;

import java.util.HashMap;
import java.util.Map;

import Algorithms.BFS;
import Algorithms.BestFirstSearch;
import Algorithms.DFS;
import Algorithms.HillClimbing;

public class GenericFactory<Product> {

	private interface Creator<Product> {

		public Product create(); // no unhandled exceptions

	}

	Map<String, Creator<Searcher>> map;

	public GenericFactory() {

		map = new HashMap<String, Creator<Searcher>>();
		insertProduct("bfs", new BFS().getClass());
		insertProduct("dfs", new DFS().getClass());
		insertProduct("bestfirstsearch", new BestFirstSearch().getClass());
		insertProduct("hillclimbing", new HillClimbing().getClass());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertProduct(String key, Class<? extends Searcher> c) {
		Creator creator = new Creator<Searcher>() {

			public Searcher create() {

				try {
					return c.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {

					e.printStackTrace();
					return null;
				}
			}
		};

		map.put(key, creator);

	}

	@SuppressWarnings("rawtypes")
	public Searcher getNewProduct(String key) {
		Creator creator = map.get(key);
		if(creator==null)
			return null;
		return (Searcher) creator.create();
	}

}
