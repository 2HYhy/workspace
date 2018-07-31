## mongodb 
基于分布式文件存储的非关系型数据库。
优点:
> 文档结构的存储方式，获取数据便捷             
> 支持大容量存储 
> 自带缓存功能
> 自动分片(突破单节点数据库服务器的I/O能力限制，解决数据库扩展性问题)      
> 海量数据下，性能优越       

缺点:
> 不支持事务(指作为单个逻辑工作单元执行的一系列操作)操作       
> 无法进行关联表查询   
> 占用空间过大     
> 删除数据集合后空间不会自动释放，需手动调用db.repairDatabase()     

| database  |   table | row  | column  | index  |
|-----------|---------|------|---------|---------|
| database |  collection |  document  |   field   |  index  |     

> ./mongod (mac)    
> mongod.exe (windows)
>- 启动mongodb服务，使用默认的数据库目录/data/db  

> ./mongod --dbpath /Users/hyh/xxxxx(绝对路径)   
>- 启动mongodb服务，指定数据库目录 

> ./mongo     
> mongo.exe
>- 通过shell连接mongodb服务器  

> mongodb://localhost (windows)
>- 连接本地数据库服务器，端口默认  

> mongodb://<用户名>:<密码>@<主机地址>/<数据库名>  
>- 使用用户名/密码连接到指定服务器的某个数据库  

> show dbs 
>- 查看所有数据库   

> db  
>- 显示当前数据库

> db.dropDatabase() 
>- 删除当前数据库

> show tables
>- 查看数据库的所有集合

> db.createCollection(tableName)  
>- 创建集合（表）  

> db.TableName.drop()
>- 删除数据库的指定集合

### MongoDB的增删改查
> db.CollectName.insert({key1:value1,key2:value2})   
>- 向数据库集合中插入文档  

> db.CollectName.find()
>- 查询文档

> db.CollectName.find().pretty()
>- 查询文档，并将结果格式化

> db.CollectName.findOne()
>- 只返回一个文档  

> db.CollectName.find( {key:value} )
>- 条件查询 

> db.CollectName.find({key1:value1, key2:value2})
>- and 条件查询 

> db.CollectName.find( {key: {$lt:IntNum} } )  
>- 小于条件查询(gt>,lte>=,ne!=)

>db.CollectName.find( {$or:[ {key1:value1},{key2:value2} ]} )  
>- or 条件查询 

> db.CollectName.find().limit(Num) 
>- 返回指定数量的文档

> db.TableName.update( {oldkey:oldvalue},{$set:{newkey:newvalue}},{upsert:boolean,multi:boolean})  
>- 更新集合中的文档，upsert表示无该记录时是否插入，multi表示有多条记录符合条件时是否全都更新，默认都为false 

> db.CollectionName.update( {oldkey:oldvalue},{$set:{newkey:newvalue}} )   
>- 只更新第一条记录  
 
> db.CollectName.remove( {key:value},{justOne:boolean})
>- 删除文档，justOne表示是否只删除一个符合条件的文档

> db.CollectName.find().sort( {key:1} )
>- 按指定字段升序排序，-1为降序  

> db.CollectionName.find( {key:{$type:2}} )  
>- 查找集合中指定key类型为String的记录  

> db.CollectionName.find().skip(10).limit(100)  
>- 获取从第10条记录后的100条记录   

> db.CollectionName.find( {key:{$in:[x,x,x]}} )  
>- 获取key在指定集合中的记录  

> db.CollectionName.find( {},{key1:1, _id:0})  
>- 只返回key1列，0表示不显示 1表示显示，_id不同于其他咧，如果不显式设为0，就会返回  

> db.CollectionName.find( {},{key1:1, key2:1 _id:0}).limit(3).skip(1)  
>- 跳过第一列后，返回三条记录，只返回key1,key2两列  

> db.CollectionName.remove() 
>- 清空集合

### MongoDB的索引
> db.CollectName.ensureIndex( {key : 1} )
>- 按升序为指定字段创建单个索引   

> db.CollectName.ensureIndex({key1 : 1, key2 : -1})
>- 为指定字段创建复合索引 

> db.CollectName.dropIndex("IndexName")
>- 删除指定名字的索引,名字一般为:字段_1(-1)

> db.userLoginLogDO.dropIndexes()
>- 删除全部索引 

> db.CollectName.getIndexes()
>- 查看索引 

> db.CollectName.find({}).explain()
>- 获取执行计划，性能分析
### MongoDB聚合查询
> $project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。  
> $match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。  
> $limit：用来限制MongoDB聚合管道返回的文档数。  
> $skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。   
> $group：将集合中的文档分组，可用于统计结果。  
> $sort：将输入文档排序后输出。 

```java
//返回结果字段名称：returnName， 表中属性字段名称：fieldName

db.CollectionName.count() 
db.runCommand({"distinct" : "CollectName", "key" : "fieldName"})  // 必须指定集合名和要区分的字段

db.CollectionName.aggregate([{"$group" : {"_id": null, "returnName":{"$sum" : 1}}}])  //不分组
db.CollectionName.aggregate([{"$group" : {"_id" : null, "total" :{"$sum" : "$quantity"}}}])  
//select sum(quantity) as total from tableName

db.CollectionName.aggregate([{"$group" : {"_id" : "$fieldName", "returnName": {"$sum" : 1}}}])  //分组

db.CollectionName.aggregate([{$group : {_id : "$fieldName", returnName : {$max : "$fieldName"}}}])
db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName1: {$max : "$fieldName"}}}, {$group: {_id: null, returnName: {$max: "$returnName1"}}}])

db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName: {$push: "$fieldName"}}}])   

db.CollectionName.aggregate({"$project" : {"key1" : 1, "key2" : 1, "_id" : 0}})
//结果中只返回指定的列
db.CollectionName.find({}, {key1: 1, key2:1, _id:0})

db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName: {$push: {time: "$fieldName", returnName: "$fieldName"}}}}])
//将指定字段的值添加到数组中

db.collectName.aggregate([{$group: {_id: "$uid", sets: {$addToSet: "$loginTime"}}}])  
//将指定字段的值添加到数组中，不允许重复值

db.collectName.aggregate([{$group: {_id: "$uid", results: {$first: "$loginTime"}}}]) 
//返回每组第一个文档
db.collectName.aggregate([{$group: {_id: "$uid", results: {$last: "$loginTime"}}}])  
//返回每组最后一个文档

db.CollectName.find({"uid": {"$in" : ["c7d6ad0c2ea34719", "6817f364aeb44ab1", "f020cbf41d7b4e51"] }})
select * from user_login_log where uid in [xxx, xxx, xxx]
// "$nin"  =  no in ,  "$ne"  =   !=

db.CollectName.find({"uid"  :  /6817/})
db.CollectName.find({"uid" : {"$regex" : "6817"}})
select * from tableName where uid like concat("%", "6817", "%")  //正则表达式

db.ConnectNam.find({"uid"  :  {"$all" : ["6817f364aeb44ab1", "c7d6ad0c2ea34719"]}})  
//必须满足all中的所有值，而in是满足其中一个即可

db.CollectName.find({"uid"  :  {"$exists" : true}})  //判断某个字段是否存在
```

### MongoDB与日期时间相关查询
```java
db.CollectName.find({"fieldName" : { "$gte" : ISODate("2017-11-02 06:09:00.000Z")  
, "$lte" : ISODate("2017-11-02 06:09:00.000Z") }})    

db.CollectName.find({"fieldName" : {"$gte" : new Date("2017-11-02"), "$lte" : new Date("2017-11-06")}})

db.CollectName.aggregate([{"$match" : {"fieldName" : {"$gte" : new Date("2017-11-02"), "$lte" : new Date("2017-11-10")}}},
                                             {"$group" : {"_id" : "$fieldName", "returnName" : {"$sum" : 1}}}])  
                                            
db.CollectName.find({"loginTime":{"$gte":new Date("2018-01-18"),"$lte": new Date("2018-01-21")}}).count()

db.CollectName.find({"loginTime":{"$gte":new Date(2018,00,18),"$lte": new Date(2018,00,21)}}).count()

db.getCollection('CollectName').find({"loginTime":{"$gte":ISODate("2018-01-17T16:00:00.000Z"),"$lte": ISODate("2018-01-21T15:59:59.000Z")}}).count()                                            
```   

### mongoTemplate, java版：
```java
public UserLoginLogDO func1(String param) {
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("uid").is(param));
        List<UserLoginLogDO> loginLogDOList = mongoTemplate.find(new Query(base).with(new Sort(Sort.Direction.DESC, "loginTime")).limit(1), UserLoginLogDO.class);
        if (loginLogDOList.size() > 0) {
            return loginLogDOList.get(0);
        }
    }

 public String func2(String param1, String param2) {
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("deviceSn").is(param1), Criteria.where("macAddress").is(param2));
        List<UserLoginLogDO> loginLogDOList = mongoTemplate.find(new Query(base).with(new Sort(Sort.Direction.DESC, "loginTime")).limit(1), UserLoginLogDO.class);
        if (loginLogDOList.size() > 0) {
            String uid = loginLogDOList.get(0).getUid();
            UserDO userDO = userMapper.findUserByUid(uid, livemode);
            return userDO.getPhone();
        }
    }

 public int func3(String start, String end, Integer livemode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        Date eDate = null;
        try {
             sDate = sdf.parse(start);
             eDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("loginTime").gte(sDate), Criteria.where("loginTime").lte(eDate));
        Aggregation agg = newAggregation(match(base), group("uid"));
        AggregationResults<UserLoginLogDO> temp = mongoTemplate.aggregate(agg, UserLoginLogDO.class, UserLoginLogDO.class);
        List<UserLoginLogDO> results = temp.getMappedResults();
        if (results.size() > 0) {
            return results.size();
        }
    }

 public String func4(String stime, String etime, String uid, Integer livemode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = sdf.parse(stime);
            eDate = sdf.parse(etime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("loginTime").gte(sDate), Criteria.where("loginTime").lte(eDate), Criteria.where("uid").is(uid));
        UserLoginLogDO userLoginLog = mongoTemplate.findOne(new Query(base).with(new Sort(Sort.Direction.DESC, "loginTime")).limit(1), UserLoginLogDO.class);
        if (userLoginLog != null) {
            return String.valueOf(userLoginLog.getLoginTime());
        }
    }
```
