-- path
show search_path;

set search_path to astronomy,public;

-- system
select s.*
from   pg_stats s;

select n.*
from   pg_catalog.pg_namespace n
order by n.nspname;

SELECT s.schema_name,
       s.schema_owner
FROM   information_schema.schemata s
where  s.catalog_name = 'jooq';

select t.*
from   pg_tables t
where  t.tableowner = 'jooq';

-- schema
create schema if not exists astronomy authorization jooq;

drop schema astronomy cascade;

-- type
select unnest(enum_range(null::planet_type))::text as "planet_type";

select *
from   pg_enum e,
       pg_type t
where  t.oid = e.enumtypid
and    t.typname = 'planet_type';

-- table
select * from planet_system;
select * from planet;
select * from planet_attribute;
select * from planet_moon;
select * from planet_atmosphere;
select * from planet_atmosphere_map;

-- apply
commit;
