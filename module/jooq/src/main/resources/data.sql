-- schema
set schema astronomy;
-- planet system
insert into planet_system (id, name, age, stars, location, note)
    values (nextval('planet_system_seq'), 'Solar System', 4568000000, 1, 'Milky Way Galaxy', 'Distance to Galactic Center: 27000 +/- 1000 ly');
-- planet
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Mercury', 'TERRESTRIAL', '☿', '1st');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Venus', 'TERRESTRIAL', '♀', '2nd');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Earth', 'TERRESTRIAL', '⊕', '3rd');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Mars', 'TERRESTRIAL', '♂', '4th');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Jupiter', 'GIANT', '♃', '5th');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Saturn', 'GIANT', '♄', '6th');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Uranus', 'GIANT', '♅', '7th');
insert into planet (id, system_id, name, type, sign, note)
    values (nextval('planet_seq'), 1001, 'Neptune', 'GIANT', '♆', '8th');
-- planet attribute
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1001, 0.383, 0.06, 0.39, 0.24, 3.38, 0.206, 58.65, 0, 0);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1002, 0.949, 0.81, 0.72, 0.62, 3.86, 0.007, 243.02, 0, 0);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1003, 1.000, 1.00, 1.00, 1.00, 7.25, 0.017, 1.00, 1, 0);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1004, 0.532, 0.11, 1.52, 1.88, 5.65, 0.093, 1.03, 2, 0);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1005, 11.209, 317.83, 5.20, 11.86, 6.09, 0.048, 0.41, 79, 1);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1006, 9.449, 95.16, 9.54, 29.45, 5.51, 0.054, 0.44, 82, 1);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1007, 4.007, 14.54, 19.19, 84.02, 6.48, 0.047, 0.72, 27, 1);
insert into planet_attribute (id, planet_id, diameter, mass, axis, period, inclination, eccentricity, rotation, moons, rings)
    values (nextval('planet_attribute_seq'), 1008, 3.883, 17.15, 30.07, 164.79, 6.43, 0.009, 0.67, 14, 1);
-- planet moon
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1003, 'Moon', 384400, 1737.4, 'Earth I');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1004, 'Phobos', 9377.2, 11.2667, 'Mars I');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1004, 'Deimos', 23458, 6.2, 'Mars II');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Io', 421700, 1821.6, 'Jupiter I');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Europa', 670900, 1560.8, 'Jupiter II');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Ganymede', 1070400, 2634.1, 'Jupiter III');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Callisto', 1882700, 2410.3, 'Jupiter IV');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Amalthea', 181365.84, 83.5, 'Jupiter V');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Himalia', 11388690, 69.8, 'Jupiter VI');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Elara', 11703130, 40.9, 'Jupiter VII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1005, 'Pasiphae', 23208940, 28.9, 'Jupiter VIII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Mimas', 185539, 198.2, 'Saturn I');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Enceladus', 237948, 252.1, 'Saturn II');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Tethys', 294619, 531.1, 'Saturn III');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Dione', 377396, 561.4, 'Saturn IV');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Rhea', 527108, 763.8, 'Saturn V');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Titan', 1221870, 2574.73, 'Saturn VI');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Hyperion', 1481009, 135, 'Saturn VII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1006, 'Iapetus', 3560820, 734.5, 'Saturn VIII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Ariel', 190900, 578.9, 'Uranus I');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Umbriel', 266000, 584.7, 'Uranus II');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Titania', 435910, 788.4, 'Uranus III');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Oberon', 583520, 761.4, 'Uranus IV');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Miranda', 129390, 235.8, 'Uranus V');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Cordelia', 49751.7, 20.1, 'Uranus VI');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Ophelia', 53763.39, 21.4, 'Uranus VII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1007, 'Bianca', 59165.55, 25.7, 'Uranus VIII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Triton', 354759, 1353.4, 'Neptune I');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Nereid', 5513940, 357, 'Neptune II');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Naiad', 48224.41, 30.2, 'Neptune III');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Thalassa', 50074.44, 40.7, 'Neptune IV');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Despina', 52525.95, 78, 'Neptune V');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Galatea', 61952.57, 87.4, 'Neptune VI');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Larissa', 73548.26, 97, 'Neptune VII');
insert into planet_moon (id, planet_id, name, distance, radius, note)
    values (nextval('planet_moon_seq'), 1008, 'Proteus', 117647, 210, 'Neptune VIII');
-- planet atmosphere
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'H2');
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'He');
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'N2');
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'O2');
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'CO2');
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'CH4');
insert into planet_atmosphere (id, name)
    values (nextval('planet_atmosphere_seq'), 'Ar');
-- planet atmosphere map
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1002, 1005);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1002, 1003);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1003, 1003);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1003, 1004);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1003, 1006);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1004, 1005);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1004, 1003);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1004, 1006);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1005, 1001);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1005, 1002);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1006, 1001);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1006, 1002);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1007, 1001);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1007, 1002);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1007, 1006);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1008, 1001);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1008, 1002);
insert into planet_atmosphere_map (planet_id, atmosphere_id) values (1008, 1006);
