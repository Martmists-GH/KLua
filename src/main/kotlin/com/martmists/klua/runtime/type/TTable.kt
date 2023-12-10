package com.martmists.klua.runtime.type

class TTable : TValueWithMeta<Map<TValue<*>, TValue<*>>>() {
    override val type = ValueType.TABLE

    private val map = mutableMapOf<TValue<*>, TValue<*>>()
    override val value: Map<TValue<*>, TValue<*>> = map

    operator fun set(key: TValue<*>, value: TValue<*>) {
        for (k in this.value.keys) {
            if (k == key) {
                map[k] = value
                return
            }
        }
        map[key] = value
    }

    operator fun set(key: String, value: TValue<*>) = set(TString(key), value)

    operator fun get(key: TValue<*>): TValue<*> {
        return map[key] ?: TNil
    }

    operator fun get(key: String): TValue<*> = get(TString(key))
}