package week5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtil {

	public static <K, V> boolean isOneOnOne(Map<K, V> map) {
		Object[] keys = map.keySet().toArray(new Object[map.size()]);
		Object[] values = new Object[map.size()];

		boolean result = true;

		for (int i = 0; i < keys.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (values[j] != null && values[j].equals(map.get(keys[i]))) {
					return !result;
				}

			}
			values[i] = map.get(keys[i]);
		}
		return result;
	}

	public static <K, V> 
	boolean isSurjectiveOnRange(Map<K, V> map, Set<V> range) {
		Object[] keys = map.keySet().toArray(new Object[map.size()]);
		Object[] values = new Object[map.size()];
		boolean result = true;

		for (int i = 0; i < keys.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (values[j] != null && values[j].equals(map.get(keys[i]))) {
					return !result;
				}

			}
			values[i] = map.get(keys[i]);
		}
		return result;
	}

	public static <K, V> Map<V, Set<K>> inverse(Map<K, V> map) {
		Map<V, Set<K>> inverseMap = new HashMap<V, Set<K>>();

		for (V value : map.values()) {

			Set<K> temp = new HashSet<K>();

			for (K k : map.keySet()) {
				if (value.equals(map.get(k))) {
					temp.add(k);
				}
			}

			inverseMap.put(value, temp);
		}
		return inverseMap;

	}

	public static <K, V> Map<V, K> inverseBijection(Map<K, V> map) {
		Map<V, K> inverseMap = null;
		if (isOneOnOne(map)) {
			inverseMap = new HashMap<V, K>();

			for (K k : map.keySet()) {
				inverseMap.put(map.get(k), k);
			}
		}
		return inverseMap;
	}

	public static <K, V, W> boolean compatible(Map<K, V> f, Map<V, W> g) {

		if (!f.isEmpty() && g.isEmpty()) return false;

		for (V v : g.keySet()) {
			for (V w : f.values()) {
				if (!v.equals(w) && !w.equals(v)) {
					return false;
				}
			}
		}
		return true;
	}

	public static <K, V, W> Map<K, W> compose(Map<K, V> f, Map<V, W> g) {
		Map<K, W> composedMap = new HashMap<>();

		if (compatible(f,g)){
			for (K k : f.keySet()) {
				composedMap.put(k, g.get(k));
			}
		}
		return composedMap;
	}


	//	@Override
	//	public boolean equals(Object o){
	//		if(o instanceof String){
	//			String toCompare = (String) o;
	//			return this.equals(toCompare);
	//		}
	//		return false;
	//	}

}
