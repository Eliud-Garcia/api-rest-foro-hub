
/*el titulo y el mensaje de un topico no se pueden repetir*/

ALTER TABLE topico ADD UNIQUE (titulo_topico);

ALTER TABLE topico ADD UNIQUE (mensaje_topico);
