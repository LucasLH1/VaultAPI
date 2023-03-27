
--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'VAULTAPI_MANAGEMENT_USER';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('VAULTAPI_MANAGEMENT_USER','vaultapi.adminFeature.ManageUser.name',1,'jsp/admin/plugins/vaultapi/ManageUsers.jsp','vaultapi.adminFeature.ManageUser.description',0,'vaultapi',NULL,NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'VAULTAPI_MANAGEMENT_USER';
INSERT INTO core_user_right (id_right,id_user) VALUES ('VAULTAPI_MANAGEMENT_USER',1);

