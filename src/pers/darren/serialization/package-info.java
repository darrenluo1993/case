/**
 * <pre>
 * Java序列化与反序列化
 * 序列化：把对象转换为字节序列的过程称为对象的序列化
 * 反序列化：把字节序列恢复为对象的过程称为对象的反序列化
 *
 * 1、什么时候需要用到序列化和反序列化呢?
 * 当我们只在本地JVM里运行下Java实例, 这个时候是不需要什么序列化和反序列化的,
 * 但当我们需要将内存中的对象持久化到磁盘, 数据库中时, 当我们需要与浏览器进行交互时, 当我们需要实现RPC时, 这个时候就需要序列化和反序列化了.
 *
 * 参考文档：https://mp.weixin.qq.com/s/Q10k_Kld8JHLl6Rv8q46jA
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 8/5/22 4:50 PM
 */
package pers.darren.serialization;