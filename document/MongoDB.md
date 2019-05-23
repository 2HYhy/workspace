## mongodb特点 
基于分布式文件存储的非关系型数据库。   
优点:
> 文档结构的存储方式，获取数据便捷         
> 支持大容量存储 
> 自带缓存功能
> 自动分片(突破单节点数据库服务器的I/O能力限制，解决数据库扩展性问题)             

缺点:
> 不支持事务(指作为单个逻辑工作单元执行的一系列操作)操作       
> 无法进行关联表查询   
> 占用空间过大     
> 删除数据集合后空间不会自动释放，需手动调用db.repairDatabase()     

| database  |  table  | row  | column  | index   |
|-----------|---------|------|---------|---------|
| database |  collection |  document  |   field   |  index  |     

> cd /Documents/tools/mongodb/bin
>- 先进入MongoDB的安装目录下

> ./mongod (mac)    
> mongod.exe (windows)
>- 1. 启动mongodb服务，使用默认的数据库目录/data/db  

> ./mongod --dbpath /Users/hyh/xxxxx(绝对路径)   
>- 1. 启动mongodb服务，指定数据库目录 

> ./mongo     
> mongo.exe
>- 2. 重新打开一个窗口，连接mongodb服务器  

> mongodb://localhost 
>- 连接本地数据库服务器，端口默认  (windows) 

> mongodb://<用户名>:<密码>@<主机地址>/<数据库名>   
>- 使用用户名/密码连接到指定服务器的某个数据库 (windows) 

> 访问http://localhost:27017, 显示It looks like you are trying to access MongoDB over HTTP on the native driver port 即成功连接。

> show dbs 
>- 查看所有数据库, admin,local两个数据库是系统自带的 

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
>- 小于条件查询(gt>, lte<=, ne!=)

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

> db.CollectionName.count()
>- 返回集合的数据总行数

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
```java
//以appId分组，去掉重复的uid
db.getCollection('ucopUserLogDO').aggregate([ 
{ $group: {"_id": { "appId" : "$appId", "uid": "$uid"}}},
{ $group: {"_id": { "appId" : "$_id.appId"}, counts : { $sum : 1 }}}
])

//以appId分组，未去掉重复的uid(以下2种方式的返回形式略有不同)
db.getCollection('ucopUserLogDO').aggregate([
{"$group": {"_id": {"appId": "$appId"},counts : {"$sum": 1}}}
])
db.getCollection('ucopUserLogDO').aggregate([
{"$group": {"_id": "$appId",counts : {"$sum": 1}}}
])

//以appId+uid分组
db.getCollection('ucopUserLogDO').aggregate([
{"$group": {"_id": {"appId": "$appId","uid": "$uid"},counts : {"$sum": 1}}}
])

//以uid分组，返回每组中createTime最大的记录
db.getCollection('ucopUserLogDO').aggregate([{$group : {_id : "$uid", createTime : {$max : "$createTime"}}}])

//fieldName为表中的字段，returnName为返回结果中展示的字段名
db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName1: {$max : "$fieldName"}}}, {$group: {_id: null, returnName2: {$max: "$returnName1"}}}])

//指定只返回appId和uid两列，_id若不设为0，是默认会返回的
db.getCollection('ucopUserLogDO').aggregate({"$project": {"appId": 1, "uid": 1, "_id": 0}})
db.getCollection('ucopUserLogDO').find({}, {appId: 1, uid: 1, _id: 0})

//以appId分组，返回没每组下的所有uid集合
db.getCollection('ucopUserLogDO').aggregate([{$group: {_id: {"appId": "$appId"}, uids: {$addToSet: "$uid"}}}]) 

//先满足时间段的条件，再进行分组
db.getCollection('ucopUserLogDO').aggregate([{"$match": {"createTime": {"$gte": new Date("2018-11-14"), "$lte": new Date("2018-11-16")}}},{"$group": {"_id": "$appId", "counts": {"$sum" : 1}}}])  

//返回每组第一个文档
db.getCollection('ucopUserLogDO').aggregate([{$group: {_id: "$uid", results: {$first: "$createTime"}}}])

//返回每组最后一个文档
db.getCollection('ucopUserLogDO').aggregate([{$group: {_id: "$uid", results: {$last: "$loginTime"}}}])  

//select * from user_login_log where uid in [xxx, xxx, xxx]
// "$nin"  =  no in ,  "$ne"  =   !=
db.CollectName.find({"uid": {"$in" : ["c7d6ad0c2ea34719", "6817f364aeb44ab1", "f020cbf41d7b4e51"] }})

//select * from table where uid like concat("%", "6817", "%")
db.CollectName.find({"uid"  :  /6817/})
db.CollectName.find({"uid" : {"$regex" : "6817"}})

//必须满足all中的所有值，而in是满足其中一个即可
db.ConnectNam.find({"uid":  {"$all": ["6817f364aeb44ab1", "c7d6ad0c2ea34719"]}})  

 //判断某个字段是否存在
db.CollectName.find({"uid": {"$exists" : true}}) 

//返回去重的所有uid
db.getCollection('ucopUserLogDO').distinct("uid")

//返回所有去重，且满足时间段条件的uid
db.getCollection('ucopUserLogDO').distinct("uid", {"createTime": {$gte: new Date('2018-11-15')}})

//分页返回
db.getCollection('ucopUserLogDO').find({"uid": '494a79a64aad4823'}).skip(0).limit(2)
```

### MongoDB与日期时间相关查询
```java
db.CollectName.find({"createTime": { "$gte": ISODate("2017-11-02 06:09:00.000Z"), "$lte": ISODate("2017-11-02 06:09:00.000Z") }})    

db.CollectName.find({"createTime": {"$gte": new Date("2018-11-15"), "$lte": new Date("2018-11-17")}})
                                            
db.CollectName.find({"loginTime": {"$gte": new Date("2018-01-18"), "$lte": new Date("2018-01-21")}}).count()

db.CollectName.find({"loginTime":{"$gte": new Date(2018,00,18), "$lte": new Date(2018,00,21)}}).count()                                        
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
