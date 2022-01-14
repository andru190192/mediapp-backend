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
	VALUES (4, '', 'Citas', '2022-01-06T09:13:03.165257', true, null, '/appointment');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (5, '', 'Usuarios', '2022-01-06T09:13:03.165257', true, null, '/users');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (6, '', 'Roles', '2022-01-06T09:13:03.165257', true, null, '/roles');
INSERT INTO public.functionality(
	id_functionality, icon, name, registration_date, status, update_date, url)
	VALUES (7, '', 'Funcionalidades', '2022-01-06T09:13:03.165257', true, null, '/functionalities');
----------------------------------------------------------------------------------------------
-- Administrador
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (1, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (2, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (3, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (4, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (5, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (6, 3);
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (7, 3);
-- Paciente
INSERT INTO public.functionality_rol(id_functionality, id_rol) VALUES (4, 1);