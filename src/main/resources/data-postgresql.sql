INSERT INTO BUILDINGS (name, address, created_at, updated_at) VALUES
    ('Building 1', '123 Main St', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ROOMS (name, number, capacity, building_id, created_at, updated_at) VALUES
    ('Room A', '101', 50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO EVENTS (name, start_date_time, end_date_time, created_at, updated_at) VALUES
    ('Event 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '2 HOURS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO EVENTS_ROOMS (event_id, room_id) VALUES
    (1, 1);
