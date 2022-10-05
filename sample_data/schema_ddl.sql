DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    current_map integer NOT NULL,
    map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_name text NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL ,
    player_name text primary key ,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    inventory text[]
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_name FOREIGN KEY (player_name) REFERENCES public.player(player_name);