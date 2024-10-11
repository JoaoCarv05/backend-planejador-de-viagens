CREATE TABLE owner_confirmation_email(
id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
email_from VARCHAR(255) NOT NULL,
email_to VARCHAR(255) NOT NULL,
subject VARCHAR(255) NOT NULL,
text TEXT NOT NULL,
date_email TIMESTAMP NOT NULL,
);