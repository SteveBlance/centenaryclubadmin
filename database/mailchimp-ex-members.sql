select distinct email email_address, forename first_name, surname last_name, membership_number from (
select m.membership_number, m.forename, m.surname, m.membership_type, m.status, m.email, m.landline_number, m.mobile_number,
m.payer_type, m.join_date, p.payment_amount, max(p.payment_date) last_payment,
concat(ifnull(ad.address_line_1,''),',',ifnull(ad.address_line_2,''),',',ifnull(ad.address_line_3, ''),',',ifnull(ad.town,''),',',ifnull(ad.region,''),',',ifnull(ad.postcode, '')) address, m.comments
from members m
inner join payments p
on p.member_id = m.id
right outer join addresses ad
on ad.member_id = m.id
where is_eligible_for_draw = false
and m.email_opt_out = false
and m.deceased = false
and m.email like '%@%.%'
and m.status in ('Closed', 'Cancelled')
group by m.membership_number, m.forename, m.surname, m.membership_type, m.status, m.email, m.landline_number, m.mobile_number, m.payer_type, m.join_date, p.payment_amount,
ad.address_line_1, ad.address_line_2, ad.address_line_3, ad.town, ad.region, ad.region, ad.postcode
order by max(p.payment_date) desc
) a
where a.last_payment > '2021-09-01'