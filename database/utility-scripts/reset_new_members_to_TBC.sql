update members set status = 'TBC'
where status not in ('Closed', 'Cancelled', 'Not Required', 'TBC')
and id not in (select member_id from payments where member_id is not null);
