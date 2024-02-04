package net.uniquepixels.coreapi;

import java.util.HashMap;

public record MapPaginator<K, V>(HashMap<K, V> data) {

    public HashMap<Integer, HashMap<K, V>> maxSizePerPage(int size) {

        HashMap<Integer, HashMap<K, V>> map = new HashMap<>();

        Integer step = 0;

        for (K k : data.keySet()) {
            // if nothing exists
            if (!map.containsKey(step)) {
                HashMap<K, V> value = new HashMap<>();
                value.put(k, data.get(k));
                map.put(step, value);

                continue;
            }

            var hashMap = map.get(step);

            // if the list is "full" by the limit provided in header
            if (hashMap.size() == size) {

                step++;

                HashMap<K, V> value = new HashMap<>();
                value.put(k, this.data.get(k));
                map.put(step, value);

                continue;
            }

            hashMap.put(k, this.data.get(k));
            map.put(step, hashMap);

        }

        return map;
    }
}
