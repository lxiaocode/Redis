# Redis
Redis相关资料

**Redis是什么东西？**

Redis是一个开源的、基于内存且可以持久化到磁盘的键值对数据库。

**Redis有什么特点？**

- 高性能的内存数据库，且支持持久化到磁盘。
- 支持发布/订阅模式的消息系统。
- 支持主从复制备份数据。
- 支持Redis哨兵和自动分区提供高可用。
- 原子性操作命令，且支持事务处理。
- 提供丰富的数据类型。
- ......

**Redis有什么使用场景？**

- 数据缓存

  对频繁查询且变动不频繁的数据进行缓存。

- 消息队列

  使用Redis提供的发布/订阅消息系统实现消息队列。

- 计数器

  例如：统计点击数、点赞数，Redis的操作命令具有原子性，可以避免并发问题。

- 热点数据

  对于需要频繁更新的热点数据，例如：用户最近浏览记录。

- 分布式锁

  由于Redis的操作命令具有原子性，可以避免并发问题。

**Redis的数据类型：**

| 数据类型      | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| String 字符串 | 最基本的数据类型，能存储任何类型的字符串。                   |
| Hash 哈希     | 存储String类型的键值对集合，常用于存储结构化的数据。         |
| List 列表     | 存储String类型的双向列表。                                   |
| Set 集合      | 存储String类型的无序的、自动去重的集合。                     |
| Zset 有序集合 | 存储String类型的有序序的、自动去重的集合。每个元素会关联一个用于排序的分数。 |

