### Prometheus:(pull型时间序列数据库)
Mac下载的发行版为darwin版
1. 启动prometheus
```java
tar -zxvf prometheus-2.2.1.darwin-amd64.tar.gz  //解压
cd <prometheus目录>
./prometheus  或者 ./prometheus --config.file=prometheus.yml  //运行
```
2. 访问 `http://localhost:9090` 即进入管理界面
3. 修改配置文件prometheus.yml
```java
//在最后添加
- job_name: mysql
  static_configs:
    - targets: [‘127.0.0.1:9104’]  //mysql对应的ip，mysqlId_exporter的默认监听端口
```
4. 重启prometheus,访问 `http://localhost:9090/targets`
```java
http://localhost:9090/metrics  //status是up

http://127.0.0.1:9104/metrics  //status是down，因为还没配置mysqlId_exporter
```
5. 退出为`exit`

#### 安装mysqlId_exporter
同prometheus的地址和版本
1. 配置文件.my.cnf
```java
vi .my.cnf
//内容为:
[client]
user=root
password=123456
```
> 如果要连接新的mysql账户，需要先连接mysql创建用户并授权
```java
//查看用户授予的权限
mysql> show grants for 'user-center';

mysql> GRANT REPLICATION CLIENT,PROCESS ON *.* TO 'apple'@'localhost' identified by '123456';
mysql> GRANT SELECT ON *.* TO ‘root’@‘localhost';
```
2. 启动
```java
./mysqld_exporter -config.my-cnf=".my.cnf"
```
3. 访问 `http://localhost:9104`
4. 刷新 `http://localhost:9090/targets`, status由down变为up

### Grafana
配置文件地址:
/usr/local/opt/grafana/share/grafana/conf/default.ini
/usr/local/etc/grafana/grafana.ini
1.安装
```java
brew update    //若无反应则执行:cd "$(brew --repo)" && git fetch && git reset --hard origin/master && brew update
brew install grafana
```
2. 启动：
`brew services start grafana`
3. 访问`http://localost:3000`, 进入管理界面，默认账号密码admin+admin
4. 配置数据源(name=influxdb / Prometheus)
5. 新建或引入dashboard即可
6. 停止为`brew services stop grafana`

### influxDB:(push型时序数据库)
安装位置：/usr/local/opt/influxdb
配置文件地址：/usr/local/etc/influxdb.conf
1. 安装启动
```java
brew update
brew install influxdb
brew services start influxdb
```
2. 访问 `http:localhost；8083` 进入管理界面 或者 命令行输入 `influx` 进入命令行界面
3. 增删改查
```java
//命令行格式：
//tag的值有空格时用\+空格，tag的value必须是String，field的value必须是数字(可以是Integer<数字后+i>，float，boolean)
insert <表名>[,<tag-key>=<tag-value>...] <field-key>=<field-value>[,<field2-key>=<field2-value>...] [unix-nano-timestamp]
//url格式：
curl -i -XPOST 'http://127.0.0.1:8086/write?db=<数据库名>’ --data-binary ‘<表名>[,<tag-key>=<tag-value>...] <field-key>=<field-value>[,<field2-key>=<field2-value>...] [unix-nano-timestamp]'

//url格式查询
curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=<数据库名>” --data-urlencode “<sql查询语句>”

//删除表
delete from <表名>

//查看数据保留策略
show retention policies on <数据库名>
//创建,必须双引号
create retention policy "<策略名>" on "<数据库名>" duration 4d replication 1 default  
//修改，单位可以是w, h, d 
alter retention policy "<策略名>" on "<数据库名>" duration 5d default 
//删除
drop retention policy "<策略名>" on "<数据库名>"

//新建用户并开通权限,注意单引号和双引号
create user “<用户名>” with password ‘<password>’ with all privileges  
//删除用户
drop user "<用户名>"
```
4. 退出用 `exit`





