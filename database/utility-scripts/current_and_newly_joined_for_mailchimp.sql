select * from (
select m.email, m.FORENAME, m.SURNAME, m.MEMBERSHIP_NUMBER
from members m
where m.IS_ELIGIBLE_FOR_DRAW = true
and email is not null
and email like '%@%'
and m.deceased = false
and email_opt_out = false
union
select m.email, m.FORENAME, m.SURNAME, m.MEMBERSHIP_NUMBER
from members m
where JOIN_DATE > (DATE_SUB(curdate(), INTERVAL 30 DAY))
) s
order by 4