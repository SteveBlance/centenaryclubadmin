# noinspection SqlNoDataSourceInspectionForFile

drop table members;

create table members (
    id                         int         not null auto_increment primary key,
    membership_number          int         not null,
    membership_type            varchar(50) not null,
    status                     varchar(50) not null default 'Open',
    forename                   varchar(50) not null,
    surname                    varchar(50) not null,
    payer_type                 varchar(20) not null default 'Monthly',
    join_date                  date,
    leave_date                 date,
    email                      varchar(255),
    landline_number            varchar(50),
    mobile_number              varchar(50),
    card_request_date          date,
    card_issued_date           date,
    welcome_letter_issued_date date,
    comments                   varchar(2000),
    is_eligible_for_draw       boolean not null default false,
    email_opt_out              boolean not null default false,
    deceased                   boolean not null default false,
    fanbase_id                 int unique
);,

drop table addresses;

create table addresses (
    id             int          not null auto_increment primary key,
    address_line_1 varchar(255) not null,
    address_line_2 varchar(255),
    address_line_3 varchar(255),
    town           varchar(255) not null,
    region         varchar(50)  not null,
    postcode       varchar(10),
    country        varchar(50),
    is_active      boolean,
    member_id      int          not null references members(id)
);

drop table payments;

create table payments (
    id                 int           not null auto_increment primary key,
    payment_date       date          not null,
    payment_amount     decimal(10,2) not null,
    credit_reference   varchar(200),
    name               varchar(200),
    credited_account   varchar(50),
    member_id          int references members(id),
    is_lottery_payment boolean not null default true,
    comments           varchar(2000)
);

drop table payment_references;

create table payment_references (
    id        int          not null auto_increment primary key,
    reference varchar(255) not null,
    is_active boolean      not null,
    name      varchar(255),
    member_id int          not null references members(id)
);

drop table lottery_draws;

create table lottery_draws (
    id           int  not null auto_increment primary key,
    draw_date    date not null,
    name         varchar(255),
    lottery_date date,
    draw_master  varchar(255)
);

drop table prizes;

create table prizes (
    id                   int     not null auto_increment primary key,
    prize                varchar(1000),
    lottery_draw_id      int     not null references lottery_draws(id),
    winner_id            int references member(id)
);

drop table notifications;

create table notifications (
  id          int           not null auto_increment primary key,
  event_type  varchar(50)   not null,
  event_date  date          not null,
  description varchar(1000) not null
);

drop table security_subjects;

create table security_subjects (
  id                     int          not null auto_increment primary key,
  forename               varchar(100) not null,
  surname                varchar(100) not null,
  username               varchar(100) not null,
  password               varchar(100) not null,
  failed_login_attempts  int          not null default 0,
  account_locked         boolean      not null default false,
  password_to_be_changed boolean      not null default false,
  unique key `username_unique` (`username`)
);

drop table event_log;

create table event_log (
  id         int          not null  auto_increment primary key,
  message    varchar(250) not null,
  event_date timestamp    not null default now()
);

drop table configuration;

create table configuration (
  id            int          not null  auto_increment primary key,
  name          varchar(250) not null,
  string_value  varchar(250),
  boolean_value boolean,
  date_value    timestamp
);

drop table reports;

create table reports (
  id          int          not null auto_increment primary key,
  name        varchar(100) not null,
  report_date date         not null,
  int_value   int          not null
);

create view ineligible_for_draw_vw as
select m.id member_id, m.membership_number, m.forename, m.surname, m.email, m.landline_number, m.mobile_number, m.payer_type, m.join_date, p.payment_amount, max(p.payment_date) last_payment
from members m, payments p
where status = 'Open'
and is_eligible_for_draw = false
and p.member_id = m.id
group by m.id, m.membership_number, m.forename, m.surname, m.email, m.landline_number, m.mobile_number, m.payer_type, m.join_date, p.payment_amount
order by max(p.payment_date) desc;

create index member_address_idx on addresses (member_id);

create index address_is_active_idx on addresses(is_active);

create index member_prizes_idx on prizes (winner_id);

create index prizes_draw_id_idx on prizes (lottery_draw_id);

create index member_references_idx on payment_references (member_id);

create index payment_references_active_idx on payment_references (is_active);

create index member_payments_idx on payments (member_id);

create unique index member_membership_number on members (membership_number);

create index payment_date_idx on payments (payment_date);

create index member_surname_idx on members (surname);

create index member_forename_idx on members (forename);

create index member_type_idx on members (membership_type);

create index member_status_idx on members (status);

create index member_is_eligible_idx  on members (is_eligible_for_draw);

create index security_subject_name_idx on security_subjects (username);

create index payment_is_lottery_idx on payments (is_lottery_payment);

create index reports_report_date_idx on reports (report_date);

create index configuraton_name_idx on configuration(name);

create index lottery_draws_date_idx on lottery_draws(lottery_date);