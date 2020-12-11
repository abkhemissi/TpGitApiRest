CREATE TABLE repository (name varchar(50) PRIMARY KEY NOT NULL,
                        owner varchar(50),
                        issues int,
                        fork int,
                        update varchar(50));



INSERT INTO repository values ('TPSpringTestForkIssue', 'abkhemissi',0,0);

CREATE TABLE stats (id serial PRIMARY KEY,
                    entry_type varchar(50),
                    datetime varchar(50),
                    value_entry int,
                    name varchar(50));