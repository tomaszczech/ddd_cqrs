CREATE TABLE client (
  aggregate_id     VARCHAR2(255) NOT NULL,
  aggregate_status NUMBER(2,0),
  version          NUMBER(10,0),
  name             VARCHAR2(255),
  PRIMARY KEY (aggregate_id)
);