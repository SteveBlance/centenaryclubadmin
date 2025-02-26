SELECT concat(date_format(now(), '%Y%m%d'),', , 82621900174982CA, , CR, AAA, Transfer, 20,"FPS, ', forename, ' ', surname, ', ', membership_number, '","', membership_number,'", "00000000000000", GBP')
FROM cclifeline.MEMBERS
where status = 'Open'
and membership_type = 'Lifeline';