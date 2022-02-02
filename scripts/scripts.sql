INSERT INTO public.rol(
	id_rol, description, name, registration_date, status, update_date)
	VALUES (1, 'Paciente', 'Paciente', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.rol(
	id_rol, description, name, registration_date, status, update_date)
	VALUES (2, 'Medico', 'Medico', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.rol(
	id_rol, description, name, registration_date, status, update_date)
	VALUES (3, 'Administrador', 'Administrador', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.rol(
	id_rol, description, name, registration_date, status, update_date)
	VALUES (4, 'Tecnico1', 'Tecnico1', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.rol(
	id_rol, description, name, registration_date, status, update_date)
	VALUES (5, 'Tecnico2', 'Tecnico2', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.rol(
	id_rol, description, name, registration_date, status, update_date)
	VALUES (6, 'Auditor', 'Auditor', '2022-01-06T09:13:03.165257', true, null);
	
----------------------------------------------------------------------------------------------

INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (1, '', 'Especialidades', '2022-01-06T09:13:03.165257', true, null, 'specialties');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (2, '', 'Médicos', '2022-01-06T09:13:03.165257', true, null, '/doctors');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (3, '', 'Pacientes', '2022-01-06T09:13:03.165257', true, null, '/patients');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (4, '', 'Agendar Cita', '2022-01-06T09:13:03.165257', true, null, '/scheduleAppointment');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (5, '', 'Cancelar Cita', '2022-01-06T09:13:03.165257', true, null, '/appointments');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (6, '', 'Mis Recetas', '2022-01-06T09:13:03.165257', true, null, '/recipes');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (7, '', 'Usuarios', '2022-01-06T09:13:03.165257', true, null, '/users');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (8, '', 'Agenda', '2022-01-06T09:13:03.165257', true, null, '/schedule');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (9, '', 'Roles', '2022-01-06T09:13:03.165257', true, null, '/roles');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (10, '', 'Funcionalidades', '2022-01-06T09:13:03.165257', true, null, '/functionalities');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (11, '', 'Examenes', '2022-01-06T09:13:03.165257', true, null, '/exams');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (12, '', 'Medicamentos', '2022-01-06T09:13:03.165257', true, null, '/medicines');

----------------------------------------------------------------------------------------------
-- Administrador
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (1, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (2, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (3, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (4, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (5, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (6, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (7, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (8, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (9, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (10, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (11, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (12, 3);
-- Paciente
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (4, 1);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (5, 1);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (6, 1);
-- Médico
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (8, 2);


----------------------------------------------------------------------------------------------
INSERT INTO public.laboratory_exam(id_laboratory_exam, name, registration_date, status, update_date) VALUES (1, 'Colesterol', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.laboratory_exam(id_laboratory_exam, name, registration_date, status, update_date) VALUES (2, 'Hemograma completo', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.laboratory_exam(id_laboratory_exam, name, registration_date, status, update_date) VALUES (3, 'Urinálisis completo', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.laboratory_exam(id_laboratory_exam, name, registration_date, status, update_date) VALUES (4, 'VDRL en Suero [Serología]', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.laboratory_exam(id_laboratory_exam, name, registration_date, status, update_date) VALUES (5, 'Glucosa', '2022-01-06T09:13:03.165257', true, null);

----------------------------------------------------------------------------------------------
INSERT INTO public.medicine(id_medicine, name, registration_date, status, update_date) VALUES (1, 'Paracetamol', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.medicine(id_medicine, name, registration_date, status, update_date) VALUES (2, 'Ibuprofeno', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.medicine(id_medicine, name, registration_date, status, update_date) VALUES (3, 'Aspirina', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.medicine(id_medicine, name, registration_date, status, update_date) VALUES (4, 'Loratadina', '2022-01-06T09:13:03.165257', true, null);
INSERT INTO public.medicine(id_medicine, name, registration_date, status, update_date) VALUES (5, 'Buprex', '2022-01-06T09:13:03.165257', true, null);


--select doc.* from specialty sp
select doc.* from doctor doc
inner join specialty_doctor sd on sd.id_doctor = doc.id_doctor
inner join specialty sp on sp.id_specialty = sd.id_specialty
where sp.id_specialty = 12 and sp.status = true and doc.status = true

select * from schedule where id_doctor = 2 and status = true;

select ap.* from appointment ap
where id_doctor = 2
and time_stamp >= now()
and time_stamp <= '2022-01-17T21:11:18.464552'
and status = 0

select * from appointment where id_doctor = 2 and time_stamp >= now() and time_stamp <= '2022-01-17T21:11:18.464552' and status = 0

select * from appointment appointmen0_
inner join doctor doctor1_ on appointmen0_.id_doctor=doctor1_.id_doctor
left outer join specialty_doctor specialtie2_ on doctor1_.id_doctor=specialtie2_.id_doctor
left outer join specialty specialty3_ on specialtie2_.id_specialty=specialty3_.id_specialty
inner join patient patient4_ on appointmen0_.id_patient=patient4_.id_patient
inner join specialty specialty5_ on appointmen0_.id_specialty=specialty5_.id_specialty
where appointmen0_.id_appointment=2

select medicine0_.id_medicine as id_medic1_6_0_, medicine0_.name as name2_6_0_, medicine0_.registration_date as registra3_6_0_, medicine0_.status as status4_6_0_, medicine0_.update_date as update_d5_6_0_
from medicine medicine0_ where medicine0_.id_medicine=1



