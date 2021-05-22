# Mybatis与Redis结合

#### 介绍
Mybatis结合redis，从数据库查询到的数据会存入redis，下次直接从redis读取

需要先配置自己的数据库和redis，sql数据结构在resource中，先运行

#### 软件架构
```java
/**
     * 采用缓存的方式，以后查询相同数据就可以直接使用
     *
     * cacheNames/value-----指定缓存组件的名字，可以指定为数组---{"",""},保存多个缓存
     * key：使用SqEL设置key值，默认使用传入的参数作为key值
     * keyGenerator：指定key生成器，该属性和上面的key二选一适用
     * condition：指定符合条件才缓存，例如cadition("#id>0")，id>0才生效
     * unless：与上面的condition相反，符合条件就 不生效F
     * sync：是否使用异步
     * cacheManager：指定缓存管理器
     *
**/
```

## 新增
resource设置H5页面（方便查看效果）
