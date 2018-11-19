package util.collection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class CollectionUtil {
    private CollectionUtil(){}

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return (collection != null && collection.size() > 0);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return (map != null && map.size() > 0);
    }

    public static <E> List<E> arrayToList(E[] array) {
        if (array == null || array.length == 0) return new ArrayList<E>(0);
        List<E> list = new ArrayList<E>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
        return new EnumerationIterator<E>(enumeration);
    }

    private static class EnumerationIterator<E> implements Iterator<E> {

        private Enumeration<E> enumeration;

        public EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = enumeration;
        }

        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        public E next() {
            return this.enumeration.nextElement();
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public static <F, T> List<T> transformList(
            List<F> fromList, Function<? super F, ? extends T> function) {
        if (fromList == null) {
            return null;
        } else if (fromList.size() == 0) {
            return Lists.newArrayList();
        }
        List<T> resultList = Lists.newArrayListWithCapacity(fromList.size());
        for (F fromItem : fromList) {
            T item = function.apply(fromItem);
            if (item != null) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    public static <K, V1, V2> Map<K, V2> transformMapValue(
            Map<K, V1> fromMap, Function<? super V1, V2> function) {
        if (fromMap == null) {
            return null;
        } else if (fromMap.size() == 0) {
            return Maps.newHashMap();
        }
        Map<K, V2> resultMap = Maps.newHashMapWithExpectedSize(fromMap.size());
        for (Map.Entry<K, V1> fromEntry : fromMap.entrySet()) {
            V2 value = function.apply(fromEntry.getValue());
            if (value != null) {
                resultMap.put(fromEntry.getKey(), value);
            }
        }
        return resultMap;
    }

    public static <F, K, V> Map<K, V> transformListToMap(
            List<F> fromList, Function<? super F, Map.Entry<K, V>> function) {
        if (fromList == null) {
            return null;
        } else if (fromList.size() == 0) {
            return Maps.newHashMap();
        }
        Map<K, V> resultMap = Maps.newHashMapWithExpectedSize(fromList.size());
        for (F fromItem : fromList) {
            Map.Entry<K, V> item = function.apply(fromItem);
            if (item != null) {
                resultMap.put(item.getKey(), item.getValue());
            }
        }
        return resultMap;
    }

    public static <T> void addWithoutNull(Collection<T> collection, T item) {
        if (collection != null && item != null) {
            collection.add(item);
        }
    }

    public static <K, V> List<V> getListFromMap(Map<K, V> dataMap, List<K> keyList) {
        if (isEmpty(dataMap) || isEmpty(keyList)) {
            return Lists.newArrayList();
        }
        List<V> resultList = Lists.newArrayListWithCapacity(keyList.size());
        for (K key : keyList) {
            V value = dataMap.get(key);
            if (value != null) {
                resultList.add(value);
            }
        }
        return resultList;
    }
}

