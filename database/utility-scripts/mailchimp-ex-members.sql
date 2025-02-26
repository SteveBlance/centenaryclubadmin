select email email_address, FORENAME first_name, SURNAME last_name from (
select m.MEMBERSHIP_NUMBER, m.FORENAME, m.SURNAME, m.MEMBERSHIP_TYPE, m.STATUS, m.email, m.LANDLINE_NUMBER, m.MOBILE_NUMBER, 
m.PAYER_TYPE, m.JOIN_DATE, p.PAYMENT_AMOUNT, max(p.PAYMENT_DATE) LAST_PAYMENT, 
concat(ifnull(ad.ADDRESS_LINE_1,''),',',ifnull(ad.ADDRESS_LINE_2,''),',',ifnull(ad.ADDRESS_LINE_3, ''),',',ifnull(ad.TOWN,''),',',ifnull(ad.REGION,''),',',ifnull(ad.POSTCODE, '')) ADDRESS, m.COMMENTS
from members m
inner join payments p
on p.MEMBER_ID = m.id
right outer join ADDRESSES ad
on ad.MEMBER_ID = m.id
where IS_ELIGIBLE_FOR_DRAW = false
and m.EMAIL_OPT_OUT = false
and m.DECEASED = false
and m.email like '%@%.%'
and m.status in ('Closed', 'Cancelled')
group by m.MEMBERSHIP_NUMBER, m.FORENAME, m.SURNAME, m.MEMBERSHIP_TYPE, m.STATUS, m.email, m.LANDLINE_NUMBER, m.MOBILE_NUMBER, m.PAYER_TYPE, m.JOIN_DATE, p.PAYMENT_AMOUNT,
ad.ADDRESS_LINE_1, ad.ADDRESS_LINE_2, ad.ADDRESS_LINE_3, ad.TOWN, ad.REGION, ad.REGION, ad.POSTCODE
order by max(p.PAYMENT_DATE) desc
) a
where a.last_payment > '2021-01-01'