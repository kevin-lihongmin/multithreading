package com.kevin.multithreading.threadlocal;

import com.esotericsoftware.kryo.Kryo;

public class DeepCopyInheritableThreadLocal extends InheritableThreadLocal {

    final Kryo kryo = new Kryo();

    @Override
    protected Object childValue(Object o) {
        // 将Object对象进行深拷贝并返回
        return kryo.copy(0);
    }
}
