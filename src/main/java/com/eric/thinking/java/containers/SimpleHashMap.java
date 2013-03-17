package com.eric.thinking.java.containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

	static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if (bucket == null)
				continue;
			for (MapEntry<K, V> pair : bucket) {
				set.add(pair);
			}
		}
		return set;
	}

	public V put(K key, V value) {
		V oldValue = null;
		int index = findIndex(key.hashCode());
		LinkedList<MapEntry<K, V>> bucket = findBucket(index);
		MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
		oldValue = addToBucket(bucket, entry);
		return oldValue;
	}

	public V get(Object key) {
		int index = findIndex(key.hashCode());
		LinkedList<MapEntry<K, V>> bucket = findBucket(index);
		if (bucket == null)
			return null;
		for (MapEntry<K, V> e : bucket) {
			if (e.getKey().equals(key)) {
				return e.getValue();
			}
		}
		return null;
	}

	private V addToBucket(LinkedList<MapEntry<K, V>> bucket,
			MapEntry<K, V> entry) {
		V oldValue = null;
		ListIterator<MapEntry<K, V>> iterator = bucket.listIterator();
		while (iterator.hasNext()) {
			MapEntry<K, V> entryInBucket = (MapEntry<K, V>) iterator.next();
			if (entryInBucket.getKey().equals(entry.getKey())) {
				oldValue = entryInBucket.getValue();
				entryInBucket.setValue(entry.getValue());
				return oldValue;
			}
		}
		bucket.add(entry);
		return oldValue;
	}

	private LinkedList<MapEntry<K, V>> findBucket(int index) {
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		if (bucket == null) {
			bucket = new LinkedList<MapEntry<K, V>>();
		}
		return bucket;
	}

	private int findIndex(int KeyCode) {
		return Math.abs(KeyCode) % SIZE;
	}
}
