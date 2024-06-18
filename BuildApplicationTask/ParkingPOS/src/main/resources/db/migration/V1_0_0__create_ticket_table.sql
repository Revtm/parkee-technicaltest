CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS ticket (
    id uuid DEFAULT uuid_generate_v4(),
    plate_number varchar(10),
    check_in_time timestamp not NULL,
    check_out_time timestamp,
    parking_status varchar(10) not NULL,
    total_price int,
    insert_time timestamp not NULL,
    update_time timestamp not NULL
)