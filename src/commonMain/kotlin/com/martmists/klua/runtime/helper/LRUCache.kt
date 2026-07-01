package com.martmists.klua.runtime.helper

class LRUCache<K : Any, V>(private val cacheSize: Int) {
    private val cache = mutableMapOf<K, V>()
    private val useQueue = mutableListOf<K>()

    fun markUsed(key: K) {
        if (key in cache) {
            useQueue.remove(key)
        } else if (useQueue.size >= cacheSize) {
            val removed = useQueue.removeFirst()
            cache.remove(removed)
        }

        useQueue.add(key)
    }

    fun clear() {
        useQueue.clear()
        cache.clear()
    }

    fun getOrPut(key: K, compute: () -> V): V {
        markUsed(key)
        return cache.getOrPut(key, compute)
    }
}
