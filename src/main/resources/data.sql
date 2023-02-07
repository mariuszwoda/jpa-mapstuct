INSERT INTO CLUB (id, name, CREATE_AT, LAST_MODIFIED_AT, VERSION) VALUES (1, 'barca', '2023-02-07 00:00:00.000000', '2023-02-07 00:00:00.000001', 0);
-- INSERT INTO CLUB (id, name) VALUES (2, 'psg');
-- INSERT INTO CLUB (id, name) VALUES (1, 'barca');
-- INSERT INTO CLUB (id, name) VALUES (2, 'psg');
--
INSERT INTO MEMBER (id, name, club_id) VALUES (1, 'messi', 1);
-- INSERT INTO MEMBER (id, name, club_id) VALUES (2, 'neymar', 2);
--
INSERT INTO EVENT (id, name, event_type) VALUES (1, 'morning activity', 'TRAINING');
INSERT INTO EVENT (id, name, event_type) VALUES (2, 'match with real', 'MATCH');

INSERT INTO MEMBER_EVENTS (member_id, event_id) VALUES (1, 1);
INSERT INTO MEMBER_EVENTS (member_id, event_id) VALUES (1, 2);

-- INSERT INTO EVENT (id, name, event_type, member_id) VALUES (1, 'morning activity', 'TRAINING', 1);
-- INSERT INTO EVENT (id, name, member_id, event_type) VALUES (1, 'morning activity', 1, 'TRAINING');
--
-- INSERT INTO EVENT (id, name, member_id) VALUES (1, 'morning activity', 1);
-- INSERT INTO EVENT (id, name, member_id) VALUES (2, 'midday activity', 1);
-- INSERT INTO EVENT (id, name, member_id) VALUES (3, 'match with real', 1);
--
-- -- INSERT INTO EVENT (id, name, event_type) VALUES (1, 'morning activity', 'TRAINING');
-- -- INSERT INTO EVENT (id, name, event_type) VALUES (2, 'midday activity', 'TRAINING');
-- -- INSERT INTO EVENT (id, name, event_type) VALUES (3, 'match with real', 'MATCH');
--
--
