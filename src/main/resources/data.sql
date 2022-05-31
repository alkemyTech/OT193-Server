insert into role (name,description,create_date)
    values ('admin','Usuario con permisos totales',NOW()),
           ('regular','Usuario con permisos parciales',NOW()),
           ('gerente','Usuario con permisos sobre una gerencia',NOW());

insert into categories (name, description,image,created_at,deleted)
    values ('categoria1','descCategoria1','url',NOW(),false),
           ('categoria2','descCategoria2','url',NOW(),false),
           ('categoria3','descCategoria3','url',NOW(),false),
           ('categoria4','descCategoria4','url',NOW(),false);

insert into user (first_name,last_name,email,pass,photo,role_id,created_at,deleted)
    values ('ayrton','paez','ayrton2190@gmail.com','$2a$10$KjJNEJ/QHvFxF/rq9XcM6uAnrLYgN6Ht47Ly7EXoiIzKUSkdXAioG','url',1,NOW(),false),
           ('cristian','rikkonen','rkk2000@gmail.com','$2a$10$u0fmpXMvLJFqT.JxAKMGXeSs9/WYxlBXSl99qNFCMLnh2CokQe.Hy','url',2,NOW(),false),
           ('brenda','gomez','bgomez@gmail.com','$2a$10$1BlcMrX5JllUcUGRPhNBQO9zqpRUYmPJJdWcktZDtUnMTgQ6A068e','url',2,NOW(),false),
           ('milton','aguero','aguuerooo@gmail.com','$2a$10$RNKMDEYHtqhm.C/Kz6LWe.xCjdDfdHQhig/93MBvVeMV38Z4Ja4fG','url',1,NOW(),false),
           ('matilde','albornoz','matalbo@gmail.com','$2a$10$.nCVZWK40Z94nCs1xgdzquMZmlUV5.sXKAwV8tV9cE4.k4LGx.koO','url',2,NOW(),false);

insert into members (name,facebook_url,instagram_url,linkedin_url,image,description,created_date,deleted)
    values ('miembro1','faceMiembro1','igMiembro1','lkMiembro1','url','descMiembro1',NOW(),false),
           ('miembro2','faceMiembro2','igMiembro2','lkMiembro2','url','descMiembro2',NOW(),false),
           ('miembro3','faceMiembro3','igMiembro3','lkMiembro3','url','descMiembro3',NOW(),false),
           ('miembro4','faceMiembro4','igMiembro4','lkMiembro4','url','descMiembro4',NOW(),false);

insert into organizations (name,image,address,phone,email,welcome_text,about_us_text,create_date,deleted)
    values ('organizacion1','url','dirOrg1','3421','org1@gmail.com','Bienvenidos a Organizacion 1','asd',NOW(),false),
           ('organizacion2','url','dirOrg2','3426','org2@gmail.com','Bienvenidos a Organizacion 2','asd',NOW(),false),
           ('organizacion3','url','dirOrg3','3428','org3@gmail.com','Bienvenidos a Organizacion 3','asd',NOW(),false),
           ('organizacion4','url','dirOrg4','3428','org4@gmail.com','Bienvenidos a Organizacion 4','asd',NOW(),false);

insert into news (name,content,image,category_id,create_date,deleted)
    values ('noticia1','contenidoNoticia1','url',1,NOW(),false),
           ('noticia2','contenidoNoticia2','url',2,NOW(),false),
           ('noticia3','contenidoNoticia3','url',2,NOW(),false),
           ('noticia4','contenidoNoticia4','url',1,NOW(),false);

insert into testimonials (name,image,content,create_at,deleted)
    values ('testimonio1','url','contenidoTestimonio1',NOW(),false),
           ('testimonio2','url','contenidoTestimonio2',NOW(),false),
           ('testimonio3','url','contenidoTestimonio3',NOW(),false),
           ('testimonio4','url','contenidoTestimonio4',NOW(),false);

insert into activity (name_activity,content_activity,image_activity,create_at,deleted)
    values ('actividad1','contenidoActividad1','url',NOW(),false),
           ('actividad2','contenidoActividad2','url',NOW(),false),
           ('actividad3','contenidoActividad3','url',NOW(),false),
           ('actividad4','contenidoActividad4','url',NOW(),false);