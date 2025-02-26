select * from ineligible_for_draw_vw;

-- VIEW LAPSED 
select * from ineligible_for_draw_vw
where LAST_PAYMENT like '2024-12-%';


-- UPDATE LAPSED TO CLOSED

update members set STATUS = 'Closed', leave_date = now() where id in (
select member_id from ineligible_for_draw_vw
where LAST_PAYMENT like '2024-11-%');

-- Current Legacy memberships
select count(1) from members  where status = 'Open' and MEMBERSHIP_TYPE = 'Legacy'; 

-- Current Lifeline memberships
select count(1) from members  where status = 'Open' and MEMBERSHIP_TYPE = 'Lifeline';

-- Closed/Cancelled memberships
select count(1) from members  where status in ('Closed', 'Cancelled');


update members set status = 'TBC' where JOIN_DATE > DATE_SUB(NOW(), INTERVAL 30 DAY)
and not exists (select 1 from payments where MEMBER_ID = members.id);

-- Members ineligible for draw having not started payments
select count(1) from members where status = 'TBC';  

-- Members who have joined during the month (check emails - join date in DB is when I add the member
select * from members
where join_date like '2022-04%';

-- Didn't pay in month
select * from ineligible_for_draw_vw;

-- Closed in month
SELECT m.id member_id, m.MEMBERSHIP_NUMBER, m.FORENAME, m.SURNAME, m.email, m.LANDLINE_NUMBER, m.MOBILE_NUMBER, m.PAYER_TYPE, m.JOIN_DATE, p.PAYMENT_AMOUNT, MAX(p.PAYMENT_DATE) LAST_PAYMENT, m.STATUS
FROM members m, payments p
WHERE status in ('Closed', 'Cancelled')
AND IS_ELIGIBLE_FOR_DRAW = FALSE
AND p.member_id = m.id
GROUP BY m.id, m.MEMBERSHIP_NUMBER, m.FORENAME, m.SURNAME, m.email, m.LANDLINE_NUMBER, m.MOBILE_NUMBER, m.PAYER_TYPE, m.JOIN_DATE, p.PAYMENT_AMOUNT
ORDER BY MAX(p.PAYMENT_DATE) DESC;
