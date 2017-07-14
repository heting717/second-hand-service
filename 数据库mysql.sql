CREATE DATABASE school_2 CHARACTER SET utf8;
USE school;
-- 用户信息表：用户名，密码，学校,入学时间，离校时间，电话号码，邮箱，头像图片路径
CREATE TABLE userTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	phone VARCHAR(20), -- 电话号码
	userName VARCHAR(10), -- 用户名
	userPassword VARCHAR(20), -- 密码
	school VARCHAR(20), -- 学校
	
	-- adress VARCHAR(20), -- 地址
)
ALTER TABLE userTable DROP COLUMN outTime;-- 删除离校时间项
ALTER TABLE userTable DROP COLUMN imagePath;-- 删除图片路径项
DROP TABLE userTable;


ALTER TABLE userTable ADD COLUMN inTime VARCHAR(20);-- 入学时间
ALTER TABLE userTable ADD COLUMN outTime VARCHAR(20);-- 添加离校时间
ALTER TABLE userTable ADD COLUMN imagePath VARCHAR(100);-- 添加图片路径
ALTER TABLE userTable ADD COLUMN mail VARCHAR(20);-- 添加邮箱项

ALTER TABLE userTable DROP COLUMN imagePath ;-- 添加图片路径


SELECT *FROM userTable;


INSERT INTO userTable(phone,userName,userPassword,school,intime,outTime) VALUES('13511116666','张三','123321','四川大学','2014年9月','2018年6月'); -- 注册保存数据
SELECT *FROM userTable WHERE phone=?;-- 查询电话号码，判断用户是否存在
SELECT *FROM userTable WHERE phone=? AND userPassword=? -- 根据电话号码和密码查找用户，登陆
SELECT *FROM userTable WHERE id = ?;-- 根据id查找
UPDATE userTable SET phone="13511116666",userName="张三",school="电子科技大学",intime="2014",outTime="2018", imagePath="co120422145302-7.jpg" WHERE id = 2;-- 修改个人信息
UPDATE userTable SET userPassword=? WHERE id=?;-- 修改密码
UPDATE userTable SET phone=? WHERE id = ?;-- 修改电话号码
UPDATE userTable SET outTime=2018;


-- 商品主类别信息表：类别名
CREATE TABLE mainType(
	id INT PRIMARY KEY AUTO_INCREMENT,
	mainTypeName VARCHAR(10) -- 类别名
)
SELECT *FROM mainType;
INSERT INTO mainType(mainTypeName) VALUES('');-- 添加保存数据
SELECT *FROM mainType WHERE id=?;-- 根据id查询主类别
DELETE FROM mainType WHERE id=?;-- 根据id删除
UPDATE mainType SET mainTypeName=? WHERE id = ?;



-- 次类别表，住类别id，次类别名
CREATE TABLE typeTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	mainType_id INT,-- 外键
	typeName VARCHAR(20) -- 次类别名

)
SELECT *FROM typeTable;

INSERT INTO typeTable(mainType_id,typeName) VALUES(1,'电脑显示屏');-- 添加保存数据
SELECT *FROM typeTable WHERE id=?;-- 根据id查类别
SELECT *FROM typeTable WHERE mainType_id=?;
DELETE FROM typeTable WHERE id=?;-- 根据id删除
UPDATE typeTable SET mainType_id=?,typeName=? WHERE id = ?;

SELECT t.id,mt.mainTypeName,t.typeName FROM typeTable t,mainType mt WHERE t.mainType_id=mt.id;
-- 主类别表和次类别表的约束
ALTER TABLE typeTable ADD CONSTRAINT fk_mainType_id FOREIGN KEY (mainType_id) REFERENCES mainType(id);


-- 出售信息表：类别，物品名称，品牌，购买时间，购买价格，出售价格，出售数量，物品描述，上传时间，用户id，出售状态，图片路径
CREATE TABLE sellTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	type_id INT , -- 类别id，外键
	sellName VARCHAR(20),-- 物品名称
	brandName VARCHAR(20),-- 品牌
	buyTime VARCHAR(20), -- 购买时间
	buyPrice INT, -- 购买价格
	sellPrice INT, -- 出售价格
	sellCount INT,-- 销售数量
	goodsDescribe VARCHAR(100), -- 物品描述
	upTime DATETIME -- 上传时间
	
)
DROP TABLE sellTable;


SELECT *FROM sellTable; 

ALTER TABLE sellTable ADD COLUMN userId INT;-- 添加用户id项
ALTER TABLE sellTable ADD COLUMN sellStatus INT DEFAULT 0; -- 添加出售状态项，0为出售，1已经出售
ALTER TABLE sellTable ADD COLUMN imagePath VARCHAR(100);-- 添加图片路径项




	
	
SELECT st.id,st.type_id,t.typeName,st.sellName,st.brandName,st.buyTime,st.buyPrice,st.sellPrice,st.sellCount,st.goodsDescribe,st.upTime FROM sellTable st,typeTable t WHERE st.type_id=t.id AND st.userId=?;-- 获取所有数据

UPDATE sellTable SET userId=2;

-- 出售信息表和商品类别信息表的约束关系
ALTER TABLE sellTable ADD CONSTRAINT fk_sell_type_id FOREIGN KEY (type_id) REFERENCES typeTable(id);
-- 出售表和用户表的约束关系
ALTER TABLE sellTable ADD CONSTRAINT fk_sell_user_id FOREIGN KEY (userId) REFERENCES userTable(id);


INSERT INTO sellTable(type_id,brandName,buyTime,buyPrice,sellPrice,sellCount,goodsDescribe,upTime) VALUE(); 
UPDATE sellTable SET type_id=?,brandName=?,buyTime=?,buyPrice=?,sellPrice=?,goodsDescribe=?,upTime=? WHERE id=?;
UPDATE sellTable SET type_id=4,sellName="无线鼠标",brandName="mm",buyTime="2016-1",buyPrice=45,sellPrice=20,sellCount=2,goodsDescribe="好" WHERE id=11;

UPDATE sellTable SET sellCount=1 WHERE id=11;

DELETE FROM sellTable WHERE id=7 AND id=10;
SELECT *FROM sellTable WHERE id=?; 
SELECT st.id,st.userId,u.mail,st.type_id,st.sellName,st.brandName,st.buyPrice,st.sellPrice,st.sellCount,st.goodsDescribe,st.sellStatus FROM sellTable st,userTable u WHERE st.userId=u.id AND st.id=12;-- 查询出邮箱
SELECT st.*,u.mail FROM sellTable st,userTable u WHERE st.userId=u.id AND st.id=12;-- 查询出邮箱

SELECT *FROM sellTable WHERE type_id=6;
SELECT COUNT(*) FROM sellTable WHERE type_id=6;


SELECT *FROM sellTable WHERE sellCount>0 AND type_id=6;-- 根据出售数量查找
-- 根据出售状态查找
SELECT st.id,st.type_id,t.typeName,st.sellName,st.brandName,st.buyTime,st.buyPrice,st.sellPrice,st.sellCount,st.goodsDescribe,st.upTime FROM sellTable st,typeTable t WHERE st.type_id=t.id AND st.userId=? AND st.sellStatus=?;



-- 求购信息表：类别，物品名称，价格要求，求购物品要求，上传时间，求购状态
CREATE TABLE buyTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userId INT,-- 用户id
	type_id INT,-- 类别
	brandName VARCHAR(20),-- 物品名称
	priceRequest  VARCHAR(20), -- 价格要求
	requireDescribe VARCHAR(100), -- 求购物品要求
	upTime DATETIME 
)

SELECT *FROM buyTable;
ALTER TABLE buyTable ADD COLUMN buyStatus INT DEFAULT 0;-- 添加想购状态，0未解决，1解决 

DROP TABLE buyTable;

-- 求购信息表和商品类别信息表的约束关系
ALTER TABLE buyTable ADD CONSTRAINT fk_buy_type_id FOREIGN KEY (type_id) REFERENCES typeTable(id);
ALTER TABLE buyTable ADD CONSTRAINT fk_buy_user_id FOREIGN KEY (userId) REFERENCES userTable(id);


-- 添加保存数据
INSERT INTO buyTable(userId,type_id,brandName,priceRequest,requireDescribe,upTime) VALUES(?,?,?,?,?,?)
-- 获取所有数据，不同人显示的不同
SELECT b.type_id,t.typeName,b.brandName,b.priceRequest,b.requireDescribe,b.upTime FROM buyTable b,typeTable t WHERE b.type_id=t.id AND userId=?;
-- 根据id查找数据,主键是唯一的
SELECT *FROM buyTable WHERE id=? 
-- 修改
UPDATE buyTable SET type_id=?,brandName=?,priceRequest=?,requireDescribe=?,upTime=? WHERE id=? 
-- 删除
DELETE FROM buyTable WHERE id=?
-- 修改状态
UPDATE buyTable SET buyStatus=1 WHERE id= 17;
-- 显示未解决的情况列表
SELECT *FROM buyTable WHERE buyStatus=0;
SELECT b.id,b.userId,b.type_id,t.typeName,b.brandName,b.priceRequest,b.requireDescribe,b.upTime FROM buyTable b,typeTable t WHERE b.type_id=t.id AND b.userId=2 AND buyStatus=1;
SELECT b.id,b.userId,u.mail,b.type_id,t.typeName,b.brandName,b.priceRequest,b.requireDescribe,b.upTime FROM buyTable b,typeTable t,userTable u WHERE b.type_id=t.id AND b.userId=u.id AND b.id=?
-- 查找到主类别
SELECT *FROM mainType;
-- 根据主类别查询到次类别
SELECT *FROM typeTable WHERE mainType_id=1;
-- 根据类别查找出售数据,还未出售的数据
SELECT *FROM sellTable WHERE type_id=6; 
--  根据类别id查找想购数据
SELECT *FROM buyTable WHERE type_id=?;

-- 购买信息表， 用户id、出售信息id、购买数量、购买备注，购买时间
CREATE TABLE userBuy(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userId INT,-- 用户id，外键
	sellId INT,-- 出售信息id，外键
	buyCount INT,-- 购买数量
	buyRemark VARCHAR(50)

)
ALTER TABLE userBuy ADD COLUMN buyTime DATETIME;-- 添加购买时间
SELECT *FROM userBuy;
INSERT INTO userBuy(userId,sellId,buyCount,buyRemark) VALUES();-- 插入数据
SELECT *FROM userBuy WHERE userId=?;-- 根据用户id查询购买信息
SELECT ub.id,ub.userId,ub.sellId,ub.buyCount,ub.buyRemark,st.sellName,st.sellPrice,ub.buyTime FROM userBuy ub,sellTable st WHERE ub.sellId=st.id AND userId=?;


-- 收藏表，用户id，出售id
CREATE TABLE collectTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userId INT,-- 用户id，外键
	sellId INT
)
SELECT *FROM collectTable;

INSERT INTO collectTable(userId,sellId) VALUES(?,?);
SELECT ct.id,ct.sellId,st.sellName,st.brandName,st.sellPrice,st.goodsDescribe,st.upTime FROM collectTable ct,sellTable st WHERE ct.sellId=st.id AND ct.userId=2;
DELETE FROM collectTable WHERE id=?;-- 删除



-- 收货地址表
CREATE TABLE adress(
	id INT PRIMARY KEY AUTO_INCREMENT,
	adressMessage VARCHAR(30) -- 收货地址
)
SELECT *FROM adress;

-- 接收信息	发送人	时间	内容
CREATE TABLE mailTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	sendUser VARCHAR(20),
	textMessage VARCHAR(100)
)
SELECT *FROM mailTable;
