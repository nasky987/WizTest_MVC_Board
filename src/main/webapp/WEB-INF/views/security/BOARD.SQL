CREATE TABLE board (
	mId NUMBER(4) PRIMARY KEY,
	mWriter VARCHAR2(100),
	mContent VARCHAR2(300)
);

CREATE SEQUENCE board_seq;

COMMIT;
