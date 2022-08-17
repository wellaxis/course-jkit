-- type
select e.enumlabel as planet_type
from   pg_enum e,
       pg_type t
where  t.oid = e.enumtypid
and    t.typname = 'planet_type';
