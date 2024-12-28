INSERT INTO BUILDINGS (NAME, ADDRESS)
VALUES
    ('MAIN OFFICE', '123 MAIN ST'),
    ('RESEARCH CENTER', '456 SCIENCE AVE'),
    ('LIBRARY', '789 BOOK RD');

INSERT INTO ROOMS (ROOM_NUMBER, ROOM_CAPACITY, ROOM_NAME, BUILDING_ID)
VALUES
    ('101', 50, 'Conference Room', 1),   -- Room 101 belongs to MAIN OFFICE
    ('102', 20, 'Office Room', 1),       -- Room 102 belongs to MAIN OFFICE
    ('201', 30, 'Research Lab', 2),      -- Room 201 belongs to RESEARCH CENTER
    ('202', 10, 'Meeting Room', 2),      -- Room 202 belongs to RESEARCH CENTER
    ('301', 100, 'Auditorium', 3),       -- Room 301 belongs to LIBRARY
    ('302', 60, 'Study Room', 3);        -- Room 302 belongs to LIBRARY

INSERT INTO EVENTS (EVENT_NAME, START_DATE_TIME, END_DATE_TIME)
VALUES
    ('ANNUAL MEETING', '2024-02-15 09:00:00', '2024-02-15 17:00:00'),
    ('TECH WORKSHOP', '2024-03-10 10:00:00', '2024-03-10 12:00:00'),
    ('BOOK FAIR', '2024-04-20 08:00:00', '2024-04-20 15:00:00');

INSERT INTO EVENT_ROOMS (EVENT_ID, ROOM_ID)
VALUES
    (1, 1), -- ANNUAL MEETING @ Conference Room (ROOM_ID 1)
    (2, 3), -- TECH WORKSHOP @ Research Lab (ROOM_ID 3)
    (3, 5); -- BOOK FAIR @ Auditorium (ROOM_ID 5)