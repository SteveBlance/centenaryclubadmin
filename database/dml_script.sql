insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1234, 'Legacy', 'Open', 'Bobby', 'Smith', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'bsmith@email.com',
        '01383 335577', null, STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null, null);

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1234', false, surname, id from members where membership_number = 1234;

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1234 NEW', true, surname, id from members where membership_number = 1234;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, leave_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1235, 'Legacy', 'Cancelled', 'Jim', 'Smith', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2014', '%d/%m/%Y'), 'jsmith@email.com',
        '01383 776622', '077666666', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), NOW(), 'Membership card lost');

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1235', true, surname, id from members where membership_number = 1235;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1244, 'Legacy', 'Open', 'Dave', 'Spence', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null,
        '00441383 887766', '077666666', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), NOW(), null);

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1244', true, surname, id from members where membership_number = 1244;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1245, 'Lifeline', 'Open', 'Jane', 'Wilson', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'jwilson@email.com',
        null, null, STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), NOW(), 'Membership card lost');

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1245', true, surname, id from members where membership_number = 1245;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1566, 'Lifeline', 'Open', 'Sandy', 'Jamieson', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'sj@email.com',
        '01383 445566', '077666666', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), NOW(), 'Was member 1122');

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1566', true, surname, id from members where membership_number = 1566;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1567, 'Lifeline', 'Closed', 'Arthur', 'Cole', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'arty@email.com',
        '+441383 112233', null, STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), NOW(), null);

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1567', true, surname, id from members where membership_number = 1567;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1568, 'Lifeline', 'Open', 'Mike', 'Reid', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'mreid@email.com',
        '01383 445566', '077666666', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null, 'Membership card lost. Found under a tree outside Dens Park. Returned via carrier pigeon. Card still in good condition but the left upper edge is slightly curled up. Smells slightly damp also.');

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1568', true, surname, id from members where membership_number = 1568;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1569, 'Lifeline', 'Open', 'Liz', 'Marshall', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'LizzyM@email.com',
        '01383 231423', '077666666', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null, 'Membership card lost');

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1569', true, surname, id from members where membership_number = 1569;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1570, 'Lifeline', 'Open', 'Bob', 'Marshall', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'Bmarsh@email.com',
        '01383 446688', '0712226668', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null, 'Was member 1111');

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1570', true, surname, id from members where membership_number = 1570;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1578, 'Lifeline', 'Open', 'Davie', 'Marshall', 'Annual', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'dm@email.com',
        '+1 555 443322', '0722334454', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null, null);

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT 1578', true, surname, id from members where membership_number = 1578;

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments)
values (1580, 'Premium Legacy', 'Open', 'John', 'Brown', 'Monthly', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), 'broony@email.com',
        '01383 111111', '077111111', STR_TO_DATE('14/12/2013', '%d/%m/%Y'), STR_TO_DATE('14/12/2013', '%d/%m/%Y'), null, 'Membership card lost');

insert into members (membership_number, membership_type, status, forename, surname, payer_type, join_date, email,
                     landline_number, mobile_number, card_request_date, card_issued_date, welcome_letter_issued_date, comments, fanbase_id)
values (1681, 'Lifeline', 'Open', 'Steve', 'Strange', 'Monthly', STR_TO_DATE('15/12/2023', '%d/%m/%Y'), 'steve.strange@email.com',
        '01383 722322', '07766554433', null, null, null, null, 1335);

insert into payment_references (reference, is_active, name, member_id)
select 'FPS CREDIT CC LIFELINE', true, 'JOHN BROWN', id from members where membership_number = 1580;

insert into payments (payment_date, payment_amount, credit_reference, name, credited_account, member_id)
values (STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 20.00, 'FPS CREDIT 1161', 'J NOMAN', '91231800145173BB', null);

insert into payments (payment_date, payment_amount, credit_reference, name, credited_account, member_id)
values (STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 20.00, 'FPS CREDIT 1578', '', '91231800145173BB', null);

insert into payments (payment_date, payment_amount, credit_reference, name, credited_account, member_id)
values (STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 20.23, 'FPS CREDIT 1570', 'MARSHALL', '91231800145173BB', null);

insert into payments (payment_date, payment_amount, credit_reference, name, credited_account, member_id)
values (STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 20.00, 'FPS CREDIT 1568', 'REID', '91231800145173BB', null);

insert into payments (payment_date, payment_amount, credit_reference, name, credited_account, member_id)
values (STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 20.00, 'FPS CREDIT 9988', 'P NORSEMAN', '91231800145173BB', null);

insert into payments (payment_date, payment_amount, credit_reference, name, credited_account, member_id)
values (STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 20.00, 'FPS CREDIT 8800', 'J NORMAN', '91231800145173BB', null);

insert into lottery_draws (draw_date, name, lottery_date, draw_master)
values (STR_TO_DATE('01/05/2017', '%d/%m/%Y'), 'MAYDAY MAYHEM', STR_TO_DATE('14/05/2017', '%d/%m/%Y'), 'Ross');

insert into prizes(prize, lottery_draw_id, winner_id)
select 'Ford Ka', LD.ID, M.ID from lottery_draws LD, members M WHERE LD.NAME = 'MAYDAY MAYHEM' AND M.MEMBERSHIP_NUMBER = 1578;

insert into prizes(prize, lottery_draw_id, winner_id)
select '£250', LD.ID, M.ID from lottery_draws LD, members M WHERE LD.NAME = 'MAYDAY MAYHEM' AND M.MEMBERSHIP_NUMBER = 1569;

insert into prizes(prize, lottery_draw_id, winner_id)
select '£100', LD.ID, M.ID from lottery_draws LD, members M WHERE LD.NAME = 'MAYDAY MAYHEM' AND M.MEMBERSHIP_NUMBER = 1244;

insert into addresses (address_line_1, town, region, postcode, country, is_active, member_id)
select CONCAT(ID, ' High Street'),'Dunfermline','Fife', 'KY12 9YY', 'Scotland', true, ID
from members;

insert into security_subjects (forename, surname, username, password)
values ('Ross', 'Lindsay', 'Ross', '$2a$10$A8hKMalj4ioo1UA64QDA2.x3wC/21zX5QUagcvJxbRBj5H7bq58Mu');

insert into security_subjects (forename, surname, username, password)
values ('Steve', 'Blance', 'Steve', '$2a$10$AYZtnmn.elowcWkiRyD8y.atpi4eFj8/sNV3.QFcs5ybeVIccr5AG');

insert into notifications (event_type, event_date, description)
values ('Twitter', STR_TO_DATE('06/06/2017', '%d/%m/%Y'), '10 new followers');

INSERT INTO notifications(EVENT_TYPE, EVENT_DATE, DESCRIPTION)
values ('Announcement', STR_TO_DATE('06/06/2017', '%d/%m/%Y'), 'New Lifeline System Now Live!');

insert into notifications (event_type, event_date, description)
values ('Announcement', STR_TO_DATE('03/05/2017', '%d/%m/%Y'), 'Lifeline Launched');

insert into configuration(name, date_value)
values ('LAST_ELIGIBILITY_REFRESH_DATE', NOW());

insert into configuration(name, boolean_value)
values ('ELIGIBILITY_REFRESH_REQUIRED', true);
