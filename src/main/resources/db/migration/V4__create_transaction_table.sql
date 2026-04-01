CREATE TABLE transaction(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    account_id UUID NOT NULL,
    category_id UUID NOT NULL,
    type VARCHAR(30) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    occurrence_date TIMESTAMP NOT NULL DEFAULT now(),
    description VARCHAR(100),

    CONSTRAINT fk_transaction_account
                        FOREIGN KEY (account_id) REFERENCES accounts(id),
    CONSTRAINT fk_category_account
                        FOREIGN KEY (category_id) REFERENCES category(id),
    created_at TIMESTAMP NOT NULL DEFAULT now()

);