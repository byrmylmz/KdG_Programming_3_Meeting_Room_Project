-- Insert a new building (note: id is omitted)
INSERT INTO BUILDINGS (name, address, created_at, updated_at) VALUES
    ('Building 1', '123 Main St', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert a new room (omit id since it's auto-generated)
INSERT INTO ROOMS (name, number, capacity, building_id, created_at, updated_at) VALUES
    ('Room A', '101', 50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert a new event (omit id and use DATEADD for compatibility with H2)
INSERT INTO EVENTS (name, start_date_time, end_date_time, created_at, updated_at) VALUES
    ('Event 1', CURRENT_TIMESTAMP, DATEADD(HOUR, 2, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert into the events_rooms table (ensure event_id and room_id are valid references)
INSERT INTO EVENTS_ROOMS (event_id, room_id) VALUES
    (1, 1);