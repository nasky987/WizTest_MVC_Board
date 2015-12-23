create table card(
    consumerid varchar2(10),
    amount varchar2(10)
);

create table ticket(
    consumerid varchar2(10),
    countnum varchar2(10) check (countnum < 5)
);

commit;