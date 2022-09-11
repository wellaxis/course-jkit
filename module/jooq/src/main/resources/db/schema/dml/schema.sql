-- schema
select s.schema_name,
       s.schema_owner
from   information_schema.schemata s
where  s.catalog_name = 'jooq';
