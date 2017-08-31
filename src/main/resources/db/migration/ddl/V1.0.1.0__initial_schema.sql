
CREATE TABLE customer (
  aggregate_id     VARCHAR2(255) NOT NULL,
  aggregate_status NUMBER(2,0),
  version          NUMBER(10,0),
  activity         VARCHAR2(255),
  city             VARCHAR2(255),
  post_code        VARCHAR2(255),
  street           VARCHAR2(255),
  name             VARCHAR2(255),
  PRIMARY KEY (aggregate_id)
);

CREATE TABLE order_basket (
  aggregate_id      VARCHAR2(255) NOT NULL,
  aggregate_status  NUMBER(2,0),
  version           NUMBER(10,0),
  creation_date     TIMESTAMP,
  creator           VARCHAR2(20)  NOT NULL,
  modification_date TIMESTAMP,
  modifier          VARCHAR2(20)  NOT NULL,
  client_id         VARCHAR2(255) NOT NULL,
  status            VARCHAR2(255),
  PRIMARY KEY (aggregate_id)
);

CREATE TABLE order_item (
  entity_id            NUMBER(19,0),
  quantity             NUMBER(10,0) NOT NULL,
  product_aggregate_id VARCHAR2(255),
  order_basket VARCHAR (255),
  PRIMARY KEY (entity_id)
);

CREATE TABLE product (
  aggregate_id     VARCHAR2(255) NOT NULL,
  aggregate_status NUMBER(2,0),
  version          NUMBER(10,0),
  name             VARCHAR2(255),
  money            DECIMAL(19, 2),
  product_type     VARCHAR2(255),
  PRIMARY KEY (aggregate_id)
);

CREATE TABLE sequence (
  id NUMBER(19,0) ,
  name      VARCHAR2(255) NOT NULL,
  separator VARCHAR2(255),
  sequence  NUMBER(10,0) NOT NULL,
  PRIMARY KEY ("ID")
);
--
alter table sequence add constraint UKfjqyxmtlmj9s4oro29y6u43w6 unique (name);
alter table order_item add constraint FKlo7ulnkmrb2j4kcttid9xrr6o foreign key (product_aggregate_id) references product;
alter table order_item add constraint FKsrnghl54y476h7bidk7wkpgfo foreign key (order_basket) references order_basket;