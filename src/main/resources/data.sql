INSERT INTO users (id, email, full_name, created_at)
VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'john.doe@example.com', 'John Doe', NOW())
ON CONFLICT (email) DO NOTHING;

INSERT INTO users (id, email, full_name, created_at)
VALUES ('b2c3d4e5-f6a7-8901-bcde-f12345678901', 'jane.smith@example.com', 'Jane Smith', NOW())
ON CONFLICT (email) DO NOTHING;

INSERT INTO users (id, email, full_name, created_at)
VALUES ('c3d4e5f6-a7b8-9012-cdef-123456789012', 'bob.wilson@example.com', 'Bob Wilson', NOW())
ON CONFLICT (email) DO NOTHING;
